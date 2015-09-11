package com.nirvana.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ��Ʒʵ�塣
 * 
 * */
@Entity
@Table(name = "common_product")
public class Product {

	/** ��Ʒ���� */
	@Id
	@Column(length = 20)
	private String code;

	/** ��Ʒ���� */
	@Column(length = 100, nullable = false)
	private String description;

	/** ��Ʒ���� */
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	/** ��Ʒ��װ */
	@Column(length = 100, nullable = false)
	private String commodity;

	/** ������ת��ϵ������������ͳ�Ʊ����ã� */
	@Column(length = 100, nullable = false)
	private Float trsptConv;

	/** ����ת��ϵ�������ڱ�׼��ͳ�Ʊ����ã� */
	@Column(length = 100, nullable = false)
	private Float standardConv;

	/** ��Ʒ�汾 */
	@Column(length = 40, nullable = false)
	private String version;

	public Product() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public Float getTrsptConv() {
		return trsptConv;
	}

	public void setTrsptConv(Float trsptConv) {
		this.trsptConv = trsptConv;
	}

	public Float getStandardConv() {
		return standardConv;
	}

	public void setStandardConv(Float standardConv) {
		this.standardConv = standardConv;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
