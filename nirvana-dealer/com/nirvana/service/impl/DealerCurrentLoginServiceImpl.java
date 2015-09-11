package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.exception.UserNotLoginException;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.usersys.DealerUserRepository;
import com.nirvana.service.DealerCurrentLoginService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DealerCurrentLoginServiceImpl implements DealerCurrentLoginService {

	@Resource
	private DealerUserRepository dealerUserRepository;
	@Resource
	private DealerRepository dealerRepository;

	@Override
	public long getCurrentLoginDealerId() {
		return getCurrentLoginDealer().getId();
	}

	@Override
	public Dealer getCurrentLoginDealer() {
		return getCurrentLoginDealerUser().getDealer();
	}

	@Override
	public DealerUser getCurrentLoginDealerUser() {
		String username = getCurrentLoginUsername();
		DealerUser user = dealerUserRepository.findByUsername(username);
		if (user == null) {
			throw new UserNotLoginException();
		}
		return user;
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

}
