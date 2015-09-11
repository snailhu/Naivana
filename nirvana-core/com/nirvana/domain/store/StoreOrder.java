package com.nirvana.domain.store;

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
import javax.persistence.Version;

import com.nirvana.domain.dealer.Dealer;

/**
 * 门店订单实体。
 * 
 * */
@Entity
@Table(name = "store_storeorder")
public class StoreOrder {

	@Id
	@GeneratedValue
	private Long id;

	/** 订单编号,十九位,定义为：时间+6位经销商编号+5位经销商流水号，例子：2014070800000100005 */
	@Column(nullable = false, unique = true)
	private Long orderNo;

	/** 所属经销商 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** 所属一阶门店 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "store_id")
	private Store store;

	/** 是否为业务员帮助下单 */
	@Column(nullable = false)
	private Boolean isAgentHelped = false;

	/** 是否线内订单 */
	private Boolean isInLine = false;

	/** 订单创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	/** 订单完成时间。 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;

	/** 订单状态 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StoreOrderState state;

	/** 商品总金额 */
	@Column(nullable = false,precision=10,scale=2)
	private BigDecimal productTotalPrice = new BigDecimal(0);

	/** 订单总金额 */
	@Column(nullable = false,precision=10,scale=2)
	private BigDecimal totalPrice = new BigDecimal(0);

	/** 应付款(实际需要支付的费用) */
	@Column(nullable = false,precision=10,scale=2)
	private BigDecimal payablefee = new BigDecimal(0);
	
	/** 订单项 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Set<StoreOrderItem> items;

	/** 门店签字 */
	@Column(length = 200)
	private String signPic;

	/** 商品描述相符 */
	private Integer product;

	/** 物流 */
	private Integer delivery;

	/** 服务态度 */
	private Integer manner;

	/** 留言 */
	@Column(length = 50)
	private String words;

	/** 备注 */
	@Column(length = 50)
	private String note;

	/** 赠品 */
	@Column(length = 300)
	private String rewards;

	@Version
	private Integer version;

	public StoreOrder() {
	}

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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
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

	public StoreOrderState getState() {
		return state;
	}

	public void setState(StoreOrderState state) {
		this.state = state;
	}

	public BigDecimal getProductTotalPrice() {
		return productTotalPrice;
	}

	public void setProductTotalPrice(BigDecimal productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPayablefee() {
		return payablefee;
	}

	public void setPayablefee(BigDecimal payablefee) {
		this.payablefee = payablefee;
	}

	public Set<StoreOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<StoreOrderItem> items) {
		this.items = items;
	}

	public String getSignPic() {
		return signPic;
	}

	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	public Integer getManner() {
		return manner;
	}

	public void setManner(Integer manner) {
		this.manner = manner;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		StoreOrder other = (StoreOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
