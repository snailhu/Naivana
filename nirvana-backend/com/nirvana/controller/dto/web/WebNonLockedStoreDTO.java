package com.nirvana.controller.dto.web;

import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.BaseDTO;

public class WebNonLockedStoreDTO extends BaseDTO<Store> {

	private Long id;

	private String name = "";

	private String addr = "";

	private String phoneNum = "";

	private String codeInERP = "";

	private Integer agentAreaId;

	public WebNonLockedStoreDTO() {
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public Integer getAgentAreaId() {
		return agentAreaId;
	}

	public void setAgentAreaId(Integer agentAreaId) {
		this.agentAreaId = agentAreaId;
	}

	@Override
	public WebNonLockedStoreDTO convert(Store domain) {
		if (domain == null) {
			return null;
		}
		WebNonLockedStoreDTO dto = new WebNonLockedStoreDTO();
		if (domain.getStorefrontInfo() != null) {
			dto.setAddr(domain.getStorefrontInfo().getDeliveryAddr());
			dto.setName(domain.getStorefrontInfo().getName());
			dto.setPhoneNum(domain.getStorefrontInfo().getPhoneNum());
		}
		if (domain.getAgentArea() != null) {
			dto.setAgentAreaId(domain.getAgentArea().getId());
		}
		dto.setCodeInERP(domain.getCodeInERP());
		dto.setId(domain.getId());

		return dto;
	}

}
