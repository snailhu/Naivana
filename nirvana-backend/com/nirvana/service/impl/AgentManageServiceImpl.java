package com.nirvana.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.domain.backend.VisitNodeType;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.VisitRouteNode;
import com.nirvana.domain.backend.VisitRouteNodePK;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.domain.backend.goal.AgentAreaNrGoal;
import com.nirvana.domain.backend.goal.BigAreaNrGoal;
import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;
import com.nirvana.domain.backend.goal.ManagerAreaNrGoal;
import com.nirvana.domain.backend.goal.sdo.AgentAreaSdoGoal;
import com.nirvana.domain.backend.goal.sdo.MonthSdo;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.backend.goal.sdo.SdoExcel;
import com.nirvana.domain.common.LockType;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.statistics.DayAgentStatistics;
import com.nirvana.domain.statistics.MonthAgentStatistics;
import com.nirvana.domain.store.Store;
import com.nirvana.exception.CloseNotNullAgentAreaException;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.RelationNotMatchingException;
import com.nirvana.exception.ResourceAccessException;
import com.nirvana.exception.ServerIOException;
import com.nirvana.mongo.document.VisitRecordDocument;
import com.nirvana.repository.backend.AgentDealerCategoryRepository;
import com.nirvana.repository.backend.AgentStoreCategoryRepository;
import com.nirvana.repository.backend.EmployeeRepository;
import com.nirvana.repository.backend.PositionRepository;
import com.nirvana.repository.backend.VisitRouteNodeRepository;
import com.nirvana.repository.backend.VisitRouteRepository;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.area.BigAreaRepository;
import com.nirvana.repository.backend.area.DirectorAreaRepository;
import com.nirvana.repository.backend.area.ManagerAreaRepository;
import com.nirvana.repository.backend.goal.AgentAreaNrGoalRepository;
import com.nirvana.repository.backend.goal.BigAreaGoalRepository;
import com.nirvana.repository.backend.goal.DirectorAreaGoalRepository;
import com.nirvana.repository.backend.goal.ManagerAreaGoalRepository;
import com.nirvana.repository.backend.goal.sdo.AgentAreaSdoGoalRepository;
import com.nirvana.repository.backend.goal.sdo.MonthSdoRepository;
import com.nirvana.repository.backend.goal.sdo.SdoExcelRepository;
import com.nirvana.repository.backend.goal.sdo.SdoRepository;
import com.nirvana.repository.backend.statistics.DayAgentStatisticsRepository;
import com.nirvana.repository.backend.statistics.MonthAgentStatisticsRepository;
import com.nirvana.repository.backend.usersys.BackEndUserRepository;
import com.nirvana.repository.common.OmnipotentLockRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.InventoriesCheckHistoryRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.AreaService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.OperateSdoExcelService;
import com.nirvana.service.PersonnelManageService;
import com.nirvana.service.pojo.web.BigAreaGoalResult;
import com.nirvana.service.pojo.web.DirectAreaGoalResult;
import com.nirvana.service.pojo.web.GlobalGoalResult;
import com.nirvana.service.pojo.web.ManagerAreaGoalResult;
import com.nirvana.service.pojo.web.NodeSet;
import com.nirvana.service.pojo.web.WebProductivityStatsResult;
import com.nirvana.service.pojo.web.WebSalesVolumeStatsResult;
import com.nirvana.utils.Assert;

@Service
@Transactional
public class AgentManageServiceImpl implements AgentManageService {

	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private AreaService areaService;
	@Resource
	private PersonnelManageService personnelManageService;
	@Resource
	private OperateSdoExcelService operateSdoExcelService;
	@Resource
	private BackEndUserRepository backEndUserRepository;
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private BigAreaRepository bigAreaRepository;
	@Resource
	private BigAreaGoalRepository bigAreaGoalRepository;
	@Resource
	private VisitRouteRepository visitRouteRepository;
	@Resource
	private AgentAreaRepository agentAreaRepository;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private ManagerAreaRepository managerAreaRepository;
	@Resource
	private ManagerAreaGoalRepository managerAreaGoalRepository;
	@Resource
	private DirectorAreaRepository directorAreaRepository;
	@Resource
	private DirectorAreaGoalRepository directorAreaGoalRepository;
	@Resource
	private AgentAreaNrGoalRepository agentAreaGoalRepository;
	@Resource
	private MonthAgentStatisticsRepository monthAgentStatisticsRepository;
	@Resource
	private DayAgentStatisticsRepository dayAgentStatisticsRepository;
	@Resource
	private VisitRouteNodeRepository visitRouteNodeRepository;
	@Resource
	private PositionRepository positionRepository;
	@Resource
	private AgentDealerCategoryRepository agentDealerCategoryRepository;
	@Resource
	private AgentStoreCategoryRepository agentStoreCategoryRepository;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private SdoExcelRepository sdoExcelRepository;
	@Resource
	private MonthSdoRepository monthSdoRepository;
	@Resource
	private AgentAreaSdoGoalRepository agentAreaSdoGoalRepository;
	@Resource
	private SdoRepository sdoRepository;
	@Resource
	private OmnipotentLockRepository omnipotentLockRepository;
	@Resource
	private InventoriesCheckHistoryRepository inventoriesCheckHistoryRepository;

	private void judgeDate(int year, int month) {
		if (year < 2000 || year > 9999 || month < 1 || month > 12) {
			throw new IllegalArgumentException("日期参数错误。");
		}
	}

