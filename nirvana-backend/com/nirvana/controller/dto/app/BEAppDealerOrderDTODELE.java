///**
// * Copyright 2014 SFI
// * @Author:guzhen
// * @Email:1132053388@qq.com
// * @Date:2015��2��14�� ����10:01:06
// */
//package com.nirvana.controller.dto.app;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import com.nirvana.domain.dealer.DealerOrder;
//import com.nirvana.pojo4json.BaseDTO;
//
//public class BEAppDealerOrderDTODELE extends BaseDTO<DealerOrder> {
//
//	/** �����ţ����ӣ�2014070900001005 */
//	private String id = "2014070900001005";
//
//	/** �����ľ����� */
//	// private Dealer dealer;
//
//	/** �����Ķ����� */
//	private Set<BEAppDealerOrderItemDTO> items;// = new
//												// HashSet<BEAppDealerOrderItemDTO>()
//												// {
//	// {
//	// add(new BEAppDealerOrderItemDTO());
//	// add(new BEAppDealerOrderItemDTO());
//	// }
//	// };
//
//	/** �ܼ� */
//	private Float totalPrice = 100f;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
//	 */
//	@Override
//	public BaseDTO<DealerOrder> convert(DealerOrder domain) {
//		BEAppDealerOrderDTO dto = new BEAppDealerOrderDTO();
//		if (domain == null) {
//			HashSet<BEAppDealerOrderItemDTO> set=new HashSet<BEAppDealerOrderItemDTO>();
//			set.add(new BEAppDealerOrderItemDTO());
//			set.add(new BEAppDealerOrderItemDTO());
//			dto.setItems(set);
//			return dto;
//		}
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public Set<BEAppDealerOrderItemDTO> getItems() {
//		return items;
//	}
//
//	public void setItems(Set<BEAppDealerOrderItemDTO> items) {
//		this.items = items;
//	}
//
//	public Float getTotalPrice() {
//		return totalPrice;
//	}
//
//	public void setTotalPrice(Float totalPrice) {
//		this.totalPrice = totalPrice;
//	}
//
//}
