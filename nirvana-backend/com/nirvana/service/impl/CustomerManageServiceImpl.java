package com.nirvana.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.exception.RecordExistedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.ResourceAccessException;
import com.nirvana.repository.backend.PositionRepository;
import com.nirvana.repository.backend.VisitRouteNodeRepository;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.area.BigAreaRepository;
import com.nirvana.repository.common.ChannelRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.DealerStoreCategoryRepository;
import com.nirvana.repository.dealer.DealerStorefrontInfoRepository;
import com.nirvana.repository.dealer.usersys.DealerUserRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.repository.store.StoreStorefrontInfoRepository;
import com.nirvana.repository.store.usersys.StoreUserRepository;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.CustomerManageService;
import com.nirvana.service.pojo.web.StoreInfoData;
import com.nirvana.utils.Assert;

@Service
@Transactional
public class CustomerManageServiceImpl implements CustomerManageService {

	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private DealerUserRepository dealerUserRepository;
	@Resource
	private BigAreaRepository bigAreaRepository;
	@Resource
	private ChannelRepository channelRepository;
	@Resource
	private DealerStorefrontInfoRepository dealerStorefrontInfoRepository;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private StoreUserRepository storeUserRepository;
	@Resource
	private AgentAreaRepository agentAreaRepository;
	@Resource
	private StoreStorefrontInfoRepository storeStorefrontInfoRepository;
	@Resource
	private PositionRepository positionRepository;
	@Resource
	private VisitRouteNodeRepository visitRouteNodeRepository;
	@Resource
	private DealerStoreCategoryRepository dealerStoreCategoryRepository;

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

