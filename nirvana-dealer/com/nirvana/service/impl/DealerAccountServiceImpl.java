package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.exception.BindNumExitedException;
import com.nirvana.exception.BindNumUnexistException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.WrongCaptchaException;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.usersys.DealerUserRepository;
import com.nirvana.service.DealerAccountService;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.service.XCaptchaService;
import com.nirvana.service.XCaptchaService.UserType;

@Service
@Transactional
public class DealerAccountServiceImpl implements DealerAccountService {

	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private DealerUserRepository dealerUserRepository;
	@Resource
	private XCaptchaService xCaptchaService;
	@Resource
	private DealerCurrentLoginService dealerCurrentLoginService;

	@Override
	public Dealer getDealer() {
		return dealerCurrentLoginService.getCurrentLoginDealer();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void verifyPhoneNumber(String number) {
		DealerUser user = dealerCurrentLoginService.getCurrentLoginDealerUser();
		if (user.getPhone() != null) {
			throw new BindNumExitedException();
		}
		xCaptchaService.savePhone(user.getUsername(), number, UserType.USER_DEALER);
		xCaptchaService.sendCaptcha(number);
	}

	@Override
	public void bindPhoneNumber(String captcha) {
		DealerUser user = dealerCurrentLoginService.getCurrentLoginDealerUser();
		if (user.getPhone() != null) {
			throw new BindNumExitedException();
		}
		String phone = xCaptchaService.getPhone(user.getUsername(), UserType.USER_DEALER);
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPhone(phone);
			dealerUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void getCaptcha() {
		DealerUser user = dealerCurrentLoginService.getCurrentLoginDealerUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		xCaptchaService.sendCaptcha(phone);
	}

	@Override
	public void unbindPhoneNumber(String captcha) {
		DealerUser user = dealerCurrentLoginService.getCurrentLoginDealerUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPhone(null);
			dealerUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	public void editPassword(String password, String captcha) {
		DealerUser user = dealerCurrentLoginService.getCurrentLoginDealerUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPassword(password);
			dealerUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void postCaptcha(String phone) {
		DealerUser user = dealerUserRepository.findByBindNum(phone);
		if (user == null) {
			throw new RecordNotFoundException("此号码绑定的用户不存在。");
		}
		xCaptchaService.sendCaptcha(phone);
	}

	@Override
	public void resetPassword(String phone, String captcha, String password) {
		if (xCaptchaService.validate(phone, captcha, true)) {
			DealerUser user = dealerUserRepository.findByBindNum(phone);
			if (user == null) {
				throw new RecordNotFoundException("此号码绑定的用户不存在。");
			}
			user.setPassword(password);
			dealerUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	public void bindChannelId(String channelId, int deviceType) {
		// TODO Auto-generated method stub
		Dealer dealer = getDealer();
		dealer.setChannelId(channelId);
		dealer.setDeviceType(deviceType);
		dealerRepository.saveAndFlush(dealer);

		// 绑定
	}

}
