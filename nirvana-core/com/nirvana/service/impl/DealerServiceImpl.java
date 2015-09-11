package com.nirvana.service.impl;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerPoints;
import com.nirvana.domain.dealer.DealerSerialNumber;
import com.nirvana.domain.dealer.DealerStockSerialNumber;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.repository.common.ChannelRepository;
import com.nirvana.repository.dealer.DealerPointsRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.DealerSerialNumberRepository;
import com.nirvana.repository.dealer.DealerStockSerialNumberRepository;
import com.nirvana.repository.dealer.DealerStoreCategoryRepository;
import com.nirvana.service.DealerService;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {

	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private ChannelRepository channelRepository;
	@Resource
	private DealerSerialNumberRepository dealerSerialNumberRepository;
	@Resource
	private DealerStockSerialNumberRepository dealerStockSerialNumberRepository;
	@Resource
	private DealerPointsRepository dealerPointsRepository;
	@Resource
	private DealerStoreCategoryRepository dealerStoreCategoryRepository;

	@Override
	public void initDealer(long dealerId) {
		Dealer dealer = dealerRepository.findOne(dealerId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (dealer == null) {
			throw new RecordNotFoundException("此经销商或者直营店未找到。");
		}

		// 设置DealerSerialNumber
		if (dealer.getSerialNumber() == null && dealer.getIsDirect() == false) {
			DealerSerialNumber dealerSerialNumber = new DealerSerialNumber();
			dealerSerialNumber.setDealer(dealer);
			dealerSerialNumber.setDate(19990101);
			dealerSerialNumber.setSerialNum(0);
			dealerSerialNumberRepository.save(dealerSerialNumber);
		}

		// 设置DealerStockSerialNumber
		if (dealer.getStockSerialNumber() == null) {
			DealerStockSerialNumber dealerStockSerialNumber = new DealerStockSerialNumber();
			dealerStockSerialNumber.setDealer(dealer);
			dealerStockSerialNumber.setDate(19990101);
			dealerStockSerialNumber.setSerialNum(0);
			dealerStockSerialNumberRepository.save(dealerStockSerialNumber);
		}

		// 设置DealerPoints
		if (dealer.getPoints() == null) {
			DealerPoints points = new DealerPoints();
			points.setAvailablePoints(0);
			points.setConsumedPoints(0);
			points.setDealer(dealer);
			dealerPointsRepository.save(points);
		}

		if (dealer.getIsDirect() == false) {
			DealerStoreCategory dealerStoreCategory = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealerId, DealerStoreCategory.UNDEFINED);
			if (dealerStoreCategory == null) {
				dealerStoreCategory = new DealerStoreCategory();
				dealerStoreCategory.setDealer(dealer);
				dealerStoreCategory.setCategoryName(AgentDealerCategory.UNDEFINED);
				dealerStoreCategoryRepository.save(dealerStoreCategory);
			}
		}

	}
}
