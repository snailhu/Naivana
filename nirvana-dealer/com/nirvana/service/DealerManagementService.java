package com.nirvana.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.MonthIncomeAndExpenditure;
import com.nirvana.domain.store.Store;

/**
 * �����̵Ĺ�����񡣰��������ѯ�����ֲ�ѯ��һ���ŵ�Ĺ���ȵȡ�
 * 
 * */
public interface DealerManagementService {

	/**
	 * ��ȡ�����̷�����̵����ݡ�
	 * 
	 * Ȩ�ޣ�������
	 * 
	 * */
	public Map<String, List<Store>> getStoresGroupByCategory();

	/**
	 * ����µ��ŵ���ࡣ
	 * 
	 * Ȩ�ޣ�������
	 * 
	 * */
	public void addNewStoreCategory(String category);

	/**
	 * ��ȡ�����̵������ŵ���ࡣ
	 * 
	 * Ȩ�ޣ�������
	 * 
	 * */
	public List<DealerStoreCategory> getStoreCategories();

	/**
	 * ɾ���ŵ���ࡣ
	 * 
	 * Ȩ�ޣ�������
	 * 
	 * */
	public void deleteStoreCategory(String category);

	/**
	 * �������ŵ���ࡣ
	 * 
	 * Ȩ�ޣ�������
	 * 
	 * */
	public void renameStoreCategory(String category, String newName);

	/**
	 * �ƶ��ŵ굽ĳ�����ࡣ
	 * 
	 * Ȩ�ޣ�������
	 * 
	 * */
	public void moveToCategory(String category, List<Long> storeIds);

	/**
	 * �������֧��ͳ�����ݡ�
	 * 
	 * */
	List<MonthIncomeAndExpenditure> getMonthIncomeAndExpenditures(Date start, Date end);

}
