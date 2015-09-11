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
 * �����̵Ķ���ʵ�塣
 * 
 * */
@Entity
@Table(name = "dealer_dealerorder")
public class DealerOrder {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** �����ţ����ӣ�20140709000001005 */
	@Column(nullable = false, unique = true)
	private Long orderNo;

	/** ERP�ж�����š� */
	@Column(length = 30, nullable = false, unique = true)
	private String codeInERP;

	/** �����ľ����� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** ��������ʱ�� */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	/** �µ�ʱ�䡣 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date enterDate;

	/** ϣ������ʱ�䡣 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date wantDate;

	/** �������ʱ�� */
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;

	/** �����Ķ����� */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Set<DealerOrderItem> items;

	/** �����׶� */
	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	private DealerOrderState state = DealerOrderState.PLANNED;

	/** �ŵ�ǩ�� */
	@Column(nullable = false, length = 200)
	private String signPic;

	/** �ܼ� */
	@Column(precision = 10, scale = 2)
	private BigDecimal totalPrice;

	/** �Ƿ�Ϊҵ��Ա�����µ� */
	@Column(nullable = false)
	private Boolean isAgentHelped = false;

	/** �Ƿ�Ϊ���� */
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
