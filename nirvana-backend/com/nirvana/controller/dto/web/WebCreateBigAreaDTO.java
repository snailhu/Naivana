package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.pojo4json.BaseDTO;

public class WebCreateBigAreaDTO extends BaseDTO<BigArea> {

	private Integer id;

	private String code;

	private String name;

	public WebCreateBigAreaDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BaseDTO<BigArea> convert(BigArea domain) {
		WebCreateBigAreaDTO dto = new WebCreateBigAreaDTO();
		dto.setId(domain.getId());
		dto.setCode(domain.getAreaCode());
		dto.setName(domain.getName());
		return dto;
	}

}
