package com.nirvana.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.domain.store.StoreOrderItemPK;
import com.nirvana.domain.store.StoreOrderState;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.ResourceAccessException;
import com.nirvana.mongo.converter.impl.StoreOrderConverter;
import com.nirvana.mongo.converter.impl.StoreOrderDocumentConverter;
import com.nirvana.mongo.document.StoreOrderDocument;
import com.nirvana.mongo.repository.StoreOrderDocumentRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.InventoryRepository;
import com.nirvana.repository.store.StoreOrderItemRepository;
import com.nirvana.repository.store.StoreOrderRepository;
import com.nirvana.service.DealerBusinessService;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.service.InventoryService;
import com.nirvana.service.MonthIncomeAndExpenditureService;
import com.nirvana.service.pojo.Appraises;

@Service
public class DealerBusinessServiceImpl implements DealerBusinessService {
	@Resource
	StoreOrderDocumentConverter storeOrderDocumentConverter;
	@Resource
	private StoreOrderRepository storeOrderRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private StoreOrderItemRepository storeOrderItemRepository;
	@Resource
	private InventoryRepository inventoryRepository;
	@Resource
	private DealerCurrentLoginService dealerCurrentLoginService;
	@Resource
	private StoreOrderDocumentRepository storeOrderDocumentRepository;
	@Resource
	private StoreOrderConverter storeOrderConverter;
	@Resource
	private InventoryService inventoryService;
	@Resource
	private MonthIncomeAndExpenditureService monthIncomeAndExpenditureService;

