package com.nirvana.domain.dealer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.common.Product;

@Entity
@Table(name = "dealer_checkhistoryitem")
public class CheckHistoryItem {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** �̵����Ʒ */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_code")
	private Product product;

	/** �̵�ǰ���� */
	@Column(nullable = false)
	private Integer beforeAmount;

	/** �̵������ */
	@Column(nullable = false)
	private Integer afterAmount;

	/** �̵�ǰ�۸� */
	@Column(nullable = false)
	private Double beforePrice;

	/** �̵��۸� */
	@Column(nullable = false)
	private Double afterPrice;

	public CheckHistoryItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getBeforeAmount() {
		return beforeAmount;
	}

	public void setBeforeAmount(Integer beforeAmount) {
		this.beforeAmount = beforeAmount;
	}

	public Integer getAfterAmount() {
		return afterAmount;
	}

	public void setAfterAmount(Integer afterAmount) {
		this.afterAmount = afterAmount;
	}

	public Double getBeforePrice() {
		return beforePrice;
	}

	public void setBeforePrice(Double beforePrice) {
		this.beforePrice = beforePrice;
	}

	public Double getAfterPrice() {
		return afterPrice;
	}

	public void setAfterPrice(Double afterPrice) {
		this.afterPrice = afterPrice;
	}

}
