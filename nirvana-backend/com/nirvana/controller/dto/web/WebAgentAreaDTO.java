/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年2月14日 下午5:08:42
 */
package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.pojo4json.BaseDTO;

public class WebAgentAreaDTO extends BaseDTO<AgentArea> {
	private Integer id;

	private String areaName = "";

	private Long agentId;

	private String realName = "";

	private String areaCode = "";

	private String ecode = "";

	private boolean isClosed;

	private String phone = "";

	private String position = "";
	private String positionName = "";

	private String workType = "";

	@Override
	public BaseDTO<AgentArea> convert(AgentArea domain) {
		WebAgentAreaDTO dto = new WebAgentAreaDTO();
		if (domain == null)
			return dto;
		dto.setPosition(domain.getAgent().getType().name());
		dto.setPositionName(domain.getAgent().getType().getName());
		dto.setId(domain.getId());
		dto.setAreaName(domain.getDesc());
		dto.setClosed(domain.getIsClosed());
		dto.setAreaCode(domain.getAreaCode());
		dto.setWorkType(domain.getType().name());
		if (domain.getAgent().getEmployee() != null) {
			dto.setAgentId(domain.getAgent().getEmployee().getId());
			dto.setRealName(domain.getAgent().getEmployee().getName());
			dto.setPhone(domain.getAgent().getEmployee().getUser().getPhone());
			dto.setEcode(domain.getAgent().getEmployee().getECode());
		}
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer agentAreaId) {
		this.id = agentAreaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

}
