package com.nirvana.controller.dto.web;

import java.util.List;

import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;
import com.nirvana.domain.backend.goal.ManagerAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.service.pojo.web.ManagerAreaGoalResult;

public class WebManagerAreaGoalResultDTO extends BaseDTO<ManagerAreaGoalResult> {

	private WebManagerAreaNrGoalDTO father;

	private List<WebDirectorAreaNrGoalDTO> sons;

	private List<WebDirectorAreaDTO> sonAreas;

	@Override
	public BaseDTO<ManagerAreaGoalResult> convert(ManagerAreaGoalResult domain) {
		WebManagerAreaGoalResultDTO dto = new WebManagerAreaGoalResultDTO();
		if (domain == null) {
			return dto;
		}
		Converter<ManagerAreaNrGoal, WebManagerAreaNrGoalDTO> fatherConverter = DTOContext.getConverter(WebManagerAreaNrGoalDTO.class);
		dto.setFather(fatherConverter.convert(domain.getManagerAreaNrGoal()));
		Converter<DirectorAreaNrGoal, WebDirectorAreaNrGoalDTO> sonConverter = DTOContext.getConverter(WebDirectorAreaNrGoalDTO.class);
		dto.setSons(sonConverter.convert(domain.getDirectorAreaNrGoals()));
		Converter<DirectorArea, WebDirectorAreaDTO> sonAreaConverter = DTOContext.getConverter(WebDirectorAreaDTO.class);
		dto.setSonAreas(sonAreaConverter.convert(domain.getDirectorAreas()));
		return dto;
	}

	public WebManagerAreaNrGoalDTO getFather() {
		return father;
	}

	public void setFather(WebManagerAreaNrGoalDTO father) {
		this.father = father;
	}

	public List<WebDirectorAreaNrGoalDTO> getSons() {
		return sons;
	}

	public void setSons(List<WebDirectorAreaNrGoalDTO> sons) {
		this.sons = sons;
	}

	public List<WebDirectorAreaDTO> getSonAreas() {
		return sonAreas;
	}

	public void setSonAreas(List<WebDirectorAreaDTO> sonAreas) {
		this.sonAreas = sonAreas;
	}

}
