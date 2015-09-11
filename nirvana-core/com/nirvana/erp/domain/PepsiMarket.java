package com.nirvana.erp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NJPEPSI_APP_MARKET", schema = "IFSAPP")
public class PepsiMarket implements java.io.Serializable {

	@Id
	@Column(name = "MARKET_CODE", nullable = false, length = 40)
	private String marketCode;

	@Column(name = "DESCRIPTION", length = 140)
	private String description;

	@Column(name = "GROUP1", length = 400)
	private String group1;

	@Column(name = "OBJVERSION", length = 4000)
	private String objversion;

	public PepsiMarket() {
	}

	public String getMarketCode() {
		return this.marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroup1() {
		return this.group1;
	}

	public void setGroup1(String group1) {
		this.group1 = group1;
	}

	public String getObjversion() {
		return this.objversion;
	}

	public void setObjversion(String objversion) {
		this.objversion = objversion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PepsiMarket))
			return false;
		PepsiMarket castOther = (PepsiMarket) other;

		return ((this.getMarketCode() == castOther.getMarketCode()) || (this.getMarketCode() != null && castOther.getMarketCode() != null && this.getMarketCode().equals(
				castOther.getMarketCode())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getMarketCode() == null ? 0 : this.getMarketCode().hashCode());
		return result;
	}

}