package com.nirvana.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.service.pojo.web.StoreInfoData;

/**
 * �ͻ�������ط���
 * 
 * ���ᣬ��ӣ��޸ĵȵȡ�
 * 
 * */
public interface CustomerManageService {

	/**
	 * ��ȡ���о����̵ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ���Ա,SIS,����Ա��
	 * 
	 * ��Աֻ���Կ����Լ���ӵ�еľ����̡�
	 * 
	 * */
	public Page<Dealer> getDealers(int page, int size);

	/**
	 * ��ȡ����ֱӪ��ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ���Ա,SIS,����Ա��
	 * 
	 * ��Աֻ���Կ����Լ���ӵ�е�ֱӪ�ꡣ
	 * 
	 * */
	public Page<Dealer> getDirectStores(int page, int size);

	/**
	 * �رվ����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ�SIS,����Ա��
	 * 
	 * */
	public void closeDealer(long dealerId, String closeReason);

	/**
	 * ���¿��ž����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ�SIS������Ա��
	 * 
	 * */
	public void openDealer(long dealerId);

	/**
	 * ��ȡ�����ľ����̻���ֱӪ����Ϣ��
	 * 
	 * Ȩ�ޣ���Ա��SIS������Ա��
	 * 
	 */
	public Dealer getOneDealer(long dealerId);

	/**
	 * ��ȡĳ�������̵������ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա��SIS������Ա��
	 * 
	 * */
	public List<Store> getDealerStores(long dealerId);

	/**
	 * ��ȡ�ɷ��侭���̵��ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public List<Store> getNotAllotedStores();

	/**
	 * �������̷����ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public void allotStoresToDealer(long dealerId, List<Long> outIds, List<Long> inIds);

	/**
	 * ��ERP���ֶ�ͬ���˾����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public Dealer synchronizeDealer(long dealerId);

	/**
	 * ͨ��ERPCODE��ERP���ֶ�ͬ���˾����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public Dealer synchronizeDealer(String erpCode);

	/**
	 * �༭�����̻���ֱӪ����˻���Ϣ��
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public void editDealerAccount(long dealerId, String username, String password, String phoneNum,Boolean isClose,String closeReason);

	/**
	 * ��ȡ�����ŵ�ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public Page<Store> getStores(int page, int size);

	/**
	 * �ر��ŵ��û���
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public void closeStore(long storeId, String closeReason);

	/**
	 * ���¿���ĳ���ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public void openStore(long storeId);

	/**
	 * �༭�ŵ��˻���Ϣ��
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * @param storeId
	 *            �ŵ�ID
	 * @param phoneNum
	 *            ��ϵ�绰
	 * @param password
	 *            ����
	 * 
	 * */
	public void editStoreAccount(long storeId, String username, String password, String phoneNum);

	/**
	 * �༭�ŵ������Ϣ��
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * @param storeId
	 *            �ŵ�ID
	 * 
	 * @param data
	 *            �ŵ������Ϣ
	 * 
	 * */
	public void editStoreInfo(long storeId, StoreInfoData data);
	
	/**
	 * �༭�ŵ������Ϣ��
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * @param storeId
	 *            �ŵ�ID
	 * 
	 * @param data
	 *            �ŵ������Ϣ
	 * 
	 * */
	public void editStoreInfoWithClose(long storeId, StoreInfoData data,Boolean isClose,String closeReason);

	/**
	 * ��ȡ�����ŵ���Ϣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * @param StoreId
	 *            �ŵ�ID
	 */
	public Store getOneStore(long storeId);

	/**
	 * ����µ��ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	public void addNewStore(String username, String password, String phoneNum, StoreInfoData data);

}
