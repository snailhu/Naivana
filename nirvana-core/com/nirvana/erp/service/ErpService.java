package com.nirvana.erp.service;

import java.util.Map;

import com.nirvana.erp.domain.PepsiOrderHead;

public interface ErpService {

	/**
	 * 同步所有客户数据。
	 * 
	 * */
	public void syncCustomers(boolean syncRegion);

	/**
	 * 同步本地数据库存在的客户数据。
	 * 
	 * */
	public void syncLocalCustomers(boolean syncRegion);

	/**
	 * 向ERP下订单。
	 * 
	 * */
	public PepsiOrderHead placeOrder(String customerId, Integer addrNo, Map<String, Integer> skus);

}
