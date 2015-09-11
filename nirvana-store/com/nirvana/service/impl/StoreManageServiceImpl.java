package com.nirvana.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.service.StoreCurrentAccountService;
import com.nirvana.service.StoreManageService;

@Service
@Transactional
public class StoreManageServiceImpl implements StoreManageService {

	@Resource
	private StoreCurrentAccountService storeCurrentAccountService;
	@Resource
	private DealerRepository dealerRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public StoreStorefrontInfo getAddr() {
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		return store.getStorefrontInfo();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Dealer getDealerName() {
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		return store.getDealer();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dealer> getDealersAround() {
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		Dealer dealer = store.getDealer();
		if (dealer == null) {
			return null;
		}
		DirectorArea directorArea = dealer.getDirectorArea();
		if (directorArea == null) {
			return null;
		}
		Set<Dealer> set = dealerRepository.findByDirectorAreaIdAndIsDirect(directorArea.getId(), false);
		return new ArrayList<Dealer>(set);
	}

}
