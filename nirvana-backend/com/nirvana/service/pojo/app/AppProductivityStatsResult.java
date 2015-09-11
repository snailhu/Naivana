package com.nirvana.service.pojo.app;

/**
 * 封装生产力统计结果。
 * */
public class AppProductivityStatsResult {

	// today

	// 预访客户数。
	private Integer tdpreVisitCount=10;

	// 实访客户数。
	private Integer tdvisitCount=9;

	// 拜访完成率。
	private Float tdvisitPercent=0.9f;

	// 拜访成功率。
	private Float tdsuccessPercent=0.8f;

	// 平均订单销量。
	private Float tdaverageNr=6f;

	// 平均订单SKU。
	private Float tdaverageSku=6f;

	// 平均拜访时间。
	private Integer tdaverageVisitTime=50;

	// this month

	// 本月预访客户数。
	private Integer tmpreVisitCount=40;

	// 本月累计实访客户数。
	private Integer tmvisitCount=30;

	// 本月累计拜访完成率。
	private Integer tmvisitPercent=70;

	// 本月拜访成功率。
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
