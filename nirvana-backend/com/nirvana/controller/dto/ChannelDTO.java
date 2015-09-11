/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月26日 下午9:41:21
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.common.Channel;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:ChannelDTO.java
 * @Description:
 * @Version:v1.0
 */
public class ChannelDTO extends BaseDTO<Channel> {
	/** ID */
	public String code;

	/** 渠道描述 */
	public String description;

	/** 大渠道名称 */
	public String groupName;

	/** 版本 */
	public String version;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Channel> convert(Channel domain) {
		ChannelDTO dto = new ChannelDTO();
		if (domain == null) {
			return dto;
		}
		dto.setCode(domain.getCode());
		dto.setDescription(domain.getDescription());
		dto.setGroupName(domain.getGroupName());
		dto.setVersion(domain.getVersion());
		return dto;
	}
}
