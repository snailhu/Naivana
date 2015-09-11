///**
// * Copyright 2014 SFI
// * @Author:guzhen
// * @Email:1132053388@qq.com
// * @Date:2015��2��14�� ����10:01:06
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
//	/** �������,ʮ��λ,����Ϊ��ʱ��+6λ�����̱��+5λ��������ˮ�ţ����ӣ�2014070800000100005 */
//	private Long id=1l;
//
//	/** ���������� */
//	private Long dealerId=1l;
//
//	// ����������
//	private String dealerName="��������";
//
//	/** ����һ���ŵ� */
//	private Long storeId=1l;
//
//	/** ��������ʱ�� */
//	private Date createDate=new Date();
//
//	/** ��Ʒ�ܽ�� */
//	private Float productTotalPrice = 0f;
//
//	/** ������ */
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
