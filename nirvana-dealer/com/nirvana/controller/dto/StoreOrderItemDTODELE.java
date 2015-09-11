package com.nirvana.controller.dto;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.ValidataUtil;

public class StoreOrderItemDTODELE extends BaseDTO<StoreOrderItem> {

	/** 购买数量 */
	private Integer amount ;

	/** 单价 */
	private String unitPrice ;

	private String discountDisc ;

	private ProductDTO product = new ProductDTO();

	@Override
	public BaseDTO<StoreOrderItem> convert(StoreOrderItem domain) {
		if (domain == null)
			return null;
		StoreOrderItemDTO dto = new StoreOrderItemDTO();
		dto.setAmount(domain.getAmount());
		dto.setUnitPrice(ValidataUtil.DECIMALFORMAT.format(domain.getUnitPrice()));
		dto.setDiscountDisc(domain.getDiscountDisc());
		Converter<Product, ProductDTO> converter = DTOContext.getConverter(ProductDTO.class);
		dto.setProduct(converter.convert(domain.getPk().getProduct()));
		return dto;
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

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public String getDiscountDisc() {
		return discountDisc;
	}

	public void setDiscountDisc(String discountDisc) {
		this.discountDisc = discountDisc;
	}

}
