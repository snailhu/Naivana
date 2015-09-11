package com.nirvana.controller.dto;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.pojo4json.BaseDTO;

public class DealerPepsiPromotionDTO extends BaseDTO<PepsiPromotion> {

	private Integer id=1;

	/** 活动标题 */
	private String title="标题";

	/** 开始日期,形式为20101125 */
	private Integer startDate=20101125;

	/** 结束日期 ,形式为20101125 */
	private Integer endDate=20101125;

	/** 活动奖励（文字说明） */
	private String reward="活动奖励（文字说明）";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public Integer getEndDate() {
		return endDate;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	@Override
	public BaseDTO<PepsiPromotion> convert(PepsiPromotion domain) {
		DealerPepsiPromotionDTO dto = new DealerPepsiPromotionDTO();
		if (domain == null) {
			return dto;
		}
		dto.setId(domain.getId());
		dto.setEndDate(domain.getEndDate());
		dto.setReward(domain.getReward());
		dto.setStartDate(domain.getStartDate());
		dto.setTitle(domain.getTitle());
		return dto;
	}

}
