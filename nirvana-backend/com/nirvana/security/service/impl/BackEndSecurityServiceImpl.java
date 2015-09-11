package com.nirvana.security.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.repository.backend.usersys.BackEndUserRepository;
import com.nirvana.security.service.BackEndSecurityService;

@Service
@Transactional
public class BackEndSecurityServiceImpl implements BackEndSecurityService {

	@Resource
	private BackEndUserRepository backEndUserRepository;

	@Override
	public Set<ERole> getUserRoles(BackEndUser user) {
		if (user == null) {
			throw new IllegalArgumentException("参数不能为空。");
		}
		Set<ERole> roles = new HashSet<ERole>();
		Set<Position> positions = user.getEmployee().getPositions();
		for (Position position : positions) {
			roles.addAll(position.getType().getRole().getRoleSet());
		}
		roles.add(ERole.USER);
		return roles;
	}

	@Override
	public BackEndUser findUserByUserLogin(String userName) {
		if (userName == null) {
			throw new IllegalArgumentException("参数不能为空。");
		}
		BackEndUser user = backEndUserRepository.findByUsername(userName);
		return user;
	}

	@Override
	public Set<ERole> getUserRoles(String userName) {
		BackEndUser user = backEndUserRepository.findByUsername(userName);
		return getUserRoles(user);
	}

}
