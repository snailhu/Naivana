package com.nirvana.security.service;

import com.nirvana.domain.store.usersys.StoreUser;

public interface StoreSecurityService {

	/**
	 * ͨ���û���¼��Ѱ���û���
	 * 
	 * */
	public StoreUser findUserByUserLogin(String username);

}
