package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.exception.BindNumExitedException;
import com.nirvana.exception.BindNumUnexistException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.WrongCaptchaException;
import com.nirvana.repository.backend.EmployeeRepository;
import com.nirvana.repository.backend.usersys.BackEndUserRepository;
import com.nirvana.service.BackEndAccountService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.XCaptchaService;
import com.nirvana.service.XCaptchaService.UserType;

@Service
@Transactional
public class BackEndUserAccountServiceImpl implements BackEndAccountService {

	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private XCaptchaService xCaptchaService;
	@Resource
	private BackEndUserRepository backEndUserRepository;
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private CacheManager cacheManager;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void verifyPhoneNumber(String number) {
		BackEndUser user = backEndCurrentLoginService.getCurrentLoginUser();
		if (user.getPhone() != null) {
			throw new BindNumExitedException();
		}
		xCaptchaService.savePhone(user.getUsername(), number, UserType.USER_BACKEND);
		xCaptchaService.sendCaptcha(number);
	}

	@Override
	public void bindPhoneNumber(String captcha) {
		String username = backEndCurrentLoginService.getCurrentLoginUsername();
		BackEndUser user = backEndUserRepository.findByUsernameLocked(username);
		if (user.getPhone() != null) {
			throw new BindNumExitedException();
		}
		String phone = xCaptchaService.getPhone(user.getUsername(), UserType.USER_BACKEND);
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPhone(phone);
			backEndUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void getCaptcha() {
		BackEndUser user = backEndCurrentLoginService.getCurrentLoginUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		xCaptchaService.sendCaptcha(phone);
	}

	@Override
	public void unbindPhoneNumber(String captcha) {
		BackEndUser user = backEndCurrentLoginService.getCurrentLoginUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPhone(null);
			backEndUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	public void editUserRealName(String name, String captcha) {
		BackEndUser user = backEndCurrentLoginService.getCurrentLoginUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		if (xCaptchaService.validate(phone, captcha, true)) {
			Employee employee = user.getEmployee();
			employee.setName(name);
			employeeRepository.save(employee);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	public void editPassword(String password, String captcha) {
		BackEndUser user = backEndCurrentLoginService.getCurrentLoginUser();
		String phone = user.getPhone();
		if (phone == null) {
			throw new BindNumUnexistException();
		}
		if (xCaptchaService.validate(phone, captcha, true)) {
			user.setPassword(password);
			backEndUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void postCaptcha(String phone) {
		BackEndUser user = backEndUserRepository.findByBindNum(phone);
		if (user == null) {
			throw new RecordNotFoundException("此号码绑定的用户不存在。");
		}
		xCaptchaService.sendCaptcha(phone);
	}

	@Override
	public void resetPassword(String phone, String captcha, String password) {
		if (xCaptchaService.validate(phone, captcha, true)) {
			BackEndUser user = backEndUserRepository.findByBindNum(phone);
			if (user == null) {
				throw new RecordNotFoundException("此号码绑定的用户不存在。");
			}
			user.setPassword(password);
			backEndUserRepository.save(user);
		} else {
			throw new WrongCaptchaException();
		}
	}

	@Override
	public void resetPassword(String phone, String password) {
		BackEndUser user = backEndUserRepository.findByBindNum(phone);
		if (user == null) {
			throw new RecordNotFoundException("此号码绑定的用户不存在。");
		}
		user.setPassword(password);
		backEndUserRepository.save(user);

	}
}
