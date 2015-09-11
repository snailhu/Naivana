package com.nirvana.service.pojo.app;

/**
 * 封装销量统计结果。
 * */
public class AppSalesVolumeStatsResult {

	// today

	// 本日目标销量
	private Integer tdsalesGoal = 0;

	// 本日实际销量
	private Integer tdsalesVol = 0;

	// 本日完成百分比
	private Float tdPercent = 0f;

	// 本月目标销量
	private Integer tmsalesGoal = 0;

	// 本月累计销量
	private Integer tmsalesVol = 0;

	// 本月累计完成百分比
	private Float tmPercent = 0f;

	public AppSalesVolumeStatsResult() {
	}

	public Integer getTdsalesGoal() {
		return tdsalesGoal;
	}

	public void setTdsalesGoal(Integer tdsalesGoal) {
		this.tdsalesGoal = tdsalesGoal;
	}

	public Integer getTdsalesVol() {
		return tdsalesVol;
	}

	public void setTdsalesVol(Integer tdsalesVol) {
		this.tdsalesVol = tdsalesVol;
	}

	public Float getTdPercent() {
		return tdPercent;
	}

	public void setTdPercent(Float tdPercent) {
		this.tdPercent = tdPercent;
	}

	public Integer getTmsalesGoal() {
		return tmsalesGoal;
	}

	public void setTmsalesGoal(Integer tmsalesGoal) {
		this.tmsalesGoal = tmsalesGoal;
	}

	public Integer getTmsalesVol() {
		return tmsalesVol;
	}

	public void setTmsalesVol(Integer tmsalesVol) {
		this.tmsalesVol = tmsalesVol;
	}

	public Float getTmPercent() {
		return tmPercent;
	}

	public void setTmPercent(Float tmPercent) {
		this.tmPercent = tmPercent;
	}

}
