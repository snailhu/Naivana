package com.nirvana.service.pojo.web;

/**
 * 封装的门店用户信息的pojo，用于新建用户。
 * 
 */
public class StoreUserData {

	// 用户名
	private String username;

	// 密码
	private String password;

	// ERP客户代码
	private String codeInERP;

	// 店主姓名
	private String manager;

	// 门店名称
	private String storeName;

	// 绑定的手机号
	private String phoneNum;

	// 送货地址
	private String deliveryAddr;

	public StoreUserData() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
