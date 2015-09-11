package com.nirvana.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NnClient {

	/** ��ַID */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**UMֵ*/
	@Column(length = 20)
	private String UM;
	
	/** TDMֵ*/
	@Column(length = 20)
	private String TDM;
	
	/** TDSֵ*/
	@Column(length = 20)
	private String TDS;
	
	/**С������*/
	@Column(length = 20)
	private String propertyCode;
	
	/** CR����*/
	@Column(length = 120)
	private String CRName;
	
	/**�ͻ�����*/
	@Column(length = 120)
	private String clientCode;
	
	/**�ͻ�����*/
	@Column(length = 120)
	private String clientName;
	
	/**�ͻ���ַ*/
	@Column(length = 1200)
	private String clitenAddress;
	
	/** ��ϵ�绰*/
	@Column(length = 20)
	private String phone;
	
	/**��ϵ��*/
	@Column(length = 120)
	private String contactPeople;
	
	/**��������*/
	@Column(length = 20)
	private String channelCode;
	
	/**������*/
	@Column(length = 120)
	private String channelName;
	
	/**��·��*/
	@Column(length = 120)
	private String lineNum;
	
	/**�رձ��*/
	@Column(length = 20)
	private String isClosed;
	
	/**�ر�����*/
	@Column(length = 120)
	private String closeDay;
	
	/**��������*/
	@Column(length = 120)
	private String openDay;
	
	/**�����̱���*/
	@Column(length = 120)
	private String dealerCode;	
	
	/**����������*/
	@Column(length = 120)
	private String dealerName;
	
	/**ERP�ͻ�*/
	@Column(length = 120)
	private String ERPclient;
	
	/**�ͻ�����*/
	@Column(length = 120)
	private String clientType;
	
	/**�豸����*/
	@Column(length = 120)
	private String deviceCode;
	
	/**����ֵ*/
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
