package com.nirvana.mongo.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.mongo.converter.AbstractConverter;
import com.nirvana.mongo.document.StoreOrderDocument;
import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument;
import com.nirvana.mongo.document.StoreOrderDocument.StoreOrderItemDocument.ProductDocument;

@Component
public class StoreOrderConverter extends AbstractConverter<StoreOrder, StoreOrderDocument> {

	@Override
	public StoreOrderDocument convert(StoreOrder source) {
		StoreOrderDocument document = new StoreOrderDocument();
		document.setId(source.getOrderNo());
		document.setDealerId(source.getDealer().getId());
		document.setStoreId(source.getStore().getId());
		document.setIsAgentHelped(source.getIsAgentHelped());
		document.setCreateDate(source.getCreateDate());
		document.setState(source.getState());
		document.setProductTotalPrice(source.getProductTotalPrice());
		document.setTotalPrice(source.getTotalPrice());
		document.setPayablefee(source.getPayablefee());
		document.setSignPic(source.getSignPic());
		document.setProduct(source.getProduct());
		document.setDelivery(source.getDelivery());
		document.setManner(source.getManner());
		document.setWords(source.getWords());
		document.setNote(source.getNote());
		document.setRewards(source.getRewards());
		document.setFinishDate(source.getFinishDate());
		List<StoreOrderItemDocument> itemDocuments = new ArrayList<StoreOrderItemDocument>();
		for (StoreOrderItem item : source.getItems()) {
			StoreOrderItemDocument itemDocument = new StoreOrderItemDocument();
			itemDocument.setAmount(item.getAmount());
			itemDocument.setDiscountDisc(item.getDiscountDisc());
			itemDocument.setUnitPrice(item.getUnitPrice());
			Product product = item.getPk().getProduct();
			ProductDocument productDocument = new ProductDocument();
			productDocument.setCode(product.getCode());
			productDocument.setCommodity(product.getCommodity());
			productDocument.setDescription(product.getDescription());
			productDocument.setBrand(product.getBrand().getName());
			itemDocument.setProduct(productDocument);
			itemDocuments.add(itemDocument);
		}
		document.setItems(itemDocuments);
		return document;
	}
}
