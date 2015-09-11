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
 * 业务代表们所需做的日常工作相关服务。
 * 
 * 比如拜访八步骤之类。
 * 
 * */
public interface AgentWorkService {

	/**
	 * 获取管理的所有小区。
	 * 
	 * */
	public List<AgentArea> getManageAgentAreas();

	/**
	 * 获取某个业务员的今日的拜访路线。
	 * 
	 * @param employeeId
	 *            业务员ID（操作者）
	 * 
	 * */
	public VisitRoute getTodayVisitRoute(int agentAreaId);

	/**
	 * 获取一个直营店信息。
	 * 
	 * */
	public Dealer getOneDealerInfo(long dealerId);

	/**
	 * 获取一个门店信息。
	 * 
	 * */
	public Store getOneStoreInfo(long storeId);

	/**
	 * 下拜访订单。
	 * 
	 * @param employeeId
	 *            下单的业务员（操作者）
	 * @param recordId
	 *            操作的拜访记录ID
	 * @param orderItems
	 *            订单项
	 * @param picture
	 *            门店签字
	 * 
	 * @return 返回生成的订单ID
	 * 
	 * */
	public StoreOrder placeVisitOrder(VisitNodeType type, long storeId, Date date, Map<String, Integer> orderItems, MultipartFile picture);

	/**
	 * 同步拜访记录。
	 * 
	 * @##################基础信息（必要）##################
	 * @param employeeId
	 *            业务员的员工ID（操作者）
	 * @param date
	 *            拜访的时间
	 * @param nodeType
	 *            拜访节点类型
	 * @param storeId
	 *            拜访的门店或者直营店ID
	 * @param longitude
	 *            经度（必要）
	 * @param latitude
	 *            维度（必要）
	 * 
	 * @#################采集到的信息#################
	 * @param mainShelfInfoData
	 *            主货架信息
	 * @param deviceInfoDatas
	 *            设备信息
	 * @param secondShelfInfoData
	 *            第二陈列信息
	 * 
	 * @#################图片信息#################
	 * @param storeHeadPics
	 *            门头照片
	 * @param mainShelfPics
	 *            主货架照片
	 * @param secondShelfPics
	 *            第二陈列照片
	 * @param devicePics
	 *            设备照片
	 * @param filmPics
	 *            围膜照片
	 * @param posterPics
	 *            海报照片
	 * @param lightPics
	 *            灯箱照片
	 * @param otherPics
	 *            其他照片
	 * 
	 * @#################其他信息#################
	 * @param orderId
	 *            关联的订单ID
	 * @param isFinished
	 *            是否完成
	 * @param finishDate
	 *            完成时间
	 * 
	 * */
	public void synchronizeVisitRecord(
			/** 基础信息，必须。 */
			Date date,
			VisitNodeType nodeType,
			long storeId,
			float longitude,
			float latitude,
			/** 拜访采集到的信息。 */
			MainShelfInfoData mainShelfInfoData,
			Set<DeviceInfoData> deviceInfoDatas,
			SecondShelfInfoData secondShelfInfoData,
			/** 图片信息。 */
			MultipartFile[] storeHeadPics,
			MultipartFile[] mainShelfPics,
			MultipartFile[] secondShelfPics,
			MultipartFile[] devicePics,
			MultipartFile[] filmPics,
			MultipartFile[] posterPics,
			MultipartFile[] lightPics,
			MultipartFile[] otherPics,
			/** 其他信息。 */
			Boolean isFinished,
			Date finishDate);

	/**
	 * 设置备忘。
	 * 
	 * */
	public void setNotes(VisitNodeType type, long storeId, String notes);

	/**
	 * 下订单。
	 * 
	 * @param employeeId
	 *            下单的业务员（操作者）
	 * @param type
	 *            门店类型
	 * @param id
	 *            下单的门店或者直营店。
	 * @param orderItems
	 *            订单项
	 * @param picture
	 *            门店签字
	 * 
	 * */
	public StoreOrder placeOrder(VisitNodeType type, long storeId, Map<String, Integer> orderItems, MultipartFile picture);

	/**
	 * 提交门店订单。
	 * 
	 * */
	public StoreOrder submitOrder(long orderNo);

