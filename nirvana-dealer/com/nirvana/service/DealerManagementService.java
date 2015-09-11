package com.nirvana.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.MonthIncomeAndExpenditure;
import com.nirvana.domain.store.Store;

/**
 * 经销商的管理服务。包括账务查询，积分查询，一届门店的管理等等。
 * 
 * */
public interface DealerManagementService {

	/**
	 * 获取经销商分组的商店数据。
	 * 
	 * 权限：经销商
	 * 
	 * */
	public Map<String, List<Store>> getStoresGroupByCategory();

	/**
	 * 添加新的门店分类。
	 * 
	 * 权限：经销商
	 * 
	 * */
	public void addNewStoreCategory(String category);

	/**
	 * 获取经销商的所有门店分类。
	 * 
	 * 权限：经销商
	 * 
	 * */
	public List<DealerStoreCategory> getStoreCategories();

	/**
	 * 删除门店分类。
	 * 
	 * 权限：经销商
	 * 
	 * */
	public void deleteStoreCategory(String category);

	/**
	 * 重命名门店分类。
	 * 
	 * 权限：经销商
	 * 
	 * */
	public void renameStoreCategory(String category, String newName);

	/**
	 * 移动门店到某个分类。
	 * 
	 * 权限：经销商
	 * 
	 * */
	public void moveToCategory(String category, List<Long> storeIds);

	/**
	 * 获得收入支出统计数据。
	 * 
	 * */
	List<MonthIncomeAndExpenditure> getMonthIncomeAndExpenditures(Date start, Date end);

}
