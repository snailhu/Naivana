package com.nirvana.service.pojo.web;

import com.nirvana.domain.backend.PositionType;

public class PositionData {

	private PositionType type;

	private Integer areaId;

	public PositionData() {
	}

	public PositionType getType() {
		return type;
	}

	public void setType(PositionType type) {
		this.type = type;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

}
