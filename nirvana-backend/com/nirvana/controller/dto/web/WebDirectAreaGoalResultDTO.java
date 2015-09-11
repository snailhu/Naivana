package com.nirvana.controller.dto.web;

import java.util.List;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.goal.AgentAreaNrGoal;
import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.service.pojo.web.DirectAreaGoalResult;

public class WebDirectAreaGoalResultDTO extends BaseDTO<DirectAreaGoalResult> {

	private WebDirectorAreaNrGoalDTO father;

	private List<WebAgentAreaNrGoalDTO> sons;

	private List<WebAgentAreaDTO> sonAreas;

	@Override
	public BaseDTO<DirectAreaGoalResult> convert(DirectAreaGoalResult domain) {
		WebDirectAreaGoalResultDTO dto = new WebDirectAreaGoalResultDTO();
		if (domain == null) {
			return dto;
		}
		Converter<DirectorAreaNrGoal, WebDirectorAreaNrGoalDTO> fatherConverter = DTOContext
				.getConverter(WebDirectorAreaNrGoalDTO.class);
		dto.setFather(fatherConverter.convert(domain.getDirectorAreaNrGoal()));
		Converter<AgentAreaNrGoal, WebAgentAreaNrGoalDTO> sonConverter = DTOContext
				.getConverter(WebAgentAreaNrGoalDTO.class);
		dto.setSons(sonConverter.convert(domain.getAgentAreaNrGoals()));
		Converter<AgentArea, WebAgentAreaDTO> sonAreaConverter = DTOContext
				.getConverter(WebAgentAreaDTO.class);
		dto.setSonAreas(sonAreaConverter.convert(domain.getAgentAreas()));
		return dto;
	}

	public WebDirectorAreaNrGoalDTO getFather() {
		return father;
	}

	public void setFather(WebDirectorAreaNrGoalDTO father) {
		this.father = father;
	}

	public List<WebAgentAreaNrGoalDTO> getSons() {
		return sons;
	}

	public void setSons(List<WebAgentAreaNrGoalDTO> sons) {
		this.sons = sons;
	}

	public List<WebAgentAreaDTO> getSonAreas() {
		return sonAreas;
	}

	public void setSonAreas(List<WebAgentAreaDTO> sonAreas) {
		this.sonAreas = sonAreas;
	}

}
