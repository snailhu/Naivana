package com.nirvana.service.pojo.web;

public class DeviceInfoData {

	// 设备码
	private String deviceCode;

	// 位置
	private String dvcPosition;

	// 纯度
	private String purity;

	// 是否异常
	private boolean abnormal;

	// 异常描述
	private String note;

	public DeviceInfoData() {
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDvcPosition() {
		return dvcPosition;
	}

	public void setDvcPosition(String dvcPosition) {
		this.dvcPosition = dvcPosition;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public boolean isAbnormal() {
		return abnormal;
	}

	public void setAbnormal(boolean abnormal) {
		this.abnormal = abnormal;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
