package com.nirvana.erp.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NJPEPSI_APP_POINTS_HISTORY", schema = "IFSAPP")
public class PepsiPointsHistory implements java.io.Serializable {

	private PepsiPointsHistoryId id;

	public PepsiPointsHistory() {
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "customerNo", column = @Column(name = "CUSTOMER_NO", length = 80)),
			@AttributeOverride(name = "dateEntered", column = @Column(name = "DATE_ENTERED", length = 32)),
			@AttributeOverride(name = "orderNo", column = @Column(name = "ORDER_NO", length = 160)),
			@AttributeOverride(name = "chargeType", column = @Column(name = "CHARGE_TYPE", length = 100)),
			@AttributeOverride(name = "chargeDescription", column = @Column(name = "CHARGE_DESCRIPTION", length = 4000)),
			@AttributeOverride(name = "chargedQty", column = @Column(name = "CHARGED_QTY", precision = 22, scale = 0)),
			@AttributeOverride(name = "chargedAmount", column = @Column(name = "CHARGED_AMOUNT", precision = 22, scale = 0)),
			@AttributeOverride(name = "chargeCollect", column = @Column(name = "CHARGE_COLLECT", length = 4000)),
			@AttributeOverride(name = "state", column = @Column(name = "STATE", length = 4000)),
			@AttributeOverride(name = "qtyReturned", column = @Column(name = "QTY_RETURNED", length = 160)) })
	public PepsiPointsHistoryId getId() {
		return this.id;
	}

	public void setId(PepsiPointsHistoryId id) {
		this.id = id;
	}

}