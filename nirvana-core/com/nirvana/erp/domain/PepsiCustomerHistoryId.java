package com.nirvana.erp.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PepsiCustomerHistoryId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerNo;
	private Timestamp updateDate;

	public PepsiCustomerHistoryId() {
	}

	public PepsiCustomerHistoryId(String customerNo) {
		this.customerNo = customerNo;
	}

	@Column(name = "CUSTOMER_NO", nullable = false, length = 20)
	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
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
		if (!(other instanceof PepsiCustomerHistoryId))
			return false;
		PepsiCustomerHistoryId castOther = (PepsiCustomerHistoryId) other;

		return ((this.getCustomerNo() == castOther.getCustomerNo()) || (this.getCustomerNo() != null && castOther.getCustomerNo() != null && this.getCustomerNo().equals(
				castOther.getCustomerNo())))
				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this.getUpdateDate() != null && castOther.getUpdateDate() != null && this.getUpdateDate().equals(
						castOther.getUpdateDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCustomerNo() == null ? 0 : this.getCustomerNo().hashCode());
		result = 37 * result + (getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode());
		return result;
	}

}