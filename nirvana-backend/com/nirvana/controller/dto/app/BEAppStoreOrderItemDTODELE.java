//package com.nirvana.controller.dto.app;
//
//import com.nirvana.domain.common.Product;
//import com.nirvana.domain.store.StoreOrderItem;
//import com.nirvana.pojo4json.BaseDTO;
//
//public class BEAppStoreOrderItemDTODELE extends BaseDTO<StoreOrderItem> {
//
//	// 商品CODE
//	private String productCode = "xxx";
//
//	// 商品名称
//	private String productName = "产品名";
//
//	// 单价
//	private Float unitPrice=11.2f;
//
//	// 购买数量
//	private Integer amount=3;
//
//	public String getProductCode() {
//		return productCode;
//	}
//
//	public void setProductCode(String productCode) {
//		this.productCode = productCode;
//	}
//
//	public String getProductName() {
//		return productName;
//	}
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
//
//	public Float getUnitPrice() {
//		return unitPrice;
//	}
//
//	public void setUnitPrice(Float unitPrice) {
//		this.unitPrice = unitPrice;
//	}
//
//	public Integer getAmount() {
//		return amount;
//	}
//
//	public void setAmount(Integer amount) {
//		this.amount = amount;
//	}
//
//	@Override
//	public BaseDTO<StoreOrderItem> convert(StoreOrderItem domain) {
//		if (domain == null) {
//			return null;
//		}
//		BEAppStoreOrderItemDTO dto = new BEAppStoreOrderItemDTO();
//		dto.setAmount(domain.getAmount());
//		Product p;
//		if (domain.getPk() != null && (p = domain.getPk().getProduct()) != null) {
//			dto.setProductCode(p.getCode());
//			dto.setProductName(p.getName());
//		}
//		dto.setUnitPrice(domain.getUnitPrice());
//		return dto;
//	}
//}
