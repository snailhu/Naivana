/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��4��7�� ����10:08:00
 */
package com.nirvana.controller.dto;


/**
 * @Title:StoreAddrDTO.java
 * @Description:
 * @Version:v1.0
 */
public class StoreAddrDTO {
	// TODO �����������
	/** ���������� */
	private String manager = "����������";
	/** �绰�ֻ����� */
	private String phoneNum = "�绰����";
	/** �ͻ���ַ */
	private String deliveryAddr = "�ͻ���ַ";
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
