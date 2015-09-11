package com.nirvana.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.service.pojo.InventoryCheck;

/**
 * �����̵Ŀ����ط���
 * 
 * */
public interface DealerInventoryService {

	/**
	 * ��ȡ�����̿��ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ������̡�
	 * 
	 * */
	public Page<Inventory> getInventories(int page, int size);

	/**
	 * ����Ʒ�ƻ�ȡ���ķ�ҳ���ݡ�
	 * 
	 * Ȩ��:�����̡�
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand(String brandName, int page, int size);

	/**
	 * ��ȡ�����̿��ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ������̡�
	 * 
	 * */
	public Page<Inventory> getInventoriesForUpdate(int page, int size);

	/**
	 * ����Ʒ�ƻ�ȡ���ķ�ҳ���ݡ�
	 * 
	 * Ȩ��:�����̡�
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrandForUpdate(String brandName, int page, int size);

	/**
	 * ���¿�档
	 * 
	 * */
	public void updateInventories(List<InventoryCheck> pojos, MultipartFile sign);

	/**
	 * ��ȡ����̵��¼��
	 * 
	 * */
	Page<InventoriesCheckHistory> getInventoriesHistory(Date start, Date end, int page, int size);

}
