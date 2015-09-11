package com.nirvana.controller.dto.app;

import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.pojo4json.BaseDTO;

/**
 * 获取业务员门店分类的DTO
 * 
 * */
public class AgentStoreCategoryDTO extends BaseDTO<AgentStoreCategory> {

	private String name="分类名";

	public AgentStoreCategoryDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BaseDTO<AgentStoreCategory> convert(AgentStoreCategory domain) {
		
		AgentStoreCategoryDTO dto = new AgentStoreCategoryDTO();
		if (domain == null){
			return dto;
		}
		dto.setName(domain.getCategoryName());
		return dto;
	}

}
