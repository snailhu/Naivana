package com.nirvana.controller.dto;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.ValidataUtil;

public class StoreOrderItemDTO extends BaseDTO<StoreOrderItem> {
	/** 联合主键 */
	private ProductDTO product;

	/** 购买数量 */
	private Integer amount;

	/** 单价 */
	private String unitPrice;

	/** 折扣信息描述 */
	private String discountDisc;

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
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
	public BaseDTO<StoreOrderItem> convert(StoreOrderItem domain) {
		StoreOrderItemDTO dto = new StoreOrderItemDTO();
		if (domain == null) {
			return dto;
		}
		dto.setAmount(domain.getAmount());
		dto.setDiscountDisc(domain.getDiscountDisc());
		dto.setUnitPrice(ValidataUtil.DECIMALFORMAT.format(domain.getUnitPrice()));
		Converter<Product, ProductDTO> converter = DTOContext.getConverter(ProductDTO.class);
		dto.setProduct(converter.convert(domain.getPk().getProduct()));
		return dto;
	}
}
