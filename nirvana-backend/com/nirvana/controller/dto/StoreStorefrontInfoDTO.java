/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��5��27�� ����9:01:46
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:StoreStorefrontInfoDTO.java
 * @Description:
 * @Version:v1.0
 */
public class StoreStorefrontInfoDTO extends BaseDTO<StoreStorefrontInfo>{
	/** ID */
	private Long id;

	/** ���� */
	private String name;

	/** ���������� */
	private String manager;

	/** �������� */
	private String postalCode;

	/** �绰�ֻ����� */
	private String phoneNum;

	/** ���� */
	private String email;

	/** ���� */
	private String faxNum;

	/** ��ַʡ����Ϣ */
//	private String pCity;

	/** ��ַ��ϸ��Ϣ */
//	private String addrDetail;

	/** �ͻ���ַ */
	private String deliveryAddr;

	/* (non-Javadoc)
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<StoreStorefrontInfo> convert(StoreStorefrontInfo domain) {
		StoreStorefrontInfoDTO dto=new StoreStorefrontInfoDTO();
		if(domain==null){
			return dto;
		}
//		dto.setAddrDetail(domain.getAddrDetail());
		dto.setDeliveryAddr(domain.getDeliveryAddr());
		dto.setEmail(domain.getEmail());
		dto.setFaxNum(domain.getFaxNum());
		dto.setId(domain.getId());
		dto.setManager(domain.getManager());
		dto.setName(domain.getName());
//		dto.setpCity(domain.getpCity());
		dto.setPhoneNum(domain.getPhoneNum());
		dto.setPostalCode(domain.getPostalCode());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

//	public String getpCity() {
//		return pCity;
//	}
//
//	public void setpCity(String pCity) {
//		this.pCity = pCity;
//	}
//
//	public String getAddrDetail() {
//		return addrDetail;
//	}
//
//	public void setAddrDetail(String addrDetail) {
//		this.addrDetail = addrDetail;
//	}

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}
}
