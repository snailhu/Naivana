package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.pojo4json.BaseDTO;

public class WebBigAreaDTO extends BaseDTO<BigArea> {

	private String areaName;
	private Integer id ;
	
	private String position="";
	private String positionName="";

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
	public BaseDTO<BigArea> convert(BigArea domain) {
		WebBigAreaDTO dto = new WebBigAreaDTO();
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
