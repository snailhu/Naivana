/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月28日 下午1:13:12
 */
package com.nirvana.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventPK;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.InventoryRepository;
import com.nirvana.repository.store.usersys.StoreUserRepository;

/**
 * @Title:AddInventory.java
 * @Description:
 * @Version:v1.0
 */
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-jpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AddInventory {
	@Resource
	InventoryRepository inventoryRepository;
	@Resource
	StoreUserRepository storeUserRepository;
	@Resource
	ProductRepository productRepository;
	@Test
	public void addInventory(){
		StoreUser user=storeUserRepository.findByUserName("store1");
		List<Product> l=productRepository.findAll();
		Dealer dealer=user.getStore().getDealer();
		Random random=new Random();
		List<Inventory> list=new ArrayList<Inventory>();
		for(Product p:l){
			Inventory inventory=new Inventory();
			inventory.setAmounts(random.nextInt(110));
			inventory.setPrice(random.nextInt(110)+random.nextDouble());
			inventory.setSalesVol(random.nextInt(10000));
			inventory.setPk(new InventPK(dealer, p));
			list.add(inventory);
		}
		inventoryRepository.save(list);
	}
}
