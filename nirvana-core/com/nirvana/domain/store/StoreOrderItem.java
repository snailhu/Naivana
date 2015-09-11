package com.nirvana.domain.store;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * �ŵ궩����Ŀʵ�塣
 * 
 * */
@Entity
@Table(name = "store_storeorderitem")
public class StoreOrderItem {

	/** �������� */
	@EmbeddedId
	private StoreOrderItemPK pk;

	/** �������� */
	@Column(nullable = false)
	private Integer amount;

	/** ���� */
	@Column(nullable = false)
	private Double unitPrice;

	/** �ۿ���Ϣ���� */
	@Column(length = 180)
	private String discountDisc;

	public StoreOrderItem() {
	}

	public StoreOrderItemPK getPk() {
		return pk;
	}

	public void setPk(StoreOrderItemPK pk) {
		this.pk = pk;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDiscountDisc() {
		return discountDisc;
	}

	public void setDiscountDisc(String discountDisc) {
		this.discountDisc = discountDisc;
	}

}
