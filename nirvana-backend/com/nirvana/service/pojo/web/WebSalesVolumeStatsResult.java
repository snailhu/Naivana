package com.nirvana.service.pojo.web;

/**
 * ��װ����ͳ�ƽ����
 * */
public class WebSalesVolumeStatsResult {

	// ҵ��Ա����
	private String agentName="name";

	// ������
	private String bigArae="b";

	// ��������
	private String manageArae="m";

	// ��������
	private String dirArae="d";

	// CR����
	private String agentArae="a";

	// ��λ
	private String position="posision";

	// Ŀ������
	private Float salesGoal=100f;

	// ʵ������
	private Float salesVol=90f;

	// ��ɰٷֱ�
	private Float finishPercent=0.9f;

	public WebSalesVolumeStatsResult() {
	}

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

	public String getManageArae() {
		return manageArae;
	}

	public void setManageArae(String manageArae) {
		this.manageArae = manageArae;
	}

	public Float getFinishPercent() {
		return finishPercent;
	}

	public void setFinishPercent(Float finishPercent) {
		this.finishPercent = finishPercent;
	}

	

}
