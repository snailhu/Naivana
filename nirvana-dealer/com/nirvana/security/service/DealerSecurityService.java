package com.nirvana.security.service;

import com.nirvana.domain.dealer.usersys.DealerUser;

public interface DealerSecurityService {

	/**
	 * ͨ���û���¼��Ѱ���û���
	 * 
	 * */
	public DealerUser findUserByUserLogin(String username);

}
