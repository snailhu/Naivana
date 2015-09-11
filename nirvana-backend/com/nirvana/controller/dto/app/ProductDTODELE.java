///**
// * Copyright 2014 SFI
// * @Author:guzhen
// * @Email:1132053388@qq.com
// * @Date:2015年2月14日 下午10:37:24
// */
//package com.nirvana.controller.dto.app;
//
//import com.nirvana.domain.common.Product;
//import com.nirvana.pojo4json.BaseDTO;
//
///**
// * @Title:ProductDTO.java
// * @Description:
// * @Version:v1.0
// */
//public class ProductDTODELE extends BaseDTO<Product> {
//
//	/** 商品条码 */
//	private String code="xxx";
//
//	/** 商品名称 */
//	private String name="商品名";
//
//	/** 规格 */
//	private String specifications="规格";
//
//	private String url="xxx.com";
//
//	/** 所属品牌 */
//	private String brandName="品牌名";
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
//	 */
//	@Override
//	public BaseDTO<Product> convert(Product product) {
//		ProductDTO dto = new ProductDTO();
//		if (product == null)
//			return dto;
//		if (product.getBrand() != null)
//			dto.setBrandName(product.getBrand().getName());
//		dto.setCode(product.getCode());
//		dto.setName(product.getName());
//		dto.setSpecifications(product.getSpecifications());
//		dto.setUrl(product.getUrl());
//		return dto;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSpecifications() {
//		return specifications;
//	}
//
//	public void setSpecifications(String specifications) {
//		this.specifications = specifications;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getBrandName() {
//		return brandName;
//	}
//
//	public void setBrandName(String brandName) {
//		this.brandName = brandName;
//	}
//
//}
