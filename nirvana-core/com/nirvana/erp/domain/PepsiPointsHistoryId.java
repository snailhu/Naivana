package com.nirvana.erp.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NjpepsiAppPointsHistoryId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PepsiPointsHistoryId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerNo;
	private String dateEntered;
	private String orderNo;
	private String chargeType;
	private String chargeDescription;
	private BigDecimal chargedQty;
	private BigDecimal chargedAmount;
	private String chargeCollect;
	private String state;
	private String qtyReturned;

	// Constructors

	/** default constructor */
	public PepsiPointsHistoryId() {
	}

	/** full constructor */
	public PepsiPointsHistoryId(String customerNo, String dateEntered, String orderNo, String chargeType, String chargeDescription, BigDecimal chargedQty,
			BigDecimal chargedAmount, String chargeCollect, String state, String qtyReturned) {
		this.customerNo = customerNo;
		this.dateEntered = dateEntered;
		this.orderNo = orderNo;
		this.chargeType = chargeType;
		this.chargeDescription = chargeDescription;
		this.chargedQty = chargedQty;
		this.chargedAmount = chargedAmount;
		this.chargeCollect = chargeCollect;
		this.state = state;
		this.qtyReturned = qtyReturned;
	}

	// Property accessors

	@Column(name = "CUSTOMER_NO", length = 80)
	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	@Column(name = "DATE_ENTERED", length = 32)
	public String getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(String dateEntered) {
		this.dateEntered = dateEntered;
	}

	@Column(name = "ORDER_NO", length = 160)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "CHARGE_TYPE", length = 100)
	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	@Column(name = "CHARGE_DESCRIPTION", length = 4000)
	public String getChargeDescription() {
		return this.chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}

	@Column(name = "CHARGED_QTY", precision = 22, scale = 0)
	public BigDecimal getChargedQty() {
		return this.chargedQty;
	}

	public void setChargedQty(BigDecimal chargedQty) {
		this.chargedQty = chargedQty;
	}

	@Column(name = "CHARGED_AMOUNT", precision = 22, scale = 0)
	public BigDecimal getChargedAmount() {
		return this.chargedAmount;
	}

	public void setChargedAmount(BigDecimal chargedAmount) {
		this.chargedAmount = chargedAmount;
	}

	@Column(name = "CHARGE_COLLECT", length = 4000)
	public String getChargeCollect() {
		return this.chargeCollect;
	}

	public void setChargeCollect(String chargeCollect) {
		this.chargeCollect = chargeCollect;
	}

	@Column(name = "STATE", length = 4000)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "QTY_RETURNED", length = 160)
	public String getQtyReturned() {
		return this.qtyReturned;
	}

	public void setQtyReturned(String qtyReturned) {
		this.qtyReturned = qtyReturned;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PepsiPointsHistoryId))
			return false;
		PepsiPointsHistoryId castOther = (PepsiPointsHistoryId) other;

		return ((this.getCustomerNo() == castOther.getCustomerNo()) || (this.getCustomerNo() != null && castOther.getCustomerNo() != null && this.getCustomerNo().equals(
				castOther.getCustomerNo())))
				&& ((this.getDateEntered() == castOther.getDateEntered()) || (this.getDateEntered() != null && castOther.getDateEntered() != null && this.getDateEntered()
						.equals(castOther.getDateEntered())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null && castOther.getOrderNo() != null && this.getOrderNo().equals(
						castOther.getOrderNo())))
				&& ((this.getChargeType() == castOther.getChargeType()) || (this.getChargeType() != null && castOther.getChargeType() != null && this.getChargeType().equals(
						castOther.getChargeType())))
				&& ((this.getChargeDescription() == castOther.getChargeDescription()) || (this.getChargeDescription() != null && castOther.getChargeDescription() != null && this
						.getChargeDescription().equals(castOther.getChargeDescription())))
				&& ((this.getChargedQty() == castOther.getChargedQty()) || (this.getChargedQty() != null && castOther.getChargedQty() != null && this.getChargedQty().equals(
						castOther.getChargedQty())))
				&& ((this.getChargedAmount() == castOther.getChargedAmount()) || (this.getChargedAmount() != null && castOther.getChargedAmount() != null && this
						.getChargedAmount().equals(castOther.getChargedAmount())))
				&& ((this.getChargeCollect() == castOther.getChargeCollect()) || (this.getChargeCollect() != null && castOther.getChargeCollect() != null && this
						.getChargeCollect().equals(castOther.getChargeCollect())))
				&& ((this.getState() == castOther.getState()) || (this.getState() != null && castOther.getState() != null && this.getState().equals(castOther.getState())))
				&& ((this.getQtyReturned() == castOther.getQtyReturned()) || (this.getQtyReturned() != null && castOther.getQtyReturned() != null && this.getQtyReturned()
						.equals(castOther.getQtyReturned())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCustomerNo() == null ? 0 : this.getCustomerNo().hashCode());
		result = 37 * result + (getDateEntered() == null ? 0 : this.getDateEntered().hashCode());
		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getChargeType() == null ? 0 : this.getChargeType().hashCode());
		result = 37 * result + (getChargeDescription() == null ? 0 : this.getChargeDescription().hashCode());
		result = 37 * result + (getChargedQty() == null ? 0 : this.getChargedQty().hashCode());
		result = 37 * result + (getChargedAmount() == null ? 0 : this.getChargedAmount().hashCode());
		result = 37 * result + (getChargeCollect() == null ? 0 : this.getChargeCollect().hashCode());
		result = 37 * result + (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result + (getQtyReturned() == null ? 0 : this.getQtyReturned().hashCode());
		return result;
	}

}