package com.nirvana.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.Memorandum;
import com.nirvana.domain.backend.VisitNodeType;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.service.pojo.app.AppAgentNrGoalResult;
import com.nirvana.service.pojo.app.AppProductivityStatsResult;
import com.nirvana.service.pojo.app.AppSalesVolumeStatsResult;
import com.nirvana.service.pojo.app.InventoryChange;
import com.nirvana.service.pojo.web.DeviceInfoData;
import com.nirvana.service.pojo.web.MainShelfInfoData;
import com.nirvana.service.pojo.web.SecondShelfInfoData;

/**
 * ҵ����������������ճ�������ط���
 * 
 * ����ݷð˲���֮�ࡣ
 * 
 * */
public interface AgentWorkService {

	/**
	 * ��ȡ���������С����
	 * 
	 * */
	public List<AgentArea> getManageAgentAreas();

	/**
	 * ��ȡĳ��ҵ��Ա�Ľ��յİݷ�·�ߡ�
	 * 
	 * @param employeeId
	 *            ҵ��ԱID�������ߣ�
	 * 
	 * */
	public VisitRoute getTodayVisitRoute(int agentAreaId);

	/**
	 * ��ȡһ��ֱӪ����Ϣ��
	 * 
	 * */
	public Dealer getOneDealerInfo(long dealerId);

	/**
	 * ��ȡһ���ŵ���Ϣ��
	 * 
	 * */
	public Store getOneStoreInfo(long storeId);

	/**
	 * �°ݷö�����
	 * 
	 * @param employeeId
	 *            �µ���ҵ��Ա�������ߣ�
	 * @param recordId
	 *            �����İݷü�¼ID
	 * @param orderItems
	 *            ������
	 * @param picture
	 *            �ŵ�ǩ��
	 * 
	 * @return �������ɵĶ���ID
	 * 
	 * */
	public StoreOrder placeVisitOrder(VisitNodeType type, long storeId, Date date, Map<String, Integer> orderItems, MultipartFile picture);

	/**
	 * ͬ���ݷü�¼��
	 * 
	 * @##################������Ϣ����Ҫ��##################
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID�������ߣ�
	 * @param date
	 *            �ݷõ�ʱ��
	 * @param nodeType
	 *            �ݷýڵ�����
	 * @param storeId
	 *            �ݷõ��ŵ����ֱӪ��ID
	 * @param longitude
	 *            ���ȣ���Ҫ��
	 * @param latitude
	 *            ά�ȣ���Ҫ��
	 * 
	 * @#################�ɼ�������Ϣ#################
	 * @param mainShelfInfoData
	 *            ��������Ϣ
	 * @param deviceInfoDatas
	 *            �豸��Ϣ
	 * @param secondShelfInfoData
	 *            �ڶ�������Ϣ
	 * 
	 * @#################ͼƬ��Ϣ#################
	 * @param storeHeadPics
	 *            ��ͷ��Ƭ
	 * @param mainShelfPics
	 *            ��������Ƭ
	 * @param secondShelfPics
	 *            �ڶ�������Ƭ
	 * @param devicePics
	 *            �豸��Ƭ
	 * @param filmPics
	 *            ΧĤ��Ƭ
	 * @param posterPics
	 *            ������Ƭ
	 * @param lightPics
	 *            ������Ƭ
	 * @param otherPics
	 *            ������Ƭ
	 * 
	 * @#################������Ϣ#################
	 * @param orderId
	 *            �����Ķ���ID
	 * @param isFinished
	 *            �Ƿ����
	 * @param finishDate
	 *            ���ʱ��
	 * 
	 * */
	public void synchronizeVisitRecord(
			/** ������Ϣ�����롣 */
			Date date,
			VisitNodeType nodeType,
			long storeId,
			float longitude,
			float latitude,
			/** �ݷòɼ�������Ϣ�� */
			MainShelfInfoData mainShelfInfoData,
			Set<DeviceInfoData> deviceInfoDatas,
			SecondShelfInfoData secondShelfInfoData,
			/** ͼƬ��Ϣ�� */
			MultipartFile[] storeHeadPics,
			MultipartFile[] mainShelfPics,
			MultipartFile[] secondShelfPics,
			MultipartFile[] devicePics,
			MultipartFile[] filmPics,
			MultipartFile[] posterPics,
			MultipartFile[] lightPics,
			MultipartFile[] otherPics,
			/** ������Ϣ�� */
			Boolean isFinished,
			Date finishDate);

	/**
	 * ���ñ�����
	 * 
	 * */
	public void setNotes(VisitNodeType type, long storeId, String notes);

	/**
	 * �¶�����
	 * 
	 * @param employeeId
	 *            �µ���ҵ��Ա�������ߣ�
	 * @param type
	 *            �ŵ�����
	 * @param id
	 *            �µ����ŵ����ֱӪ�ꡣ
	 * @param orderItems
	 *            ������
	 * @param picture
	 *            �ŵ�ǩ��
	 * 
	 * */
	public StoreOrder placeOrder(VisitNodeType type, long storeId, Map<String, Integer> orderItems, MultipartFile picture);

