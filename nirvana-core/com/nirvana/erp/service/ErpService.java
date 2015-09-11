package com.nirvana.erp.service;

import java.util.Map;

import com.nirvana.erp.domain.PepsiOrderHead;

public interface ErpService {

	/**
	 * ͬ�����пͻ����ݡ�
	 * 
	 * */
	public void syncCustomers(boolean syncRegion);

	/**
	 * ͬ���������ݿ���ڵĿͻ����ݡ�
	 * 
	 * */
	public void syncLocalCustomers(boolean syncRegion);

	/**
	 * ��ERP�¶�����
	 * 
	 * */
	public PepsiOrderHead placeOrder(String customerId, Integer addrNo, Map<String, Integer> skus);

}
