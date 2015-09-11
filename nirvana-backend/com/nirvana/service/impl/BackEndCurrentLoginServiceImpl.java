package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.exception.UserNotLoginException;
import com.nirvana.repository.backend.area.DirectorAreaRepository;
import com.nirvana.repository.backend.usersys.BackEndUserRepository;
import com.nirvana.service.BackEndCurrentLoginService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class BackEndCurrentLoginServiceImpl implements BackEndCurrentLoginService {
	@Resource
	private BackEndUserRepository backEndUserRepository;
	@Resource
	private DirectorAreaRepository directorAreaRepository;

	@Override
	public Employee getCurrentLoginEmployee() {
		return getCurrentLoginUser().getEmployee();
	}

	@Override
	public String getCurrentLoginUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken || authentication == null) {
			throw new UserNotLoginException();
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return userDetails.getUsername();
	}

	@Override
	public BackEndUser getCurrentLoginUser() {
		BackEndUser user = backEndUserRepository.findByUsername(getCurrentLoginUsername());
		if (user == null) {
			throw new UserNotLoginException();
		}
		return user;
	}

	@Override
	public Long getCurrentLoginEmployeeId() {
		return getCurrentLoginEmployee().getId();
	}
}
