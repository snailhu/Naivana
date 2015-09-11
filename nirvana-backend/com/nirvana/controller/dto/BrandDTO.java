/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月27日 上午10:09:41
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.common.Brand;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:BrandDTO.java
 * @Description:
 * @Version:v1.0
 */
public class BrandDTO extends BaseDTO<Brand> {
	private String name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Brand> convert(Brand domain) {
		BrandDTO dto = new BrandDTO();
		if (domain == null) {
			return dto;
		}
		dto.setName(domain.getName());
		return dto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
