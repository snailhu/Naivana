/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年4月2日 上午9:09:55
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.common.Device;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:DeviceDTO.java
 * @Description:
 * @Version:v1.0
 */
public class DeviceDTO extends BaseDTO<Device> {
	private Long id;

	/** 设备码 */
	private String code;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Device> convert(Device domain) {
		DeviceDTO dto = new DeviceDTO();
		if (domain == null) {
			return dto;
		}
		dto.setCode(domain.getCode());
		dto.setId(domain.getId());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
