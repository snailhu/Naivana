package com.nirvana.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NnClient {

	/** 地址ID */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**UM值*/
	@Column(length = 20)
	private String UM;
	
	/** TDM值*/
	@Column(length = 20)
	private String TDM;
	
	/** TDS值*/
	@Column(length = 20)
	private String TDS;
	
	/**小区编码*/
	@Column(length = 20)
	private String propertyCode;
	
	/** CR姓名*/
	@Column(length = 120)
	private String CRName;
	
	/**客户编码*/
	@Column(length = 120)
	private String clientCode;
	
	/**客户姓名*/
	@Column(length = 120)
	private String clientName;
	
	/**客户地址*/
	@Column(length = 1200)
	private String clitenAddress;
	
	/** 联系电话*/
	@Column(length = 20)
	private String phone;
	
	/**联系人*/
	@Column(length = 120)
	private String contactPeople;
	
	/**渠道编码*/
	@Column(length = 20)
	private String channelCode;
	
	/**渠道名*/
	@Column(length = 120)
	private String channelName;
	
	/**线路号*/
	@Column(length = 120)
	private String lineNum;
	
	/**关闭标记*/
	@Column(length = 20)
	private String isClosed;
	
	/**关闭日期*/
	@Column(length = 120)
	private String closeDay;
	
	/**开户日期*/
	@Column(length = 120)
	private String openDay;
	
	/**经销商编码*/
	@Column(length = 120)
	private String dealerCode;	
	
	/**经销商姓名*/
	@Column(length = 120)
	private String dealerName;
	
	/**ERP客户*/
	@Column(length = 120)
	private String ERPclient;
	
	/**客户类型*/
	@Column(length = 120)
	private String clientType;
	
	/**设备编码*/
	@Column(length = 120)
	private String deviceCode;
	
	/**其他值*/
	@Column(length =20)
	private String volue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUM() {
		return UM;
	}

	public void setUM(String uM) {
		UM = uM;
	}

	public String getTDM() {
		return TDM;
	}

	public void setTDM(String tDM) {
		TDM = tDM;
	}

	public String getTDS() {
		return TDS;
	}

	public void setTDS(String tDS) {
		TDS = tDS;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}
	

	public String getCRName() {
		return CRName;
	}

	public void setCRName(String cRName) {
		CRName = cRName;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClitenAddress() {
		return clitenAddress;
	}

	public void setClitenAddress(String clitenAddress) {
		this.clitenAddress = clitenAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactPeople() {
		return contactPeople;
	}

	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}

	public String getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}

	public String getOpenDay() {
		return openDay;
	}

	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getERPclient() {
		return ERPclient;
	}

	public void setERPclient(String eRPclient) {
		ERPclient = eRPclient;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getVolue() {
		return volue;
	}

	public void setVolue(String volue) {
		this.volue = volue;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
		
}
