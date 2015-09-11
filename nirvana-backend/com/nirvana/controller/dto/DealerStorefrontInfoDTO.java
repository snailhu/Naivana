/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月26日 下午9:10:14
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.dealer.DealerStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:DealerStorefrontInfoDTO.java
 * @Description:
 * @Version:v1.0
 */
public class DealerStorefrontInfoDTO extends BaseDTO<DealerStorefrontInfo> {
	/** ID */
	private Integer id;

	/** 店名 */
	private String name;

	/** 负责人姓名 */
	private String manager;

	/** 发票类型 */
	private String invoiceType;

	/** 联系方式 */
	private String contactType;

	/** 联系方式值 */
	private String contactValue;

	/** 销售模式 */
	private String custGrp;

	/** 送货仓库 */
	private String warehouse;

	/** 市 */
	private String city;

	/** 区/县 */
	private String country;

	/** 地址详细信息 */
	private String addr;

	private String addrDeatail;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<DealerStorefrontInfo> convert(DealerStorefrontInfo domain) {
		DealerStorefrontInfoDTO dto = new DealerStorefrontInfoDTO();
		if (domain == null) {
			return dto;
		}
		dto.setAddr(domain.getBusinessAddr());
		dto.setCity(domain.getCity());
		dto.setContactType(domain.getContactType());
		dto.setContactValue(domain.getContactValue());
		dto.setCountry(domain.getCountry());
		dto.setCustGrp(domain.getCustGrp());
		dto.setId(domain.getId());
		dto.setInvoiceType(domain.getInvoiceType());
		dto.setManager(domain.getManager());
		dto.setName(domain.getName());
		dto.setWarehouse(domain.getWarehouse());
		dto.setAddrDeatail(domain.getBusinessAddr());
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public String getCustGrp() {
		return custGrp;
	}

	public void setCustGrp(String custGrp) {
		this.custGrp = custGrp;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrDeatail() {
		return addrDeatail;
	}

	public void setAddrDeatail(String addrDeatail) {
		this.addrDeatail = addrDeatail;
	}
}
