package com.nirvana.service;

public interface SynchronizeService {

	/**
	 * ȫ�ֳ�ʼ����
	 * 
	 * */
	public void globalInitialization();

	/**
	 * ͬ�������������ݡ�
	 * 
	 * */
	public void syncRegions();

	/**
	 * ͬ��������
	 * 
	 * */
	public void syncChannels();

	/**
	 * ͬ����Ʒ��
	 * 
	 * */
	public void syncProducts();

	/**
	 * ͬ���������ݡ�
	 * 
	 * */
	public void syncPoints();

	/**
	 * ͬ��ĳ���ͻ���Ϣ��
	 * 
	 * #������ͬ���ͻ�������Ĺ����ṹ��ϵ#
	 * 
	 * */
	public void syncCustomer(String customerId, boolean syncRegion);

	/**
	 * ͬ�����пͻ����ݡ�
	 * 
	 * @param syncRegion
	 *            �Ƿ�ͬ������
	 * 
	 * */
	public void syncCustomers(boolean syncRegion);

	/**
	 * ͬ�������̶�����
	 * 
	 * @param syncNew
	 *            �Ƿ���¶�������ͬ����
	 * 
	 * */
	public void syncDealerOrders(boolean syncNew);

}
