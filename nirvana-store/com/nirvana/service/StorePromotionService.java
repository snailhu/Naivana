package com.nirvana.service;

import org.springframework.data.domain.Page;
import com.nirvana.domain.dealer.DealerPromotion;

public interface StorePromotionService {

	/**
	 * 获取经销商的促销信息。
	 * 
	 * */
	Page<DealerPromotion> getPromotions(int page, int size);
}
