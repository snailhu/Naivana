package com.nirvana.service.pojo.app;

/**
 * ��װ������ͳ�ƽ����
 * */
public class AppProductivityStatsResult {

	// today

	// Ԥ�ÿͻ�����
	private Integer tdpreVisitCount=10;

	// ʵ�ÿͻ�����
	private Integer tdvisitCount=9;

	// �ݷ�����ʡ�
	private Float tdvisitPercent=0.9f;

	// �ݷóɹ��ʡ�
	private Float tdsuccessPercent=0.8f;

	// ƽ������������
	private Float tdaverageNr=6f;

	// ƽ������SKU��
	private Float tdaverageSku=6f;

	// ƽ���ݷ�ʱ�䡣
	private Integer tdaverageVisitTime=50;

	// this month

	// ����Ԥ�ÿͻ�����
	private Integer tmpreVisitCount=40;

	// �����ۼ�ʵ�ÿͻ�����
	private Integer tmvisitCount=30;

	// �����ۼưݷ�����ʡ�
	private Integer tmvisitPercent=70;

	// ���°ݷóɹ��ʡ�
	private Integer tmsuccessPercent=90;

	public AppProductivityStatsResult() {
	}

	public Integer getTdpreVisitCount() {
		return tdpreVisitCount;
	}

	public void setTdpreVisitCount(Integer tdpreVisitCount) {
		this.tdpreVisitCount = tdpreVisitCount;
	}

	public Integer getTdvisitCount() {
		return tdvisitCount;
	}

	public void setTdvisitCount(Integer tdvisitCount) {
		this.tdvisitCount = tdvisitCount;
	}

	public Float getTdvisitPercent() {
		return tdvisitPercent;
	}

	public void setTdvisitPercent(Float tdvisitPercent) {
		this.tdvisitPercent = tdvisitPercent;
	}

	public Float getTdsuccessPercent() {
		return tdsuccessPercent;
	}

	public void setTdsuccessPercent(Float tdsuccessPercent) {
		this.tdsuccessPercent = tdsuccessPercent;
	}

	public Float getTdaverageNr() {
		return tdaverageNr;
	}

	public void setTdaverageNr(Float tdaverageNr) {
		this.tdaverageNr = tdaverageNr;
	}

	public Float getTdaverageSku() {
		return tdaverageSku;
	}

	public void setTdaverageSku(Float tdaverageSku) {
		this.tdaverageSku = tdaverageSku;
	}

	public Integer getTdaverageVisitTime() {
		return tdaverageVisitTime;
	}

	public void setTdaverageVisitTime(Integer tdaverageVisitTime) {
		this.tdaverageVisitTime = tdaverageVisitTime;
	}

	public Integer getTmpreVisitCount() {
		return tmpreVisitCount;
	}

	public void setTmpreVisitCount(Integer tmpreVisitCount) {
		this.tmpreVisitCount = tmpreVisitCount;
	}

	public Integer getTmvisitCount() {
		return tmvisitCount;
	}

	public void setTmvisitCount(Integer tmvisitCount) {
		this.tmvisitCount = tmvisitCount;
	}

	public Integer getTmvisitPercent() {
		return tmvisitPercent;
	}

	public void setTmvisitPercent(Integer tmvisitPercent) {
		this.tmvisitPercent = tmvisitPercent;
	}

	public Integer getTmsuccessPercent() {
		return tmsuccessPercent;
	}

	public void setTmsuccessPercent(Integer tmsuccessPercent) {
		this.tmsuccessPercent = tmsuccessPercent;
	}

}
