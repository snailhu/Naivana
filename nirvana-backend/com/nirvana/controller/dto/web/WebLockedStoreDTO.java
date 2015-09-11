package com.nirvana.controller.dto.web;

import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.BaseDTO;

public class WebLockedStoreDTO extends BaseDTO<Store> {

	private Long id;

	private String name = "";

	public WebLockedStoreDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public WebLockedStoreDTO convert(Store domain) {
		if (domain == null) {
			return null;
		}
		WebLockedStoreDTO dto = new WebLockedStoreDTO();
		if (domain.getStorefrontInfo() != null) {
			dto.setName(domain.getStorefrontInfo().getName());
		}

		dto.setId(domain.getId());

		return dto;
	}

}
