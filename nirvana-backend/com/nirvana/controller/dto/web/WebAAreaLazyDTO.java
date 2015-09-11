package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.pojo4json.BaseDTO;

public class WebAAreaLazyDTO extends BaseDTO<AgentArea> {
	private String title;
	private String key;
	private boolean icon;

	@Override
	public BaseDTO<AgentArea> convert(AgentArea domain) {
		WebAAreaLazyDTO dto = new WebAAreaLazyDTO();
		if (domain == null) {
			return dto;
		}
		dto.setKey("agentArea-" + domain.getId());
		dto.setTitle(domain.getDesc());
		dto.setIcon(domain.getIsClosed());
		return dto;
	}
	
	public boolean isIcon() {
		return icon;
	}

	public void setIcon(boolean icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
