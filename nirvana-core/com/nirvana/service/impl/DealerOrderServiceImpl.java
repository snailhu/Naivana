package com.nirvana.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderItem;
import com.nirvana.domain.dealer.DealerOrderItemPK;
import com.nirvana.domain.dealer.DealerOrderItemType;
import com.nirvana.domain.dealer.DealerOrderState;
import com.nirvana.domain.dealer.DealerStockSerialNumber;
import com.nirvana.erp.domain.PepsiOrderHead;
import com.nirvana.erp.domain.PepsiOrderLine;
import com.nirvana.erp.service.ErpService;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.fdfs.FileOperator;
import com.nirvana.mongo.converter.impl.DealerOrderConverter;
import com.nirvana.mongo.document.DealerOrderDocument;
import com.nirvana.mongo.repository.DealerOrderDocumentRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.DealerOrderItemRepository;
import com.nirvana.repository.dealer.DealerOrderRepository;
import com.nirvana.repository.dealer.DealerStockSerialNumberRepository;
import com.nirvana.service.DealerOrderService;
import com.nirvana.utils.Assert;

@Service
@Transactional
public class DealerOrderServiceImpl implements DealerOrderService {

	@Resource
	private ErpService erpService;
	@Resource
	private DealerOrderRepository dealerOrderRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private DealerOrderItemRepository dealerOrderItemRepository;
	@Resource
	private DealerStockSerialNumberRepository dealerStockSerialNumberRepository;
	@Resource
	private DealerOrderDocumentRepository dealerOrderDocumentRepository;
	@Resource
	private DealerOrderConverter dealerOrderConverter;

	@Override
	public long operateNewOrderNo(Dealer dealer) {
		long time = System.currentTimeMillis();
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTimeInMillis(time);
		int year = currentCalendar.get(Calendar.YEAR);
		int month = currentCalendar.get(Calendar.MONTH) + 1;
		int day = currentCalendar.get(Calendar.DAY_OF_MONTH);
		int date = year * 100 * 100 + month * 100 + day;
		long dealerId = dealer.getId();
		DealerStockSerialNumber dealerSerialNumber = dealerStockSerialNumberRepository.findOne(dealerId, LockModeType.OPTIMISTIC);
		if (dealerSerialNumber == null) {
			throw new DataIntegrityException("经销商的流水号未初始化。");
		}

		if (date > dealerSerialNumber.getDate()) {
			dealerSerialNumber.setDate(date);
			dealerSerialNumber.setSerialNum(1);
			dealerStockSerialNumberRepository.save(dealerSerialNumber);
		} else {
			dealerStockSerialNumberRepository.updateSerialNum(dealerId);
			dealerStockSerialNumberRepository.refresh(dealerSerialNumber);
		}
		long orderId = (long) date * 10000l * 10000l * 10l + dealerId * 1000l + (long) dealerSerialNumber.getSerialNum();
		return orderId;
	}

	@Override
	public DealerOrder placeOrder(Dealer dealer, Map<String, Integer> skus, boolean isAgentHelped, boolean isInline, MultipartFile picture) {
		PepsiOrderHead head = erpService.placeOrder(dealer.getCodeInERP(), 1, skus);
		if (head == null) {
			throw new IllegalArgumentException("下单失败。");
		}
		long orderId = operateNewOrderNo(dealer);
		DealerOrder dealerOrder = new DealerOrder();
		dealerOrder.setOrderNo(orderId);
		dealerOrder.setIsAgentHelped(isAgentHelped);
		dealerOrder.setIsInLine(isInline);
		dealerOrder.setCodeInERP(head.getOrderNo());
		dealerOrder.setDealer(dealer);
		dealerOrder.setEnterDate(head.getDateEntered());
		dealerOrder.setState(DealerOrderState.fromERP(head.getState()));
		dealerOrder.setWantDate(head.getWantedDeliveryDate());
		Set<DealerOrderItem> items = new HashSet<DealerOrderItem>();
		if (picture != null) {
			String url = FileOperator.savePicture(picture);
			dealerOrder.setSignPic(url);
		}
		BigDecimal totalPrice = new BigDecimal(0);
		for (PepsiOrderLine line : head.getLines()) {
			DealerOrderItem item = new DealerOrderItem();
			DealerOrderItemPK pk = new DealerOrderItemPK();
			pk.setLineNo(Integer.parseInt(line.getId().getLineNo()));
			pk.setOrder(dealerOrder);
			item.setPk(pk);
			item.setAmount(line.getQty().intValue());
			if (line.getDiscount() != null) {
				item.setDiscount(line.getDiscount().floatValue());
			}

			if (line.getPointsAmount() != null) {
				item.setPoints(line.getPointsAmount().intValue());
			}
			Product product = productRepository.findOne(line.getPartNo());
			if (product == null) {
				throw new RecordNotFoundException("此商品未找到。");
			}
			item.setProduct(product);
			item.setType(DealerOrderItemType.parseDealerOrderItemType(line.getOrderType()));
			item.setUnitMeas(line.getSalesUnitMeas());
			item.setUnitPrice(line.getSaleUnitPrice().floatValue());
			items.add(item);
			totalPrice = totalPrice.add(line.getQty().multiply(line.getSaleUnitPrice()));
		}
		dealerOrder.setItems(items);
		dealerOrder.setTotalPrice(totalPrice);
		dealerOrderRepository.save(dealerOrder);
		return dealerOrder;
	}

	@Override
	public DealerOrder placeOrder(Dealer dealer, Map<String, Integer> skus, boolean isAgentHelped, MultipartFile picture) {
		return placeOrder(dealer, skus, isAgentHelped, false, picture);
	}

	@Override
	public void deleteTwoWeekAgoFinishedOrders() {
		long time = System.currentTimeMillis();
		long twoWeek = 2 * 7 * 24 * 60 * 60 * 1000;
		long twoWeekAgo = time - twoWeek;
		Date date = new Date(twoWeekAgo);
		dealerOrderRepository.deleteByDateAndState(date, DealerOrderState.CANCELED);
		dealerOrderRepository.deleteByDateAndState(date, DealerOrderState.CLOSED);
	}

	@Override
	public void finishOrder(DealerOrder dealerOrder) {
		Assert.notNull(dealerOrder);
		DealerOrderDocument document = dealerOrderConverter.convert(dealerOrder);
		dealerOrderDocumentRepository.save(document);
		// TODO 结束经销商订单...
	}
}
