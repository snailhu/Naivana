package com.nirvana.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.domain.backend.goal.sdo.AgentAreaSdoGoal;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.backend.goal.sdo.SdoExcel;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.store.Store;
import com.nirvana.mongo.document.VisitRecordDocument;
import com.nirvana.service.pojo.web.BigAreaGoalResult;
import com.nirvana.service.pojo.web.DirectAreaGoalResult;
import com.nirvana.service.pojo.web.GlobalGoalResult;
import com.nirvana.service.pojo.web.ManagerAreaGoalResult;
import com.nirvana.service.pojo.web.NodeSet;
import com.nirvana.service.pojo.web.WebProductivityStatsResult;
import com.nirvana.service.pojo.web.WebSalesVolumeStatsResult;

/**
 * 业务代表管理模块。
 * 
 * 分配工作，路线，门店等等。
 * 
 * */
public interface AgentManageService {

	/**
	 * 获取管理的主任区。
	 * 
	 * 权限：主任。
	 * 
	 * */
	public List<DirectorArea> getManagedDirectorArea();

	/**
	 * 获取管理的职位。（SIS,大区经理,经理区经理,主任区经理）
	 * 
	 * 权限：SIS,大区经理，经理区经理，主任区经理。
	 * 
	 * */
	public List<Position> getHasPositions();

	/**
	 * 获取分页的小区数据。
	 * 
	 * 权限：SIS,文员。
	 * 
	 * @param employeeId
	 *            操作者员工ID，如果操作者为SIS,那么将看到所有小区，如果操作者为文员，那么将看到自己下面的小区。
	 * @param page
	 *            页数
	 * @param size
	 *            分页大小
	 * 
	 * */
	public Page<AgentArea> getAgentAreas(int page, int size);

	/**
	 * 关闭小区。
	 * 
	 * 权限：SIS。
	 * 
	 * @param agentAreaId
	 *            小区ID
	 * 
	 * */
	public void closeAgentArea(int agentAreaId);

	/**
	 * 重新激活小区。
	 * 
	 * 权限：SIS。
	 * 
	 * @param agentAreaId
	 *            小区ID
	 * 
	 * */
	public void activateAgentArea(int agentAreaId);

	/**
	 * 编辑小区，给小区指定业务员。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * @param agentAreaId
	 *            小区ID
	 * @param ECode
	 *            员工编码
	 * 
	 * */
	public void editAgentArea(int agentAreaId, String ECode);

	/**
	 * 创建新小区。
	 * 
	 * 权限：SIS。
	 * 
	 * @param areaCode
	 *            小区编号
	 * @param directorAreaId
	 *            所属的主任区
	 * 
	 * */
	public void createAgentArea(String areaCode, String desc, WorkType type, int directorAreaId);

	/**
	 * 获取此小区已管理的经销商。
	 * 
	 * 权限：文员。
	 * 
	 * */
	public List<Dealer> getAgentAreaDealers(int agentAreaId);

	/**
	 * 获取可分配给此小区的经销商。
	 * 
	 * 权限：文员。
	 * 
	 * */
	public List<Dealer> getCanAllotedDealers(int agentAreaId);

