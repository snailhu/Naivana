package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.pojo4json.BaseDTO;

public class WebDirectorAreaDTO extends BaseDTO<DirectorArea> {

	private String areaName = "name";
	private Integer id = 1;

	private String position = "";
	private String positionName = "";

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String araeName) {
		this.areaName = araeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public BaseDTO<DirectorArea> convert(DirectorArea domain) {
		WebDirectorAreaDTO dto = new WebDirectorAreaDTO();
		if (domain == null)
			return dto;
		dto.setPosition(domain.getDirector().getType().name());
		dto.setPositionName(domain.getDirector().getType().getName());
		dto.setAreaName(domain.getName());
		dto.setId(domain.getId());
		return dto;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
