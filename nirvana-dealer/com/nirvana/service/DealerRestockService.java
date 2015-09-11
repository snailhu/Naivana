package com.nirvana.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderState;
import com.nirvana.mongo.document.DealerOrderDocument;

/**
 * 经销商进货的相关服务。
 * 
 * */
public interface DealerRestockService {

	/**
	 * 获取商品列表。
	 * 
	 * */
	public Page<Product> getProduct(int page, int size);

	/**
	 * 根据品牌获取商品列表。
	 * 
	 * */
	public Page<Product> getProduct(String brand, int page, int size);

	/**
	 * 下订单。
	 * 
	 * @param dealerId
	 *            经销商ID（操作者）
	 * @param skuData
	 *            商品数据
	 * @param sign
	 * 
	 * */
	public DealerOrder placeOrder(Map<String, Integer> skuData, MultipartFile sign);

	/**
	 * 根据状态获取订单。只接受除了CLOSED,CANCELED.以外的状态参数。
	 * 
	 */
	Page<DealerOrder> getNotFinishedOrders(DealerOrderState state, int page, int size);

	/**
	 * 根据状态获取订单。只接受参数：CLOSED,CANCELED.
	 * 
	 * */
	Page<DealerOrderDocument> getFinishedOrders(DealerOrderState state, int page, int size);

}
