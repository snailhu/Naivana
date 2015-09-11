package com.nirvana.controller.dto.app;

import java.text.DecimalFormat;

import com.nirvana.domain.dealer.CheckHistoryItem;
import com.nirvana.pojo4json.BaseDTO;

public class CheckHistoryItemDTO extends BaseDTO<CheckHistoryItem> {

	private String productCode;

	private String productName;

	private Integer beforeAmount;

	private Integer afterAmount;

	private String beforePrice;

	private String afterPrice;

	public CheckHistoryItemDTO() {
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	@Override
	public CheckHistoryItemDTO convert(CheckHistoryItem domain) {
		CheckHistoryItemDTO dto = new CheckHistoryItemDTO();
		dto.setAfterAmount(domain.getAfterAmount());
		DecimalFormat format = new DecimalFormat("0.00");
		dto.setAfterPrice(format.format(domain.getAfterPrice()));
		dto.setBeforeAmount(domain.getBeforeAmount());
		dto.setBeforePrice(format.format(domain.getBeforePrice()));
		dto.setProductCode(domain.getProduct().getCode());
		dto.setProductName(domain.getProduct().getDescription());
		return dto;
	}

}
