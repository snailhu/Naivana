package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.domain.dealer.DealerOrderItemType;
import com.nirvana.mongo.document.DealerOrderDocument;
import com.nirvana.mongo.document.DealerOrderDocument.DealerOrderItemDocument;
import com.nirvana.mongo.document.DealerOrderDocument.DealerOrderItemDocument.ProductDocument;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.DateUtil;
import com.nirvana.utils.ValidataUtil;

public class DealerGetFinishedDealerOrderDTO extends
		BaseDTO<DealerOrderDocument> {

	/** �����ţ����ӣ�2014070900001005 */
	private Long id;

	/** �����Ķ����� */
	private List<DealerDealerOrderItemDocumentDTO> items;

	private String totalPrice;

	/** �µ�ʱ�䡣 */
	private String enterDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<DealerDealerOrderItemDocumentDTO> getItems() {
		return items;
	}

	public void setItems(List<DealerDealerOrderItemDocumentDTO> items) {
		this.items = items;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	@Override
	public BaseDTO<DealerOrderDocument> convert(DealerOrderDocument domain) {
		DealerGetFinishedDealerOrderDTO dto = new DealerGetFinishedDealerOrderDTO();
		dto.setEnterDate(DateUtil.dateToString(domain.getEnterDate(),
				"yyyy/MM/dd"));
		dto.setId(domain.getId());
		Converter<DealerOrderItemDocument, DealerDealerOrderItemDocumentDTO> converter = DTOContext
				.getConverter(DealerDealerOrderItemDocumentDTO.class);
		dto.setItems(converter.convert(domain.getItems()));
		dto.setTotalPrice(ValidataUtil.DECIMALFORMAT.format(domain
				.getTotalPrice().doubleValue()));
		return dto;
	}

	public static class DealerDealerOrderItemDocumentDTO extends
			BaseDTO<DealerOrderItemDocument> {

		private ProductDocumentDTO product;

		/** ���� */
		private Integer amount;

		/** �������к� */
		private Integer lineNo;

		/** ���� */
		private Float unitPrice;

		/** ��λ */
		private String unitMeas;

		/** �ۿ۰ٷֱ� */
		private Float discount;

		/** ���ֽ�� */
		private Integer points;

		private String type;

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

		public Integer getLineNo() {
			return lineNo;
		}

		public void setLineNo(Integer lineNo) {
			this.lineNo = lineNo;
		}

		public Float getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(Float unitPrice) {
			this.unitPrice = unitPrice;
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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		@Override
		public BaseDTO<DealerOrderItemDocument> convert(
				DealerOrderItemDocument domain) {
			DealerDealerOrderItemDocumentDTO dto = new DealerDealerOrderItemDocumentDTO();
			dto.setAmount(domain.getAmount());
			dto.setDiscount(domain.getDiscount());
			dto.setLineNo(domain.getLineNo());
			dto.setPoints(domain.getPoints());
			dto.setType(DealerOrderItemType.DO == domain.getType() ? "�ۿ۶���"
					: "���۶���");
			dto.setUnitMeas(domain.getUnitMeas());
			dto.setUnitPrice(domain.getUnitPrice());
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

			@Override
			public BaseDTO<ProductDocument> convert(ProductDocument domain) {
				ProductDocumentDTO dto = new ProductDocumentDTO();
				dto.setCode(domain.getCode());
				dto.setCommodity(domain.getCommodity());
				dto.setDescription(domain.getDescription());
				dto.setBrand(domain.getBrand());
				return dto;
			}
		}
	}

}
