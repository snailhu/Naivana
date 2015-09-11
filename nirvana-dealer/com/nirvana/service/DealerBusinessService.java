package com.nirvana.service;

import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.nirvana.domain.store.StoreOrder;
import com.nirvana.service.pojo.Appraises;

/**
 * 经销商经营相关服务。
 * 
 * 包括订单的查看，配送等等。
 * 
 * */
public interface DealerBusinessService {

	/**
	 * 按时间排序获取未处理的订单。
	 * 
	 * */
	public Page<StoreOrder> getUnhandledOrders(int page, int size);

	/**
	 * 按时间排序获取处理中的订单。
	 * 
	 * */
	public Page<StoreOrder> getHandlingOrders(int page, int size);

	/**
	 * 按时间排序获取完成的的订单。
	 * 
	 * */
	public Page<StoreOrder> getFinishedOrders(int page, int size);

	/**
	 * 按时间排序获取未处理的订单。
	 * 
	 * */
	public Page<StoreOrder> getUnhandledOrdersByDate(int page, int size, Date start, Date end);

	/**
	 * 按时间排序获取处理中的订单。
	 * 
	 * */
	public Page<StoreOrder> getHandlingOrdersByDate(int page, int size, Date start, Date end);

	/**
	 * 按时间排序获取完成的的订单。
	 * 
	 * */
	public Page<StoreOrder> getFinishedOrdersByDate(int page, int size, Date start, Date end);

	/**
	 * 处理订单。
	 * 
	 * @param orderId
	 *            订单ID
	 * 
	 * */
	public void handleOrder(long orderNo, String note);

	/**
	 * 完成订单。
	 * 
	 * @param orderId
	 *            订单ID
	 * 
	 * */
	public void finishOrder(long orderNo, Map<String, Integer> skuData, Double payPrice,String gifts, String note);

	/**
	 * 获取评价。
	 * 
	 * */
	public Appraises getAppraises(long orderNo);

}
