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
 * �̵������ط���
 * 
 * */
public interface StoreRestrockService {

	/**
	 * ��ȡ�ɹ������Ʒ��
	 * 
	 * */
	public Page<Inventory> getProducts(int page, int size);

	/**
	 * ����Ʒ�ƻ�ȡ�ɹ������Ʒ��
	 * 
	 * */
	public Page<Inventory> getProducts(String brandCode, int page, int size);

	/**
	 * ������Ʒ�����ȡ��Ʒ��
	 * 
	 * */
	public Inventory getProduct(String productCode);

	/**
	 * �¶�����
	 * 
	 * */
	public StoreOrder placeOrder(Map<String, Integer> items);

	/**
	 * �ύ������
	 * 
	 * */
	public StoreOrder submitOrder(long orderNo, MultipartFile sign);

	/**
	 * ȡ��������
	 * 
	 * */
	public void cancelOrder(long orderNo);

	/**
	 * ��ȡ�������顣
	 * 
	 * */
	public StoreOrder getOrder(long orderNo);

	/**
	 * ��ȡȫ��������
	 * 
	 * */
	public Page<StoreOrder> getOrders(int page, int size);

	/**
	 * ���ݶ���״̬��ȡ������
	 * 
	 * */
	public Page<StoreOrder> getOrders(StoreOrderState state, int page, int size);
	
	public Page<StoreOrder> getOrdersByMongo(StoreOrderState state, int page, int size);

	/**
	 * ����ʱ���ȡ������
	 * 
	 * */
	public Page<StoreOrder> getOrders(Date startDate, Date endDate, int page, int size);
	
	/**
	 * ����ʱ���ȡ�˵���
	 * 
	 * */
	public Page<StoreOrderDocument> getOrdersBill(Date startDate, Date endDate, int page, int size);

	/**
	 * ���۶�����
	 * 
	 * */
	public void appraise(long orderNo, int product, int delivery, int manner, String note);
}