	private PageRequest getPageRequest(int page, int size) {
		PageRequest pageRequest = null;
		// 分页查询
		if (size <= 0)
			size = 10;
		if (page <= 1) {
			pageRequest = new PageRequest(0, size);
		} else {
			pageRequest = new PageRequest(page - 1, size);
		}
		return pageRequest;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DirectorArea> getManagedDirectorArea() {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.TDS);
		if (positions.size() == 0) {
			// TODO 刷新用户权限...
		}
		List<DirectorArea> areas = new ArrayList<DirectorArea>();
		for (Position position : positions) {
			DirectorArea area = position.getDirectorArea();
			areas.add(area);
		}
		return areas;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Position> getHasPositions() {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = new ArrayList<Position>();
		List<Position> sisPositions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.SIS);
		List<Position> umPositions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.UM);
		List<Position> tdmPositions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.TDM);
		List<Position> tdsPositions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.TDS);
		positions.addAll(sisPositions);
		positions.addAll(umPositions);
		positions.addAll(tdmPositions);
		positions.addAll(tdsPositions);
		return positions;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<AgentArea> getAgentAreas(int page, int size) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.SIS);
		if (positions.size() > 0) {
			Page<AgentArea> areas = agentAreaRepository.findAll(getPageRequest(page, size));
			return areas;
		}
		positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.CLERK);
		if (positions.size() == 0) {
			// TODO 刷新用户权限...
			throw new ResourceAccessException("此用户无权进行此操作。");
		}
		if (positions.size() != 1) {
			throw new DataIntegrityException();
		}
		Position position = positions.get(0);

		ManagerArea area = position.getcManagerArea();
		Page<AgentArea> areas = agentAreaRepository.findByManagerAreaId(area.getId(), getPageRequest(page, size));
		return areas;
	}

	@Override
	public void closeAgentArea(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC);
		if (agentArea == null)
			throw new RecordNotFoundException("没有找到此CR区");
		Set<Dealer> dealerSet = agentArea.getDealers();
		Set<Dealer> directDealerSet = agentArea.getDirectStores();
		if ((dealerSet.size() + directDealerSet.size()) > 0) {
			throw new CloseNotNullAgentAreaException();
		}
		agentArea.setIsClosed(true);
		agentAreaRepository.save(agentArea);
	}

	@Override
	public void activateAgentArea(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null)
			throw new RecordNotFoundException("没有找到此CR区");
		agentArea.setIsClosed(false);
		agentAreaRepository.save(agentArea);

	}

	@Override
	public void editAgentArea(int agentAreaId, String ECode) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("没有找到此CR区");
		}
		Employee loginEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> postions = positionRepository.findPositionByEmployeeIdAndPositionType(loginEmployee.getId(), PositionType.INFO_MINISTRY);
		if (postions.size() <= 0) {
			Position position = agentArea.getDirectorArea().getManagerArea().getClerk();
			Employee needEmployee = position.getEmployee();
			if (!loginEmployee.equals(needEmployee)) {
				throw new ResourceAccessException("此用户无权操作。");
			}
		}
		Employee employee = employeeRepository.findByEcode(ECode);
		if (employee == null) {
			throw new RecordNotFoundException("没有找到此业务员");
		}
		Position position = agentArea.getAgent();
		position.setEmployee(employee);
		positionRepository.save(position);
	}

	@Override
	public void createAgentArea(String areaCode, String desc, WorkType type, int directorAreaId) {
		personnelManageService.createAgentArea(desc, areaCode, type, directorAreaId);
	}

	private void dealerBelongsDirectorArea(Dealer dealer, DirectorArea area) {
		Assert.notNull(dealer);
		Assert.notNull(area);
		if (!dealer.getDirectorArea().getId().equals(area.getId())) {
			throw new IllegalArgumentException("此经销或者门店不属于此主任区。");
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dealer> getAgentAreaDealers(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		List<Dealer> dealers = new ArrayList<Dealer>();
		dealers.addAll(agentArea.getDealers());
		return dealers;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dealer> getCanAllotedDealers(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		DirectorArea directorArea = agentArea.getDirectorArea();
		Set<Dealer> dealers = dealerRepository.findByDirectorAreaIdAndIsDirect(directorArea.getId(), false);
		dealers.removeAll(agentArea.getDealers());
		List<Dealer> list = new ArrayList<Dealer>();
		list.addAll(dealers);
		return list;
	}

	@Override
	public void allotDealersToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		if (agentArea.getIsClosed()) {
			throw new RecordNotFoundException("此小区已经关闭。");
		}
		DirectorArea directorArea = agentArea.getDirectorArea();

		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.INFO_MINISTRY);
		if (positions.size() <= 0) {
			Position needPosition = agentArea.getDirectorArea().getManagerArea().getClerk();
			Employee needEmployee = needPosition.getEmployee();
			if (!employee.equals(needEmployee)) {
				throw new ResourceAccessException("此用户无权操作。");
			}
		}

		Set<Dealer> dealers = agentArea.getDealers();

		// 断开关系。
		if (outIds != null) {
			for (Long outId : outIds) {
				Dealer dealer = dealerRepository.findOne(outId);
				if (dealer == null || dealer.getIsDirect() == true) {
					throw new RecordNotFoundException("参数中某个经销商不存在。");
				}
				if (!dealers.contains(dealer)) {
					throw new RelationNotMatchingException("某个经销商不属于此CR区。");
				}
				Set<Dealer> set = agentArea.getDealers();
				set.remove(dealer);
				storeRepository.updateAgentAreaAndStoreCategoryToNullByDealerIdAndAgentAreaId(dealer.getId(), agentAreaId);
				List<VisitRouteNode> nodes = visitRouteNodeRepository.findByAgentAreaIdAndDealerId(agentArea.getId(), dealer.getId());
				visitRouteNodeRepository.delete(nodes);
			}
		}
		// 建立关系。
		if (inIds != null) {
			for (Long inId : inIds) {
				Dealer dealer = dealerRepository.findOne(inId);
				if (dealer == null || dealer.getIsDirect() == true) {
					throw new RecordNotFoundException("此经销商未找到。");
				}
				dealerBelongsDirectorArea(dealer, directorArea);
				dealers.add(dealer);
			}
		}
		agentAreaRepository.save(agentArea);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dealer> getAgentAreaDirectStores(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		List<Dealer> dealers = new ArrayList<Dealer>();
		dealers.addAll(agentArea.getDirectStores());
		return dealers;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dealer> getCanAllotedDirectStores(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		List<Dealer> dealers = dealerRepository.findByDirectorAreaIdAndTrueIsDirectAndNullAgentAreaId(agentArea.getDirectorArea().getId());
		return dealers;
	}

	@Override
	public void allotDirectStoresToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		if (agentArea.getIsClosed()) {
			throw new RecordNotFoundException("此小区已经关闭。");
		}
		DirectorArea directorArea = agentArea.getDirectorArea();
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.INFO_MINISTRY);
		if (positions.size() <= 0) {
			Position needPosition = agentArea.getDirectorArea().getManagerArea().getClerk();
			Employee needEmployee = needPosition.getEmployee();
			if (!employee.equals(needEmployee)) {
				throw new ResourceAccessException("此用户无权操作。");
			}
		}
		// 断开关系。
		if (outIds != null) {
			for (Long outId : outIds) {
				Dealer dealer = dealerRepository.findOne(outId);
				if (dealer == null || dealer.getIsDirect() == false) {
					throw new RecordNotFoundException("参数中某个直营店不存在。");
				}
				if (!dealer.getAgentArea().getId().equals(agentArea.getId())) {
					throw new RelationNotMatchingException("此直营店不属于此小区");
				}
				dealer.setAgentArea(null);
				dealer.setCategory(null);
				dealerRepository.save(dealer);
				visitRouteNodeRepository.deleteByDealerId(outId);
			}
		}
		// 建立关系。
		if (inIds != null) {
			for (Long inId : inIds) {
				Dealer dealer = dealerRepository.findOne(inId);
				if (dealer == null || dealer.getIsDirect() != true) {
					throw new RecordNotFoundException("此直营店未找到。");
				}
				if (dealer.getAgentArea() != null) {
					throw new IllegalArgumentException("此直营店已分配给其他小区。");
				}
				dealerBelongsDirectorArea(dealer, directorArea);
				AgentDealerCategory agentDealerCategory = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), AgentDealerCategory.UNDEFINED);
				if (agentDealerCategory == null) {
					throw new DataIntegrityException("此小区分类未初始化。");
				}
				dealer.setAgentArea(agentArea);
				dealer.setCategory(agentDealerCategory);
				dealerRepository.save(dealer);
			}
		}
	}

	private void storeCanTouchAgentAreaThroughDealer(Store store, AgentArea area) {
		if (store == null || area == null) {
			throw new IllegalArgumentException("参数不能为空。");
		}
		Dealer dealer = store.getDealer();
		if (dealer == null) {
			throw new RecordAcessDeniedException("无权限操作此门店");
		}
		if (!dealer.getAgentAreas().contains(area)) {
			throw new RecordAcessDeniedException("无权限操作此门店");
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Store> getAgentAreaStores(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		List<Store> list = new ArrayList<Store>();
		list.addAll(agentArea.getStores());
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Store> getCanAllotedStores(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		Set<Dealer> dealers = agentArea.getDealers();
		List<Store> stores = new ArrayList<Store>();
		for (Dealer dealer : dealers) {
			List<Store> list = storeRepository.findByDealerIdAndNullAgentAreaId(dealer.getId());
			stores.addAll(list);
		}
		return stores;
	}

	@Override
	public void allotStoresToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区未找到。");
		}
		if (agentArea.getIsClosed()) {
			throw new RecordNotFoundException("此小区已经关闭。");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.INFO_MINISTRY);
		if (positions.size() <= 0) {
			Position needPosition = agentArea.getDirectorArea().getManagerArea().getClerk();
			Employee needEmployee = needPosition.getEmployee();
			if (!employee.equals(needEmployee)) {
				throw new ResourceAccessException("此用户无权操作。");
			}
		}

		// 断绝关系。
		if (outIds != null) {
			for (Long outId : outIds) {
				Store store = storeRepository.findOne(outId);
				if (!store.getAgentArea().equals(agentArea)) {
					throw new RelationNotMatchingException("此门店不属于此小区。");
				}
				store.setAgentArea(null);
				store.setAgentCategory(null);
				storeRepository.save(store);
				visitRouteNodeRepository.deleteByStoreId(outId);
			}
		}

		// 建立关系。
		if (inIds != null) {
			for (Long inId : inIds) {
				Store store = storeRepository.findOne(inId);
				if (store == null) {
					throw new RecordNotFoundException("未找到此门店。");
				}
				storeCanTouchAgentAreaThroughDealer(store, agentArea);
				AgentStoreCategory agentStoreCategory = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), AgentStoreCategory.UNDEFINED);
				if (agentStoreCategory == null) {
					agentStoreCategory = new AgentStoreCategory();
					agentStoreCategory.setArea(agentArea);
					agentStoreCategory.setCategoryName(AgentStoreCategory.UNDEFINED);
					agentStoreCategoryRepository.save(agentStoreCategory);
				}
				store.setAgentCategory(agentStoreCategory);
				store.setAgentArea(agentArea);
				storeRepository.save(store);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<BigArea> getAllBigAreas() {
		return areaService.getBigAreas();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ManagerArea> getManagerAreas(int bigAreaId) {
		return areaService.getManagerAreasByBigAreaId(bigAreaId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DirectorArea> getDirectorAreas(int managerAreaId) {
		return areaService.getDirectorAreasByManagerAreaId(managerAreaId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AgentArea> getAgentAreas(int directAreaId) {
		return areaService.getAgentAreasByDirectorAreaId(directAreaId);
	}

	@Override
	public void setBigAreaNrGoal(int year, int month, Map<Integer, Float> nrs) {
		judgeDate(year, month);
		int date = year * 100 + month;
		Set<Integer> areaIds = nrs.keySet();
		for (Integer areaId : areaIds) {
			BigArea area = bigAreaRepository.findOne(areaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (area == null) {
				throw new RecordNotFoundException("此大区未找到。");
			}
			BigAreaNrGoal goal = bigAreaGoalRepository.findByAreaIdAndDate(areaId, date);
			if (goal == null) {
				goal = new BigAreaNrGoal();
				goal.setArea(area);
				goal.setDate(date);
				goal.setNr(nrs.get(areaId));
				bigAreaGoalRepository.save(goal);
			} else {
				goal.setNr(nrs.get(areaId));
				goal.setIsAlloted(false);
				bigAreaGoalRepository.save(goal);
				// 删除此大区下面的所有经理区目标。
				List<ManagerArea> managerAreas = managerAreaRepository.findByBigAreaId(areaId);
				for (ManagerArea managerArea : managerAreas) {
					managerAreaGoalRepository.deleteByAreaIdAndDate(managerArea.getId(), date);
					List<DirectorArea> directorAreas = directorAreaRepository.findByManagerAreaId(managerArea.getId());
					for (DirectorArea directorArea : directorAreas) {
						directorAreaGoalRepository.deleteByAreaIdAndDate(directorArea.getId(), date);
						List<AgentArea> agentAreas = agentAreaRepository.findByDirectorAreaId(directorArea.getId());
						for (AgentArea agentArea : agentAreas) {
							agentAreaGoalRepository.deleteByAreaIdAndDate(agentArea.getId(), date);
						}
					}
				}
			}
		}
	}

	@Override
	public void setManagerAreaNrGoals(int bigAreaId, int year, int month, Map<Integer, Float> nrs) {
		BigArea bigArea = bigAreaRepository.findOne(bigAreaId, LockModeType.OPTIMISTIC);
		if (bigArea == null) {
			throw new RecordNotFoundException("此大区未找到。");
		}
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Position position = bigArea.getManager();
		Employee employee = position.getEmployee();
		if (employee == null) {
			throw new RecordAcessDeniedException("你无权操作此大区。");
		}
		if (!employee.equals(currentEmployee)) {
			throw new RecordAcessDeniedException("你无权操作此大区。");
		}

		Set<ManagerArea> managerAreas = bigArea.getManagerAreas();
		if (managerAreas.size() != nrs.size()) {
			throw new IllegalArgumentException("参数错误，参数数量不匹配。");
		}
		judgeDate(year, month);
		int date = year * 100 + month;
		BigAreaNrGoal goal = bigAreaGoalRepository.findByAreaIdAndDate(bigArea.getId(), date);
		if (goal == null) {
			throw new RecordNotFoundException("父区域的目标未找到，无法分配。");
		}

		float totalNr = 0f;
		for (Integer areaId : nrs.keySet()) {
			ManagerArea managerArea = managerAreaRepository.findOne(areaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (managerArea == null) {
				throw new IllegalArgumentException("参数中某个经理区不存在。");
			}
			if (!managerAreas.contains(managerArea)) {
				throw new IllegalArgumentException("参数错误，此经理区你无权操作。");
			}
			ManagerAreaNrGoal managerAreaNrGoal = managerAreaGoalRepository.findByAreaIdAndDate(areaId, date);
			if (managerAreaNrGoal == null) {
				managerAreaNrGoal = new ManagerAreaNrGoal();
				managerAreaNrGoal.setArea(managerArea);
				managerAreaNrGoal.setDate(date);
				managerAreaNrGoal.setNr(nrs.get(areaId));
				managerAreaGoalRepository.save(managerAreaNrGoal);
			} else {
				managerAreaNrGoal.setNr(nrs.get(areaId));
				managerAreaNrGoal.setIsAlloted(false);
				managerAreaGoalRepository.save(managerAreaNrGoal);
				List<DirectorArea> directorAreas = directorAreaRepository.findByManagerAreaId(areaId);
				for (DirectorArea directorArea : directorAreas) {
					directorAreaGoalRepository.deleteByAreaIdAndDate(directorArea.getId(), date);
					List<AgentArea> agentAreas = agentAreaRepository.findByDirectorAreaId(directorArea.getId());
					for (AgentArea agentArea : agentAreas) {
						agentAreaGoalRepository.deleteByAreaIdAndDate(agentArea.getId(), date);
					}
				}
			}
			totalNr = totalNr + nrs.get(areaId);
		}
		if (totalNr != goal.getNr()) {
			throw new IllegalArgumentException("总金额不匹配。");
		}
		goal.setIsAlloted(true);
		bigAreaGoalRepository.save(goal);
	}

	@Override
	public void setDirectorAreaNrGoals(int managerAreaId, int year, int month, Map<Integer, Float> nrs) {
		ManagerArea managerArea = managerAreaRepository.findOne(managerAreaId);
		if (managerArea == null) {
			throw new RecordNotFoundException("此经理区未找到。");
		}
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Position position = managerArea.getManager();
		Employee employee = position.getEmployee();
		if (employee == null) {
			throw new RecordAcessDeniedException("你无权操作此经理区。");
		}
		if (!employee.equals(currentEmployee)) {
			throw new RecordAcessDeniedException("你无权操作此经理区。");
		}

		Set<DirectorArea> directorAreas = managerArea.getDirectorAreas();
		if (directorAreas.size() != nrs.size()) {
			throw new IllegalArgumentException("参数错误，参数数量不匹配。");
		}
		judgeDate(year, month);
		int date = year * 100 + month;
		ManagerAreaNrGoal goal = managerAreaGoalRepository.findByAreaIdAndDate(managerArea.getId(), date);
		if (goal == null) {
			throw new RecordNotFoundException("父区域的目标未找到，无法分配。");
		}

		float totalNr = 0f;
		for (Integer areaId : nrs.keySet()) {
			DirectorArea directorArea = directorAreaRepository.findOne(areaId);
			if (directorArea == null) {
				throw new IllegalArgumentException("参数中某个主任区不存在。");
			}
			if (!directorAreas.contains(directorArea)) {
				throw new IllegalArgumentException("参数错误，此主任区你无权操作。");
			}
			DirectorAreaNrGoal directorAreaNrGoal = directorAreaGoalRepository.findByAreaIdAndDate(areaId, date);
			if (directorAreaNrGoal == null) {
				directorAreaNrGoal = new DirectorAreaNrGoal();
				directorAreaNrGoal.setArea(directorArea);
				directorAreaNrGoal.setDate(date);
				directorAreaNrGoal.setNr(nrs.get(areaId));
				directorAreaGoalRepository.save(directorAreaNrGoal);
			} else {
				directorAreaNrGoal.setNr(nrs.get(areaId));
				directorAreaNrGoal.setIsAlloted(false);
				directorAreaGoalRepository.save(directorAreaNrGoal);
				List<AgentArea> agentAreas = agentAreaRepository.findByDirectorAreaId(areaId);
				for (AgentArea agentArea : agentAreas) {
					agentAreaGoalRepository.deleteByAreaIdAndDate(agentArea.getId(), date);
				}
			}
			totalNr = totalNr + nrs.get(areaId);
		}
		if (totalNr != goal.getNr()) {
			throw new IllegalArgumentException("总金额不匹配。");
		}
		goal.setIsAlloted(true);
		managerAreaGoalRepository.save(goal);
	}

	@Override
	public void setAgentAreaNrGoals(int directAreaId, int year, int month, Map<Integer, Float> nrs) {
		DirectorArea directorArea = directorAreaRepository.findOne(directAreaId);
		if (directorArea == null) {
			throw new RecordNotFoundException("此主任区未找到。");
		}
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Position position = directorArea.getDirector();
		Employee employee = position.getEmployee();
		if (employee == null) {
			throw new RecordAcessDeniedException("你无权操作此主任区。");
		}
		if (!employee.equals(currentEmployee)) {
			throw new RecordAcessDeniedException("你无权操作此主任区。");
		}

		Set<AgentArea> agentAreas = directorArea.getAgentAreas();
		if (agentAreas.size() != nrs.size()) {
			throw new IllegalArgumentException("参数错误，参数数量不匹配。");
		}
		judgeDate(year, month);
		int date = year * 100 + month;
		DirectorAreaNrGoal goal = directorAreaGoalRepository.findByAreaIdAndDate(directorArea.getId(), date);
		if (goal == null) {
			throw new RecordNotFoundException("父区域的目标未找到，无法分配。");
		}

		float totalNr = 0f;
		for (Integer areaId : nrs.keySet()) {
			AgentArea agentArea = agentAreaRepository.findOne(areaId);
			if (agentArea == null) {
				throw new IllegalArgumentException("参数中某个CR区不存在。");
			}
			if (!agentAreas.contains(agentArea)) {
				throw new IllegalArgumentException("参数错误，此CR区你无权操作。");
			}
			AgentAreaNrGoal agentAreaNrGoal = agentAreaGoalRepository.findByAreaIdAndDate(areaId, date);
			if (agentAreaNrGoal == null) {
				agentAreaNrGoal = new AgentAreaNrGoal();
				agentAreaNrGoal.setArea(agentArea);
				agentAreaNrGoal.setDate(date);
				agentAreaNrGoal.setNr(nrs.get(areaId));
				agentAreaGoalRepository.save(agentAreaNrGoal);
			} else {
				agentAreaNrGoal.setNr(nrs.get(areaId));
				agentAreaGoalRepository.save(agentAreaNrGoal);
			}
			totalNr = totalNr + nrs.get(areaId);
		}
		if (totalNr != goal.getNr()) {
			throw new IllegalArgumentException("总金额不匹配。");
		}
		goal.setIsAlloted(true);
		directorAreaGoalRepository.save(goal);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public GlobalGoalResult getGlobalGoal(int year, int month) {
		judgeDate(year, month);
		GlobalGoalResult result = new GlobalGoalResult();
		List<BigArea> bigAreas = bigAreaRepository.findAll();
		List<BigAreaNrGoal> goals = new ArrayList<BigAreaNrGoal>();
		for (BigArea bigArea : bigAreas) {
			BigAreaNrGoal goal = bigAreaGoalRepository.findByAreaIdAndDate(bigArea.getId(), year * 100 + month);
			if (goal == null) {
				result.setBigAreas(bigAreas);
				return result;
			}
			goals.add(goal);
		}
		result.setBigAreaNrGoals(goals);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public BigAreaGoalResult getBigAreaGoal(int bigAreaId, int year, int month) {
		BigArea bigArea = bigAreaRepository.findOne(bigAreaId);
		if (bigArea == null) {
			throw new RecordNotFoundException("此大区未找到。");
		}
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Position position = bigArea.getManager();
		Employee employee = position.getEmployee();
		if (employee == null) {
			throw new RecordAcessDeniedException("你无权操作此大区。");
		}
		if (!employee.equals(currentEmployee)) {
			throw new RecordAcessDeniedException("你无权操作此大区。");
		}

		judgeDate(year, month);
		BigAreaNrGoal areaNrGoal = bigAreaGoalRepository.findByAreaIdAndDate(bigAreaId, year * 100 + month);
		if (areaNrGoal == null) {
			throw new RecordNotFoundException("此大区目标不存在。");
		}
		BigAreaGoalResult result = new BigAreaGoalResult();
		result.setBigAreaNrGoal(areaNrGoal);
		if (areaNrGoal.getIsAlloted()) {
			result.setManagerAreaNrGoals(getBigAreaSonsGoals(bigAreaId, year, month));
		} else {
			result.setManagerAreas(areaService.getManagerAreasByBigAreaId(bigAreaId));
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ManagerAreaGoalResult getManagerAreaNrGoal(int managerAreaId, int year, int month) {
		ManagerArea managerArea = managerAreaRepository.findOne(managerAreaId);
		if (managerArea == null) {
			throw new RecordNotFoundException("此经理区未找到。");
		}
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Position position = managerArea.getManager();
		Employee employee = position.getEmployee();
		if (employee == null) {
			throw new RecordAcessDeniedException("你无权操作此经理区。");
		}
		if (!employee.equals(currentEmployee)) {
			throw new RecordAcessDeniedException("你无权操作此经理区。");
		}
		judgeDate(year, month);
		ManagerAreaNrGoal areaNrGoal = managerAreaGoalRepository.findByAreaIdAndDate(managerAreaId, year * 100 + month);
		if (areaNrGoal == null) {
			throw new RecordNotFoundException("此经理区目标不存在。");
		}
		ManagerAreaGoalResult result = new ManagerAreaGoalResult();
		result.setManagerAreaNrGoal(areaNrGoal);
		if (areaNrGoal.getIsAlloted()) {
			result.setDirectorAreaNrGoals(getManagerAreaSonsGoals(managerAreaId, year, month));
		} else {
			result.setDirectorAreas(areaService.getDirectorAreasByManagerAreaId(managerAreaId));
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public DirectAreaGoalResult getDirectorAreaGoal(int directAreaId, int year, int month) {
		DirectorArea directorArea = directorAreaRepository.findOne(directAreaId);
		if (directorArea == null) {
			throw new RecordNotFoundException("此主任区未找到。");
		}
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Position position = directorArea.getDirector();
		Employee employee = position.getEmployee();
		if (employee == null) {
			throw new RecordAcessDeniedException("你无权操作此主任区。");
		}
		if (!employee.equals(currentEmployee)) {
			throw new RecordAcessDeniedException("你无权操作此主任区。");
		}
		judgeDate(year, month);
		DirectorAreaNrGoal areaNrGoal = directorAreaGoalRepository.findByAreaIdAndDate(directAreaId, year * 100 + month);
		if (areaNrGoal == null) {
			throw new RecordNotFoundException("此主任区目标不存在。");
		}
		DirectAreaGoalResult result = new DirectAreaGoalResult();
		result.setDirectorAreaNrGoal(areaNrGoal);
		if (areaNrGoal.getIsAlloted()) {
			result.setAgentAreaNrGoals(getDirectAreaSonsGoals(directAreaId, year, month));

		} else {
			result.setAgentAreas(areaService.getAgentAreasByDirectorAreaId(directAreaId));
		}
		return result;
	}

	private List<ManagerAreaNrGoal> getBigAreaSonsGoals(int bigAreaId, int year, int month) {
		List<ManagerArea> areas = areaService.getManagerAreasByBigAreaId(bigAreaId);
		List<ManagerAreaNrGoal> goals = new ArrayList<ManagerAreaNrGoal>();
		for (ManagerArea area : areas) {
			ManagerAreaNrGoal goal = managerAreaGoalRepository.findByAreaIdAndDate(area.getId(), year * 100 + month);
			if (goal == null) {
				throw new IllegalArgumentException("参数错误，此大区下面子区目标并未分配。");
			}
			goals.add(goal);
		}
		return goals;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private List<DirectorAreaNrGoal> getManagerAreaSonsGoals(int managerAreaId, int year, int month) {
		List<DirectorArea> areas = areaService.getDirectorAreasByManagerAreaId(managerAreaId);
		List<DirectorAreaNrGoal> goals = new ArrayList<DirectorAreaNrGoal>();
		for (DirectorArea area : areas) {
			DirectorAreaNrGoal goal = directorAreaGoalRepository.findByAreaIdAndDate(area.getId(), year * 100 + month);
			if (goal == null) {
				throw new IllegalArgumentException("参数错误，此经理区下面子区目标并未分配。");
			}
			goals.add(goal);
		}
		return goals;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private List<AgentAreaNrGoal> getDirectAreaSonsGoals(int directAreaId, int year, int month) {
		List<AgentArea> areas = areaService.getAgentAreasByDirectorAreaId(directAreaId);
		List<AgentAreaNrGoal> goals = new ArrayList<AgentAreaNrGoal>();
		for (AgentArea area : areas) {
			AgentAreaNrGoal goal = agentAreaGoalRepository.findByAreaIdAndDate(area.getId(), year * 100 + month);
			if (goal == null) {
				throw new IllegalArgumentException("参数错误，此主任区下面子区目标并未分配。");
			}
			goals.add(goal);
		}
		return goals;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AgentAreaSdoGoal> getAgentAreaSdoGoals(int directAreaId, int year, int month) {
		DirectorArea directorArea = directorAreaRepository.findOne(directAreaId);
		if (directorArea == null) {
			throw new RecordNotFoundException("此主任区未找到。");
		}
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Position position = directorArea.getDirector();
		Employee employee = position.getEmployee();
		if (employee == null) {
			throw new RecordAcessDeniedException("你无权操作此主任区。");
		}
		if (!employee.equals(currentEmployee)) {
			throw new RecordAcessDeniedException("你无权操作此主任区。");
		}
		Set<AgentArea> agentAreas = directorArea.getAgentAreas();
		List<AgentAreaSdoGoal> areaSdoGoals = new ArrayList<AgentAreaSdoGoal>();
		int date = year * 100 + month;
		for (AgentArea agentArea : agentAreas) {
			AgentAreaSdoGoal sdoGoal = agentAreaSdoGoalRepository.findByAgentAreaIdAndDate(agentArea.getId(), date);
			if (sdoGoal != null) {
				areaSdoGoals.add(sdoGoal);
			}
		}
		return areaSdoGoals;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public SdoExcel getSdoExcel(int year, int month) {
		judgeDate(year, month);
		int date = year * 100 + month;
		SdoExcel excel = sdoExcelRepository.findOne(date);
		return excel;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Sdo> getSdos(int year, int month) {
		judgeDate(year, month);
		int date = year * 100 + month;
		MonthSdo monthSdo = monthSdoRepository.findOne(date);
		return monthSdo == null ? null : monthSdo.getSdos();
	}

	@Override
	public void setSdos(int agentAreaId, List<Long> sdoIds, int year, int month) {

		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("此CR区未找到。");
		}
		DirectorArea directorArea = agentArea.getDirectorArea();
		if (directorArea == null) {
			throw new DataIntegrityException();
		}
		Position needPosition = directorArea.getDirector();
		Employee needEmployee = needPosition.getEmployee();
		if (!employee.equals(needEmployee)) {
			throw new ResourceAccessException("此用户无权操作此小区。");
		}
		judgeDate(year, month);
		int date = year * 100 + month;
		Set<Sdo> sdos = new HashSet<Sdo>();
		for (Long sdoId : sdoIds) {
			Sdo sdo = sdoRepository.findOne(sdoId);
			if (sdo == null) {
				throw new RecordNotFoundException("此SDO未找到。");
			}
			sdos.add(sdo);
		}
		AgentAreaSdoGoal sdoGoal = agentAreaSdoGoalRepository.findByAgentAreaIdAndDate(agentAreaId, date);
		if (sdoGoal == null) {
			sdoGoal = new AgentAreaSdoGoal();
			sdoGoal.setAgentArea(agentArea);
			sdoGoal.setDate(date);
			sdoGoal.setSdos(sdos);
			agentAreaSdoGoalRepository.save(sdoGoal);
		} else {
			sdoGoal.getSdos().clear();
			sdoGoal.getSdos().addAll(sdos);
			agentAreaSdoGoalRepository.save(sdoGoal);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AgentArea> getDirectorAgentAreas(int directAreaId) {
		return areaService.getAgentAreasByDirectorAreaId(directAreaId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public VisitRoute getVisitRoute(int agentAreaId, int routeCode) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此CR区不存在。");
		}
		Employee needEmployee = agentArea.getDirectorArea().getDirector().getEmployee();
		if (!employee.equals(needEmployee)) {
			throw new RecordAcessDeniedException("你无权访问此CR区。");
		}
		VisitRoute route = visitRouteRepository.findByAgentAreaIdAndRouteCode(agentAreaId, routeCode);
		if (route == null) {
			throw new RecordNotFoundException("此路线不存在。");
		}
		return route;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Store> getNotInThisRouteStores(int agentAreaId, int routeCode, int page, int size) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此CR区不存在。");
		}
		Employee needEmployee = agentArea.getDirectorArea().getDirector().getEmployee();
		if (!employee.equals(needEmployee)) {
			throw new RecordAcessDeniedException("你无权访问此CR区。");
		}
		VisitRoute route = visitRouteRepository.findByAgentAreaIdAndRouteCode(agentAreaId, routeCode);
		if (route == null) {
			throw new RecordNotFoundException("此路线不存在。");
		}
		List<Store> stores = storeRepository.findInVisitRouteStores(route.getId());
		Page<Store> storePage;
		if (stores.size() == 0) {
			storePage = storeRepository.findByAgentAreaId(agentAreaId, getPageRequest(page, size));
		} else {
			storePage = storeRepository.findByAgentAreaIdAndNotInList(agentAreaId, stores, getPageRequest(page, size));
		}

		return storePage;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Dealer> getNotInThisRouteDealers(int agentAreaId, int routeCode, int page, int size) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此CR区不存在。");
		}
		Employee needEmployee = agentArea.getDirectorArea().getDirector().getEmployee();
		if (!employee.equals(needEmployee)) {
			throw new RecordAcessDeniedException("你无权访问此CR区。");
		}
		VisitRoute route = visitRouteRepository.findByAgentAreaIdAndRouteCode(agentAreaId, routeCode);
		if (route == null) {
			throw new RecordNotFoundException("此路线不存在。");
		}
		List<Dealer> dealers = dealerRepository.findInVisitRouteDealers(route.getId());
		Page<Dealer> dealerPage;
		if (dealers.size() == 0) {
			dealerPage = dealerRepository.findByAgentAreaId(agentAreaId, getPageRequest(page, size));
		} else {
			dealerPage = dealerRepository.findByAgentAreaIdAndNotInList(agentAreaId, dealers, getPageRequest(page, size));
		}
		return dealerPage;
	}

	@Override
	public void setAgentVisitRoute(int agentAreaId, int routeCode, List<NodeSet> sets) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此CR区不存在。");
		}
		Employee needEmployee = agentArea.getDirectorArea().getDirector().getEmployee();
		if (!employee.equals(needEmployee)) {
			throw new RecordAcessDeniedException("你无权访问此CR区。");
		}
		VisitRoute route = visitRouteRepository.findByAgentAreaIdAndRouteCodeLocked(agentAreaId, routeCode);
		if (route == null) {
			throw new RecordNotFoundException("此路线不存在。");
		}
		visitRouteNodeRepository.deleteByRouteId(route.getId());

		Set<Long> storeIds = new HashSet<Long>();
		Set<Long> dealerIds = new HashSet<Long>();
		for (NodeSet set : sets) {
			if (set.getType().equals(VisitNodeType.DIRECT_STORE)) {
				storeIds.add(set.getId());
			} else if (set.getType().equals(VisitNodeType.DISTRIBUTE_STORE)) {
				dealerIds.add(set.getId());
			}
		}
		if (sets.size() != (storeIds.size() + dealerIds.size())) {
			throw new IllegalArgumentException("节点参数中存在重复。");
		}

		int i = 0;
		for (NodeSet set : sets) {
			VisitRouteNode node = new VisitRouteNode();
			VisitRouteNodePK pk = new VisitRouteNodePK();
			pk.setRoute(route);
			pk.setSerialNum(i);
			node.setPk(pk);
			VisitNodeType type = set.getType();
			node.setType(type);
			if (type == VisitNodeType.DIRECT_STORE) {
				Dealer dealer = dealerRepository.findOne(set.getId());
				if (dealer == null || dealer.getIsDirect() == false) {
					throw new RecordNotFoundException("此直营店未找到。");
				}
				node.setDealer(dealer);
			} else if (type == VisitNodeType.DISTRIBUTE_STORE) {
				Store store = storeRepository.findOne(set.getId());
				if (store == null) {
					throw new RecordNotFoundException("此门店未找到。");
				}
				node.setStore(store);
			}
			visitRouteNodeRepository.save(node);
			i++;
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
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
			int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<WebSalesVolumeStatsResult> getSalesVolStatisResultsByDayToDay(
			int startDay,
			int endDay,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
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
			int size) {
		// TODO Auto-generated method stub
		judgeDate(startYear, startMonth);
		judgeDate(endYear, endMonth);

		Integer startDate = startYear * 100 + startMonth;
		Integer endDate = endYear * 100 + endMonth;

		Pageable pageable = new PageRequest(page, size);
		List<WebProductivityStatsResult> list = new ArrayList<WebProductivityStatsResult>();
		if (agentAreaId != null) {

			WebProductivityStatsResult webProductivityStatsResult = findResultByAgentAreaId(agentAreaId, startDate, endDate);
			list.add(webProductivityStatsResult);

		}

		if (dirAreaId != null) {
			list = findResultByDirAreaId(dirAreaId, startDate, endDate, pageable);

		}

		if (manageAreaId != null) {
			list = findResultByManageAreaId(manageAreaId, startDate, endDate, pageable);

		}
		if (bigAreaId != null) {

			list = findResultByBigAreaId(bigAreaId, startDate, endDate, pageable);

		}
		Page<WebProductivityStatsResult> webProductivityStatsResults = new PageImpl<WebProductivityStatsResult>(list, pageable, list.size());
		return webProductivityStatsResults;

	}

	private List<WebProductivityStatsResult> findResultByBigAreaId(Integer bigAreaId, Integer startDate, Integer endDate, Pageable pageable) {

		List<WebProductivityStatsResult> list = new ArrayList<WebProductivityStatsResult>();
		Page<AgentArea> agentAreas = agentAreaRepository.findPageByBigAreaId(bigAreaId, pageable);

		for (AgentArea agentArea : agentAreas) {
			WebProductivityStatsResult webProductivityStatsResult = findResultByAgentAreaId(agentArea.getId(), startDate, endDate);
			webProductivityStatsResult.setBigArea(agentArea.getDirectorArea().getManagerArea().getBigarea().getName());
			webProductivityStatsResult.setManageArea(agentArea.getDirectorArea().getManagerArea().getName());
			webProductivityStatsResult.setDirArea(agentArea.getDirectorArea().getName());
			list.add(webProductivityStatsResult);

		}
		return list;
	}

	private List<WebProductivityStatsResult> findResultByManageAreaId(Integer manageAreaId, Integer startDate, Integer endDate, Pageable pageable) {
		List<WebProductivityStatsResult> list = new ArrayList<WebProductivityStatsResult>();

		Page<AgentArea> agentAreas = agentAreaRepository.findByManagerAreaId(manageAreaId, pageable);

		for (AgentArea agentArea : agentAreas) {
			WebProductivityStatsResult webProductivityStatsResult = findResultByAgentAreaId(agentArea.getId(), startDate, endDate);
			webProductivityStatsResult.setManageArea(agentArea.getDirectorArea().getManagerArea().getName());
			webProductivityStatsResult.setDirArea(agentArea.getDirectorArea().getName());
			list.add(webProductivityStatsResult);

		}
		return list;

	}

	private List<WebProductivityStatsResult> findResultByDirAreaId(Integer dirAreaId, Integer startDate, Integer endDate, Pageable pageable) {
		List<WebProductivityStatsResult> list = new ArrayList<WebProductivityStatsResult>();

		Page<AgentArea> agentAreas = agentAreaRepository.findPageByDirectorAreaId(dirAreaId, pageable);
		for (AgentArea agentArea : agentAreas) {
			WebProductivityStatsResult webProductivityStatsResult = findResultByAgentAreaId(agentArea.getId(), startDate, endDate);
			webProductivityStatsResult.setDirArea(agentArea.getDirectorArea().getName());
			list.add(webProductivityStatsResult);

		}
		return list;

	}

	private WebProductivityStatsResult findResultByAgentAreaId(Integer agentAreaId, Integer startDate, Integer endDate) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		WebProductivityStatsResult webProductivityStatsResult = new WebProductivityStatsResult();
		webProductivityStatsResult.setAgentArea(agentArea.getDesc());
		webProductivityStatsResult.setAgentName(agentArea.getAgent().getEmployee().getName());

		// 服务客户数
		int customerCount;
		// 累计工作日
		int workDays = 0;
		// 预访客户数
		int preVisitCount = 0;
		// 实际访问客户数
		int visitCount = 0;
		// 拜访完成率百分比
		float finishPercent;
		// 订货的客户数
		int orderedCount = 0;
		// 拜访成功率百分比
		float successPercent;
		// 订单销量
		int totalBox = 0;
		// 线内销量
		int inlineBox = 0;
		// 线外销量
		int outlineBox = 0;
		// 户均销量
		float averageBox;
		// 订货Sku数
		int sku = 0;
		// 户均订单Sku
		float averageSku;
		// 总拜访时间（单位秒）
		int totalTime = 0;
		// 平均拜访时间（单位秒）
		int averageTime;
		customerCount = agentArea.getStores().size();
		Set<Dealer> dealers = agentArea.getDealers();
		for (Dealer dealer : dealers) {
			if (dealer.getIsDirect()) {
				customerCount++;
			}

		}
		webProductivityStatsResult.setCustomerCount(customerCount);
		if (String.valueOf(startDate).length() == 6) {
			List<MonthAgentStatistics> list = monthAgentStatisticsRepository.findByAgentAreaIdAndMonthDate(agentAreaId, startDate, endDate);
			for (MonthAgentStatistics monthAgentStatistics : list) {
				preVisitCount += monthAgentStatistics.getPreVisitCount();
				visitCount += monthAgentStatistics.getVisitCount();
				orderedCount += monthAgentStatistics.getOrderedCount();
				totalBox += monthAgentStatistics.getTotalBox();
				inlineBox += monthAgentStatistics.getInlineBox();
				outlineBox += monthAgentStatistics.getOutlineBox();
				sku += monthAgentStatistics.getSku();
				totalTime += monthAgentStatistics.getTotalTime();
				workDays += monthAgentStatistics.getWorkDay();
			}

			int endYear = Integer.parseInt(String.valueOf(endDate).substring(0, 4));
			int endMonth = Integer.parseInt(String.valueOf(endDate).substring(4));
			NumberFormat nf = new DecimalFormat("00");
			Calendar c = Calendar.getInstance();
			int month = c.get(Calendar.MONTH) + 1;
			if (endMonth == month) {
				int start = Integer.parseInt("" + nf.format(endMonth) + nf.format(0));
				int end = Integer.parseInt("" + endYear + nf.format(endMonth) + nf.format(c.get(Calendar.DAY_OF_MONTH)));
				workDays += dayAgentStatisticsRepository.findByAgentAreaIdAndDayDate(agentAreaId, start, end).size();
			}

		}
		if (String.valueOf(startDate).length() == 8) {
			List<DayAgentStatistics> list = dayAgentStatisticsRepository.findByAgentAreaIdAndDayDate(agentAreaId, startDate, endDate);
			for (DayAgentStatistics dayAgentStatistics : list) {
				preVisitCount += dayAgentStatistics.getPreVisitCount();
				visitCount += dayAgentStatistics.getVisitCount();
				orderedCount += dayAgentStatistics.getOrderedCount();
				totalBox += dayAgentStatistics.getTotalBox();
				inlineBox += dayAgentStatistics.getInlineBox();
				outlineBox += dayAgentStatistics.getOutlineBox();
				sku += dayAgentStatistics.getSku();
				totalTime += dayAgentStatistics.getTotalTime();

			}
			workDays = list.size();
		}
		finishPercent = visitCount / preVisitCount;
		averageBox = totalBox / visitCount;
		averageSku = sku / visitCount;
		averageTime = totalTime / visitCount;
		successPercent = orderedCount / visitCount;
		webProductivityStatsResult.setWorkDays(workDays);
		webProductivityStatsResult.setSuccessPercent(successPercent);
		webProductivityStatsResult.setAverageBox(averageBox);
		webProductivityStatsResult.setAverageSku(averageSku);
		webProductivityStatsResult.setAverageTime(averageTime);
		webProductivityStatsResult.setFinishPercent(finishPercent);
		webProductivityStatsResult.setInlineBox(inlineBox);
		webProductivityStatsResult.setOrderedCount(orderedCount);
		webProductivityStatsResult.setOutlineBox(outlineBox);
		webProductivityStatsResult.setPreVisitCount(preVisitCount);
		webProductivityStatsResult.setSku(sku);
		webProductivityStatsResult.setTotalBox(totalBox);
		webProductivityStatsResult.setTotalTime(totalTime);
		webProductivityStatsResult.setVisitCount(visitCount);

		return webProductivityStatsResult;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<WebProductivityStatsResult> getSalesProducStatsResultsByDayToDay(
			int startDay,
			int endDay,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		Pageable pageable = new PageRequest(page, size);

		Integer startDate = year * 10000 + month * 100 + startDay;
		Integer endDate = year * 10000 + month * 100 + endDay;
		List<WebProductivityStatsResult> list = new ArrayList<WebProductivityStatsResult>();
		if (agentAreaId != null) {

			WebProductivityStatsResult webProductivityStatsResult = findResultByAgentAreaId(agentAreaId, startDate, endDate);
			list.add(webProductivityStatsResult);
		}

		if (dirAreaId != null) {
			list = findResultByDirAreaId(dirAreaId, startDate, endDate, pageable);
		}

		if (manageAreaId != null) {
			list = findResultByManageAreaId(manageAreaId, startDate, endDate, pageable);

		}
		if (bigAreaId != null) {
			list = findResultByBigAreaId(bigAreaId, startDate, endDate, pageable);

		}

		Page<WebProductivityStatsResult> webProductivityStatsResults = new PageImpl<WebProductivityStatsResult>(list, pageable, list.size());
		return webProductivityStatsResults;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<VisitRecordDocument> getVisitRecords(int agentAreaId, int startDate, int endDate, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMonthSdo(int year, int month, MultipartFile file) {
		omnipotentLockRepository.lock(LockType.SIS_OPERATE);
		judgeDate(year, month);
		int date = year * 100 + month;
		try {
			InputStream stream = file.getInputStream();
			operateSdoExcelService.uploadExcelSdoFile(stream, date);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServerIOException();
		}
	}

	@Override
	public Page<InventoriesCheckHistory> getInventoriesHistory(Long dealerId, Date start, Date end, int page, int size) {
		// TODO 暂时没有权限检查
		return inventoriesCheckHistoryRepository.findByDealerIdAndDateBetweenOrderByDateDesc(dealerId, start, end, getPageRequest(page, size));
	}
}
