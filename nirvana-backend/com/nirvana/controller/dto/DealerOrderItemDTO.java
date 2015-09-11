/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月26日 下午10:09:38
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.DealerOrderItem;
import com.nirvana.domain.dealer.DealerOrderItemType;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

/**
 * @Title:DealerOrderItemDTO.java
 * @Description:
 * @Version:v1.0
 */
public class DealerOrderItemDTO extends BaseDTO<DealerOrderItem> {
	private ProductDTO product;

	/** 数量 */
	private Integer amount;

	/** 订单项行号 */
	private Integer lineNo;

	/** 单价 */
	private Float unitPrice;

	/** 单位 */
	private String unitMeas;

	/** 折扣百分比 */
	private Float discount;

	/** 积分金额 */
	private Integer points;

	private String type;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<DealerOrderItem> convert(DealerOrderItem domain) {
		DealerOrderItemDTO dto = new DealerOrderItemDTO();
		if (domain == null) {
			return dto;
		}
		dto.setAmount(domain.getAmount());
		dto.setLineNo(domain.getPk().getLineNo());
		Converter<Product, ProductDTO> converter = DTOContext
				.getConverter(ProductDTO.class);
		dto.setProduct(converter.convert(domain.getProduct()));
		dto.setDiscount(domain.getDiscount());
		dto.setPoints(domain.getPoints());
		dto.setUnitMeas(domain.getUnitMeas());
		dto.setUnitPrice(domain.getUnitPrice());
		dto.setType(DealerOrderItemType.DO == domain.getType() ? "折扣订单"
				: "销售订单");

		return dto;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitMeas() {
		return unitMeas;
	}

	public void setUnitMeas(String unitMeas) {
		this.unitMeas = unitMeas;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
