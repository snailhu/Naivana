/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月13日 上午9:37:33
 */
package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:WebPositionDTO.java
 * @Description:
 * @Version:v1.0
 */
public class WebPositionDTO extends BaseDTO<Position> {

	/** ID */
	private Integer id;

	/** 职位类型 */
	private String type = PositionType.CLERK.name();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Position> convert(Position domain) {
		WebPositionDTO dto = new WebPositionDTO();
		if (domain == null) {
			return dto;
		}
		dto.setId(domain.getId());
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
