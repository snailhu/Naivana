package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.pojo4json.BaseDTO;

public class WebManagerAreaDTO extends BaseDTO<ManagerArea> {

	private String areaName = "mName";
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
	public BaseDTO<ManagerArea> convert(ManagerArea domain) {
		WebManagerAreaDTO dto = new WebManagerAreaDTO();
		if (domain == null)
			return dto;

		dto.setPosition(domain.getManager().getType().name());
		dto.setPositionName(domain.getManager().getType().getName());
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
