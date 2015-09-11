package com.nirvana.erp.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NJPEPSI_APP_ORDER_HEAD", schema = "IFSAPP")
public class PepsiOrderHead implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORDER_NO", nullable = false, length = 48)
	private String orderNo;

	@Column(name = "ORDER_TYPE", length = 12)
	private String orderType;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ENTERED", length = 7)
	private Date dateEntered;

	@Temporal(TemporalType.DATE)
	@Column(name = "WANTED_DELIVERY_DATE", length = 7)
	private Date wantedDeliveryDate;

	@Column(name = "CUSTOMER_NO", nullable = false, length = 80)
	private String customerNo;

	@Column(name = "STATE", length = 4000)
	private String state;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_NO")
	private Set<PepsiOrderLine> lines;

	public PepsiOrderHead() {
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getWantedDeliveryDate() {
		return this.wantedDeliveryDate;
	}

	public void setWantedDeliveryDate(Date wantedDeliveryDate) {
		this.wantedDeliveryDate = wantedDeliveryDate;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<PepsiOrderLine> getLines() {
		return lines;
	}

	public void setLines(Set<PepsiOrderLine> lines) {
		this.lines = lines;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PepsiOrderHead))
			return false;
		PepsiOrderHead castOther = (PepsiOrderHead) other;

		return ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null && castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		return result;
	}

}