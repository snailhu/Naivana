package com.nirvana.domain.store;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nirvana.domain.common.Product;

/**
 * 门店订单的联合主键实体。
 * 
 * */
@Embeddable
public class StoreOrderItemPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1216789205312784021L;

	/** 关联的订单 */
	@ManyToOne
	@JoinColumn(name = "order_id")
	private StoreOrder order;

	/** 关联的商品 */
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public StoreOrderItemPK() {
	}

	public StoreOrderItemPK(StoreOrder order, Product product) {
		this.order = order;
		this.product = product;
	}

	public StoreOrder getOrder() {
		return order;
	}

	public void setOrder(StoreOrder order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order.getId() == null) ? 0 : order.getId().hashCode()) + 3 * ((product.getCode() == null) ? 0 : product.getCode().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StoreOrderItemPK other = (StoreOrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order)) {
			return false;
		}
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product)) {
			return false;
		}
		return true;
	}

}
