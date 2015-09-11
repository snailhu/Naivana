package com.nirvana.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.PromCondition;
import com.nirvana.domain.dealer.PromWay;

/**
 * 经销商的促销信息服务。
 * 
 * 包括从后端获得的活动和促销，以及自身面向一阶门店发布的活动和促销。
 * 
 * */
public interface DealerPromotionService {

	/**
	 * 根据时间倒序获取我的促销信息的分页数据。
	 * 
	 * @param page
	 *            页数
	 * @param size
	 *            分页大小
	 * */
	public Page<DealerPromotion> getMyPromotions(int page, int size);

	/**
	 * 编辑促销信息。
	 * 
	 * */
	public void editDealerPromotion(
			String title,
			long promotionId,
			int startDate,
			int endDate,
			List<String> catList,
			Set<String> products,
			PromCondition pCondition,
			int cdtParam,
			PromWay way,
			float wayParam,
			Set<String> goods);

	/**
	 * 撤回促销信息。
	 * 
	 * 
	 * */
	public void withdrawDealerPromotion(long promotionId);

	/**
	 * 新建促销信息。
	 * 
	 * */
	public void createDealerPromotion(
			String title,
			int startDate,
			int endDate,
			List<String> catList,
			Set<String> products,
			PromCondition pCondition,
			int cdtParam,
			PromWay way,
			float wayParam,
			Set<String> goods);

	/**
	 * 获取来自百事的促销信息。
	 * 
	 * */
	public Page<PepsiPromotion> getPepsiPromotions(int page, int size);

}
