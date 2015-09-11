///**
// * Copyright 2014 SFI
// * @Author:guzhen
// * @Email:1132053388@qq.com
// * @Date:2015年2月14日 下午10:34:22
// */
//package com.nirvana.controller.dto.app;
//
//import com.nirvana.domain.common.Brand;
//import com.nirvana.pojo4json.BaseDTO;
//
//public class BrandDTODELE extends BaseDTO<Brand> {
//	private Integer id=1;
//	private String name="品牌名";
//
//	@Override
//	public BaseDTO<Brand> convert(Brand domain) {
//		BrandDTO dto = new BrandDTO();
//		if (domain == null)
//			return dto;
//		dto.setId(domain.getId());
//		dto.setName(domain.getName());
//		return dto;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
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
//}
