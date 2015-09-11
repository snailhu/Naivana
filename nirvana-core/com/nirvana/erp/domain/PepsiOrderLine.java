package com.nirvana.erp.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NJPEPSI_APP_ORDER_LINE", schema = "IFSAPP")
public class PepsiOrderLine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PepsiOrderLineId id;

	@Column(name = "PART_NO", nullable = false, length = 100)
	private String partNo;

	@Column(name = "SALE_UNIT_PRICE", precision = 22, scale = 0)
	private BigDecimal saleUnitPrice;

	@Column(name = "QTY", nullable = false, precision = 22, scale = 0)
	private BigDecimal qty;

	@Column(name = "SALES_UNIT_MEAS", nullable = false, length = 40)
	private String salesUnitMeas;

	@Column(name = "DISCOUNT", nullable = false, precision = 22, scale = 0)
	private BigDecimal discount;

	@Column(name = "POINTS_AMOUNT", precision = 22, scale = 0)
	private BigDecimal pointsAmount;

	@Column(name = "ORDER_TYPE", length = 8)
	private String orderType;

	public PepsiOrderLine() {
	}

	public PepsiOrderLineId getId() {
		return this.id;
	}

	public void setId(PepsiOrderLineId id) {
		this.id = id;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public BigDecimal getSaleUnitPrice() {
		return saleUnitPrice;
	}

	public void setSaleUnitPrice(BigDecimal saleUnitPrice) {
		this.saleUnitPrice = saleUnitPrice;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getSalesUnitMeas() {
		return salesUnitMeas;
	}

	public void setSalesUnitMeas(String salesUnitMeas) {
		this.salesUnitMeas = salesUnitMeas;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPointsAmount() {
		return pointsAmount;
	}

	public void setPointsAmount(BigDecimal pointsAmount) {
		this.pointsAmount = pointsAmount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}