	/**
	 * �ύ�ŵ궩����
	 * 
	 * */
	public StoreOrder submitOrder(long orderNo);

	/**
	 * ��ȡҵ��Ա����صķ�ҳ�ŵ�order.
	 * 
	 * @param employeeId
	 *            ҵ��ԱId
	 * @param page
	 *            ��ҳ��
	 * @param pagesize
	 *            ��ҳ��С
	 * 
	 * */
	public Page<StoreOrder> getAgentPagedStoreOrders(int agentAreaId, int page, int pagesize);

	/**
	 * ��ȡҵ��Ա����صķ�ҳֱӪ��order.
	 * 
	 * @param employeeId
	 *            ҵ��ԱId
	 * @param page
	 *            ��ҳ��
	 * @param pagesize
	 *            ��ҳ��С
	 * 
	 * */
	public Page<DealerOrder> getAgentPagedDealerOrders(int agentAreaId, int page, int pagesize);

	/**
	 * ��ȡҵ��Ա����صķ�ҳ�ŵ�order.
	 * 
	 * @param employeeId
	 *            ҵ��ԱId
	 * @param page
	 *            ��ҳ��
	 * @param pagesize
	 *            ��ҳ��С
	 * 
	 * */
	public Page<StoreOrder> getAgentPagedStoreOrdersNotAgentHelped(int agentAreaId, int page, int pagesize);

	/**
	 * ��ȡҵ��Ա����صķ�ҳֱӪ��order.
	 * 
	 * @param employeeId
	 *            ҵ��ԱId
	 * @param page
	 *            ��ҳ��
	 * @param pagesize
	 *            ��ҳ��С
	 * 
	 * */
	public Page<DealerOrder> getAgentPagedDealerOrdersNotAgentHelped(int agentAreaId, int page, int pagesize);

	/**
	 * ��ȡҵ��Ա����صķ�ҳ�ŵ�order.
	 * 
	 * @param employeeId
	 *            ҵ��ԱId
	 * @param page
	 *            ��ҳ��
	 * @param pagesize
	 *            ��ҳ��С
	 * 
	 * */
	public Page<StoreOrder> getAgentPagedStoreOrdersNotAgentHelpedByDate(int agentAreaId, Date start, Date end, int page, int pagesize);

	/**
	 * ��ȡҵ��Ա����صķ�ҳֱӪ��order.
	 * 
	 * @param employeeId
	 *            ҵ��ԱId
	 * @param page
	 *            ��ҳ��
	 * @param pagesize
	 *            ��ҳ��С
	 * 
	 * */
	public Page<DealerOrder> getAgentPagedDealerOrdersNotAgentHelpedByDate(int agentAreaId, Date start, Date end, int page, int pagesize);

	/**
	 * ��ȡҵ��ԱNRĿ��ҳ�����ݡ�
	 * 
	 * @param agentAreaId
	 *            Ҫ��ȡ��CR��ID��
	 * 
	 * */
	public AppAgentNrGoalResult getNrGoal(int agentAreaId);

	/**
	 * ����ҵ��Ա���ܵ�Ŀ�ꡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param nr
	 *            ����
	 * 
	 * */
	public void setThisWeekGoals(int agentAreaId, float nr);

	/**
	 * ����ҵ��Ա���յ�Ŀ�ꡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param nr
	 *            ����
	 * 
	 * */
	public void setThisDayGoals(int agentAreaId, float nr);

	/**
	 * ��ȡ���µ�sdo��Ϣ��
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * */
	public List<Sdo> getThisMonthSdos(int agentAreaId);

	/**
	 * ��ȡҵ��Ա�ֻ��˵�������ͳ����Ϣ��
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * */
	public AppProductivityStatsResult getProductivityStatsResult(int agentAreaId);

	/**
	 * ��ȡҵ��Ա�ֻ��˵�����ͳ����Ϣ��
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * */
	public AppSalesVolumeStatsResult getSalesVolumeStatsResult(int agentAreaId);

	/**
	 * ��ȡ��ҵ��Ա�����CR�����ŵ������Ϣ��
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * */
	public List<AgentStoreCategory> getStoreCategories(int agentAreaId);

	/**
	 * ��������ȡ�ŵ�ķ������ݡ�
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * @return Map<������, List<�ŵ�>>
	 * 
	 * */
	public Map<String, List<Store>> getStoresGroupByCategories(int agentAreaId);

	/**
	 * ����������ƻ�ȡ�ŵꡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * 
	 * */
	public List<Store> getStoresByCategory(int agentAreaId, String category);

	/**
	 * ����ŵ���ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * 
	 */
	public void addNewStoreCategory(int agentAreaId, String category);

	/**
	 * ɾ���ŵ���ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * 
	 */
	public void deleteStoreCategory(int agentAreaId, String category);

