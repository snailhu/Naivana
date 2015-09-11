package com.nirvana.service;

import org.springframework.data.domain.Page;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.Inventory;

public interface InventoryService {

	/**
	 * 获取经销商库存的分页数据。
	 * 
	 * 权限：经销商。
	 * 
	 * */
	public Page<Inventory> getInventories(Dealer dealer, int page, int size);

	/**
	 * 根据品牌获取库存的分页数据。
	 * 
	 * 权限:经销商。
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand(Dealer dealer, String brandName, int page, int size);

	/**
	 * 获取经销商库存的分页数据。
	 * 
	 * 权限：经销商。
	 * 
	 * */
	public Page<Inventory> getInventoriesForUpdate(Dealer dealer, int page, int size);

	/**
	 * 根据品牌获取库存的分页数据。
	 * 
	 * 权限:经销商。
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrandForUpdate(Dealer dealer, String brandName, int page, int size);

	/**
	 * 更新销量。
	 * 
	 * */
	public void updateSellVol(Dealer dealer, Product product, int amount);

	/**
	 * 更新库存量。
	 * 
	 * */
	public void updateAmount(Dealer dealer, Product product, int amount);

}