	private Pageable getPageable(int page, int size) {
		if (page <= 0)
			page = 1;
		if (size <= 0) {
			size = 20;
		}
		return new PageRequest(page - 1, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getUnhandledOrders(int page, int size) {
		long dealerId = dealerCurrentLoginService.getCurrentLoginDealerId();
		Page<StoreOrder> storeOrders = storeOrderRepository.findByDealerIdAndState(dealerId, StoreOrderState.NOTHANDLE, new PageRequest(page - 1, size));
		return storeOrders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getHandlingOrders(int page, int size) {
		long dealerId = dealerCurrentLoginService.getCurrentLoginDealerId();
		Page<StoreOrder> storeOrders = storeOrderRepository.findByDealerIdAndState(dealerId, StoreOrderState.HANDLING, new PageRequest(page - 1, size));
		return storeOrders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getFinishedOrders(int page, int size) {
		long dealerId = dealerCurrentLoginService.getCurrentLoginDealerId();
		Page<StoreOrderDocument> documents = storeOrderDocumentRepository.findByDealerIdAndStateOrderByIdDesc(dealerId, StoreOrderState.FINISHHANDLE, getPageable(page, size));
		List<StoreOrder> list = storeOrderDocumentConverter.convert(documents.getContent());
		Page<StoreOrder> storeOrderPage = new PageImpl<StoreOrder>(list, getPageable(page, size), documents.getTotalElements());
		return storeOrderPage;
	}

	@Override
	public void handleOrder(long orderNo, String note) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealer();
		StoreOrder storeOrder = storeOrderRepository.findByOrderNo(orderNo);
		if (storeOrder == null)
			throw new RecordNotFoundException("�˶��������ڡ�");
		if (storeOrder.getState() != StoreOrderState.NOTHANDLE) {
			throw new RecordAcessDeniedException("δ����Ķ������ܴ���");
		}
		if (!dealer.equals(storeOrder.getDealer())) {
			throw new ResourceAccessException("����Ȩ�����˶�����");
		}
		storeOrder.setState(StoreOrderState.HANDLING);
		if (note != null) {
			storeOrder.setNote(note);
		}
		storeOrderRepository.saveAndFlush(storeOrder);

	}

	@Override
	public void finishOrder(long orderNo, Map<String, Integer> skuData, Double payPrice, String gifts, String note) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealer();
		StoreOrder storeOrder = storeOrderRepository.findByOrderNo(orderNo);
		if (storeOrder == null) {
			throw new RecordNotFoundException("�˶��������ڡ�");
		}
		if (storeOrder.getState() != StoreOrderState.HANDLING) {
			throw new RecordAcessDeniedException("�Ѵ���Ķ���������ɶ�����");
		}
		if (!dealer.equals(storeOrder.getDealer())) {
			throw new ResourceAccessException("����Ȩ�����˶�����");
		}
		editOrder(storeOrder, skuData, payPrice, gifts, note);
		storeOrder.setState(StoreOrderState.FINISHHANDLE);
		storeOrderRepository.saveAndFlush(storeOrder);

		monthIncomeAndExpenditureService.updateThisMonthIncome(dealer, storeOrder.getPayablefee().doubleValue());

		StoreOrderDocument document = storeOrderConverter.convert(storeOrder);
		storeOrderDocumentRepository.save(document);

	}

	private void editOrder(StoreOrder storeOrder, Map<String, Integer> skuData, Double payPrice, String gifts, String note) {
		Assert.notNull(storeOrder, "��������Ϊ�ա�");
		Dealer dealer = storeOrder.getDealer();
		if (skuData != null) {
			List<StoreOrderItem> itemList = new ArrayList<StoreOrderItem>();
			for (Entry<String, Integer> entry : skuData.entrySet()) {
				Product product = productRepository.findOne(entry.getKey());
				if (product == null) {
					throw new RecordNotFoundException();
				}
				StoreOrderItemPK pk = new StoreOrderItemPK(storeOrder, product);
				StoreOrderItem item = storeOrderItemRepository.findOne(pk);
				// ���¿�漰����
				if (item == null) {
					throw new IllegalArgumentException("�������󣬶������޴���Ŀ��");
				}
				int amount = entry.getValue() - item.getAmount();
				inventoryService.updateAmount(dealer, product, -amount);
				inventoryService.updateSellVol(dealer, product, amount);
				item.setAmount(entry.getValue());
				itemList.add(item);
			}
			storeOrderItemRepository.save(itemList);
			if (payPrice != null) {
				storeOrder.setPayablefee(new BigDecimal(payPrice));
			}
		}

		if (gifts != null) {
			storeOrder.setRewards(gifts);
		}

		if (note != null) {
			storeOrder.setNote(note);
		}

		storeOrderRepository.save(storeOrder);

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Appraises getAppraises(long orderNo) {
		Dealer dealer = dealerCurrentLoginService.getCurrentLoginDealer();
		StoreOrderDocument order = storeOrderDocumentRepository.findOne(orderNo);
		if (order == null) {
			throw new RecordNotFoundException("�˶��������ڡ�");
		}
		if (!(dealer.getId()).equals(order.getDealerId())) {
			throw new ResourceAccessException("����Ȩ�����˶�����");
		}
		Appraises appraises = new Appraises();
		appraises.setDelivery(order.getDelivery());
		appraises.setManner(order.getManner());
		appraises.setNote(order.getWords());
		appraises.setProduct(order.getProduct());
		return appraises;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getUnhandledOrdersByDate(int page, int size, Date start, Date end) {
		long dealerId = dealerCurrentLoginService.getCurrentLoginDealerId();
		Page<StoreOrder> storeOrders = storeOrderRepository.findByDealerIdAndStateAndCreateDateBetweenOrderByIdDesc(dealerId, StoreOrderState.NOTHANDLE, start, end,
				getPageable(page, size));
		return storeOrders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getHandlingOrdersByDate(int page, int size, Date start, Date end) {
		long dealerId = dealerCurrentLoginService.getCurrentLoginDealerId();
		Page<StoreOrder> storeOrders = storeOrderRepository.findByDealerIdAndStateAndCreateDateBetweenOrderByIdDesc(dealerId, StoreOrderState.HANDLING, start, end,
				getPageable(page, size));
		return storeOrders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getFinishedOrdersByDate(int page, int size, Date start, Date end) {
		long dealerId = dealerCurrentLoginService.getCurrentLoginDealerId();
		Page<StoreOrderDocument> documents = storeOrderDocumentRepository.findByDealerIdAndStateAndCreateDateBetweenOrderByIdDesc(dealerId, StoreOrderState.FINISHHANDLE, start,
				end, getPageable(page, size));
		List<StoreOrder> list = storeOrderDocumentConverter.convert(documents.getContent());
		Page<StoreOrder> storeOrderPage = new PageImpl<StoreOrder>(list, getPageable(page, size), documents.getTotalElements());
		return storeOrderPage;
	}
}
