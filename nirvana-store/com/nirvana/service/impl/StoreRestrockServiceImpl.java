package com.nirvana.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.domain.store.StoreOrderState;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.ResourceAccessException;
import com.nirvana.mongo.converter.impl.StoreOrderConverter;
import com.nirvana.mongo.converter.impl.StoreOrderDocumentConverter;
import com.nirvana.mongo.document.StoreOrderDocument;
import com.nirvana.mongo.repository.StoreOrderDocumentRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.DealerPromotionRepository;
import com.nirvana.repository.dealer.InventoryRepository;
import com.nirvana.repository.store.StoreOrderRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.service.InventoryService;
import com.nirvana.service.StoreCurrentAccountService;
import com.nirvana.service.StoreOrderService;
import com.nirvana.service.StoreRestrockService;

@Service
@Transactional
public class StoreRestrockServiceImpl implements StoreRestrockService {

	@Resource
	private StoreOrderDocumentConverter storeOrderDocumentConverter;
	@Resource
	private StoreOrderConverter storeOrderConverter;
	@Resource
	private StoreCurrentAccountService storeCurrentAccountService;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private StoreOrderRepository storeOrderRepository;
	@Resource
	private InventoryRepository inventoryRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private DealerPromotionRepository dealerPromotionRepository;
	@Resource
	private StoreOrderService storeOrderService;
	@Resource
	private StoreOrderDocumentRepository storeOrderDocumentRepository;
	@Resource
	private InventoryService inventoryService;

	private Pageable getPageable(int page, int size) {
		if (page <= 0) {
			page = 1;
		}
		if (size <= 0) {
			page = 20;
		}
		return new PageRequest(page - 1, size);
	}

	private void orderBelongToMe(StoreOrder order) {
		Assert.notNull(order, "参数不能为空。");
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		if (!store.equals(order.getStore())) {
			throw new ResourceAccessException("你无权操作此订单。");
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getProducts(int page, int size) {
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		if (store.getDealer() == null) {
			throw new RecordNotFoundException("你没有经销商。");
		}
		return inventoryRepository.findByPkDealer(store.getDealer(), getPageable(page, size));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getProducts(String brandCode, int page, int size) {
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		if (store.getDealer() == null) {
			return null;
		}
		return inventoryRepository.findByPkDealerAndPkProductBrandName(store.getDealer(), brandCode, getPageable(page, size));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Inventory getProduct(String productCode) {
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		if (store.getDealer() == null) {
			return null;
		}
		return inventoryRepository.findByPkDealerAndPkProductCode(store.getDealer(), productCode);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public StoreOrder getOrder(long orderNo) {
		StoreOrder order = storeOrderRepository.findByOrderNo(orderNo);
		if (order == null) {
			throw new RecordNotFoundException("此订单不存在。");
		}
		return order;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getOrders(int page, int size) {
		long storeId = storeCurrentAccountService.getCurrentLoginStoreId();
		return storeOrderRepository.findByStoreIdOrderByIdDesc(storeId, getPageable(page, size));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getOrders(Date startDate, Date endDate, int page, int size) {
		long storeId = storeCurrentAccountService.getCurrentLoginStoreId();
		return storeOrderRepository.findByStoreIdAndStateAndCreateDateBetweenOrderByIdDesc(storeId, StoreOrderState.FINISHHANDLE, startDate, endDate, getPageable(page, size));
	}

	@Override
	public void appraise(long orderNo, int product, int delivery, int manner, String note) {
		StoreOrderDocument order = storeOrderDocumentRepository.findOne(orderNo);
		if (order == null) {
			throw new RecordNotFoundException("此订单不存在。");
		}
		long storeId = storeCurrentAccountService.getCurrentLoginStoreId();
		if (storeId != order.getStoreId()) {
			throw new ResourceAccessException("你无权操作此订单。");
		}
		order.setManner(manner);
		order.setDelivery(delivery);
		order.setProduct(product);
		order.setWords(note);
		storeOrderDocumentRepository.save(order);
	}

	@Override
	public StoreOrder placeOrder(Map<String, Integer> items) {
		Assert.notEmpty(items, "购买的物品不能为空。");
		Store store = storeCurrentAccountService.getCurrentLoginStore();
		StoreOrder order = storeOrderService.placeOrder(store, items, false, null);
		return order;

	}

	@Override
	public StoreOrder submitOrder(long orderNo, MultipartFile sign) {
		StoreOrder order = storeOrderRepository.findByOrderNoLocked(orderNo);
		if (order == null) {
			throw new RecordNotFoundException("此订单不存在。");
		}
		orderBelongToMe(order);
		StoreOrder order1 = storeOrderService.submitOrder(order);
		return order1;
	}

	@Override
	public void cancelOrder(long orderNo) {
		StoreOrder order = storeOrderRepository.findByOrderNo(orderNo);
		if (order == null) {
			throw new RecordNotFoundException("无权操作次订单");
		}
		Dealer dealer = order.getDealer();
		if (order.getState() != StoreOrderState.NOTSUBMIT && order.getState() != StoreOrderState.NOTHANDLE) {
			throw new ResourceAccessException("订单已在处理或者已经完成,无法取消。");
		}
		orderBelongToMe(order);
		if (order.getState() == StoreOrderState.NOTHANDLE) {
			for (StoreOrderItem item : order.getItems()) {
				inventoryService.updateAmount(dealer, item.getPk().getProduct(), item.getAmount());
				inventoryService.updateSellVol(dealer, item.getPk().getProduct(), -item.getAmount());
			}
		}
		order.setState(StoreOrderState.CANCELED);
		storeOrderRepository.saveAndFlush(order);
		StoreOrderDocument document = storeOrderConverter.convert(order);
		storeOrderDocumentRepository.save(document);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getOrders(StoreOrderState state, int page, int size) {
		Assert.notNull(state, "参数不能为空。");
		long storeId = storeCurrentAccountService.getCurrentLoginStoreId();
		return storeOrderRepository.findByStoreIdAndStateOrderByIdDesc(storeId, state, getPageable(page, size));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getOrdersByMongo(StoreOrderState state, int page, int size) {
		Assert.notNull(state, "参数不能为空。");
		long storeId = storeCurrentAccountService.getCurrentLoginStoreId();
		Page<StoreOrderDocument> document = storeOrderDocumentRepository.findByStoreIdAndStateOrderByIdDesc(storeId, state, getPageable(page, size));
		List<StoreOrder> list = storeOrderDocumentConverter.convert(document.getContent());
		Page<StoreOrder> storeOrderPage = new PageImpl<StoreOrder>(list, getPageable(page, size), document.getTotalElements());
		return storeOrderPage;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrderDocument> getOrdersBill(Date startDate, Date endDate, int page, int size) {
		long storeId = storeCurrentAccountService.getCurrentLoginStoreId();
		return storeOrderDocumentRepository.findByStoreIdAndStateAndCreateDateBetweenOrderByIdDesc(storeId, StoreOrderState.FINISHHANDLE, startDate, endDate,
				getPageable(page, size));
	}

}
