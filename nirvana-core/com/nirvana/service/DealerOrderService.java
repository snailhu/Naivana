package com.nirvana.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;

public interface DealerOrderService {

	/**
	 * 生成新的订单号。
	 * 
	 * */
	public long operateNewOrderNo(Dealer dealer);

	/**
	 * 下订单。
	 * 
	 * */
	public DealerOrder placeOrder(Dealer dealer, Map<String, Integer> skus, boolean isAgentHelped, boolean isInline, MultipartFile picture);

	/**
	 * 下订单。
	 * 
	 * */
	public DealerOrder placeOrder(Dealer dealer, Map<String, Integer> skus, boolean isAgentHelped, MultipartFile picture);

	/**
	 * 删除两周之前的已完成订单。
	 * 
	 * */
	public void deleteTwoWeekAgoFinishedOrders();

	/**
	 * 完成订单。
	 * 
	 * */
	public void finishOrder(DealerOrder dealerOrder);

}
