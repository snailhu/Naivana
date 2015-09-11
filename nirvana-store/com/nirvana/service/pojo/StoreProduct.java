/**
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年4月24日 上午11:41:32
 */
package com.nirvana.service.pojo;

/**
 * @Description
 * @Version 1.0
 */
public class StoreProduct {
	/** 商品条码 */
	private String prodeuctCode = "假code";

	/** 商品名称 */
	private String prodeuctName = "商品名";

	/** 规格 */
	private String prodeuctSpecifications = "规格";

	private String prodeuctUrl = "url";

	/** ID */
	private Integer brandId = 1;

	/** 品牌名称 */
	private String brandName = "品牌名";

	/** 价格 */
	private Float price = 11.2f;

	/** 库存量 */
	private Integer amounts = 1000;

	/** 销量 */
	private Integer salesVol = 100000;

	private String promotion = "促销";

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
