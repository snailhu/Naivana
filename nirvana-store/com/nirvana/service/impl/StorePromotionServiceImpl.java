package com.nirvana.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.repository.dealer.DealerPromotionRepository;
import com.nirvana.service.StoreCurrentAccountService;
import com.nirvana.service.StorePromotionService;
import com.nirvana.utils.DateUtil;

@Service
public class StorePromotionServiceImpl implements StorePromotionService {

	@Resource
	StoreCurrentAccountService storeCurrentAccountService;
	@Resource
	DealerPromotionRepository dealerPromotionRepository;

	@SuppressWarnings("deprecation")
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<DealerPromotion> getPromotions(int page, int size) {
		Date now = new Date();
		now.setDate(now.getDate()+1);
		Date now2 = new Date();
		now2.setDate(now2.getDate()-1);
		int nowInt = Integer.parseInt(DateUtil.dateToString(now, "yyyyMMdd"));
		int nowInt2= Integer.parseInt(DateUtil.dateToString(now2, "yyyyMMdd"));
		StoreUser user = storeCurrentAccountService.getCurrentLoginUser();
		DealerStoreCategory category=user.getStore().getDealerCategory();
		Dealer dealer = user.getStore().getDealer();
		if (dealer == null) {
			throw new RecordAcessDeniedException("此门店还没有分配经销商");
		}
		return dealerPromotionRepository.findByDealerAndEndDateGreaterThanAndBeginDateLessThanAndCategories(dealer, nowInt2, nowInt,category, new PageRequest(page - 1, size));
	}
}
