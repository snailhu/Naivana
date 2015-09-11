package com.nirvana.domain.dealer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * �����̵ĵ�����Ϣʵ�塣
 * 
 * */
@Entity
@Table(name = "dealer_dealercontactinfo")
public class DealerStorefrontInfo {
	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** ���� */
	@Column(length = 40, nullable = false)
	private String name;

	/** ���������� */
	@Column(length = 20)
	private String manager;

	/** ��Ʊ���� */
	@Column(length = 40)
	private String invoiceType;

	/** ��ϵ��ʽ */
	@Column(length = 20)
	private String contactType;

	/** ��ϵ��ʽֵ */
	@Column(length = 20)
	private String contactValue;

	/** ����ģʽ */
	@Column(length = 40)
	private String custGrp;

	/** �ͻ��ֿ� */
	@Column(length = 40)
	private String warehouse;

	/** �������� */
	@Column(length = 20)
	private String postCode;

	/** ʡ/�� */
	@Column(length = 20)
	private String city;

	/** ��/�� */
	@Column(length = 20)
	private String country;

	/** ��/�� */
	@Column(length = 20)
	private String town;

	/** ע���ַ */
	@Column(length = 200)
	private String registerAddr;

	/** Ӫҵ��ַ */
	@Column(length = 200)
	private String businessAddr;

	public DealerStorefrontInfo() {
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getRegisterAddr() {
		return registerAddr;
	}

	public void setRegisterAddr(String registerAddr) {
		this.registerAddr = registerAddr;
	}

	public String getBusinessAddr() {
		return businessAddr;
	}

	public void setBusinessAddr(String businessAddr) {
		this.businessAddr = businessAddr;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
