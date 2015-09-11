/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年4月7日 上午10:08:00
 */
package com.nirvana.controller.dto;


/**
 * @Title:StoreAddrDTO.java
 * @Description:
 * @Version:v1.0
 */
public class StoreAddrDTO {
	// TODO 待清除假数据
	/** 负责人姓名 */
	private String manager = "负责人姓名";
	/** 电话手机号码 */
	private String phoneNum = "电话号码";
	/** 送货地址 */
	private String deliveryAddr = "送货地址";
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
	
}
