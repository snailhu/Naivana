package com.nirvana.controller.dto.web;

import java.util.List;

import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.goal.BigAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.service.pojo.web.GlobalGoalResult;

public class WebGlobalGoalResultDTO extends BaseDTO<GlobalGoalResult> {

	private boolean isAlloted;

	private List<WebBigAreaNrGoalDTO> sons;

	private List<WebBigAreaDTO> sonAreas;

	@Override
	public BaseDTO<GlobalGoalResult> convert(GlobalGoalResult domain) {
		WebGlobalGoalResultDTO dto = new WebGlobalGoalResultDTO();
		dto.setAlloted(domain.isAlloted());
		Converter<BigAreaNrGoal, WebBigAreaNrGoalDTO> converter1 = DTOContext.getConverter(WebBigAreaNrGoalDTO.class);
		Converter<BigArea, WebBigAreaDTO> converter2 = DTOContext.getConverter(WebBigAreaDTO.class);
		dto.setSonAreas(converter2.convert(domain.getBigAreas()));
		dto.setSons(converter1.convert(domain.getBigAreaNrGoals()));
		return dto;
	}

	public boolean isAlloted() {
		return isAlloted;
	}

	public void setAlloted(boolean isAlloted) {
		this.isAlloted = isAlloted;
	}

	public List<WebBigAreaNrGoalDTO> getSons() {
		return sons;
	}

	public void setSons(List<WebBigAreaNrGoalDTO> sons) {
		this.sons = sons;
	}

	public List<WebBigAreaDTO> getSonAreas() {
		return sonAreas;
	}

	public void setSonAreas(List<WebBigAreaDTO> sonAreas) {
		this.sonAreas = sonAreas;
	}

}
