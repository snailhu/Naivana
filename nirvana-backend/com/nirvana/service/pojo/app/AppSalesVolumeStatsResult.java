package com.nirvana.service.pojo.app;

/**
 * ��װ����ͳ�ƽ����
 * */
public class AppSalesVolumeStatsResult {

	// today

	// ����Ŀ������
	private Integer tdsalesGoal = 0;

	// ����ʵ������
	private Integer tdsalesVol = 0;

	// ������ɰٷֱ�
	private Float tdPercent = 0f;

	// ����Ŀ������
	private Integer tmsalesGoal = 0;

	// �����ۼ�����
	private Integer tmsalesVol = 0;

	// �����ۼ���ɰٷֱ�
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
