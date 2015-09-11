package com.nirvana.service;

import org.springframework.data.domain.Page;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.Inventory;

public interface InventoryService {

	/**
	 * ��ȡ�����̿��ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ������̡�
	 * 
	 * */
	public Page<Inventory> getInventories(Dealer dealer, int page, int size);

	/**
	 * ����Ʒ�ƻ�ȡ���ķ�ҳ���ݡ�
	 * 
	 * Ȩ��:�����̡�
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand(Dealer dealer, String brandName, int page, int size);

	/**
	 * ��ȡ�����̿��ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ������̡�
	 * 
	 * */
	public Page<Inventory> getInventoriesForUpdate(Dealer dealer, int page, int size);

	/**
	 * ����Ʒ�ƻ�ȡ���ķ�ҳ���ݡ�
	 * 
	 * Ȩ��:�����̡�
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrandForUpdate(Dealer dealer, String brandName, int page, int size);

	/**
	 * ����������
	 * 
	 * */
	public void updateSellVol(Dealer dealer, Product product, int amount);

	/**
	 * ���¿������
	 * 
	 * */
	public void updateAmount(Dealer dealer, Product product, int amount);

}
