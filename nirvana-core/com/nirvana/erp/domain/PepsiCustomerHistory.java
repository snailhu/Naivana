package com.nirvana.erp.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NJPEPSI_APP_CUSTOMER_T", schema = "IFSAPP")
public class PepsiCustomerHistory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PepsiCustomerHistoryId id;

	public PepsiCustomerHistory() {
	}

	/** full constructor */
	public PepsiCustomerHistory(PepsiCustomerHistoryId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "customerNo", column = @Column(name = "CUSTOMER_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "updateDate", column = @Column(name = "UPDATE_DATE", length = 7)) })
	public PepsiCustomerHistoryId getId() {
		return this.id;
	}

	public void setId(PepsiCustomerHistoryId id) {
		this.id = id;
	}

}