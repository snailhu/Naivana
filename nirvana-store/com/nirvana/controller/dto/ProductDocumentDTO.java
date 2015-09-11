/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月26日 下午9:17:49
 */
package com.nirvana.controller.dto;

import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument.ProductDocument;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:ProductDTO.java
 * @Description:
 * @Version:v1.0
 */
public class ProductDocumentDTO extends BaseDTO<ProductDocument> {
	/** 商品编码 */
	private String code;

	/** 商品描述 */
	private String description;

	/** 产品分类 */
	private String brand;

	/** 产品包装 */
	private String commodity;

	// /** 运力箱转换系数（用于运力统计报表用） */
	// private Float trsptConv;
	//
	// /** 标箱转换系数（用于标准箱统计报表用） */
	// private Float standardConv;
	//
	// /** 产品版本 */
	// private String version;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	// public Float getTrsptConv() {
	// return trsptConv;
	// }
	//
	// public void setTrsptConv(Float trsptConv) {
	// this.trsptConv = trsptConv;
	// }
	//
	// public Float getStandardConv() {
	// return standardConv;
	// }
	//
	// public void setStandardConv(Float standardConv) {
	// this.standardConv = standardConv;
	// }
	//
	// public String getVersion() {
	// return version;
	// }
	//
	// public void setVersion(String version) {
	// this.version = version;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<ProductDocument> convert(ProductDocument domain) {
		ProductDocumentDTO dto = new ProductDocumentDTO();
		if (domain == null) {
			return dto;
		}
		dto.setCode(domain.getCode());
		dto.setCommodity(domain.getCommodity());
		dto.setDescription(domain.getDescription());
		// dto.setStandardConv(domain.getStandardConv());
		// dto.setTrsptConv(domain.getTrsptConv());
		// dto.setVersion(domain.getVersion());
		return dto;
	}
}
