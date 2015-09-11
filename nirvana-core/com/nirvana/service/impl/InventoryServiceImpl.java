package com.nirvana.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventPK;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.InventoryRepository;
import com.nirvana.service.InventoryService;
import com.nirvana.utils.Assert;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class InventoryServiceImpl implements InventoryService {

	@Resource
	private InventoryRepository inventoryRepository;
	@Resource
	private ProductRepository productRepository;

	@Override
	public Page<Inventory> getInventories(Dealer dealer, int page, int size) {
		Assert.notNull(dealer);
		Page<Inventory> p = inventoryRepository.findByPkDealerId(dealer.getId(), new PageRequest(page - 1, size));
		return p;
	}

	@Override
	public Page<Inventory> getInventoriesByBrand(Dealer dealer, String brandName, int page, int size) {
		Assert.notNull(dealer);
		Page<Inventory> p = inventoryRepository.findByPkDealerIdAndPkProductBrandName(dealer.getId(), brandName, new PageRequest(page - 1, size));
		return p;
	}

	@Override
	public Page<Inventory> getInventoriesForUpdate(Dealer dealer, int page, int size) {
		Assert.notNull(dealer);
		Pageable pageable = new PageRequest(page - 1, size);
		Page<Product> pp = productRepository.findAll(pageable);

		List<Inventory> list = new ArrayList<Inventory>();
		for (Product p : pp) {
			Inventory inventory = inventoryRepository.findByPkDealerAndPkProductCode(dealer, p.getCode());
			if (inventory == null) {
				inventory = new Inventory();
				inventory.setAmounts(0);
				inventory.setPrice(0d);
				inventory.setSalesVol(0);
				inventory.setPk(new InventPK(dealer, p));
			}
			list.add(inventory);

		}
		Page<Inventory> pi = new PageImpl<Inventory>(list, pageable, pp.getTotalElements());
		return pi;
	}

	@Override
	public Page<Inventory> getInventoriesByBrandForUpdate(Dealer dealer, String brandName, int page, int size) {
		Assert.notNull(dealer);
		Pageable pageable = new PageRequest(page - 1, size);
		Page<Product> pp = productRepository.findByBrandName(brandName, pageable);

		List<Inventory> list = new ArrayList<Inventory>();
		for (Product p : pp) {
			Inventory inventory = inventoryRepository.findByPkDealerAndPkProductCode(dealer, p.getCode());
			if (inventory == null) {
				inventory = new Inventory();
				inventory.setAmounts(0);
				inventory.setPrice(0d);
				inventory.setSalesVol(0);
				inventory.setPk(new InventPK(dealer, p));
			}
			list.add(inventory);

		}
		Page<Inventory> pi = new PageImpl<Inventory>(list, pageable, pp.getTotalElements());
		return pi;
	}

	@Override
	@Transactional
	public void updateAmount(Dealer dealer, Product product, int amount) {
		Assert.notNull(dealer);
		Assert.notNull(product);
		InventPK pk = new InventPK(dealer, product);
		inventoryRepository.updateAmount(amount, pk);
		Inventory inventory = inventoryRepository.findOne(pk);
		if (inventory.getAmounts() < 0) {
			throw new IllegalArgumentException("¿â´æ²»×ã¡£");
		}
	}

	@Override
	@Transactional
	public void updateSellVol(Dealer dealer, Product product, int amount) {
		Assert.notNull(dealer);
		Assert.notNull(product);
		InventPK pk = new InventPK(dealer, product);
		inventoryRepository.updateSalesVol(amount, pk);
	}

}
