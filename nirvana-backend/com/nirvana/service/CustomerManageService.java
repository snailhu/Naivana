package com.nirvana.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.service.pojo.web.StoreInfoData;

/**
 * 客户管理相关服务。
 * 
 * 冻结，添加，修改等等。
 * 
 * */
public interface CustomerManageService {

	/**
	 * 获取所有经销商的分页数据。
	 * 
	 * 权限：文员,SIS,管理员。
	 * 
	 * 文员只可以看到自己所拥有的经销商。
	 * 
	 * */
	public Page<Dealer> getDealers(int page, int size);

	/**
	 * 获取所有直营店的分页数据。
	 * 
	 * 权限：文员,SIS,管理员。
	 * 
	 * 文员只可以看到自己所拥有的直营店。
	 * 
	 * */
	public Page<Dealer> getDirectStores(int page, int size);

	/**
	 * 关闭经销商或者直营店。
	 * 
	 * 权限：SIS,管理员。
	 * 
	 * */
	public void closeDealer(long dealerId, String closeReason);

	/**
	 * 重新开放经销商或者直营店。
	 * 
	 * 权限：SIS，管理员。
	 * 
	 * */
	public void openDealer(long dealerId);

	/**
	 * 获取单个的经销商或者直营店信息。
	 * 
	 * 权限：文员，SIS，管理员。
	 * 
	 */
	public Dealer getOneDealer(long dealerId);

	/**
	 * 获取某个经销商的下属门店。
	 * 
	 * 权限：文员，SIS，管理员。
	 * 
	 * */
	public List<Store> getDealerStores(long dealerId);

	/**
	 * 获取可分配经销商的门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public List<Store> getNotAllotedStores();

	/**
	 * 给经销商分配门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public void allotStoresToDealer(long dealerId, List<Long> outIds, List<Long> inIds);

	/**
	 * 从ERP中手动同步此经销商或者直营店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public Dealer synchronizeDealer(long dealerId);

	/**
	 * 通过ERPCODE从ERP中手动同步此经销商或者直营店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public Dealer synchronizeDealer(String erpCode);

	/**
	 * 编辑经销商或者直营店的账户信息。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public void editDealerAccount(long dealerId, String username, String password, String phoneNum,Boolean isClose,String closeReason);

	/**
	 * 获取所有门店的分页数据。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public Page<Store> getStores(int page, int size);

	/**
	 * 关闭门店用户。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public void closeStore(long storeId, String closeReason);

	/**
	 * 重新开放某个门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public void openStore(long storeId);

	/**
	 * 编辑门店账户信息。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * @param storeId
	 *            门店ID
	 * @param phoneNum
	 *            联系电话
	 * @param password
	 *            密码
	 * 
	 * */
	public void editStoreAccount(long storeId, String username, String password, String phoneNum);

	/**
	 * 编辑门店基础信息。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * @param storeId
	 *            门店ID
	 * 
	 * @param data
	 *            门店基础信息
	 * 
	 * */
	public void editStoreInfo(long storeId, StoreInfoData data);
	
	/**
	 * 编辑门店基础信息。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * @param storeId
	 *            门店ID
	 * 
	 * @param data
	 *            门店基础信息
	 * 
	 * */
	public void editStoreInfoWithClose(long storeId, StoreInfoData data,Boolean isClose,String closeReason);

	/**
	 * 获取单个门店信息
	 * 
	 * 权限：文员，管理员。
	 * 
	 * @param StoreId
	 *            门店ID
	 */
	public Store getOneStore(long storeId);

	/**
	 * 添加新的门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	public void addNewStore(String username, String password, String phoneNum, StoreInfoData data);

}
