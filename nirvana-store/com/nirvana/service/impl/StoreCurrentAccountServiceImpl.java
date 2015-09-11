package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.exception.UserNotLoginException;
import com.nirvana.repository.store.usersys.StoreUserRepository;
import com.nirvana.service.StoreCurrentAccountService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class StoreCurrentAccountServiceImpl implements StoreCurrentAccountService {

	@Resource
	StoreUserRepository storeUserRepository;

	@Override
	public long getCurrentLoginStoreId() {
		return getCurrentLoginStore().getId();
	}

	@Override
	public Store getCurrentLoginStore() {
		return getCurrentLoginUser().getStore();
	}

	@Override
	public String getCurrentLoginUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken || authentication == null) {
			throw new UserNotLoginException();
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return userDetails.getUsername();
	}

	@Override
	public StoreUser getCurrentLoginUser() {
		String username = getCurrentLoginUserName();
		StoreUser user = storeUserRepository.findByUserName(username);
		if (user == null) {
			throw new UserNotLoginException();
		}
		return user;
	}

}
