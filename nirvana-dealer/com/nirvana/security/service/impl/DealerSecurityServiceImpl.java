package com.nirvana.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.repository.dealer.usersys.DealerUserRepository;
import com.nirvana.security.service.DealerSecurityService;

@Service
@Transactional
public class DealerSecurityServiceImpl implements DealerSecurityService {

	@Resource
	private DealerUserRepository dealerUserRepository;

	@Override
	public DealerUser findUserByUserLogin(String username) {
		return dealerUserRepository.findByUsername(username);
	}

}
