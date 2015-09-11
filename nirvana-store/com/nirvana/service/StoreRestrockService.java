package com.nirvana.service;

import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderState;
import com.nirvana.mongo.document.StoreOrderDocument;

/**
 * 商店进货相关服务。
 * 
 * */
public interface StoreRestrockService {

	/**
	 * 获取可购买的商品。
	 * 
	 * */
	public Page<Inventory> getProducts(int page, int size);

	/**
	 * 根据品牌获取可购买的商品。
	 * 
	 * */
	public Page<Inventory> getProducts(String brandCode, int page, int size);

	/**
	 * 根据商品编码获取商品。
	 * 
	 * */
	public Inventory getProduct(String productCode);

	/**
	 * 下订单。
	 * 
	 * */
	public StoreOrder placeOrder(Map<String, Integer> items);

	/**
	 * 提交订单。
	 * 
	 * */
	public StoreOrder submitOrder(long orderNo, MultipartFile sign);

	/**
	 * 取消订单。
	 * 
	 * */
	public void cancelOrder(long orderNo);

	/**
	 * 获取订单详情。
	 * 
	 * */
	public StoreOrder getOrder(long orderNo);

	/**
	 * 获取全部订单。
	 * 
	 * */
	public Page<StoreOrder> getOrders(int page, int size);

	/**
	 * 根据订单状态获取订单。
	 * 
	 * */
	public Page<StoreOrder> getOrders(StoreOrderState state, int page, int size);
	
	public Page<StoreOrder> getOrdersByMongo(StoreOrderState state, int page, int size);

	/**
	 * 根据时间获取订单。
	 * 
	 * */
	public Page<StoreOrder> getOrders(Date startDate, Date endDate, int page, int size);
	
	/**
	 * 根据时间获取账单。
	 * 
	 * */
	public Page<StoreOrderDocument> getOrdersBill(Date startDate, Date endDate, int page, int size);

	/**
	 * 评价订单。
	 * 
	 * */
	public void appraise(long orderNo, int product, int delivery, int manner, String note);
}
