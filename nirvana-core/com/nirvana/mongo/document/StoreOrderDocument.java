package com.nirvana.mongo.document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nirvana.domain.store.StoreOrderState;

@Document(collection = "STORE_ORDER")
public class StoreOrderDocument {

	/** �������,ʮ��λ,����Ϊ��ʱ��+5λ�����̱��+5λ��������ˮ�ţ����ӣ�201407080000100005 */
	private Long id;

	/** ���������� */
	private Long dealerId;

	/** ����һ���ŵ� */
	private Long storeId;

	/** �Ƿ�Ϊҵ��Ա�����µ� */
	private Boolean isAgentHelped;

	/** ��������ʱ�� */
	private Date createDate;

	/** �������ʱ�䡣 */
	private Date finishDate;

	/** ����״̬ */
	private StoreOrderState state;

	/** ��Ʒ�ܽ�� */
	private BigDecimal productTotalPrice;

	/** �����ܽ�� */
	private BigDecimal totalPrice;

	/** Ӧ����(ʵ����Ҫ֧���ķ���) */
	private BigDecimal payablefee;

	/** ������ */
	private List<StoreOrderItemDocument> items;

	/** �ŵ�ǩ�� */
	private String signPic;

	/** ��Ʒ������� */
	private Integer product;

	/** ���� */
	private Integer delivery;

	/** ����̬�� */
	private Integer manner;

	/** ���� */
	private String words;

	/** ��ע */
	private String note;

	/** ��Ʒ */
	private String rewards;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Boolean getIsAgentHelped() {
		return isAgentHelped;
	}

	public void setIsAgentHelped(Boolean isAgentHelped) {
		this.isAgentHelped = isAgentHelped;
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

	public List<StoreOrderItemDocument> getItems() {
		return items;
	}

	public void setItems(List<StoreOrderItemDocument> items) {
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

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public static class StoreOrderItemDocument {

		private ProductDocument product;

		/** �������� */
		private Integer amount;

		/** ���� */
		private Double unitPrice;

		/** �ۿ���Ϣ���� */
		private String discountDisc;

		public ProductDocument getProduct() {
			return product;
		}

		public void setProduct(ProductDocument product) {
			this.product = product;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public Double getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(Double unitPrice) {
			this.unitPrice = unitPrice;
		}

		public String getDiscountDisc() {
			return discountDisc;
		}

		public void setDiscountDisc(String discountDisc) {
			this.discountDisc = discountDisc;
		}

		public static class ProductDocument {

			/** ��Ʒ���� */
			private String code;

			/** ��Ʒ���� */
			private String description;

			/** ��Ʒ��װ */
			private String commodity;

			/** Ʒ���� */
			private String brand;

			public String getCode() {
				return code;
			}

			public void setCode(String code) {
				this.code = code;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getCommodity() {
				return commodity;
			}

			public void setCommodity(String commodity) {
				this.commodity = commodity;
			}

			public String getBrand() {
				return brand;
			}

			public void setBrand(String brand) {
				this.brand = brand;
			}

		}
	}
}
