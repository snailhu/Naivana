package com.nirvana.controller.dto.web;

import java.util.List;

import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.goal.BigAreaNrGoal;
import com.nirvana.domain.backend.goal.ManagerAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.service.pojo.web.BigAreaGoalResult;

public class WebBigAreaGoalResultDTO extends BaseDTO<BigAreaGoalResult> {

	private WebBigAreaNrGoalDTO father;

	private List<WebManagerAreaNrGoalDTO> sons;

	private List<WebManagerAreaDTO> sonAreas;

	@Override
	public BaseDTO<BigAreaGoalResult> convert(BigAreaGoalResult domain) {
		WebBigAreaGoalResultDTO dto = new WebBigAreaGoalResultDTO();
		if (domain == null) {
			return dto;
		}
		Converter<BigAreaNrGoal, WebBigAreaNrGoalDTO> fatherConverter = DTOContext.getConverter(WebBigAreaNrGoalDTO.class);
		dto.setFather(fatherConverter.convert(domain.getBigAreaNrGoal()));
		Converter<ManagerAreaNrGoal, WebManagerAreaNrGoalDTO> sonConverter = DTOContext.getConverter(WebManagerAreaNrGoalDTO.class);
		dto.setSons(sonConverter.convert(domain.getManagerAreaNrGoals()));
		Converter<ManagerArea, WebManagerAreaDTO> sonAreaConverter = DTOContext.getConverter(WebManagerAreaDTO.class);
		dto.setSonAreas(sonAreaConverter.convert(domain.getManagerAreas()));
		return dto;
	}

	public WebBigAreaNrGoalDTO getFather() {
		return father;
	}

	public void setFather(WebBigAreaNrGoalDTO father) {
		this.father = father;
	}

	public List<WebManagerAreaNrGoalDTO> getSons() {
		return sons;
	}

	public void setSons(List<WebManagerAreaNrGoalDTO> sons) {
		this.sons = sons;
	}

	public List<WebManagerAreaDTO> getSonAreas() {
		return sonAreas;
	}

	public void setSonAreas(List<WebManagerAreaDTO> sonAreas) {
		this.sonAreas = sonAreas;
	}

}
