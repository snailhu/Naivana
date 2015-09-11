package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.mongo.document.StoreOrderDocument;
import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument;
import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument.ProductDocument;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.DateUtil;
import com.nirvana.utils.ValidataUtil;

public class DealerGetFinishedStoreOrderDTO extends BaseDTO<StoreOrderDocument> {

	/** ������ */
	private Long id;

	/** ����һ���ŵ� */
	private Long storeId;

	/** ��������ʱ�� */
	private String createDate;

	/** ��Ʒ�ܽ�� */
	private String productTotalPrice;

	/** �����ܽ�� */
	private String totalPrice;

	/** Ӧ����(ʵ����Ҫ֧���ķ���) */
	private String payablefee;

	/** ������ */
	private List<DealerStoreOrderItemDocumentDTO> items;

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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getProductTotalPrice() {
		return productTotalPrice;
	}

	public void setProductTotalPrice(String productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayablefee() {
		return payablefee;
	}

	public void setPayablefee(String payablefee) {
		this.payablefee = payablefee;
	}

	public List<DealerStoreOrderItemDocumentDTO> getItems() {
		return items;
	}

	public void setItems(List<DealerStoreOrderItemDocumentDTO> items) {
		this.items = items;
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

	@Override
	public BaseDTO<StoreOrderDocument> convert(StoreOrderDocument domain) {
		DealerGetFinishedStoreOrderDTO dto = new DealerGetFinishedStoreOrderDTO();
		if (domain == null) {
			return dto;
		}
		dto.setStoreId(domain.getStoreId());
		dto.setCreateDate(DateUtil.dateToString(domain.getCreateDate(),
				"yyyy/MM/dd"));
		dto.setDelivery(domain.getDelivery());
		dto.setId(domain.getId());
		Converter<StoreOrderItemDocument, DealerStoreOrderItemDocumentDTO> converter = DTOContext
				.getConverter(DealerStoreOrderItemDocumentDTO.class);
		dto.setItems(converter.convert(domain.getItems()));
		dto.setManner(domain.getManner());
		dto.setNote(domain.getNote());
		dto.setPayablefee(ValidataUtil.DECIMALFORMAT.format(domain
				.getPayablefee().doubleValue()));
		dto.setProduct(domain.getProduct());
		dto.setProductTotalPrice(ValidataUtil.DECIMALFORMAT.format(domain
				.getProductTotalPrice().doubleValue()));
		dto.setTotalPrice(ValidataUtil.DECIMALFORMAT.format(domain
				.getTotalPrice().doubleValue()));
		dto.setWords(domain.getWords());
		dto.setRewards(domain.getRewards());
		return dto;
	}

	public static class DealerStoreOrderItemDocumentDTO extends
			BaseDTO<StoreOrderItemDocument> {

		private ProductDocumentDTO product;

		/** �������� */
		private Integer amount;

		/** ���� */
		private String unitPrice;

		/** �ۿ���Ϣ���� */
		private String discountDisc;

		public ProductDocumentDTO getProduct() {
			return product;
		}

		public void setProduct(ProductDocumentDTO product) {
			this.product = product;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public String getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(String unitPrice) {
			this.unitPrice = unitPrice;
		}

		public String getDiscountDisc() {
			return discountDisc;
		}

		public void setDiscountDisc(String discountDisc) {
			this.discountDisc = discountDisc;
		}

		@Override
		public BaseDTO<StoreOrderItemDocument> convert(
				StoreOrderItemDocument domain) {
			DealerStoreOrderItemDocumentDTO dto = new DealerStoreOrderItemDocumentDTO();
			dto.setAmount(domain.getAmount());
			dto.setDiscountDisc(domain.getDiscountDisc());
			dto.setUnitPrice(ValidataUtil.DECIMALFORMAT.format(domain.getUnitPrice()));
			Converter<ProductDocument, ProductDocumentDTO> converter = DTOContext
					.getConverter(ProductDocumentDTO.class);
			dto.setProduct(converter.convert(domain.getProduct()));
			return dto;
		}

		public static class ProductDocumentDTO extends BaseDTO<ProductDocument> {

			/** ��Ʒ���� */
			private String code;

			/** ��Ʒ���� */
			private String description;

			/** ��Ʒ��װ */
			private String commodity;

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

			@Override
			public BaseDTO<ProductDocument> convert(ProductDocument domain) {
				ProductDocumentDTO dto = new ProductDocumentDTO();
				dto.setCode(domain.getCode());
				dto.setCommodity(domain.getCommodity());
				dto.setDescription(domain.getDescription());
				return dto;
			}
		}

	}

}
