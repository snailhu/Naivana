/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年2月26日 上午11:23:11
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.ValidataUtil;

/**
 * @Title:InventoryDTO.java
 * @Description:
 * @Version:v1.0
 */
public class InventoryDTO extends BaseDTO<Inventory> {
	private ProductDTO product;

	private String price;

	private Integer amounts;

	private Integer salesVol;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Inventory> convert(Inventory domain) {
		InventoryDTO dto = new InventoryDTO();
		if (domain == null){
			return dto;
		}
		dto.setAmounts(domain.getAmounts());
		dto.setPrice(ValidataUtil.DECIMALFORMAT.format(domain.getPrice()));
		dto.setSalesVol(domain.getSalesVol());
		Converter<Product, ProductDTO> converter = DTOContext
				.getConverter(ProductDTO.class);
		dto.setProduct(converter.convert(domain.getPk().getProduct()));
		return dto;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getAmounts() {
		return amounts;
	}

	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}

	public Integer getSalesVol() {
		return salesVol;
	}

	public void setSalesVol(Integer salesVol) {
		this.salesVol = salesVol;
	}

}
