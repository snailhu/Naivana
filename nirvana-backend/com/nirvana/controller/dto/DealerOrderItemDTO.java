/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��5��26�� ����10:09:38
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

	/** ���� */
	private Integer amount;

	/** �������к� */
	private Integer lineNo;

	/** ���� */
	private Float unitPrice;

	/** ��λ */
	private String unitMeas;

	/** �ۿ۰ٷֱ� */
	private Float discount;

	/** ���ֽ�� */
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
		dto.setType(DealerOrderItemType.DO == domain.getType() ? "�ۿ۶���"
				: "���۶���");

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
