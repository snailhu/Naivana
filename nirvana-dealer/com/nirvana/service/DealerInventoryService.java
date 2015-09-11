package com.nirvana.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.service.pojo.InventoryCheck;

/**
 * 经销商的库存相关服务。
 * 
 * */
public interface DealerInventoryService {

	/**
	 * 获取经销商库存的分页数据。
	 * 
	 * 权限：经销商。
	 * 
	 * */
	public Page<Inventory> getInventories(int page, int size);

	/**
	 * 根据品牌获取库存的分页数据。
	 * 
	 * 权限:经销商。
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand(String brandName, int page, int size);

	/**
	 * 获取经销商库存的分页数据。
	 * 
	 * 权限：经销商。
	 * 
	 * */
	public Page<Inventory> getInventoriesForUpdate(int page, int size);

	/**
	 * 根据品牌获取库存的分页数据。
	 * 
	 * 权限:经销商。
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrandForUpdate(String brandName, int page, int size);

	/**
	 * 更新库存。
	 * 
	 * */
	public void updateInventories(List<InventoryCheck> pojos, MultipartFile sign);

	/**
	 * 获取库存盘点记录。
	 * 
	 * */
	Page<InventoriesCheckHistory> getInventoriesHistory(Date start, Date end, int page, int size);

}