	/**
	 * �������ŵ���ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * @param newName
	 *            ����������
	 * 
	 * 
	 */
	public void renameStoreCategory(int agentAreaId, String category, String newName);

	/**
	 * �ƶ�ĳ���ŵ굽ĳ�����ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param storeId
	 *            Ҫ�ƶ����ŵ�ID
	 * @param category
	 *            ��������
	 * 
	 * 
	 * */
	public void moveStoreToCategory(int agentAreaId, long storeId, String category);

	/**
	 * �����ƶ��ŵ굽ĳ�����ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param storeId
	 *            Ҫ�ƶ����ŵ�ID
	 * @param category
	 *            ��������
	 * 
	 * */
	public void moveStoresToCategory(int agentAreaId, List<Long> storeIds, String category);

	/* ######################################################################### */
	/* ######################################################################### */

	/**
	 * ��ȡ��ҵ��Ա�����CR����ֱӪ�ŵ������Ϣ��
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * */
	public List<AgentDealerCategory> getDealerCategories(int agentAreaId);

	/**
	 * ��������ȡֱӪ�ŵ�ķ������ݡ�
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * @return Map<������, List<�ŵ�>>
	 * 
	 * */
	public Map<String, List<Dealer>> getDealersGroupByCategories(int agentAreaId);

	/**
	 * ����������ƻ�ȡֱӪ�ŵꡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * 
	 * */
	public List<Dealer> getDealersByCategory(int agentAreaId, String category);

	/**
	 * ���ֱӪ�ŵ���ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * 
	 */
	public void addNewDealerCategory(int agentAreaId, String category);

	/**
	 * ɾ��ֱӪ�ŵ���ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * 
	 */
	public void deleteDealerCategory(int agentAreaId, String category);

	/**
	 * ������ֱӪ�ŵ���ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param category
	 *            ��������
	 * @param newName
	 *            ����������
	 * 
	 * 
	 */
	public void renameDealerCategory(int agentAreaId, String category, String newName);

	/**
	 * �ƶ�ĳ��ֱӪ�ŵ굽ĳ�����ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param storeId
	 *            Ҫ�ƶ����ŵ�ID
	 * @param category
	 *            ��������
	 * 
	 * 
	 * */
	public void moveDealerToCategory(int agentAreaId, long dealerId, String category);

	/**
	 * �����ƶ�ֱӪ�ŵ굽ĳ�����ࡣ
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * @param storeId
	 *            Ҫ�ƶ����ŵ�ID
	 * @param category
	 *            ��������
	 * 
	 * */
	public void moveDealersToCategory(int agentAreaId, List<Long> dealerIds, String category);

	/**
	 * ��ȡĳ��ҵ��Ա�����б���¼��
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID(������)
	 * 
	 * */
	public List<Memorandum> getMemorandums(int agentAreaId);

	/**
	 * ��ӱ���¼��
	 * 
	 * @param employeeId
	 *            ҵ��Ա��Ա��ID
	 * @param content
	 *            ����
	 * 
	 * */
	public void addMemorandum(int agentAreaId, String content);

	/**
	 * �༭ĳҵ��Ա��ĳ������¼��
	 * 
	 * @param employeeId
	 *            ��ҵ��Ա��Ա����ID(������)
	 * @param memrdId
	 *            ����¼��ID
	 * @param content
	 *            �޸ĵ�����
	 * 
	 * 
	 * */
	public void editMemorandum(int agentAreaId, long memrdId, String content);

	/**
	 * ɾ��ĳҵ��Ա��ĳ������¼��
	 * 
	 * @param employeeId
	 *            ��ҵ��Ա��Ա����ID(������)
	 * 
	 * @param memrdId
	 *            ����¼��ID
	 * 
	 * 
	 * */
	public void deleteMemorandum(int agentAreaId, long memrdId);

	/**
	 * �̵��档
	 * 
	 * */
	public void checkInventores(long dealerId, List<InventoryChange> changes, MultipartFile sign);

	/**
	 * ��ȡ����̵��¼��
	 * 
	 * */
	public Page<InventoriesCheckHistory> getCheckHistories(long dealerId, Date start, Date end, int page, int size);

	/**
	 * ��ȡ�ʹ�CR�������ľ����̡�
	 * 
	 * */
	public List<Dealer> getRelationDealers(int agentAreaId);

	/**
	 * ��ȡ�ŵ�ľ����̿�档
	 * 
	 * */
	public List<Inventory> getStoresDealersProducts(long storeId);

	/**
	 * ��ȡ�����̿�����ڸ��¡�
	 * 
	 * */
	public Page<Inventory> getInventories(long dealerId, int page, int size);

	/**
	 * ��ȡ�����̿�����ڸ��¡�
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand(long dealerId, String brandName, int page, int size);

	/**
	 * ��ȡ�����̿�����ڸ��¡�
	 * 
	 * */
	public Page<Inventory> getInventories4Update(long dealerId, int page, int size);

	/**
	 * ��ȡ�����̿�����ڸ��¡�
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand4Update(long dealerId, String brandName, int page, int size);

}
