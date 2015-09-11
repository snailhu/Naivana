package com.nirvana.controller.dto;

import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument;
import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument.ProductDocument;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class StoreOrderItemDocumentDTO extends BaseDTO<StoreOrderItemDocument> {
	/** 联合主键 */
	private ProductDocumentDTO product;

	/** 购买数量 */
	private Integer amount;

	/** 单价 */
	private Double unitPrice;

	/** 折扣信息描述 */
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

	@Override
	public BaseDTO<StoreOrderItemDocument> convert(StoreOrderItemDocument domain) {
		StoreOrderItemDocumentDTO dto = new StoreOrderItemDocumentDTO();
		if (domain == null) {
			return dto;
		}
		dto.setAmount(domain.getAmount());
		dto.setDiscountDisc(domain.getDiscountDisc());
		dto.setUnitPrice(domain.getUnitPrice());
		Converter<ProductDocument, ProductDocumentDTO> converter = DTOContext
				.getConverter(ProductDocumentDTO.class);
		dto.setProduct(converter.convert(domain.getProduct()));
		return dto;
	}
}