	/**
	 * 给小区分配经销商。
	 * 
	 * 权限：文员。
	 * 
	 * @param agentAreaId
	 * @param outIds
	 * @param inIds
	 * 
	 * */
	public void allotDealersToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds);

	/**
	 * 获取此小区已管理的直营店。
	 * 
	 * 权限：文员 。
	 * 
	 * */
	public List<Dealer> getAgentAreaDirectStores(int agentAreaId);

	/**
	 * 获取可分配给此小区的直营店。
	 * 
	 * 权限：文员。
	 * 
	 * */
	public List<Dealer> getCanAllotedDirectStores(int agentAreaId);

	/**
	 * 给小区分配直营店。
	 * 
	 * 权限：文员。
	 * 
	 * @param agentAreaId
	 * @param outIds
	 * @param inIds
	 * 
	 * */
	public void allotDirectStoresToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds);

	/**
	 * 获取此小区已管理的小店。
	 * 
	 * 权限：文员 。
	 * 
	 * */
	public List<Store> getAgentAreaStores(int agentAreaId);

	/**
	 * 获取可分配给此小区的小店。
	 * 
	 * 权限：文员。
	 * 
	 * */
	public List<Store> getCanAllotedStores(int agentAreaId);

	/**
	 * 给小区分配门店。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * */
	public void allotStoresToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds);

	/**
	 * 获取所有大区。
	 * 
	 * */
	public List<BigArea> getAllBigAreas();

	/**
	 * 获取此大区ID的所有经理区。
	 * 
	 * */
	public List<ManagerArea> getManagerAreas(int bigAreaId);

	/**
	 * 获取此经理区下的所有主任区。
	 * 
	 * */
	public List<DirectorArea> getDirectorAreas(int managerAreaId);

	/**
	 * 获取此主任区下的所有CR区。
	 * 
	 * */
	public List<AgentArea> getAgentAreas(int directAreaId);

	/**
	 * 给所有大区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并且版本号+1。
	 * 
	 * 权限：SIS。
	 * 
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            目标金额
	 * 
	 * */
	public void setBigAreaNrGoal(int year, int month, Map<Integer, Float> nrs);

	/**
	 * 根据年月份给自己管辖下的所有经理区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并将版本号设为与父记录相同。
	 * 
	 * 权限：大区经理（UM）。
	 * 
	 * @param employeeId
	 *            分配目标的大区经理ID（操作者）
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            保存了操作者下属所有经理区的nr值的map,key为经理区ID,value为NR值。
	 * 
	 * */
	public void setManagerAreaNrGoals(int bigAreaId, int year, int month, Map<Integer, Float> nrs);

	/**
	 * 根据年月份给自己管辖下的所有主任区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并将版本号设为与父记录相同。
	 * 
	 * 权限：经理区经理（TDM）。
	 * 
	 * @param employeeId
	 *            分配目标的经理区经理ID（操作者）
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            保存了操作者下属所有主任区的nr值的map,key为主任区ID,value为NR值。
	 * 
	 * */
	public void setDirectorAreaNrGoals(int managerAreaId, int year, int month, Map<Integer, Float> nrs);

	/**
	 * 根据年月份给自己管辖下的所有CR区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并将版本号设为与父记录相同。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            分配目标的主任区经理ID（操作者）
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            保存了操作者下属所有CR区的nr值的map,key为CR区ID,value为NR值。
	 * 
	 * */
	public void setAgentAreaNrGoals(int directAreaId, int year, int month, Map<Integer, Float> nrs);

	/**
	 * SIS获取下属的所有大区目标。
	 * 
	 * 权限：主任区主任（SIS）。
	 * 
	 * */
	public GlobalGoalResult getGlobalGoal(int year, int month);

	/**
	 * 大区经理获取大区的销量目标。
	 * 
	 * 权限:大区经理（UM）。
	 * 
	 * @param employeeId
	 *            大区经理ID（操作者）
	 * 
	 * */
	public BigAreaGoalResult getBigAreaGoal(int bigAreaId, int year, int month);

	/**
	 * 经理区经理获取经理区的销量目标。
	 * 
	 * 权限：经理区经理（TDM）。
	 * 
	 * @param employeeId
	 *            经理区经理ID（操作者）
	 * 
	 * */
	public ManagerAreaGoalResult getManagerAreaNrGoal(int managerAreaId, int year, int month);

	/**
	 * 主任区经理获取主任区的销量目标。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任区主任ID（操作者）
	 * @param date
	 *            日期
	 * 
	 * */
	public DirectAreaGoalResult getDirectorAreaGoal(int directAreaId, int year, int month);

	/**
	 * 获取主任区下属SDO的列表。
	 * 
	 * 权限：主任(TDS)。
	 * 
	 * */
	public List<AgentAreaSdoGoal> getAgentAreaSdoGoals(int directAreaId, int year, int month);

	/**
	 * 设置某月的SDO目标。
	 * 
	 * 权限：SIS
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param file
	 *            上传的EXCEL
	 * 
	 * */
	public void setMonthSdo(int year, int month, MultipartFile file);

	/**
	 * 获取年月的SdoExcel.
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * */
	public SdoExcel getSdoExcel(int year, int month);

	/**
	 * 获取某年月的SDO目标。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * */
	public List<Sdo> getSdos(int year, int month);

	/**
	 * 主任给CR区分配SDO目标。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * 
	 * */
	public void setSdos(int agentAreaId, List<Long> sdoIds, int year, int month);

	/**
	 * 获取主任下面的所有CR区。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * 
	 * */
	public List<AgentArea> getDirectorAgentAreas(int directAreaId);

	/**
	 * 获取路线。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任区主任。
	 * @param agentAreaId
	 *            CR区ID
	 * @param routeCode
	 *            线路代号
	 * 
	 * */
	public VisitRoute getVisitRoute(int agentAreaId, int routeCode);

	/**
	 * 获取所有不在此路线中的门店。
	 * 
	 * 权限：主任区主任(TDS)。
	 * 
	 * @param employeeId
	 *            操作的主任ID
	 * @param agentAreaId
	 *            要操作的CR区ID
	 * @param routeCode
	 *            操作的线路代号
	 * 
	 * */
	public Page<Store> getNotInThisRouteStores(int agentAreaId, int routeCode, int page, int size);

	/**
	 * 获取所有不在此路线中的直营店。
	 * 
	 * 权限：主任区主任(TDS)。
	 * 
	 * @param employeeId
	 *            操作的主任ID
	 * @param agentAreaId
	 *            要操作的CR区ID
	 * @param routeCode
	 *            操作的线路代号
	 * 
	 * */
	public Page<Dealer> getNotInThisRouteDealers(int agentAreaId, int routeCode, int page, int size);

	/**
	 * 主任给业务员分配路线。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任区主任员工ID（操作者）
	 * @param agentAreaId
	 *            CR区的ID
	 * @param routeCode
	 *            线路代号
	 * @param sets
	 *            包装了门店类型和ID的参数。
	 * 
	 * */
	public void setAgentVisitRoute(int agentAreaId, int routeCode, List<NodeSet> sets);

	/**
	 * 根据区域筛选选项获取某年月到某年月的销量统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param startYear
	 *            开始年份
	 * @param startMonth
	 *            开始月份
	 * @param endYear
	 *            结束年份
	 * @param endMonth
	 *            结束月份
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	public Page<WebSalesVolumeStatsResult> getSalesVolStatisResultsByMonthToMonth(
			int startYear,
			int startMonth,
			int endYear,
			int endMonth,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * 根据区域筛选选项获取本月某天到某天的销量统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param startDay
	 *            起始天
	 * @param endDay
	 *            结束天
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	public Page<WebSalesVolumeStatsResult> getSalesVolStatisResultsByDayToDay(
			int startDay,
			int endDay,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * 根据区域筛选选项获取某年月到某年月的生产力统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param startYear
	 *            开始年份
	 * @param startMonth
	 *            开始月份
	 * @param endYear
	 *            结束年份
	 * @param endMonth
	 *            结束月份
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	public Page<WebProductivityStatsResult> getSalesProducStatsResultsByMonthToMonth(
			int startYear,
			int startMonth,
			int endYear,
			int endMonth,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * 根据区域筛选选项获取某月某天到某天的生产力统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param month
	 *            某月
	 * @param startDay
	 *            开始天
	 * @param endDay
	 *            结束天
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	public Page<WebProductivityStatsResult> getSalesProducStatsResultsByDayToDay(
			int startDay,
			int endDay,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * 获取拜访记录。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param agentId
	 *            业务员ID
	 * @param startDate
	 *            开始日期，形式为8位int,例如20141015表示日期2014.10.15
	 * @param endDate
	 *            结束日期，形式为8位int,例如20141015表示日期2014.10.15
	 * 
	 * */
	public Page<VisitRecordDocument> getVisitRecords(int agentAreaId, int startDate, int endDate, int page, int size);

	/**
	 * 获取某个经销商的盘点记录
	 * 
	 * @param dealerId
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	Page<InventoriesCheckHistory> getInventoriesHistory(Long dealerId, Date start, Date end, int page, int size);
}
