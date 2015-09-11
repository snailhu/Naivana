package com.nirvana.domain.dealer;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 经销商库存模块。
 * 
 * */
@Entity
@Table(name = "dealer_inventory")
public class Inventory {

	/** 联合主键ID */
	@EmbeddedId
	private InventPK pk;

	/** 价格 */
	@Column(nullable = false)
	private Double price;

	/** 库存量 */
	@Column(nullable = false)
	private Integer amounts;

	/** 销量 */
	@Column(nullable = false)
	private Integer salesVol;

	@Version
	private Integer version;

	public InventPK getPk() {
		return pk;
	}

	public void setPk(InventPK pk) {
		this.pk = pk;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
