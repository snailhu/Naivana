/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��2��14�� ����5:14:01
 */
package com.nirvana.controller.dto.web;

import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.service.pojo.web.WebSalesVolumeStatsResult;

/**
 * @Title:WebSalesVolumeDTO.java
 * @Description:
 * @Version:v1.0
 */
public class WebSalesVolumeDTO extends BaseDTO<WebSalesVolumeStatsResult> {
	// ҵ��Ա����
	private String agentName;

	// ������
	private String bigArae;

	// ��������
	private String manageArae;

	// ��������
	private String dirArae;

	// CR����
	private String agentArae;

	// ��λ
	private String position;

	// Ŀ������
	private Float salesGoal = 0f;

	// ʵ������
	private Float salesVol = 0f;

	// ��ɰٷֱ�
	private Float finishPercent = 0f;

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getBigArae() {
		return bigArae;
	}

	public void setBigArae(String bigArae) {
		this.bigArae = bigArae;
	}

	public String getManageArae() {
		return manageArae;
	}

	public void setManageArae(String manageArae) {
		this.manageArae = manageArae;
	}

	public String getDirArae() {
		return dirArae;
	}

	public void setDirArae(String dirArae) {
		this.dirArae = dirArae;
	}

	public String getAgentArae() {
		return agentArae;
	}

	public void setAgentArae(String agentArae) {
		this.agentArae = agentArae;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Float getSalesGoal() {
		return salesGoal;
	}

	public void setSalesGoal(Float salesGoal) {
		this.salesGoal = salesGoal;
	}

	public Float getSalesVol() {
		return salesVol;
	}

	public void setSalesVol(Float salesVol) {
		this.salesVol = salesVol;
	}

	public Float getFinishPercent() {
		return finishPercent;
	}

	public void setFinishPercent(Float finishPercent) {
		this.finishPercent = finishPercent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<WebSalesVolumeStatsResult> convert(
			WebSalesVolumeStatsResult s) {
		WebSalesVolumeDTO svb = new WebSalesVolumeDTO();
		if (s == null)
			return svb;
		svb.setAgentArae(s.getAgentArae());
		svb.setAgentName(s.getAgentName());
		svb.setBigArae(s.getBigArae());
		svb.setDirArae(s.getDirArae());
		svb.setFinishPercent(s.getFinishPercent());
		svb.setManageArae(s.getManageArae());
		svb.setPosition(s.getPosition());
		svb.setSalesGoal(s.getSalesGoal());
		svb.setSalesVol(s.getSalesVol());
		return svb;
	}
}
