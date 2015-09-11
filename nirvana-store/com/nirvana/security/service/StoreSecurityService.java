package com.nirvana.security.service;

import com.nirvana.domain.store.usersys.StoreUser;

public interface StoreSecurityService {

	/**
	 * 通过用户登录名寻找用户。
	 * 
	 * */
	public StoreUser findUserByUserLogin(String username);

}
