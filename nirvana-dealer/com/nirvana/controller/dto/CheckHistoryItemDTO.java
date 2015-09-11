/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年6月3日 上午9:38:32
 */
package com.nirvana.controller.dto;

import java.text.DecimalFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.CheckHistoryItem;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

/**
 * @Title:CheckHistoryItemDTO.java
 * @Description:
 * @Version:v1.0
 */
public class CheckHistoryItemDTO extends BaseDTO<CheckHistoryItem> {
	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 盘点的商品 */
	private ProductDTO product;

	/** 盘点前数量 */
	private Integer beforeAmount;

	/** 盘点后数量 */
	private Integer afterAmount;

	/** 盘点前价格 */
	private String beforePrice;

	/** 盘点后价格 */
	private String afterPrice;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<CheckHistoryItem> convert(CheckHistoryItem domain) {
		CheckHistoryItemDTO dto = new CheckHistoryItemDTO();
		if (domain == null) {
			return dto;
		}
		dto.setAfterAmount(domain.getAfterAmount());
		DecimalFormat format = new DecimalFormat("0.00");
		dto.setAfterPrice(format.format(domain.getAfterPrice()));
		dto.setBeforeAmount(domain.getBeforeAmount());
		dto.setBeforePrice(format.format(domain.getBeforePrice()));
		dto.setId(domain.getId());
		Converter<Product, ProductDTO> converter = DTOContext
				.getConverter(ProductDTO.class);
		dto.setProduct(converter.convert(domain.getProduct()));
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public Integer getBeforeAmount() {
		return beforeAmount;
	}

	public void setBeforeAmount(Integer beforeAmount) {
		this.beforeAmount = beforeAmount;
	}

	public Integer getAfterAmount() {
		return afterAmount;
	}

	public void setAfterAmount(Integer afterAmount) {
		this.afterAmount = afterAmount;
	}

	public String getBeforePrice() {
		return beforePrice;
	}

	public void setBeforePrice(String beforePrice) {
		this.beforePrice = beforePrice;
	}

	public String getAfterPrice() {
		return afterPrice;
	}

	public void setAfterPrice(String afterPrice) {
		this.afterPrice = afterPrice;
	}
}
