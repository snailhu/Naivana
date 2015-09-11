package com.nirvana.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.CheckHistoryItem;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventPK;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.fdfs.FileOperator;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.CheckHistoryItemRepository;
import com.nirvana.repository.dealer.InventoriesCheckHistoryRepository;
import com.nirvana.repository.dealer.InventoryRepository;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.service.DealerInventoryService;
import com.nirvana.service.InventoryService;
import com.nirvana.service.pojo.InventoryCheck;

@Service
@Transactional
public class DealerInventoryServiceImpl implements DealerInventoryService {

	@Resource
	private InventoryRepository inventoryRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private DealerCurrentLoginService dealerCurrentLoginService;
	@Resource
	private InventoriesCheckHistoryRepository inventoriesCheckHistoryRepository;
	@Resource
	private CheckHistoryItemRepository checkHistoryItemRepository;
	@Resource
	private InventoryService inventoryService;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventories(int page, int size) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealer();
		return inventoryService.getInventories(dealer, page, size);
	}

	@Override
	public void updateInventories(List<InventoryCheck> pojos, MultipartFile sign) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealerUser().getDealer();
		if (pojos == null) {
			return;
		}

		InventoriesCheckHistory history = new InventoriesCheckHistory();
		history.setDate(new Date());
		history.setDealer(dealer);
		history.setUrl(FileOperator.savePicture(sign));
		Set<CheckHistoryItem> historyItems = new HashSet<CheckHistoryItem>();
		history.setItems(historyItems);

		Set<Inventory> set = new HashSet<Inventory>();
		for (InventoryCheck pojo : pojos) {
			Product p = productRepository.findOne(pojo.getProductCode());
			if (p == null) {
				throw new RecordNotFoundException();
			}
			CheckHistoryItem checkHistoryItem = new CheckHistoryItem();
			InventPK pk = new InventPK(dealer, p);
			Inventory inventory = inventoryRepository.findOne(pk);
			if (inventory == null) {
				inventory = new Inventory();
				inventory.setPk(pk);
				inventory.setSalesVol(0);
			}
			if (pojo.getAmounts() < 0 || pojo.getPrice() < 0) {
				throw new IllegalArgumentException("数量价格不能为负");
			}
			checkHistoryItem.setProduct(p);
			checkHistoryItem.setAfterAmount(pojo.getAmounts());
			checkHistoryItem.setAfterPrice(pojo.getPrice());
			checkHistoryItem.setBeforeAmount(inventory.getAmounts() == null ? 0 : inventory.getAmounts());
			checkHistoryItem.setBeforePrice(inventory.getPrice() == null ? 0f : inventory.getPrice());
			historyItems.add(checkHistoryItem);

			inventory.setAmounts(pojo.getAmounts());
			inventory.setPrice(pojo.getPrice());
			set.add(inventory);

		}
		inventoryRepository.save(set);
		checkHistoryItemRepository.save(historyItems);
		inventoriesCheckHistoryRepository.save(history);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventoriesByBrand(String brandName, int page, int size) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealer();
		return inventoryService.getInventoriesByBrand(dealer, brandName, page, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<InventoriesCheckHistory> getInventoriesHistory(Date start, Date end, int page, int size) {
		long dealerId = dealerCurrentLoginService.getCurrentLoginDealerId();
		return inventoriesCheckHistoryRepository.findByDealerIdAndDateBetweenOrderByDateDesc(dealerId, start, end, new PageRequest(page - 1, size));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventoriesForUpdate(int page, int size) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealer();
		return inventoryService.getInventoriesForUpdate(dealer, page, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventoriesByBrandForUpdate(String brandName, int page, int size) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealer();
		return inventoryService.getInventoriesByBrandForUpdate(dealer, brandName, page, size);
	}

}
