/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月26日 下午10:33:34
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.backend.VisitRouteNode;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

/**
 * @Title:VisitRouteNodeDTO.java
 * @Description:
 * @Version:v1.0
 */
public class VisitRouteNodeDTO extends BaseDTO<VisitRouteNode> {

	/** 序号 */
	private Integer serialNum;

	/** 拜访节点类型 */
	private String type;

	/** 关联的门店 */
	private StoreDTO store;

	private DealerDTO dealer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<VisitRouteNode> convert(VisitRouteNode domain) {
		VisitRouteNodeDTO dto = new VisitRouteNodeDTO();
		if (domain == null) {
			return dto;
		}
		dto.setSerialNum(domain.getPk().getSerialNum());
		Converter<Store, StoreDTO> storeConverter = DTOContext
				.getConverter(StoreDTO.class);
		dto.setStore(storeConverter.convert(domain.getStore()));
		Converter<Dealer, DealerDTO> dealerConverter = DTOContext
				.getConverter(DealerDTO.class);
		dto.setDealer(dealerConverter.convert(domain.getDealer()));
		dto.setType(domain.getType().name());
		return dto;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public StoreDTO getStore() {
		return store;
	}

	public void setStore(StoreDTO store) {
		this.store = store;
	}

	public DealerDTO getDealer() {
		return dealer;
	}

	public void setDealer(DealerDTO dealer) {
		this.dealer = dealer;
	}

}
