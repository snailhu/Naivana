///**
// * Copyright 2014 SFI
// * @Author:guzhen
// * @Email:1132053388@qq.com
// * @Date:2015年2月14日 下午10:01:06
// */
//package com.nirvana.controller.dto.app;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import com.nirvana.domain.dealer.Dealer;
//import com.nirvana.domain.dealer.DealerStorefrontInfo;
//import com.nirvana.domain.store.Store;
//import com.nirvana.domain.store.StoreOrder;
//import com.nirvana.domain.store.StoreOrderItem;
//import com.nirvana.pojo4json.BaseDTO;
//import com.nirvana.pojo4json.Converter;
//import com.nirvana.pojo4json.DTOContext;
//
//public class BEAppStoreOrderDTODELE extends BaseDTO<StoreOrder> {
//
//	/** 订单编号,十九位,定义为：时间+6位经销商编号+5位经销商流水号，例子：2014070800000100005 */
//	private Long id=1l;
//
//	/** 所属经销商 */
//	private Long dealerId=1l;
//
//	// 经销商姓名
//	private String dealerName="经销商名";
//
//	/** 所属一阶门店 */
//	private Long storeId=1l;
//
//	/** 订单创建时间 */
//	private Date createDate=new Date();
//
//	/** 商品总金额 */
//	private Float productTotalPrice = 0f;
//
//	/** 订单项 */
//	private List<BEAppStoreOrderItemDTO> items = null;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getDealerId() {
//		return dealerId;
//	}
//
//	public void setDealerId(Long dealerId) {
//		this.dealerId = dealerId;
//	}
//
//	public String getDealerName() {
//		return dealerName;
//	}
//
//	public void setDealerName(String dealerName) {
//		this.dealerName = dealerName;
//	}
//
//	public Long getStoreId() {
//		return storeId;
//	}
//
//	public void setStoreId(Long storeId) {
//		this.storeId = storeId;
//	}
//
//	public Date getCreateDate() {
//		return createDate;
//	}
//
//	public void setCreateDate(Date createDate) {
//		this.createDate = createDate;
//	}
//
//	public Float getProductTotalPrice() {
//		return productTotalPrice;
//	}
//
//	public void setProductTotalPrice(Float productTotalPrice) {
//		this.productTotalPrice = productTotalPrice;
//	}
//
//	public List<BEAppStoreOrderItemDTO> getItems() {
//		return items;
//	}
//
//	public void setItems(List<BEAppStoreOrderItemDTO> items) {
//		this.items = items;
//	}
//
//	@Override
//	public BaseDTO<StoreOrder> convert(StoreOrder domain) {
//		BEAppStoreOrderDTO dto = new BEAppStoreOrderDTO();
//		if (domain == null){
//			dto.setItems(new ArrayList<BEAppStoreOrderItemDTO>(Arrays.asList(new BEAppStoreOrderItemDTO(),new BEAppStoreOrderItemDTO())));
//			return dto;
//		}
//		dto.setCreateDate(domain.getCreateDate());
//		Dealer dealer;
//		if ((dealer = domain.getDealer()) != null) {
//			dto.setDealerId(dealer.getId());
//			DealerStorefrontInfo info;
//			if ((info = dealer.getStorefrontInfo()) != null) {
//				dto.setDealerName(info.getName());
//			}
//		}
//		dto.setId(domain.getId());
//		Converter<StoreOrderItem, BEAppStoreOrderItemDTO> converter = DTOContext.getConverter(BEAppStoreOrderItemDTO.class);
//		dto.setItems(converter.convert(domain.getItems()));
//		dto.setProductTotalPrice(domain.getTotalPrice());
//		Store store;
//		if ((store = domain.getStore()) != null) {
//			dto.setStoreId(store.getId());
//		}
//		return dto;
//	}
//
//}
