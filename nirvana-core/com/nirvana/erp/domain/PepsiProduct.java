package com.nirvana.erp.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NJPEPSI_APP_PRODUCT", schema = "IFSAPP")
public class PepsiProduct implements java.io.Serializable {

	@Id
	@Column(name = "PART_NO", nullable = false, length = 100)
	private String partNo;

	@Column(name = "DESCRIPTION", nullable = false, length = 800)
	private String description;

	@Column(name = "UNIT_MEAS", nullable = false, length = 40)
	private String unitMeas;

	@Column(name = "PRODUCT_FAMILY", length = 4000)
	private String productFamily;

	@Column(name = "PRIME_COMMODITY", length = 4000)
	private String primeCommodity;

	@Column(name = "PEPSI_TRANSPORT_CONV", precision = 22, scale = 0)
	private BigDecimal pepsiTransportConv;

	@Column(name = "PEPSI_STANDARD_CONV", precision = 22, scale = 0)
	private BigDecimal pepsiStandardConv;

	@Column(name = "OBJVERSION", length = 4000)
	private String objversion;

	public PepsiProduct() {
	}

	public String getPartNo() {
		return this.partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitMeas() {
		return this.unitMeas;
	}

	public void setUnitMeas(String unitMeas) {
		this.unitMeas = unitMeas;
	}

	public String getProductFamily() {
		return this.productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	public String getPrimeCommodity() {
		return this.primeCommodity;
	}

	public void setPrimeCommodity(String primeCommodity) {
		this.primeCommodity = primeCommodity;
	}

	public BigDecimal getPepsiTransportConv() {
		return this.pepsiTransportConv;
	}

	public void setPepsiTransportConv(BigDecimal pepsiTransportConv) {
		this.pepsiTransportConv = pepsiTransportConv;
	}

	public BigDecimal getPepsiStandardConv() {
		return this.pepsiStandardConv;
	}

	public void setPepsiStandardConv(BigDecimal pepsiStandardConv) {
		this.pepsiStandardConv = pepsiStandardConv;
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
		if (!(other instanceof PepsiProduct))
			return false;
		PepsiProduct castOther = (PepsiProduct) other;

		return ((this.getPartNo() == castOther.getPartNo()) || (this.getPartNo() != null && castOther.getPartNo() != null && this.getPartNo().equals(castOther.getPartNo())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getPartNo() == null ? 0 : this.getPartNo().hashCode());
		return result;
	}

}