	/**
	 * 获取业务员的相关的分页门店order.
	 * 
	 * @param employeeId
	 *            业务员Id
	 * @param page
	 *            分页数
	 * @param pagesize
	 *            分页大小
	 * 
	 * */
	public Page<StoreOrder> getAgentPagedStoreOrders(int agentAreaId, int page, int pagesize);

	/**
	 * 获取业务员的相关的分页直营店order.
	 * 
	 * @param employeeId
	 *            业务员Id
	 * @param page
	 *            分页数
	 * @param pagesize
	 *            分页大小
	 * 
	 * */
	public Page<DealerOrder> getAgentPagedDealerOrders(int agentAreaId, int page, int pagesize);

	/**
	 * 获取业务员的相关的分页门店order.
	 * 
	 * @param employeeId
	 *            业务员Id
	 * @param page
	 *            分页数
	 * @param pagesize
	 *            分页大小
	 * 
	 * */
	public Page<StoreOrder> getAgentPagedStoreOrdersNotAgentHelped(int agentAreaId, int page, int pagesize);

	/**
	 * 获取业务员的相关的分页直营店order.
	 * 
	 * @param employeeId
	 *            业务员Id
	 * @param page
	 *            分页数
	 * @param pagesize
	 *            分页大小
	 * 
	 * */
	public Page<DealerOrder> getAgentPagedDealerOrdersNotAgentHelped(int agentAreaId, int page, int pagesize);

	/**
	 * 获取业务员的相关的分页门店order.
	 * 
	 * @param employeeId
	 *            业务员Id
	 * @param page
	 *            分页数
	 * @param pagesize
	 *            分页大小
	 * 
	 * */
	public Page<StoreOrder> getAgentPagedStoreOrdersNotAgentHelpedByDate(int agentAreaId, Date start, Date end, int page, int pagesize);

	/**
	 * 获取业务员的相关的分页直营店order.
	 * 
	 * @param employeeId
	 *            业务员Id
	 * @param page
	 *            分页数
	 * @param pagesize
	 *            分页大小
	 * 
	 * */
	public Page<DealerOrder> getAgentPagedDealerOrdersNotAgentHelpedByDate(int agentAreaId, Date start, Date end, int page, int pagesize);

	/**
	 * 获取业务员NR目标页面数据。
	 * 
	 * @param agentAreaId
	 *            要获取的CR区ID。
	 * 
	 * */
	public AppAgentNrGoalResult getNrGoal(int agentAreaId);

	/**
	 * 设置业务员本周的目标。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param nr
	 *            销量
	 * 
	 * */
	public void setThisWeekGoals(int agentAreaId, float nr);

	/**
	 * 设置业务员本日的目标。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param nr
	 *            销量
	 * 
	 * */
	public void setThisDayGoals(int agentAreaId, float nr);

	/**
	 * 获取本月的sdo信息。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * */
	public List<Sdo> getThisMonthSdos(int agentAreaId);

	/**
	 * 获取业务员手机端的生产力统计信息。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * */
	public AppProductivityStatsResult getProductivityStatsResult(int agentAreaId);

	/**
	 * 获取业务员手机端的销量统计信息。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * */
	public AppSalesVolumeStatsResult getSalesVolumeStatsResult(int agentAreaId);

	/**
	 * 获取此业务员管理的CR区的门店分类信息。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * */
	public List<AgentStoreCategory> getStoreCategories(int agentAreaId);

	/**
	 * 根据类别获取门店的分组数据。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * @return Map<分类名, List<门店>>
	 * 
	 * */
	public Map<String, List<Store>> getStoresGroupByCategories(int agentAreaId);

	/**
	 * 根据类别名称获取门店。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * 
	 * */
	public List<Store> getStoresByCategory(int agentAreaId, String category);

	/**
	 * 添加门店分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * 
	 */
	public void addNewStoreCategory(int agentAreaId, String category);

	/**
	 * 删除门店分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * 
	 */
	public void deleteStoreCategory(int agentAreaId, String category);

	/**
	 * 重命名门店分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * @param newName
	 *            分类新名称
	 * 
	 * 
	 */
	public void renameStoreCategory(int agentAreaId, String category, String newName);

	/**
	 * 移动某个门店到某个分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param storeId
	 *            要移动的门店ID
	 * @param category
	 *            分类名称
	 * 
	 * 
	 * */
	public void moveStoreToCategory(int agentAreaId, long storeId, String category);

