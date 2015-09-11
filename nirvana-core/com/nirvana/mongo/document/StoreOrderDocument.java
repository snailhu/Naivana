package com.nirvana.mongo.document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nirvana.domain.store.StoreOrderState;

@Document(collection = "STORE_ORDER")
public class StoreOrderDocument {

	/** 订单编号,十八位,定义为：时间+5位经销商编号+5位经销商流水号，例子：201407080000100005 */
	private Long id;

	/** 所属经销商 */
	private Long dealerId;

	/** 所属一阶门店 */
	private Long storeId;

	/** 是否为业务员帮助下单 */
	private Boolean isAgentHelped;

	/** 订单创建时间 */
	private Date createDate;

	/** 订单完成时间。 */
	private Date finishDate;

	/** 订单状态 */
	private StoreOrderState state;

	/** 商品总金额 */
	private BigDecimal productTotalPrice;

	/** 订单总金额 */
	private BigDecimal totalPrice;

	/** 应付款(实际需要支付的费用) */
	private BigDecimal payablefee;

	/** 订单项 */
	private List<StoreOrderItemDocument> items;

	/** 门店签字 */
	private String signPic;

	/** 商品描述相符 */
	private Integer product;

	/** 物流 */
	private Integer delivery;

	/** 服务态度 */
	private Integer manner;

	/** 留言 */
	private String words;

	/** 备注 */
	private String note;

	/** 赠品 */
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

		/** 购买数量 */
		private Integer amount;

		/** 单价 */
		private Double unitPrice;

		/** 折扣信息描述 */
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

			/** 商品编码 */
			private String code;

			/** 商品描述 */
			private String description;

			/** 产品包装 */
			private String commodity;

			/** 品牌名 */
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
