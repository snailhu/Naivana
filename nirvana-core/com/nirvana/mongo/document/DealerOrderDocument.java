package com.nirvana.mongo.document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nirvana.domain.dealer.DealerOrderItemType;
import com.nirvana.domain.dealer.DealerOrderState;

@Document(collection = "DEALER_ORDER")
public class DealerOrderDocument {

	/** 订单号，例子：2014070900001005 */
	private Long id;

	/** ERP中订单编号。 */
	private String codeInERP;

	/** 关联的经销商 */
	private Long dealerId;

	/** 订单创建时间 */
	private Date createDate;

	/** 下单时间。 */
	private Date enterDate;

	/** 希望配送时间。 */
	private Date wantDate;

	/** 订单完成时间 */
	private Date finishDate;

	/** 包含的订单项 */
	private List<DealerOrderItemDocument> items;

	/** 订单阶段 */
	private DealerOrderState state;

	/** 门店签字 */
	private String signPic;

	/** 总价 */
	private BigDecimal totalPrice;

	/** 是否为业务员帮助下单 */
	private Boolean isAgentHelped;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
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

	public List<DealerOrderItemDocument> getItems() {
		return items;
	}

	public void setItems(List<DealerOrderItemDocument> items) {
		this.items = items;
	}

	public DealerOrderState getState() {
		return state;
	}

	public void setState(DealerOrderState state) {
		this.state = state;
	}

	public String getSignPic() {
		return signPic;
	}

	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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

	public static class DealerOrderItemDocument {

		/** 订单项行号 */
		private Integer lineNo;

		/** 关联的商品 */
		private ProductDocument product;

		/** 单价 */
		private Float unitPrice;

		/** 数量 */
		private Integer amount;

		/** 单位 */
		private String unitMeas;

		/** 折扣百分比 */
		private Float discount;

		/** 积分金额 */
		private Integer points;

		/** 订单类型 */
		private DealerOrderItemType type;

		public Integer getLineNo() {
			return lineNo;
		}

		public void setLineNo(Integer lineNo) {
			this.lineNo = lineNo;
		}

		public ProductDocument getProduct() {
			return product;
		}

		public void setProduct(ProductDocument product) {
			this.product = product;
		}

		public Float getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(Float unitPrice) {
			this.unitPrice = unitPrice;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
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

		public static class ProductDocument {

			/** 商品编码 */
			private String code;

			/** 商品描述 */
			private String description;

			/** 产品包装 */
			private String commodity;

			/** 产品分类 */
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