	private Page<Dealer> getDelaersOrDirectStores(int page, int size, boolean isDirect) {
		long employeeId = backEndCurrentLoginService.getCurrentLoginEmployeeId();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.SIS);
		if (positions.size() != 0) {
			Page<Dealer> dealers = dealerRepository.findByIsDirect(isDirect, getPageRequest(page, size));
			return dealers;
		}
		positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.CLERK);
		if (positions.size() == 0) {
			// TODO 刷新用户权限...
			throw new RecordAcessDeniedException("此用户非SIS也非文员，无权限访问。");
		}
		if (positions.size() > 1) {
			throw new DataIntegrityException("一个员工只能拥有一个文员职位。");
		}
		Position position = positions.get(0);
		ManagerArea area = position.getcManagerArea();
		Page<Dealer> dealers = dealerRepository.findByIsDirectAndManagerAreaId(isDirect, area.getId(), getPageRequest(page, size));
		return dealers;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Dealer> getDealers(int page, int size) {
		return getDelaersOrDirectStores(page, size, false);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Dealer> getDirectStores(int page, int size) {
		return getDelaersOrDirectStores(page, size, true);
	}

	private void dealerBelongsManagerArea(Dealer dealer, ManagerArea area) {
		if (dealer == null || area == null) {
			throw new IllegalArgumentException("dealer参数不能为空。");
		}
		DirectorArea directorArea = dealer.getDirectorArea();
		if (directorArea == null) {
			throw new RecordAcessDeniedException("此经销商或者直营店不属于此经理区。");
		}
		ManagerArea managerArea = directorArea.getManagerArea();
		if (managerArea == null) {
			throw new RecordAcessDeniedException("此经销商或者直营店不属于此经理区。");
		}
		if (!managerArea.getId().equals(area.getId())) {
			throw new RecordAcessDeniedException("此经销商或者直营店不属于此经理区。");
		}

	}

	@Override
	public void closeDealer(long dealerId, String closeReason) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		dealer.setIsClose(true);
		dealer.setCloseDate(new Date());
		dealer.setCloseReason(closeReason);
		dealerRepository.save(dealer);
	}

	@Override
	public void openDealer(long dealerId) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		dealer.setIsClose(false);
		dealer.setCloseDate(null);
		dealer.setCloseReason(null);
		dealerRepository.save(dealer);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Dealer getOneDealer(long dealerId) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		if (dealer == null) {
			throw new RecordNotFoundException("此经销商或者门店未找到。");
		}
		return dealer;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Store> getDealerStores(long dealerId) {
		List<Store> stores = new ArrayList<Store>();
		stores.addAll(getOneDealer(dealerId).getStores());
		return stores;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Store> getNotAllotedStores() {
		List<Store> stores = storeRepository.findByNullDealer();
		return stores;
	}

	@Override
	public void allotStoresToDealer(long dealerId, List<Long> outIds, List<Long> inIds) {
		Dealer dealer = dealerRepository.findOne(dealerId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (dealer == null || dealer.getIsDirect()) {
			throw new RecordNotFoundException("此经销商未找到。");
		}
		long employeeId = backEndCurrentLoginService.getCurrentLoginEmployeeId();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.INFO_MINISTRY);
		if (positions.size() == 0) {
			positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.CLERK);
			if (positions.size() == 0) {
				throw new ResourceAccessException("此用户无权操作。");
			}
			if (positions.size() > 1) {
				throw new DataIntegrityException("一个员工只能拥有一个文员职位。");
			}
			ManagerArea area = positions.get(0).getcManagerArea();
			dealerBelongsManagerArea(dealer, area);
		}
		// 建立关系。
		for (Long id : inIds) {
			Store store = storeRepository.findOne(id);
			if (store == null) {
				throw new IllegalArgumentException("参数中某条门店未找到。");
			}
			if (store.getDealer() != null) {
				throw new RecordAcessDeniedException("此门店已经分配给其他的经销商。");
			}
			store.setDealer(dealer);
			DealerStoreCategory dealerStoreCategory = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealerId, DealerStoreCategory.UNDEFINED);
			if (dealerStoreCategory == null) {
				throw new DataIntegrityException("此经销商默认分类未初始化。");
			}
			store.setDealerCategory(dealerStoreCategory);
			storeRepository.save(store);
		}

		// 断绝关系。
		for (Long id : outIds) {
			Store store = storeRepository.findOne(id);
			if (store == null) {
				throw new IllegalArgumentException("参数中某条门店未找到。");
			}
			Dealer sDealer = store.getDealer();
			if (!dealer.equals(sDealer)) {
				throw new IllegalArgumentException("无法移除本不属于此经销商的门店。");
			}
			visitRouteNodeRepository.deleteByStoreId(id);
			store.setAgentArea(null);
			store.setAgentCategory(null);
			store.setDealerCategory(null);
			store.setDealer(null);
			storeRepository.save(store);
		}
	}

	@Override
	public Dealer synchronizeDealer(long dealerId) {
		// TODO 从ERP中同步直营店或者经销商...
		return null;
	}

	@Override
	public Dealer synchronizeDealer(String erpCode) {
		// TODO 通过ERPCODE从ERP中同步直营店或者经销商...
		return null;
	}

	@Override
	public void editDealerAccount(long dealerId, String username, String password, String phoneNum, Boolean isClose, String closeReason) {
		Assert.hasLength(username, password, phoneNum);
		Dealer dealer = dealerRepository.findOne(dealerId);
		if (dealer == null) {
			throw new RecordNotFoundException("此经销商或者直营店未找到。");
		}
		long employeeId = backEndCurrentLoginService.getCurrentLoginEmployeeId();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.INFO_MINISTRY);
		if (positions.size() == 0) {
			positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.CLERK);
			if (positions.size() == 0) {
				throw new ResourceAccessException("此用户无权操作。");
			}
			if (positions.size() > 1) {
				throw new DataIntegrityException("一个员工只能拥有一个文员职位。");
			}
			ManagerArea area = positions.get(0).getcManagerArea();
			dealerBelongsManagerArea(dealer, area);
		}
		DealerUser dealerUser = dealer.getUser();
		if (dealerUser == null) {
			dealerUser = new DealerUser();
			dealerUser.setDealer(dealer);
		}
		dealerUser.setUsername(username);
		dealerUser.setPassword(password);
		dealerUser.setPhone(phoneNum);
		dealerUserRepository.save(dealerUser);
		if (isClose != null) {
			if (isClose) {
				closeDealer(dealerId, closeReason);
			} else {
				openDealer(dealerId);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Store> getStores(int page, int size) {
		long employeeId = backEndCurrentLoginService.getCurrentLoginEmployeeId();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.INFO_MINISTRY);
		if (positions.size() > 0) {
			Page<Store> store = storeRepository.findAll(getPageRequest(page, size));
			return store;
		}
		positions = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.CLERK);
		if (positions.size() <= 0) {
			// TODO 刷新用户权限...
			throw new ResourceAccessException("此用户无权操作。");
		}
		if (positions.size() > 1) {
			throw new DataIntegrityException("一个用户最多拥有一个文员职位。");
		}
		ManagerArea area = positions.get(0).getcManagerArea();
		Page<Store> stores = storeRepository.findBycManagerAreaId(area.getId(), getPageRequest(page, size));
		return stores;
	}

	private void storeBelongsToManagerArea(Store store, ManagerArea area) {
		if (store == null || area == null) {
			throw new IllegalArgumentException("参数不能为空。");
		}
		Dealer dealer = store.getDealer();
		if (dealer == null) {
			throw new ResourceAccessException("此用户无权操作。");
		}
		ManagerArea needArea = dealer.getDirectorArea().getManagerArea();
		if (!needArea.equals(area)) {
			throw new ResourceAccessException("此用户无权操作此门店。");
		}
	}

	private void currentLoginEmployeeCanAcessStore(Store store) {
		if (store == null) {
			throw new IllegalArgumentException("参数不能为空。");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.INFO_MINISTRY);
		if (positions.size() <= 0) {
			positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.CLERK);
			if (positions.size() <= 0) {
				// TODO 刷新用户权限...
				throw new ResourceAccessException("此用户无权操作。");
			}
			if (positions.size() > 1) {
				throw new DataIntegrityException("一个用户最多拥有一个文员职位。");
			}
			ManagerArea area = positions.get(0).getcManagerArea();
			storeBelongsToManagerArea(store, area);
		}
	}

	@Override
	public void closeStore(long storeId, String closeReason) {
		Store store = storeRepository.findOne(storeId);
		if (store == null) {
			throw new RecordNotFoundException("此门店不存在。");
		}
		currentLoginEmployeeCanAcessStore(store);
		store.setIsClose(true);
		store.setCloseDate(new Date());
		store.setCloseReason(closeReason);
		storeRepository.save(store);
		StoreUser user = store.getUser();
		if (user != null) {
			user.setAccountNonLocked(false);
			storeUserRepository.save(user);
		}
	}

	@Override
	public void openStore(long storeId) {
		Store store = storeRepository.findOne(storeId);
		if (store == null) {
			throw new RecordNotFoundException("此门店不存在。");
		}
		currentLoginEmployeeCanAcessStore(store);
		store.setIsClose(false);
		store.setCloseDate(null);
		store.setCloseReason(null);
		storeRepository.save(store);
		StoreUser user = store.getUser();
		if (user != null) {
			user.setAccountNonLocked(true);
			storeUserRepository.save(user);
		}

	}

	@Override
	public void editStoreAccount(long storeId, String username, String password, String phoneNum) {
		Assert.hasLength(username, password, phoneNum);
		Store store = storeRepository.findOne(storeId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (store == null) {
			throw new RecordNotFoundException("此门店不存在。");
		}
		currentLoginEmployeeCanAcessStore(store);
		StoreUser storeUser = store.getUser();
		if (storeUser == null) {
			storeUser = new StoreUser();
			storeUser.setUsername(username);
			storeUser.setPassword(password);
			storeUser.setPhone(phoneNum);
			storeUserRepository.save(storeUser);
		} else {
			storeUser.setUsername(username);
			if (password != null) {
				storeUser.setPassword(password);
			}
			storeUser.setPhone(phoneNum);
			storeUserRepository.save(storeUser);
		}
	}

	@Override
	public void editStoreInfo(long storeId, StoreInfoData data) {
		Store store = storeRepository.findOne(storeId);
		if (store == null) {
			throw new RecordNotFoundException("此门店不存在。");
		}
		currentLoginEmployeeCanAcessStore(store);
		store.setCodeInERP(data.getCodeInERP());
		storeRepository.save(store);
		StoreStorefrontInfo info = store.getStorefrontInfo();
		info.setDeliveryAddr(data.getDeliveryAddr());
		info.setEmail(data.getEmail());
		info.setFaxNum(data.getFaxNum());
		info.setManager(data.getManager());
		info.setName(data.getName());
		info.setPhoneNum(data.getPhoneNum());
		info.setPostalCode(data.getPostalCode());
		storeStorefrontInfoRepository.save(info);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Store getOneStore(long storeId) {
		return storeRepository.findOne(storeId);
	}

	@Override
	public void addNewStore(String username, String password, String phoneNum, StoreInfoData data) {
		if (storeUserRepository.findByUserName(username) != null) {
			throw new RecordExistedException("用户名已存在");
		}
		if (storeUserRepository.findByBindNum(phoneNum) != null) {
			throw new RecordExistedException("绑定电话号码已存在");
		}

		Store store = new Store();
		StoreStorefrontInfo info = new StoreStorefrontInfo();
		StoreUser user = new StoreUser();

		info.setDeliveryAddr(data.getDeliveryAddr());
		info.setEmail(data.getEmail());
		info.setFaxNum(data.getFaxNum());
		info.setManager(data.getManager());
		info.setName(data.getName());
		info.setPhoneNum(data.getPhoneNum());
		info.setPostalCode(data.getPostalCode());
		storeStorefrontInfoRepository.save(info);

		store.setCodeInERP(data.getCodeInERP());
		store.setStorefrontInfo(info);
		storeRepository.save(store);

		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phoneNum);
		user.setStore(store);
		storeUserRepository.save(user);
	}

	@Override
	public void editStoreInfoWithClose(long storeId, StoreInfoData data, Boolean isClose, String closeReason) {
		Store store = storeRepository.findOne(storeId);
		if (store == null) {
			throw new RecordNotFoundException("此门店不存在。");
		}
		currentLoginEmployeeCanAcessStore(store);
		if (isClose != null) {
			if (isClose) {
				closeStore(storeId, closeReason);
			} else {
				openStore(storeId);
			}
		}
		store.setCodeInERP(data.getCodeInERP());
		storeRepository.save(store);
		StoreStorefrontInfo info = store.getStorefrontInfo();
		info.setDeliveryAddr(data.getDeliveryAddr());
		info.setEmail(data.getEmail());
		info.setFaxNum(data.getFaxNum());
		info.setManager(data.getManager());
		info.setName(data.getName());
		info.setPhoneNum(data.getPhoneNum());
		info.setPostalCode(data.getPostalCode());
		storeStorefrontInfoRepository.save(info);

	}

}
