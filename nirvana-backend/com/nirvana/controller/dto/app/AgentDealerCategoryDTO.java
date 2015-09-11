package com.nirvana.controller.dto.app;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.pojo4json.BaseDTO;

public class AgentDealerCategoryDTO extends BaseDTO<AgentDealerCategory> {

	private String name = "·ÖÀàÃû";

	public AgentDealerCategoryDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BaseDTO<AgentDealerCategory> convert(AgentDealerCategory domain) {
		AgentDealerCategoryDTO dto = new AgentDealerCategoryDTO();
		if (domain == null) {
			return dto;
		}
		dto.setName(domain.getCategoryName());
		return dto;
	}

}
