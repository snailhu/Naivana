/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��5��26�� ����9:17:49
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.common.Product;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:ProductDTO.java
 * @Description:
 * @Version:v1.0
 */
public class ProductDTO extends BaseDTO<Product> {
	/** ��Ʒ���� */
	private String code;

	/** ��Ʒ���� */
	private String description;

	/** ��Ʒ���� */
	private String brand;

	/** ��Ʒ��װ */
	private String commodity;

	// /** ������ת��ϵ������������ͳ�Ʊ����ã� */
	// private Float trsptConv;
	//
	// /** ����ת��ϵ�������ڱ�׼��ͳ�Ʊ����ã� */
	// private Float standardConv;
	//
	// /** ��Ʒ�汾 */
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
	public BaseDTO<Product> convert(Product domain) {
		ProductDTO dto = new ProductDTO();
		if (domain == null) {
			return dto;
		}

		dto.setBrand(domain.getBrand() == null ? null : domain.getBrand()
				.getName());
		dto.setCode(domain.getCode());
		dto.setCommodity(domain.getCommodity());
		dto.setDescription(domain.getDescription());
		// dto.setStandardConv(domain.getStandardConv());
		// dto.setTrsptConv(domain.getTrsptConv());
		// dto.setVersion(domain.getVersion());
		return dto;
	}
}
