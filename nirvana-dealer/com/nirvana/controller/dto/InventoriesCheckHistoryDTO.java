/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年6月3日 上午9:36:30
 */
package com.nirvana.controller.dto;

import java.util.List;
import com.nirvana.domain.dealer.CheckHistoryItem;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.DateUtil;

/**
 * @Title:InventoriesCheckHistoryDTO.java
 * @Description:
 * @Version:v1.0
 */
public class InventoriesCheckHistoryDTO extends
		BaseDTO<InventoriesCheckHistory> {
	private Long id;

	private Long dealerId;

	/** 盘点日期 */
	private String date;

	/** 盘点历史项 */
	private List<CheckHistoryItemDTO> items;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<InventoriesCheckHistory> convert(
			InventoriesCheckHistory domain) {
		InventoriesCheckHistoryDTO dto = new InventoriesCheckHistoryDTO();
		if (domain == null) {
			return dto;
		}
		dto.setDate(DateUtil.dateToString(domain.getDate()));
		dto.setDealerId(domain.getDealer().getId());
		dto.setId(domain.getId());
		Converter<CheckHistoryItem, CheckHistoryItemDTO> converter = DTOContext
				.getConverter(CheckHistoryItemDTO.class);
		dto.setItems(converter.convert(domain.getItems()));
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<CheckHistoryItemDTO> getItems() {
		return items;
	}

	public void setItems(List<CheckHistoryItemDTO> items) {
		this.items = items;
	}
}
