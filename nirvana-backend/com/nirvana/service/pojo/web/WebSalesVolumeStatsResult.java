package com.nirvana.service.pojo.web;

/**
 * 封装销量统计结果。
 * */
public class WebSalesVolumeStatsResult {

	// 业务员姓名
	private String agentName="name";

	// 大区名
	private String bigArae="b";

	// 经理区名
	private String manageArae="m";

	// 主任区名
	private String dirArae="d";

	// CR区名
	private String agentArae="a";

	// 岗位
	private String position="posision";

	// 目标销量
	private Float salesGoal=100f;

	// 实际销量
	private Float salesVol=90f;

	// 完成百分比
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
