package com.nirvana.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;

public interface DealerOrderService {

	/**
	 * �����µĶ����š�
	 * 
	 * */
	public long operateNewOrderNo(Dealer dealer);

	/**
	 * �¶�����
	 * 
	 * */
	public DealerOrder placeOrder(Dealer dealer, Map<String, Integer> skus, boolean isAgentHelped, boolean isInline, MultipartFile picture);

	/**
	 * �¶�����
	 * 
	 * */
	public DealerOrder placeOrder(Dealer dealer, Map<String, Integer> skus, boolean isAgentHelped, MultipartFile picture);

	/**
	 * ɾ������֮ǰ������ɶ�����
	 * 
	 * */
	public void deleteTwoWeekAgoFinishedOrders();

	/**
	 * ��ɶ�����
	 * 
	 * */
	public void finishOrder(DealerOrder dealerOrder);

}
