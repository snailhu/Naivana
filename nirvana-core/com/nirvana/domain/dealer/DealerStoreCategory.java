package com.nirvana.domain.dealer;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nirvana.domain.store.Store;

/**
 * 经销商的门店分类。
 * 
 * */
@Entity
@Table(name = "dealer_dealerstorecategory")
public class DealerStoreCategory {

	public static final String UNDEFINED = "未分组";

	@Id
	@GeneratedValue
	private Long id;

	/** 关联的经销商 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** 分类名 */
	@Column(length = 30, nullable = false)
	private String categoryName;

	/** 包括的门店 */
	@OneToMany
	@JoinColumn(name = "dealercategory_id")
	private Set<Store> stores;

	@ManyToMany
	@JoinTable(name = "dealer_dealerpromotion_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "promotion_id"))
	private Set<DealerPromotion> promotions;

	public DealerStoreCategory() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

	public Set<DealerPromotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<DealerPromotion> promotions) {
		this.promotions = promotions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		final DealerStoreCategory other = (DealerStoreCategory) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
}
