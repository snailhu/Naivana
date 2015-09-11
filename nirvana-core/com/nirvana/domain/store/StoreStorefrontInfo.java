package com.nirvana.domain.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store_storefrontinfo")
public class StoreStorefrontInfo {
	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** ���� */
	@Column(length = 20)
	private String name;

	/** ���������� */
	@Column(length = 20)
	private String manager;

	/** �������� */
	@Column(length = 20)
	private String postalCode;

	/** �绰�ֻ����� */
	@Column(length = 20)
	private String phoneNum;

	/** ���� */
	@Column(length = 20)
	private String email;

	/** ���� */
	@Column(length = 20)
	private String faxNum;

	/** �ͻ���ַ */
	@Column(length = 40)
	private String deliveryAddr;

	public StoreStorefrontInfo() {
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

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

}
