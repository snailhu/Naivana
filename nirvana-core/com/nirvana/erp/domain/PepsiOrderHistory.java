package com.nirvana.erp.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NJPEPSI_APP_ORDER_HEAD_T", schema = "IFSAPP")
public class PepsiOrderHistory implements java.io.Serializable {

	private PepsiOrderHistoryId id;

	public PepsiOrderHistory() {
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "orderNo", column = @Column(name = "ORDER_NO", nullable = false, length = 12)),
			@AttributeOverride(name = "updateDate", column = @Column(name = "UPDATE_DATE", length = 7)) })
	public PepsiOrderHistoryId getId() {
		return this.id;
	}

	public void setId(PepsiOrderHistoryId id) {
		this.id = id;
	}

}