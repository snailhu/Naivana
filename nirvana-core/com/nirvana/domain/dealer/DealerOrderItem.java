package com.nirvana.domain.dealer;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.common.Product;

@Entity
@Table(name = "dealer_dealerorderitem")
public class DealerOrderItem {

	/** �������� */
	@EmbeddedId
	private DealerOrderItemPK pk;

	/** ��������Ʒ */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_code")
	private Product product;

	/** ���� */
	private Float unitPrice;

	/** ���� */
	private Integer amount;

	/** ��λ */
	@Column(length = 20)
	private String unitMeas;

	/** �ۿ۰ٷֱ� */
	private Float discount;

	/** ���ֽ�� */
	private Integer points;

	/** �������� */
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private DealerOrderItemType type;

	public DealerOrderItem() {
	}

	public DealerOrderItemPK getPk() {
		return pk;
	}

	public void setPk(DealerOrderItemPK pk) {
		this.pk = pk;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitMeas() {
		return unitMeas;
	}

	public void setUnitMeas(String unitMeas) {
		this.unitMeas = unitMeas;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public DealerOrderItemType getType() {
		return type;
	}

	public void setType(DealerOrderItemType type) {
		this.type = type;
	}

}
