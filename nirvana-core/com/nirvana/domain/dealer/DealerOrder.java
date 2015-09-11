package com.nirvana.domain.dealer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 经销商的订单实体。
 * 
 * */
@Entity
@Table(name = "dealer_dealerorder")
public class DealerOrder {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 订单号，例子：20140709000001005 */
	@Column(nullable = false, unique = true)
	private Long orderNo;

	/** ERP中订单编号。 */
	@Column(length = 30, nullable = false, unique = true)
	private String codeInERP;

	/** 关联的经销商 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** 订单创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	/** 下单时间。 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date enterDate;

	/** 希望配送时间。 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date wantDate;

	/** 订单完成时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;

	/** 包含的订单项 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Set<DealerOrderItem> items;

	/** 订单阶段 */
	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	private DealerOrderState state = DealerOrderState.PLANNED;

	/** 门店签字 */
	@Column(nullable = false, length = 200)
	private String signPic;

	/** 总价 */
	@Column(precision = 10, scale = 2)
	private BigDecimal totalPrice;

	/** 是否为业务员帮助下单 */
	@Column(nullable = false)
	private Boolean isAgentHelped = false;

	/** 是否为线内 */
	private Boolean isInLine = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Set<DealerOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<DealerOrderItem> items) {
		this.items = items;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getWantDate() {
		return wantDate;
	}

	public void setWantDate(Date wantDate) {
		this.wantDate = wantDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public DealerOrderState getState() {
		return state;
	}

	public void setState(DealerOrderState state) {
		this.state = state;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSignPic() {
		return signPic;
	}

	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}

	public Boolean getIsAgentHelped() {
		return isAgentHelped;
	}

	public void setIsAgentHelped(Boolean isAgentHelped) {
		this.isAgentHelped = isAgentHelped;
	}

	public Boolean getIsInLine() {
		return isInLine;
	}

	public void setIsInLine(Boolean isInLine) {
		this.isInLine = isInLine;
	}

}
