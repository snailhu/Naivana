//package com.nirvana.controller.dto.app;
//
//import com.nirvana.domain.common.Product;
//import com.nirvana.domain.dealer.DealerOrderItem;
//import com.nirvana.pojo4json.BaseDTO;
//
//public class BEAppDealerOrderItemDTODELE extends BaseDTO<DealerOrderItem> {
//
//	/** 商品条码 */
//	private String code = "code";
//
//	/** 商品名称 */
//	private String name = "商品名";
//
//	/** 规格 */
//	private String specifications = "1200*500ml";
//
//	private String url = "xxx.com";
//
//	// 购买数量
//	private Integer amount = 100;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
//	 */
//	@Override
//	public BaseDTO<DealerOrderItem> convert(DealerOrderItem domain) {
//		BEAppDealerOrderItemDTO dto = new BEAppDealerOrderItemDTO();
//		if (domain == null) {
//			return dto;
//		}
//		dto.setAmount(domain.getAmount());
//		Product p = domain.getPk().getProduct();
//		dto.setCode(p.getCode());
//		dto.setName(p.getName());
//		dto.setSpecifications(p.getSpecifications());
//		dto.setUrl(p.getUrl());
//		return dto;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSpecifications() {
//		return specifications;
//	}
//
//	public void setSpecifications(String specifications) {
//		this.specifications = specifications;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public Integer getAmount() {
//		return amount;
//	}
//
//	public void setAmount(Integer amount) {
//		this.amount = amount;
//	}
//}
