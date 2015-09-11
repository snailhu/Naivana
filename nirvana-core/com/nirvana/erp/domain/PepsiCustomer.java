package com.nirvana.erp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NJPEPSI_APP_CUSTOMER", schema = "IFSAPP")
public class PepsiCustomer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CUSTOMER_NO", nullable = false, length = 80)
	private String customerId;

	@Column(name = "NAME", nullable = false, length = 400)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE", nullable = false, length = 7)
	private Date creationDate;

	@Column(name = "PEPSI_INVOICE_TYPE", length = 800)
	private String pepsiInvoiceType;

	@Column(name = "DISTRICT_CODE", nullable = false, length = 40)
	private String districtCode;

	@Column(name = "TDS", nullable = false, length = 40)
	private String tds;

	@Column(name = "TDM", length = 4000)
	private String tdm;

	@Column(name = "UM", length = 4000)
	private String um;

	@Column(name = "ADD1", length = 4000)
	private String add1;

	@Column(name = "COUNTY", length = 4000)
	private String county;

	@Column(name = "STATE", length = 4000)
	private String state;

	@Column(name = "ROUTE_ID", length = 4000)
	private String routeId;

	@Column(name = "CONTACT", length = 4000)
	private String contact;

	@Column(name = "METHOD_ID", length = 4000)
	private String methodId;

	@Column(name = "VALUE_V", length = 4000)
	private String valueV;

	@Column(name = "CUST_GRP", nullable = false, length = 40)
	private String custGrp;

	@Column(name = "MARKET_CODE", length = 40)
	private String marketCode;

	@Column(name = "DELIVERY_WAREHOUSE", length = 4000)
	private String deliveryWarehouse;

	@Column(name = "OBJVERSION", length = 4000)
	private String objversion;

	public PepsiCustomer() {
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getPepsiInvoiceType() {
		return this.pepsiInvoiceType;
	}

	public void setPepsiInvoiceType(String pepsiInvoiceType) {
		this.pepsiInvoiceType = pepsiInvoiceType;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getTds() {
		return this.tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getTdm() {
		return this.tdm;
	}

	public void setTdm(String tdm) {
		this.tdm = tdm;
	}

	public String getUm() {
		return this.um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public String getAdd1() {
		return this.add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRouteId() {
		return this.routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMethodId() {
		return this.methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getValueV() {
		return this.valueV;
	}

	public void setValueV(String valueV) {
		this.valueV = valueV;
	}

	public String getCustGrp() {
		return this.custGrp;
	}

	public void setCustGrp(String custGrp) {
		this.custGrp = custGrp;
	}

	public String getMarketCode() {
		return this.marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public String getDeliveryWarehouse() {
		return this.deliveryWarehouse;
	}

	public void setDeliveryWarehouse(String deliveryWarehouse) {
		this.deliveryWarehouse = deliveryWarehouse;
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
		if (!(other instanceof PepsiCustomer))
			return false;
		PepsiCustomer castOther = (PepsiCustomer) other;

		return ((this.getCustomerId() == castOther.getCustomerId()) || (this.getCustomerId() != null && castOther.getCustomerId() != null && this.getCustomerId().equals(
				castOther.getCustomerId())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getCustomerId() == null ? 0 : this.getCustomerId().hashCode());
		return result;
	}

}