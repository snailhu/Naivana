package com.nirvana.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.repository.store.usersys.StoreUserRepository;
import com.nirvana.security.service.StoreSecurityService;

@Service
@Transactional
public class StoreSecurityServiceImpl implements StoreSecurityService {

	@Resource
	private StoreUserRepository storeUserRepository;

	@Override
	public StoreUser findUserByUserLogin(String username) {
		return storeUserRepository.findByUserName(username);
	}

}
