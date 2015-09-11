package com.nirvana.service.pojo.web;

//包装了门店基本信息的pojo
public class StoreInfoData {

	/** ERP中的客户编号 */
	private String codeInERP;

	/** 店名 */
	private String name;

	/** 负责人姓名 */
	private String manager;

	/** 邮政编码 */
	private String postalCode;

	/** 电话手机号码 */
	private String phoneNum;

	/** 邮箱 */
	private String email;

	/** 传真 */
	private String faxNum;

	/** 送货地址 */
	private String deliveryAddr;

	public StoreInfoData() {
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

}
