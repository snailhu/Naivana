/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年7月5日 下午4:29:14
 */
package com.nirvana.mongo.converter.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.domain.store.StoreOrderItemPK;
import com.nirvana.mongo.converter.AbstractConverter;
import com.nirvana.mongo.document.StoreOrderDocument;
import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument;
import com.nirvana.repository.common.BrandRepository;
import com.nirvana.repository.store.StoreRepository;

/**
 * @Title:StoreOrderDocumentConverter.java
 * @Description:
 * @Version:v1.0
 */
@Component
public class StoreOrderDocumentConverter extends
		AbstractConverter<StoreOrderDocument, StoreOrder> {

	@Resource
	StoreRepository storeRepository;
	@Resource
	BrandRepository brandRepository;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.mongo.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public StoreOrder convert(StoreOrderDocument source) {
		StoreOrder storeOrder = new StoreOrder();
		storeOrder.setCreateDate(source.getCreateDate());
		// dealer只存id
		Dealer dealer = new Dealer();
		dealer.setId(source.getId());
		storeOrder.setDealer(dealer);
		storeOrder.setDelivery(source.getDelivery());
		storeOrder.setFinishDate(source.getFinishDate());
		storeOrder.setIsAgentHelped(source.getIsAgentHelped());
		if (source.getItems() != null) {
			Set<StoreOrderItem> storeOrderItems = new HashSet<StoreOrderItem>();
			for (StoreOrderItemDocument storeOrderItemDocument : source
					.getItems()) {
				StoreOrderItem storeOrderItem = new StoreOrderItem();
				storeOrderItem.setAmount(storeOrderItemDocument.getAmount());
				storeOrderItem.setDiscountDisc(storeOrderItemDocument
						.getDiscountDisc());
				storeOrderItem.setUnitPrice(storeOrderItemDocument
						.getUnitPrice());
				if (storeOrderItemDocument.getProduct() != null) {
					Product product = new Product();
					product.setCode(storeOrderItemDocument.getProduct()
							.getCode());
					product.setCommodity(storeOrderItemDocument.getProduct()
							.getCommodity());
					product.setDescription(storeOrderItemDocument.getProduct()
							.getDescription());
					product.setBrand(brandRepository.findOne(storeOrderItemDocument.getProduct().getBrand()));
					StoreOrderItemPK pk = new StoreOrderItemPK(storeOrder,
							product);
					storeOrderItem.setPk(pk);
				}
				storeOrderItems.add(storeOrderItem);
			}
			storeOrder.setItems(storeOrderItems);
		}

		storeOrder.setManner(source.getManner());
		storeOrder.setNote(source.getNote());
		storeOrder.setOrderNo(source.getId());
		storeOrder.setPayablefee(source.getPayablefee());
		storeOrder.setProduct(source.getProduct());
		storeOrder.setProductTotalPrice(source.getProductTotalPrice());
		storeOrder.setRewards(source.getRewards());
		storeOrder.setSignPic(source.getSignPic());
		storeOrder.setState(source.getState());
		Store store = storeRepository.findOne(source.getStoreId());
		storeOrder.setStore(store);
		storeOrder.setTotalPrice(source.getTotalPrice());
		storeOrder.setWords(source.getWords());
		return storeOrder;
	}

}
