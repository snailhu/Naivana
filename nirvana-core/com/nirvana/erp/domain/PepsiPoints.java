package com.nirvana.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NJPEPSI_APP_POINTS", schema = "IFSAPP")
public class PepsiPoints implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CUSTOMER_NO", nullable = false, length = 80)
	private String customerNo;

	@Column(name = "POINT_AVAILABLE", nullable = false, precision = 22, scale = 0)
	private BigDecimal pointAvailable;

	@Column(name = "POINT_CONSUMED", nullable = false, precision = 22, scale = 0)
	private BigDecimal pointConsumed;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_FROM", nullable = false, length = 7)
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_TO", nullable = false, length = 7)
	private Date dateTo;

	@Column(name = "MEMO", length = 800)
	private String memo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ENTERED", length = 7)
	private Date dateEntered;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_RELEASED", length = 7)
	private Date dateReleased;

	@Column(name = "STATE", length = 4000)
	private String state;

	@Column(name = "CHARGE_TYPE", nullable = false, length = 120)
	private String chargeType;

	public PepsiPoints() {
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public BigDecimal getPointAvailable() {
		return this.pointAvailable;
	}

	public void setPointAvailable(BigDecimal pointAvailable) {
		this.pointAvailable = pointAvailable;
	}

	public BigDecimal getPointConsumed() {
		return this.pointConsumed;
	}

	public void setPointConsumed(BigDecimal pointConsumed) {
		this.pointConsumed = pointConsumed;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getDateReleased() {
		return this.dateReleased;
	}

	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PepsiPoints))
			return false;
		PepsiPoints castOther = (PepsiPoints) other;

		return ((this.getCustomerNo() == castOther.getCustomerNo()) || (this.getCustomerNo() != null && castOther.getCustomerNo() != null && this.getCustomerNo().equals(
				castOther.getCustomerNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCustomerNo() == null ? 0 : this.getCustomerNo().hashCode());
		return result;
	}

}