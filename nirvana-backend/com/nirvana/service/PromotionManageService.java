package com.nirvana.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PromotionState;

public interface PromotionManageService {

	/**
	 * 创建新的促销方案。
	 * 
	 * @param title
	 *            促销标题
	 * @param channelId
	 *            渠道ID
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param content
	 *            内容（奖励措施）
	 * @param pictures
	 *            签核文件
	 * 
	 * */
	public void createNewPromotion(String title, String channelId, int beginDate, int endDate, String content, List<String> pictures);

	/**
	 * 删除促销方案。
	 * 
	 * */
	public void deletePromotion(int promotionId);

	/**
	 * 编辑促销方案。
	 * 
	 * */
	public void editPromotion(int promotionId, String title, String channelId, int beginDate, int endDate, String content, List<String> data);

	/**
	 * 通过促销方案。
	 * 
	 * */
	public void adoptPromotion(int promotionId);

	/**
	 * 拒绝促销方案。
	 * 
	 * */
	public void rejectPromotion(int promotionId);

	/**
	 * 获取历史促销信息。
	 * 
	 * */
	public Page<PepsiPromotion> getHistoryPromotions(PromotionState state, int page, int size);

	/**
	 * 获取单个促销信息。
	 * 
	 * */
	public PepsiPromotion getOnePromotion(int promotionId);


}
