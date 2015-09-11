package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;

/**
 * �ݷ�·�߽ڵ�ʵ�塣
 * 
 * */

@Entity
@Table(name = "backend_visitroutenode")
public class VisitRouteNode {

	/** ID */
	@EmbeddedId
	private VisitRouteNodePK pk;

	/** �ݷýڵ����� */
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private VisitNodeType type;

	/** �������ŵ� */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	public VisitRouteNode() {
	}

	public VisitRouteNodePK getPk() {
		return pk;
	}

	public void setPk(VisitRouteNodePK pk) {
		this.pk = pk;
	}

	public VisitNodeType getType() {
		return type;
	}

	public void setType(VisitNodeType type) {
		this.type = type;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}
