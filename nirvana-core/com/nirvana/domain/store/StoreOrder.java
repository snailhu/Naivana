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
 * �ŵ궩��ʵ�塣
 * 
 * */
@Entity
@Table(name = "store_storeorder")
public class StoreOrder {

	@Id
	@GeneratedValue
	private Long id;

	/** �������,ʮ��λ,����Ϊ��ʱ��+6λ�����̱��+5λ��������ˮ�ţ����ӣ�2014070800000100005 */
	@Column(nullable = false, unique = true)
	private Long orderNo;

	/** ���������� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** ����һ���ŵ� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "store_id")
	private Store store;

	/** �Ƿ�Ϊҵ��Ա�����µ� */
	@Column(nullable = false)
	private Boolean isAgentHelped = false;

	/** �Ƿ����ڶ��� */
	private Boolean isInLine = false;

	/** ��������ʱ�� */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	/** �������ʱ�䡣 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;

	/** ����״̬ */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StoreOrderState state;

	/** ��Ʒ�ܽ�� */
	@Column(nullable = false,precision=10,scale=2)
	private BigDecimal productTotalPrice = new BigDecimal(0);

	/** �����ܽ�� */
	@Column(nullable = false,precision=10,scale=2)
	private BigDecimal totalPrice = new BigDecimal(0);

	/** Ӧ����(ʵ����Ҫ֧���ķ���) */
	@Column(nullable = false,precision=10,scale=2)
	private BigDecimal payablefee = new BigDecimal(0);
	
	/** ������ */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Set<StoreOrderItem> items;

	/** �ŵ�ǩ�� */
	@Column(length = 200)
	private String signPic;

	/** ��Ʒ������� */
	private Integer product;

	/** ���� */
	private Integer delivery;

	/** ����̬�� */
	private Integer manner;

	/** ���� */
	@Column(length = 50)
	private String words;

	/** ��ע */
	@Column(length = 50)
	private String note;

	/** ��Ʒ */
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
