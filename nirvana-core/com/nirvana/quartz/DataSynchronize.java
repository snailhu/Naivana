package com.nirvana.quartz;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.nirvana.service.SynchronizeService;

@Component
public class DataSynchronize {

	@Resource
	private SynchronizeService synchronizeService;

	private static volatile boolean is_synchronizing_channels = false;
	private static volatile boolean is_synchronizing_products = false;
	private static volatile boolean is_synchronizing_regions = false;
	private static volatile boolean is_synchronizing_customers = false;
	private static volatile boolean is_synchronizing_dealerorders = false;

	private static Object lock_sync_channels = new Object();
	private static Object lock_sync_products = new Object();
	private static Object lock_sync_regions = new Object();
	private static Object lock_sync_customers = new Object();
	private static Object lock_sync_dealerorders = new Object();

	public void syncChannels() {
		synchronized (lock_sync_channels) {
			if (is_synchronizing_channels) {
				System.out.println("上一次渠道同步尚未完成。");
				return;
			}
			is_synchronizing_channels = true;
		}
		synchronizeService.syncChannels();
		is_synchronizing_channels = false;
	}

	public void syncProducts() {
		synchronized (lock_sync_products) {
			if (is_synchronizing_products) {
				System.out.println("上一次商品同步尚未完成。");
				return;
			}
			is_synchronizing_products = true;
		}
		synchronizeService.syncProducts();
		is_synchronizing_products = false;
	}

	public void syncRegions() {
		synchronized (lock_sync_regions) {
			if (is_synchronizing_regions) {
				System.out.println("上一次区域同步尚未完成。");
				return;
			}
			is_synchronizing_regions = true;
		}
		synchronizeService.syncRegions();
		is_synchronizing_regions = false;
	}

	public void syncCustomers() {
		synchronized (lock_sync_customers) {
			if (is_synchronizing_customers) {
				System.out.println("上一次客户同步尚未完成。");
				return;
			}
			is_synchronizing_customers = true;
		}
		synchronizeService.syncCustomers(false);
		is_synchronizing_customers = false;
	}

	public void syncDealerOrders() {
		synchronized (lock_sync_dealerorders) {
			if (is_synchronizing_dealerorders) {
				System.out.println("上一次订单同步尚未完成。");
				return;
			}
			is_synchronizing_dealerorders = true;
		}
		synchronizeService.syncDealerOrders(false);
		is_synchronizing_dealerorders = false;
	}

}
