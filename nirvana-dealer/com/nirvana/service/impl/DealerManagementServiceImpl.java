package com.nirvana.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.MonthIncomeAndExpenditure;
import com.nirvana.domain.store.Store;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.exception.RecordExistedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.repository.dealer.DealerPromotionRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.DealerStoreCategoryRepository;
import com.nirvana.repository.dealer.MonthIncomeAndExpenditureRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.service.DealerManagementService;
import com.nirvana.utils.DateUtil;

@Service
@Transactional
public class DealerManagementServiceImpl implements DealerManagementService {

	@Resource
	private DealerStoreCategoryRepository dealerStoreCategoryRepository;
	@Resource
	private DealerCurrentLoginService dealerCurrentAccountService;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private DealerPromotionRepository dealerPromotionRepository;
	@Resource
	MonthIncomeAndExpenditureRepository monthIncomeAndExpenditureRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String, List<Store>> getStoresGroupByCategory() {
		Map<String, List<Store>> map = new HashMap<String, List<Store>>();
		List<DealerStoreCategory> categories = getStoreCategories();
		for (DealerStoreCategory category : categories) {
			List<Store> stores = new ArrayList<Store>();
			stores.addAll(category.getStores());
			map.put(category.getCategoryName(), stores);
		}
		return map;
	}

	@Override
	public void addNewStoreCategory(String category) {
		Assert.hasLength(category, "分类参数不能为空。");
		if (category.equals(DealerStoreCategory.UNDEFINED)) {
			throw new RecordAcessDeniedException("此分类名不可用，请使用其他分类名。");
		}
		Dealer dealer = dealerCurrentAccountService.getCurrentLoginDealer();
		DealerStoreCategory storeCategory = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealer.getId(), category);
		if (storeCategory != null) {
			throw new RecordExistedException("此分类名称已经存在。");
		}
		storeCategory = new DealerStoreCategory();
		storeCategory.setDealer(dealer);
		storeCategory.setCategoryName(category);
		dealerStoreCategoryRepository.save(storeCategory);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DealerStoreCategory> getStoreCategories() {
		long dealerId = dealerCurrentAccountService.getCurrentLoginDealerId();
		return dealerStoreCategoryRepository.findByDealerId(dealerId);
	}

	@Override
	public void deleteStoreCategory(String category) {
		Assert.hasLength(category, "分类参数不能为空。");
		if (category.equals(AgentStoreCategory.UNDEFINED)) {
			throw new IllegalArgumentException("默认分类无法删除。");
		}
		long dealerId = dealerCurrentAccountService.getCurrentLoginDealerId();

		DealerStoreCategory storeCategory = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealerId, category);
		if (storeCategory == null) {
			throw new RecordNotFoundException("此分类未找到。");
		}

		DealerStoreCategory undifined = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealerId, DealerStoreCategory.UNDEFINED);
		if (undifined == null) {
			throw new DataIntegrityException("默认分类未初始化。");
		}

		List<Store> stores = new ArrayList<Store>();
		stores.addAll(storeCategory.getStores());

		storeRepository.updateDealerCategoryByDealerCategory(storeCategory.getId(), undifined.getId());
		dealerStoreCategoryRepository.delete(storeCategory);
	}

	@Override
	public void renameStoreCategory(String category, String newName) {
		Assert.hasLength(category, "分类参数不能为空。");
		Assert.hasLength(newName, "分类参数不能为空。");
		if (category.equals(AgentStoreCategory.UNDEFINED)) {
			throw new IllegalArgumentException("默认分类无法重命名。");
		}
		if (newName.equals(AgentStoreCategory.UNDEFINED)) {
			throw new RecordAcessDeniedException("此分类名不可用，请选择其他分类名。");
		}

		long dealerId = dealerCurrentAccountService.getCurrentLoginDealerId();
		DealerStoreCategory category1 = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealerId, category);
		if (category1 == null) {
			throw new RecordNotFoundException("要修改的分类未找到。");
		}
		DealerStoreCategory category2 = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealerId, newName);
		if (category2 != null) {
			throw new RecordExistedException("此分类名称已存在。");
		}
		category1.setCategoryName(newName);
		dealerStoreCategoryRepository.save(category1);

	}

	@Override
	public void moveToCategory(String category, List<Long> storeIds) {
		Assert.hasLength(category, "参数不能为空。");
		Assert.notNull(storeIds, "参数不能为空。");
		Dealer dealer = dealerCurrentAccountService.getCurrentLoginDealer();
		DealerStoreCategory storeCategory = dealerStoreCategoryRepository.findByDealerIdAndCategoryName(dealer.getId(), category);

		if (storeCategory == null) {
			throw new RecordNotFoundException("此分类名称未找到。");
		}
		for (long id : storeIds) {
			Store store = storeRepository.findOne(id);
			if (store == null) {
				throw new IllegalArgumentException("参数中某个门店不存在。");
			}
			if (!dealer.equals(store.getDealer())) {
				throw new IllegalArgumentException("参数中某个门店不属于此经销商。");
			}
			store.setDealerCategory(storeCategory);
			storeRepository.save(store);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MonthIncomeAndExpenditure> getMonthIncomeAndExpenditures(Date start, Date end) {
		long dealerId = dealerCurrentAccountService.getCurrentLoginDealerId();
		if (start == null | end == null) {
			throw new IllegalArgumentException("date出错");
		}
		return monthIncomeAndExpenditureRepository.findByIdDealerIdAndIdDateBetween(dealerId, Integer.parseInt(DateUtil.dateToString(start, "yyyyMM")),
				Integer.parseInt(DateUtil.dateToString(end, "yyyyMM")));
	}

}
