package com.nirvana.service;

import org.springframework.data.domain.Page;
import com.nirvana.domain.dealer.DealerPromotion;

public interface StorePromotionService {

	/**
	 * ��ȡ�����̵Ĵ�����Ϣ��
	 * 
	 * */
	Page<DealerPromotion> getPromotions(int page, int size);
}
