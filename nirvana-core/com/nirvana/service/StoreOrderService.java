package com.nirvana.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;

public interface StoreOrderService {

	/**
	 * �����µĶ����š�
	 * 
	 * */
	public long operateNewOrderNo(Dealer dealer);

	/**
	 * ��ĳ���ŵ��¶�����
	 * 
	 * */
	public StoreOrder placeOrder(Store store, Map<String, Integer> items, boolean isAgentHelped, boolean isInLine, MultipartFile sign);

	/**
	 * ��ĳ���ŵ��¶�����
	 * 
	 * */
	public StoreOrder placeOrder(Store store, Map<String, Integer> items, boolean isAgentHelped, MultipartFile sign);

	/**
	 * �ύ������<br>
	 * �˲����Ƿ��̰߳�ȫ�ģ��������ڵ��ô˷���ǰӦ��Order���м�������
	 * 
	 * */
	public StoreOrder submitOrder(StoreOrder order);

	/**
	 * ɾ��2��֮ǰ������ɶ�����
	 * 
	 * */
	public void deleteTwoWeekAgoFinishedOrders();

}
