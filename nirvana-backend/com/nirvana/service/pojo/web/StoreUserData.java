package com.nirvana.service.pojo.web;

/**
 * ��װ���ŵ��û���Ϣ��pojo�������½��û���
 * 
 */
public class StoreUserData {

	// �û���
	private String username;

	// ����
	private String password;

	// ERP�ͻ�����
	private String codeInERP;

	// ��������
	private String manager;

	// �ŵ�����
	private String storeName;

	// �󶨵��ֻ���
	private String phoneNum;

	// �ͻ���ַ
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
