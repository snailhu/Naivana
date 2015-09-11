/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月22日 上午10:34:30
 */
package com.nirvana.controller.dto.web;

import java.util.List;

import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

/**
 * @Title:WebDealerAllotDTO.java
 * @Description:
 * @Version:v1.0
 */
public class WebStoreAllotDTO {
	List<WebStoreDTO> stores;
	List<WebStoreDTO> storesNotAlloted;

	public WebStoreAllotDTO(List<Store> stores, List<Store> storesNotAlloted) {
		super();
		Converter<Store, WebStoreDTO> converter = DTOContext
				.getConverter(WebStoreDTO.class);
		this.stores = converter.convert(stores);
		this.storesNotAlloted = converter.convert(storesNotAlloted);
	}

	public WebStoreAllotDTO() {
	}

	public List<WebStoreDTO> getStores() {
		return stores;
	}

	public void setStores(List<WebStoreDTO> stores) {
		this.stores = stores;
	}

	public List<WebStoreDTO> getStoresNotAlloted() {
		return storesNotAlloted;
	}

	public void setStoresNotAlloted(List<WebStoreDTO> storesNotAlloted) {
		this.storesNotAlloted = storesNotAlloted;
	}

}
