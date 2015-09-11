/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年3月5日 上午10:32:10
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.dealer.PromotionGoods;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:PromotionGoodsDTO.java
 * @Description:
 * @Version:v1.0
 */
public class PromotionGoodsDTO extends BaseDTO<PromotionGoods> {

	private Long id = 1l;

	private String goods = "赠品";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<PromotionGoods> convert(PromotionGoods domain) {
		PromotionGoodsDTO dto = new PromotionGoodsDTO();
		if (domain == null) {
			return dto;
		}
		dto.setId(domain.getId());
		dto.setGoods(domain.getGoods());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

}
