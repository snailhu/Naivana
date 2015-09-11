package com.nirvana.domain.dealer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nirvana.domain.common.Product;

@Embeddable
public class InventPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4378204142736116704L;

	/** 关联的经销商 */
	@ManyToOne
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** 关联的商品信息 */
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public InventPK() {
	}

	public InventPK(Dealer dealer, Product product) {
		this.dealer = dealer;
		this.product = product;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
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
		result = prime * result + ((dealer.getId() == null) ? 0 : dealer.getId().hashCode()) + 3 * ((product.getCode() == null) ? 0 : product.getCode().hashCode());
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
		final InventPK other = (InventPK) obj;
		if (dealer == null) {
			if (other.dealer != null)
				return false;
		} else if (!dealer.equals(other.dealer)) {
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
