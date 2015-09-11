package com.nirvana.erp.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PepsiOrderLineId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ORDER_NO")
	private PepsiOrderHead head;

	@Column(name = "LINE_NO", nullable = false, length = 16)
	private String lineNo;

	public PepsiOrderLineId() {
	}

	public PepsiOrderHead getHead() {
		return head;
	}

	public void setHead(PepsiOrderHead head) {
		this.head = head;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PepsiOrderLineId))
			return false;
		PepsiOrderLineId castOther = (PepsiOrderLineId) other;

		return ((this.getHead().getOrderNo() == castOther.getHead().getOrderNo()) || (this.getHead().getOrderNo() != null && castOther.getHead().getOrderNo() != null && this
				.getHead().getOrderNo().equals(castOther.getHead().getOrderNo())))
				&& ((this.getLineNo() == castOther.getLineNo()) || (this.getLineNo() != null && castOther.getLineNo() != null && this.getLineNo().equals(castOther.getLineNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getHead().getOrderNo() == null ? 0 : this.getHead().getOrderNo().hashCode());
		result = 37 * result + (getLineNo() == null ? 0 : this.getLineNo().hashCode());
		return result;
	}

}