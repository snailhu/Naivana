/**
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��4��24�� ����11:41:32
 */
package com.nirvana.service.pojo;

/**
 * @Description
 * @Version 1.0
 */
public class StoreProduct {
	/** ��Ʒ���� */
	private String prodeuctCode = "��code";

	/** ��Ʒ���� */
	private String prodeuctName = "��Ʒ��";

	/** ��� */
	private String prodeuctSpecifications = "���";

	private String prodeuctUrl = "url";

	/** ID */
	private Integer brandId = 1;

	/** Ʒ������ */
	private String brandName = "Ʒ����";

	/** �۸� */
	private Float price = 11.2f;

	/** ����� */
	private Integer amounts = 1000;

	/** ���� */
	private Integer salesVol = 100000;

	private String promotion = "����";

	public String getProdeuctCode() {
		return prodeuctCode;
	}

	public void setProdeuctCode(String prodeuctCode) {
		this.prodeuctCode = prodeuctCode;
	}

	public String getProdeuctName() {
		return prodeuctName;
	}

	public void setProdeuctName(String prodeuctName) {
		this.prodeuctName = prodeuctName;
	}

	public String getProdeuctSpecifications() {
		return prodeuctSpecifications;
	}

	public void setProdeuctSpecifications(String prodeuctSpecifications) {
		this.prodeuctSpecifications = prodeuctSpecifications;
	}

	public String getProdeuctUrl() {
		return prodeuctUrl;
	}

	public void setProdeuctUrl(String prodeuctUrl) {
		this.prodeuctUrl = prodeuctUrl;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getAmounts() {
		return amounts;
	}

	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}

	public Integer getSalesVol() {
		return salesVol;
	}

	public void setSalesVol(Integer salesVol) {
		this.salesVol = salesVol;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

}