	/**
	 * 批量移动门店到某个分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param storeId
	 *            要移动的门店ID
	 * @param category
	 *            分类名称
	 * 
	 * */
	public void moveStoresToCategory(int agentAreaId, List<Long> storeIds, String category);

	/* ######################################################################### */
	/* ######################################################################### */

	/**
	 * 获取此业务员管理的CR区的直营门店分类信息。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * */
	public List<AgentDealerCategory> getDealerCategories(int agentAreaId);

	/**
	 * 根据类别获取直营门店的分组数据。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * @return Map<分类名, List<门店>>
	 * 
	 * */
	public Map<String, List<Dealer>> getDealersGroupByCategories(int agentAreaId);

	/**
	 * 根据类别名称获取直营门店。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * 
	 * */
	public List<Dealer> getDealersByCategory(int agentAreaId, String category);

	/**
	 * 添加直营门店分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * 
	 */
	public void addNewDealerCategory(int agentAreaId, String category);

	/**
	 * 删除直营门店分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * 
	 */
	public void deleteDealerCategory(int agentAreaId, String category);

	/**
	 * 重命名直营门店分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param category
	 *            分类名称
	 * @param newName
	 *            分类新名称
	 * 
	 * 
	 */
	public void renameDealerCategory(int agentAreaId, String category, String newName);

	/**
	 * 移动某个直营门店到某个分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param storeId
	 *            要移动的门店ID
	 * @param category
	 *            分类名称
	 * 
	 * 
	 * */
	public void moveDealerToCategory(int agentAreaId, long dealerId, String category);

	/**
	 * 批量移动直营门店到某个分类。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * @param storeId
	 *            要移动的门店ID
	 * @param category
	 *            分类名称
	 * 
	 * */
	public void moveDealersToCategory(int agentAreaId, List<Long> dealerIds, String category);

	/**
	 * 获取某个业务员的所有备忘录。
	 * 
	 * @param employeeId
	 *            业务员的员工ID(操作者)
	 * 
	 * */
	public List<Memorandum> getMemorandums(int agentAreaId);

	/**
	 * 添加备忘录。
	 * 
	 * @param employeeId
	 *            业务员的员工ID
	 * @param content
	 *            内容
	 * 
	 * */
	public void addMemorandum(int agentAreaId, String content);

	/**
	 * 编辑某业务员的某条备忘录。
	 * 
	 * @param employeeId
	 *            此业务员的员工的ID(操作者)
	 * @param memrdId
	 *            备忘录的ID
	 * @param content
	 *            修改的内容
	 * 
	 * 
	 * */
	public void editMemorandum(int agentAreaId, long memrdId, String content);

	/**
	 * 删除某业务员的某条备忘录。
	 * 
	 * @param employeeId
	 *            此业务员的员工的ID(操作者)
	 * 
	 * @param memrdId
	 *            备忘录的ID
	 * 
	 * 
	 * */
	public void deleteMemorandum(int agentAreaId, long memrdId);

	/**
	 * 盘点库存。
	 * 
	 * */
	public void checkInventores(long dealerId, List<InventoryChange> changes, MultipartFile sign);

	/**
	 * 获取库存盘点记录。
	 * 
	 * */
	public Page<InventoriesCheckHistory> getCheckHistories(long dealerId, Date start, Date end, int page, int size);

	/**
	 * 获取和此CR区关联的经销商。
	 * 
	 * */
	public List<Dealer> getRelationDealers(int agentAreaId);

	/**
	 * 获取门店的经销商库存。
	 * 
	 * */
	public List<Inventory> getStoresDealersProducts(long storeId);

	/**
	 * 获取经销商库存用于更新。
	 * 
	 * */
	public Page<Inventory> getInventories(long dealerId, int page, int size);

	/**
	 * 获取经销商库存用于更新。
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand(long dealerId, String brandName, int page, int size);

	/**
	 * 获取经销商库存用于更新。
	 * 
	 * */
	public Page<Inventory> getInventories4Update(long dealerId, int page, int size);

	/**
	 * 获取经销商库存用于更新。
	 * 
	 * */
	public Page<Inventory> getInventoriesByBrand4Update(long dealerId, String brandName, int page, int size);

}
