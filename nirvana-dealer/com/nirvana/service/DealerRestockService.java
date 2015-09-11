package com.nirvana.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderState;
import com.nirvana.mongo.document.DealerOrderDocument;

/**
 * �����̽�������ط���
 * 
 * */
public interface DealerRestockService {

	/**
	 * ��ȡ��Ʒ�б�
	 * 
	 * */
	public Page<Product> getProduct(int page, int size);

	/**
	 * ����Ʒ�ƻ�ȡ��Ʒ�б�
	 * 
	 * */
	public Page<Product> getProduct(String brand, int page, int size);

	/**
	 * �¶�����
	 * 
	 * @param dealerId
	 *            ������ID�������ߣ�
	 * @param skuData
	 *            ��Ʒ����
	 * @param sign
	 * 
	 * */
	public DealerOrder placeOrder(Map<String, Integer> skuData, MultipartFile sign);

	/**
	 * ����״̬��ȡ������ֻ���ܳ���CLOSED,CANCELED.�����״̬������
	 * 
	 */
	Page<DealerOrder> getNotFinishedOrders(DealerOrderState state, int page, int size);

	/**
	 * ����״̬��ȡ������ֻ���ܲ�����CLOSED,CANCELED.
	 * 
	 * */
	Page<DealerOrderDocument> getFinishedOrders(DealerOrderState state, int page, int size);

}
