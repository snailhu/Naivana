package com.nirvana.mongo.document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nirvana.domain.dealer.DealerOrderItemType;
import com.nirvana.domain.dealer.DealerOrderState;

@Document(collection = "DEALER_ORDER")
public class DealerOrderDocument {

	/** �����ţ����ӣ�2014070900001005 */
	private Long id;

	/** ERP�ж�����š� */
	private String codeInERP;

	/** �����ľ����� */
	private Long dealerId;

	/** ��������ʱ�� */
	private Date createDate;

	/** �µ�ʱ�䡣 */
	private Date enterDate;

	/** ϣ������ʱ�䡣 */
	private Date wantDate;

	/** �������ʱ�� */
	private Date finishDate;

	/** �����Ķ����� */
	private List<DealerOrderItemDocument> items;

	/** �����׶� */
	private DealerOrderState state;

	/** �ŵ�ǩ�� */
	private String signPic;

	/** �ܼ� */
	private BigDecimal totalPrice;

	/** �Ƿ�Ϊҵ��Ա�����µ� */
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

		/** �������к� */
		private Integer lineNo;

		/** ��������Ʒ */
		private ProductDocument product;

		/** ���� */
		private Float unitPrice;

		/** ���� */
		private Integer amount;

		/** ��λ */
		private String unitMeas;

		/** �ۿ۰ٷֱ� */
		private Float discount;

		/** ���ֽ�� */
		private Integer points;

		/** �������� */
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

			/** ��Ʒ���� */
			private String code;

			/** ��Ʒ���� */
			private String description;

			/** ��Ʒ��װ */
			private String commodity;

			/** ��Ʒ���� */
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
