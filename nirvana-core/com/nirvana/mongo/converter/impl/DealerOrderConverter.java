package com.nirvana.mongo.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderItem;
import com.nirvana.mongo.converter.AbstractConverter;
import com.nirvana.mongo.document.DealerOrderDocument;
import com.nirvana.mongo.document.DealerOrderDocument.DealerOrderItemDocument;
import com.nirvana.mongo.document.DealerOrderDocument.DealerOrderItemDocument.ProductDocument;

@Component
public class DealerOrderConverter extends AbstractConverter<DealerOrder, DealerOrderDocument> {

	@Override
	public DealerOrderDocument convert(DealerOrder source) {
		DealerOrderDocument orderDocument = new DealerOrderDocument();
		orderDocument.setId(source.getOrderNo());
		orderDocument.setCodeInERP(source.getCodeInERP());
		orderDocument.setDealerId(source.getDealer().getId());
		orderDocument.setEnterDate(source.getEnterDate());
		orderDocument.setWantDate(source.getWantDate());
		orderDocument.setState(source.getState());
		orderDocument.setSignPic(source.getSignPic());
		orderDocument.setTotalPrice(source.getTotalPrice());
		orderDocument.setIsAgentHelped(source.getIsAgentHelped());
		orderDocument.setCreateDate(source.getCreateDate());
		orderDocument.setFinishDate(source.getFinishDate());
		List<DealerOrderItemDocument> itemDocuments = new ArrayList<DealerOrderItemDocument>();
		for (DealerOrderItem item : source.getItems()) {
			DealerOrderItemDocument itemDocument = new DealerOrderItemDocument();
			itemDocument.setAmount(item.getAmount());
			itemDocument.setDiscount(item.getDiscount());
			itemDocument.setLineNo(item.getPk().getLineNo());
			itemDocument.setPoints(item.getPoints());
			itemDocument.setType(item.getType());
			itemDocument.setUnitMeas(item.getUnitMeas());
			itemDocument.setUnitPrice(item.getUnitPrice());
			Product product = item.getProduct();
			ProductDocument productDocument = new ProductDocument();
			productDocument.setCode(product.getCode());
			productDocument.setCommodity(product.getCommodity());
			productDocument.setDescription(product.getDescription());
			itemDocument.setProduct(productDocument);
			itemDocuments.add(itemDocument);
		}
		orderDocument.setItems(itemDocuments);
		return orderDocument;
	}
}
