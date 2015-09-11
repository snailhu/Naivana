package com.nirvana.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 商品实体。
 * 
 * */
@Entity
@Table(name = "common_product")
public class Product {

	/** 商品编码 */
	@Id
	@Column(length = 20)
	private String code;

	/** 商品描述 */
	@Column(length = 100, nullable = false)
	private String description;

	/** 产品分类 */
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	/** 产品包装 */
	@Column(length = 100, nullable = false)
	private String commodity;

	/** 运力箱转换系数（用于运力统计报表用） */
	@Column(length = 100, nullable = false)
	private Float trsptConv;

	/** 标箱转换系数（用于标准箱统计报表用） */
	@Column(length = 100, nullable = false)
	private Float standardConv;

	/** 产品版本 */
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
