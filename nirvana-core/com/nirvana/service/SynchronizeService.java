package com.nirvana.service;

public interface SynchronizeService {

	/**
	 * 全局初始化。
	 * 
	 * */
	public void globalInitialization();

	/**
	 * 同步所有区域数据。
	 * 
	 * */
	public void syncRegions();

	/**
	 * 同步渠道。
	 * 
	 * */
	public void syncChannels();

	/**
	 * 同步商品。
	 * 
	 * */
	public void syncProducts();

	/**
	 * 同步积分数据。
	 * 
	 * */
	public void syncPoints();

	/**
	 * 同步某个客户信息。
	 * 
	 * #本操作同步客户与区域的关联结构关系#
	 * 
	 * */
	public void syncCustomer(String customerId, boolean syncRegion);

	/**
	 * 同步所有客户数据。
	 * 
	 * @param syncRegion
	 *            是否同步区域。
	 * 
	 * */
	public void syncCustomers(boolean syncRegion);

	/**
	 * 同步经销商订单。
	 * 
	 * @param syncNew
	 *            是否对新订单进行同步。
	 * 
	 * */
	public void syncDealerOrders(boolean syncNew);

}
