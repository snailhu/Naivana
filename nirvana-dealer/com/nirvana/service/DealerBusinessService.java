package com.nirvana.service;

import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.nirvana.domain.store.StoreOrder;
import com.nirvana.service.pojo.Appraises;

/**
 * �����̾�Ӫ��ط���
 * 
 * ���������Ĳ鿴�����͵ȵȡ�
 * 
 * */
public interface DealerBusinessService {

	/**
	 * ��ʱ�������ȡδ����Ķ�����
	 * 
	 * */
	public Page<StoreOrder> getUnhandledOrders(int page, int size);

	/**
	 * ��ʱ�������ȡ�����еĶ�����
	 * 
	 * */
	public Page<StoreOrder> getHandlingOrders(int page, int size);

	/**
	 * ��ʱ�������ȡ��ɵĵĶ�����
	 * 
	 * */
	public Page<StoreOrder> getFinishedOrders(int page, int size);

	/**
	 * ��ʱ�������ȡδ����Ķ�����
	 * 
	 * */
	public Page<StoreOrder> getUnhandledOrdersByDate(int page, int size, Date start, Date end);

	/**
	 * ��ʱ�������ȡ�����еĶ�����
	 * 
	 * */
	public Page<StoreOrder> getHandlingOrdersByDate(int page, int size, Date start, Date end);

	/**
	 * ��ʱ�������ȡ��ɵĵĶ�����
	 * 
	 * */
	public Page<StoreOrder> getFinishedOrdersByDate(int page, int size, Date start, Date end);

	/**
	 * ��������
	 * 
	 * @param orderId
	 *            ����ID
	 * 
	 * */
	public void handleOrder(long orderNo, String note);

	/**
	 * ��ɶ�����
	 * 
	 * @param orderId
	 *            ����ID
	 * 
	 * */
	public void finishOrder(long orderNo, Map<String, Integer> skuData, Double payPrice,String gifts, String note);

	/**
	 * ��ȡ���ۡ�
	 * 
	 * */
	public Appraises getAppraises(long orderNo);

}
