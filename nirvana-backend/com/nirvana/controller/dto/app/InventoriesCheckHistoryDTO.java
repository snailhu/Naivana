package com.nirvana.controller.dto.app;

import java.util.Date;
import java.util.List;

import com.nirvana.domain.dealer.CheckHistoryItem;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class InventoriesCheckHistoryDTO extends BaseDTO<InventoriesCheckHistory> {

	private Long id;

	private Date date;

	/** 图片URL */
	private String url;

	/** 盘点历史项 */
	private List<CheckHistoryItemDTO> items;

	public InventoriesCheckHistoryDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<CheckHistoryItemDTO> getItems() {
		return items;
	}

	public void setItems(List<CheckHistoryItemDTO> items) {
		this.items = items;
	}

	@Override
	public InventoriesCheckHistoryDTO convert(InventoriesCheckHistory domain) {
		InventoriesCheckHistoryDTO dto = new InventoriesCheckHistoryDTO();
		dto.setDate(domain.getDate());
		dto.setId(domain.getId());
		Converter<CheckHistoryItem, CheckHistoryItemDTO> converter = DTOContext.getConverter(CheckHistoryItemDTO.class);
		dto.setItems(converter.convert(domain.getItems()));
		dto.setUrl(domain.getUrl());
		return dto;
	}
}
