package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.repository.backend.usersys.BackEndUserRepository;
import com.nirvana.repository.dealer.usersys.DealerUserRepository;
import com.nirvana.repository.store.usersys.StoreUserRepository;
import com.nirvana.service.DuplicateCheckService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DuplicateCheckServiceImpl implements DuplicateCheckService {

	@Resource
	private BackEndUserRepository backEndUserRepository;
	@Resource
	private DealerUserRepository dealerUserRepository;
	@Resource
	private StoreUserRepository storeUserRepository;

	@Override
	public boolean backEndUserNameExist(String username) {
		BackEndUser backEndUser = backEndUserRepository.findByUsername(username);
		if (backEndUser != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean backEndUserPhoneExist(String phone) {
		BackEndUser backEndUser = backEndUserRepository.findByBindNum(phone);
		if (backEndUser != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean dealerUserNameExist(String username) {
		DealerUser dealerUser = dealerUserRepository.findByUsername(username);
		if (dealerUser != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean dealerUserPhoneExist(String phone) {
		DealerUser dealerUser = dealerUserRepository.findByBindNum(phone);
		if (dealerUser != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean storeUserNameExist(String username) {
		StoreUser storeUser = storeUserRepository.findByUserName(username);
		if (storeUser != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean storeUserPhoneExist(String phone) {
		StoreUser storeUser = storeUserRepository.findByBindNum(phone);
		if (storeUser != null) {
			return true;
		} else {
			return false;
		}
	}

}
