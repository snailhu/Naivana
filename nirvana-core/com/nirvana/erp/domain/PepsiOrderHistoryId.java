package com.nirvana.erp.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PepsiOrderHistoryId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private Timestamp updateDate;

	public PepsiOrderHistoryId() {
	}

	public PepsiOrderHistoryId(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "ORDER_NO", nullable = false, length = 12)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "UPDATE_DATE", length = 7)
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PepsiOrderHistoryId))
			return false;
		PepsiOrderHistoryId castOther = (PepsiOrderHistoryId) other;

		return ((this.getOrderNo() == castOther.getOrderNo()) || (this.getOrderNo() != null && castOther.getOrderNo() != null && this.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this.getUpdateDate() != null && castOther.getUpdateDate() != null && this.getUpdateDate().equals(
						castOther.getUpdateDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result + (getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode());
		return result;
	}

}