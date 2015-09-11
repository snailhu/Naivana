package com.nirvana.domain.dealer;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nirvana.domain.common.Product;

/**
 * ������Ϣʵ���ࡣ
 * 
 */
@Entity
@Table(name = "dealer_promotion")
public class DealerPromotion {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** �ɴ˾����̷��� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** ���� */
	@Column(length = 30, nullable = false)
	private String title;

	/** ����ʱ�� */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	/** �༭���� */
	@Temporal(TemporalType.TIMESTAMP)
	private Date editDate;

	/** ��ʼ���� */
	@Column(nullable = false)
	private Integer beginDate;

	/** �������� */
	@Column(nullable = false)
	private Integer endDate;

	/** �Ƿ��Ѿ���Ч */
	@Column(nullable = false)
	private Boolean isEffect = false;

	/** ����Ŀ���̵���� */
	@ManyToMany
	@JoinTable(name = "dealer_dealerpromotion_category", joinColumns = @JoinColumn(name = "promotion_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<DealerStoreCategory> categories;

	/** ����Ŀ��Ʒ���б� */
	@ManyToMany
	@JoinTable(name = "dealer_dealerpromotion_product", joinColumns = @JoinColumn(name = "promotion_id"), inverseJoinColumns = @JoinColumn(name = "product_code"))
	private Set<Product> products;

	/** �Ż����� */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PromCondition pCondition;

	/** �������� */
	@Column(nullable = false)
	private Integer cdtParam;

	/** �Żݷ�ʽ */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PromWay way;

	/** ��ʽ���� */
	@Column(nullable = false)
	private Float wayParam;

	/** �Ż���Ʒ */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "promotion_id")
	private Set<PromotionGoods> goods;

	public DealerPromotion() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public Integer getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Integer beginDate) {
		this.beginDate = beginDate;
	}

	public Integer getEndDate() {
		return endDate;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Boolean isEffect) {
		this.isEffect = isEffect;
	}

	public Set<DealerStoreCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<DealerStoreCategory> categories) {
		this.categories = categories;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public PromCondition getpCondition() {
		return pCondition;
	}

	public void setpCondition(PromCondition pCondition) {
		this.pCondition = pCondition;
	}

	public Integer getCdtParam() {
		return cdtParam;
	}

	public void setCdtParam(Integer cdtParam) {
		this.cdtParam = cdtParam;
	}

	public PromWay getWay() {
		return way;
	}

	public void setWay(PromWay way) {
		this.way = way;
	}

	public Float getWayParam() {
		return wayParam;
	}

	public void setWayParam(Float wayParam) {
		this.wayParam = wayParam;
	}

	public Set<PromotionGoods> getGoods() {
		return goods;
	}

	public void setGoods(Set<PromotionGoods> goods) {
		this.goods = goods;
	}

}
