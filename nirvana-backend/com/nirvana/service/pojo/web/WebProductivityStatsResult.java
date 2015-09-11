package com.nirvana.service.pojo.web;

/**
 * 封装生产力统计结果。
 * */
public class WebProductivityStatsResult {

	// 业务员姓名
	private String agentName;

	// 大区名
	private String bigArea;

	// 经理区名
	private String manageArea;

	// 主任区名
	private String dirArea;

	// CR区名
	private String agentArea;

	// 服务客户数
	private Integer customerCount;

	// 累计工作日
	private Integer workDays;

	// 预访客户数
	private Integer preVisitCount;

	// 实际访问客户数
	private Integer visitCount;

	// 拜访完成率百分比
	private Float finishPercent;

	// 订货的客户数
	private Integer orderedCount;

	// 拜访成功率百分比
	private Float successPercent;

	// 订单销量
	private Integer totalBox;

	// 线内销量
	private Integer inlineBox;

	// 线外销量
	private Integer outlineBox;

	// 户均销量
	private Float averageBox;

	// 订货Sku数
	private Integer sku;

	// 户均订单Sku
	private Float averageSku;

	// 总拜访时间（单位秒）
	private Integer totalTime;

	// 平均拜访时间（单位秒）
	private Integer averageTime;

	public WebProductivityStatsResult() {
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getBigArea() {
		return bigArea;
	}

	public void setBigArea(String bigArea) {
		this.bigArea = bigArea;
	}

	public String getManageArea() {
		return manageArea;
	}

	public void setManageArea(String manageArea) {
		this.manageArea = manageArea;
	}

	public String getDirArea() {
		return dirArea;
	}

	public void setDirArea(String dirArea) {
		this.dirArea = dirArea;
	}

	public String getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(String agentArea) {
		this.agentArea = agentArea;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public Integer getWorkDays() {
		return workDays;
	}

	public void setWorkDays(Integer workDays) {
		this.workDays = workDays;
	}

	public Integer getPreVisitCount() {
		return preVisitCount;
	}

	public void setPreVisitCount(Integer preVisitCount) {
		this.preVisitCount = preVisitCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Float getFinishPercent() {
		return finishPercent;
	}

	public void setFinishPercent(Float finishPercent) {
		this.finishPercent = finishPercent;
	}

	public Integer getOrderedCount() {
		return orderedCount;
	}

	public void setOrderedCount(Integer orderedCount) {
		this.orderedCount = orderedCount;
	}

	public Float getSuccessPercent() {
		return successPercent;
	}

	public void setSuccessPercent(Float successPercent) {
		this.successPercent = successPercent;
	}

	public Integer getTotalBox() {
		return totalBox;
	}

	public void setTotalBox(Integer totalBox) {
		this.totalBox = totalBox;
	}

	public Integer getInlineBox() {
		return inlineBox;
	}

	public void setInlineBox(Integer inlineBox) {
		this.inlineBox = inlineBox;
	}

	public Integer getOutlineBox() {
		return outlineBox;
	}

	public void setOutlineBox(Integer outlineBox) {
		this.outlineBox = outlineBox;
	}

	public Float getAverageBox() {
		return averageBox;
	}

	public void setAverageBox(Float averageBox) {
		this.averageBox = averageBox;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public Float getAverageSku() {
		return averageSku;
	}

	public void setAverageSku(Float averageSku) {
		this.averageSku = averageSku;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(Integer averageTime) {
		this.averageTime = averageTime;
	}

}
