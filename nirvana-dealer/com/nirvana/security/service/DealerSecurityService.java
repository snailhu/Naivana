package com.nirvana.security.service;

import com.nirvana.domain.dealer.usersys.DealerUser;

public interface DealerSecurityService {

	/**
	 * 通过用户登录名寻找用户。
	 * 
	 * */
	public DealerUser findUserByUserLogin(String username);

}
