package com.nirvana.service.pojo.web;

/**
 * ��װ������ͳ�ƽ����
 * */
public class WebProductivityStatsResult {

	// ҵ��Ա����
	private String agentName;

	// ������
	private String bigArea;

	// ��������
	private String manageArea;

	// ��������
	private String dirArea;

	// CR����
	private String agentArea;

	// ����ͻ���
	private Integer customerCount;

	// �ۼƹ�����
	private Integer workDays;

	// Ԥ�ÿͻ���
	private Integer preVisitCount;

	// ʵ�ʷ��ʿͻ���
	private Integer visitCount;

	// �ݷ�����ʰٷֱ�
	private Float finishPercent;

	// �����Ŀͻ���
	private Integer orderedCount;

	// �ݷóɹ��ʰٷֱ�
	private Float successPercent;

	// ��������
	private Integer totalBox;

	// ��������
	private Integer inlineBox;

	// ��������
	private Integer outlineBox;

	// ��������
	private Float averageBox;

	// ����Sku��
	private Integer sku;

	// ��������Sku
	private Float averageSku;

	// �ܰݷ�ʱ�䣨��λ�룩
	private Integer totalTime;

	// ƽ���ݷ�ʱ�䣨��λ�룩
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
