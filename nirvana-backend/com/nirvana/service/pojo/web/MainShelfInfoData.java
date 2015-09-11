package com.nirvana.service.pojo.web;

public class MainShelfInfoData {

	/** 是否为人流方向第一顺位 */
	private boolean isFirst;

	/** 分销：CSD */
	private int distrCSD;

	/** 分销：NCB */
	private int distrNCB;

	/** 陈列:CSD */
	private int displCSD;

	/** 陈列:NCB */
	private int displNCB;

	public MainShelfInfoData() {
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public int getDistrCSD() {
		return distrCSD;
	}

	public void setDistrCSD(int distrCSD) {
		this.distrCSD = distrCSD;
	}

	public int getDistrNCB() {
		return distrNCB;
	}

	public void setDistrNCB(int distrNCB) {
		this.distrNCB = distrNCB;
	}

	public int getDisplCSD() {
		return displCSD;
	}

	public void setDisplCSD(int displCSD) {
		this.displCSD = displCSD;
	}

	public int getDisplNCB() {
		return displNCB;
	}

	public void setDisplNCB(int displNCB) {
		this.displNCB = displNCB;
	}

}
