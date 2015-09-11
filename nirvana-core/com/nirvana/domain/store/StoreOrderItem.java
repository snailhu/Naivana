package com.nirvana.domain.store;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 门店订单项目实体。
 * 
 * */
@Entity
@Table(name = "store_storeorderitem")
public class StoreOrderItem {

	/** 联合主键 */
	@EmbeddedId
	private StoreOrderItemPK pk;

	/** 购买数量 */
	@Column(nullable = false)
	private Integer amount;

	/** 单价 */
	@Column(nullable = false)
	private Double unitPrice;

	/** 折扣信息描述 */
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
