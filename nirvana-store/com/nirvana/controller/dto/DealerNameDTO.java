/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年6月26日 上午8:52:01
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:DealerNameDTO.java
 * @Description:
 * @Version:v1.0
 */
public class DealerNameDTO extends BaseDTO<Dealer> {
	private String name;
	private String contactType;
	private String contactValue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Dealer> convert(Dealer domain) {
		DealerNameDTO dto = new DealerNameDTO();
		if (domain == null) {
			return dto;
		}
		dto.setName(domain.getStorefrontInfo().getName());
		dto.setContactValue(domain.getStorefrontInfo().getContactValue());
		dto.setContactType(domain.getStorefrontInfo().getContactType());
		return dto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

}
