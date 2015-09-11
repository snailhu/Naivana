package com.nirvana.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.exception.BindNumExitedException;
import com.nirvana.exception.BindNumUnexistException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.UserExistedException;
import com.nirvana.exception.WrongCaptchaException;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.repository.store.StoreStorefrontInfoRepository;
import com.nirvana.repository.store.usersys.StoreUserRepository;
import com.nirvana.service.StoreAccountService;
import com.nirvana.service.StoreCurrentAccountService;
import com.nirvana.service.XCaptchaService;
import com.nirvana.service.XCaptchaService.UserType;

@Service
@Transactional
public class StoreAccountServiceImpl implements StoreAccountService {

	@Resource
	private StoreCurrentAccountService storeCurrentAccountService;
	@Resource
	private XCaptchaService xCaptchaService;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private StoreUserRepository storeUserRepository;
	@Resource
	private StoreStorefrontInfoRepository storeStorefrontInfoRepository;
	@Resource
	private DealerRepository dealerRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public StoreUser getInfo() {
		return storeCurrentAccountService.getCurrentLoginUser();
	}

	@Override
	public void register(String username, String password) {
		StoreUser user = storeUserRepository.findByUserName(username);
		if (user != null) {
			throw new UserExistedException();
		}
		Store store = new Store();
		StoreStorefrontInfo info = new StoreStorefrontInfo();
		store.setStorefrontInfo(info);
		storeRepository.saveAndFlush(store);
		user = new StoreUser();
		user.setPassword(password);
		user.setCreateDate(new Date());
		user.setUsername(username);
		user.setStore(store);
		storeUserRepository.save(user);
	}

	@Override
	public void editPassword(String password, String newPassword) {
		Assert.hasLength(password, "参数不能为空。");
		Assert.hasLength(newPassword, "参数不能为空。");
		StoreUser user = storeCurrentAccountService.getCurrentLoginUser();
		if (user.getPassword().equals(password)) {
			user.setPassword(newPassword);
		} else {
			throw new IllegalArgumentException("密码错误。");
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void varifyPhoneNumber(String phoneNum) {
		Assert.hasLength(phoneNum, "参数不能为空。");
		StoreUser user = storeCurrentAccountService.getCurrentLoginUser();
		if (user.getPhone() != null) {
			throw new BindNumExitedException();
		}
		xCaptchaService.savePhone(user.getUsername(), phoneNum, UserType.USER_STORE);
		xCaptchaService.sendCaptcha(phoneNum);
	}

	@Override
	public void bindPhoneNumber(String captcha) {
		Assert.hasLength(captcha, "验证码不能为空。");
		StoreUser user = storeCurrentAccountService.getCurrentLoginUser();
		if (user.getPhone() != null) {
			throw new BindNumExitedException();
		}
		String phone = xCaptchaService.getPhone(user.getUsername(), UserType.USER_STORE);
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPhone(phone);
			storeUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void getCaptcha() {
		StoreUser user = storeCurrentAccountService.getCurrentLoginUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		xCaptchaService.sendCaptcha(phone);
	}

	@Override
	public void unbindPhoneNumber(String captcha) {
		StoreUser user = storeCurrentAccountService.getCurrentLoginUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPhone(null);
			storeUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void postCaptcha(String phone) {
		StoreUser user = storeUserRepository.findByBindNum(phone);
		if (user == null) {
			throw new RecordNotFoundException("此号码绑定的用户不存在。");
		}
		xCaptchaService.sendCaptcha(phone);
	}

	@Override
	public void resetPassword(String password, String captcha, String phoneNum) {
		if (xCaptchaService.validate(phoneNum, captcha, true)) {
			StoreUser user = storeUserRepository.findByBindNum(phoneNum);
			if (user == null) {
				throw new RecordNotFoundException("此号码绑定的用户不存在。");
			}
			user.setPassword(password);
			storeUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}

	}

}
