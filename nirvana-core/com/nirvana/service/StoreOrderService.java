package com.nirvana.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;

public interface StoreOrderService {

	/**
	 * 生成新的订单号。
	 * 
	 * */
	public long operateNewOrderNo(Dealer dealer);

	/**
	 * 给某个门店下订单。
	 * 
	 * */
	public StoreOrder placeOrder(Store store, Map<String, Integer> items, boolean isAgentHelped, boolean isInLine, MultipartFile sign);

	/**
	 * 给某个门店下订单。
	 * 
	 * */
	public StoreOrder placeOrder(Store store, Map<String, Integer> items, boolean isAgentHelped, MultipartFile sign);

	/**
	 * 提交订单。<br>
	 * 此操作是非线程安全的，调用者在调用此方法前应对Order进行加锁处理。
	 * 
	 * */
	public StoreOrder submitOrder(StoreOrder order);

	/**
	 * 删除2周之前的已完成订单。
	 * 
	 * */
	public void deleteTwoWeekAgoFinishedOrders();

}
