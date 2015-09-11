package com.nirvana.domain.dealer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dealer_promotiongoods")
public class PromotionGoods {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 商品信息 */
	@Column(length = 22, nullable = false)
	private String goods;

	/** 所属的促销信息 */
	@ManyToOne
	@JoinColumn(name = "promotion_id")
	private DealerPromotion promotion;

	public PromotionGoods() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public DealerPromotion getPromotion() {
		return promotion;
	}

	public void setPromotion(DealerPromotion promotion) {
		this.promotion = promotion;
	}

}
