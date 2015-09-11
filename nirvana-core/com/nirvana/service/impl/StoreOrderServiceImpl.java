package com.nirvana.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.DealerSerialNumber;
import com.nirvana.domain.dealer.InventPK;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.dealer.PromotionGoods;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.domain.store.StoreOrderItemPK;
import com.nirvana.domain.store.StoreOrderState;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.InventoryException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.ResourceAccessException;
import com.nirvana.fdfs.FileOperator;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.DealerPromotionRepository;
import com.nirvana.repository.dealer.DealerSerialNumberRepository;
import com.nirvana.repository.dealer.InventoryRepository;
import com.nirvana.repository.store.StoreOrderRepository;
import com.nirvana.service.InventoryService;
import com.nirvana.service.StoreOrderService;
import com.nirvana.utils.Assert;
import com.nirvana.utils.DateUtil;

@Service
@Transactional
public class StoreOrderServiceImpl implements StoreOrderService {

	@Resource
	private DealerSerialNumberRepository dealerSerialNumberRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private InventoryRepository inventoryRepository;
	@Resource
	private DealerPromotionRepository dealerPromotionRepository;
	@Resource
	private StoreOrderRepository storeOrderRepository;
	@Resource
	private InventoryService inventoryService;

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
		DealerSerialNumber dealerSerialNumber = dealerSerialNumberRepository.findOne(dealerId, LockModeType.OPTIMISTIC);
		if (dealerSerialNumber == null) {
			throw new DataIntegrityException("经销商的流水号未初始化。");
		}

		if (date > dealerSerialNumber.getDate()) {
			dealerSerialNumber.setDate(date);
			dealerSerialNumber.setSerialNum(1);
			dealerSerialNumberRepository.save(dealerSerialNumber);
		} else {
			dealerSerialNumberRepository.updateSerialNum(dealerId);
			dealerSerialNumberRepository.refresh(dealerSerialNumber);
		}
		long orderId = (long) date * 10000 * 10000 * 1000 + dealerId * 10000 * 10 + dealerSerialNumber.getSerialNum();
		return orderId;
	}

	@Override
	public StoreOrder placeOrder(Store store, Map<String, Integer> items, boolean isAgentHelped, boolean isInLine, MultipartFile sign) {
		Assert.notNull(store);
		Assert.notNull(items);
		Dealer dealer = store.getDealer();
		if (dealer == null) {
			throw new ResourceAccessException("此门店没有经销商");
		}
		if (dealer.getIsClose()) {
			throw new ResourceAccessException("经销商已经被关闭，无法下单。");
		}
		StoreOrder order = new StoreOrder();
		order.setDealer(dealer);
		order.setOrderNo(operateNewOrderNo(dealer));
		order.setIsAgentHelped(isAgentHelped);
		order.setIsInLine(isInLine);
		Set<StoreOrderItem> set = new HashSet<StoreOrderItem>();
		if (sign != null) {
			String url = FileOperator.savePicture(sign);
			order.setSignPic(url);
		}
		order.setItems(set);
		order.setState(StoreOrderState.NOTSUBMIT);
		order.setStore(store);
		BigDecimal payableFee = new BigDecimal("0");
		BigDecimal productTotalPrice = new BigDecimal("0");
		BigDecimal totalPrice = new BigDecimal("0");
		BigDecimal deliverFee = new BigDecimal("0");
		// float payableFee = 0f;// 应付款
		// float productTotalPrice = 0f;// 商品总金额
		String rewards = "";// 赠品
		// float totalPrice = 0f;// 订单总金额

		// float deliverFee = 0f;
		// 加上配送费
		payableFee = payableFee.add(deliverFee);
		totalPrice = totalPrice.add(deliverFee);
		// payableFee += deliverFee;
		// totalPrice += deliverFee;

		for (Entry<String, Integer> entry : items.entrySet()) {
			String code = entry.getKey();
			Product product = productRepository.findOne(code);
			if (product == null) {
				throw new RecordNotFoundException();
			}
			InventPK pk = new InventPK(store.getDealer(), product);
			Inventory inventory = inventoryRepository.findOne(pk);
			if (inventory == null) {
				throw new InventoryException("经销商库存中无此商品。");
			}
			if (inventory.getAmounts() < entry.getValue()) {
				throw new InventoryException("产品" + inventory.getPk().getProduct().getCode() + "库存不足。");
			}

			totalPrice = totalPrice.add(new BigDecimal(String.valueOf(inventory.getPrice())).multiply(new BigDecimal(String.valueOf(entry.getValue()))));
			productTotalPrice = productTotalPrice.add(new BigDecimal(String.valueOf(inventory.getPrice())).multiply(new BigDecimal(String.valueOf(entry.getValue()))));
			payableFee = payableFee.add(new BigDecimal(String.valueOf(inventory.getPrice())).multiply(new BigDecimal(String.valueOf(entry.getValue()))));
			// totalPrice += inventory.getPrice() * entry.getValue();
			// productTotalPrice += inventory.getPrice() * entry.getValue();
			// payableFee += inventory.getPrice() * entry.getValue();

			int now = Integer.parseInt(DateUtil.dateToString(new Date(), "yyyyMMdd"));
			DealerPromotion promotion = dealerPromotionRepository.findByDealerAndCategoriesAndProductsCodeAndEndDateGreaterThanAndBeginDateLessThan(store.getDealer().getId(),
					store.getDealerCategory().getId(), product.getCode(), now, now);
			String discountStr = "";
			if (promotion != null) {
				if (!promotion.getIsEffect()) {
					promotion.setIsEffect(true);
					dealerPromotionRepository.save(promotion);
				}
				switch (promotion.getpCondition()) {
				case ACHIEVECASE:

					if (entry.getValue() >= promotion.getCdtParam()) {
						discountStr += "满" + promotion.getCdtParam() + "箱";
						// 倍数
						int n = entry.getValue() / promotion.getCdtParam();
						switch (promotion.getWay()) {
						case GIVEAWAY:
							if (n > promotion.getWayParam()) {
								n = promotion.getWayParam().intValue();
							}
							discountStr += "赠：";
							for (PromotionGoods good : promotion.getGoods()) {
								discountStr += good.getGoods() + "*" + n + " | ";
								rewards += good.getGoods() + "*" + n + " | ";
							}
							break;
						case MONEYREDUCTION:
							discountStr += "减免现金：" + promotion.getWayParam() + "*" + n + "元";
							payableFee = payableFee.subtract(new BigDecimal(String.valueOf(promotion.getWayParam())).multiply(new BigDecimal(String.valueOf(n))));
							// payableFee = payableFee - promotion.getWayParam()
							// * n;
							break;
						case PRICEDISCOUNT:
							discountStr += "享受：" + promotion.getWayParam() / 10f + "折优惠";
							payableFee = payableFee.subtract(new BigDecimal(String.valueOf(inventory.getPrice())).multiply(new BigDecimal(String.valueOf(entry.getValue())))
									.multiply(new BigDecimal("1").subtract(new BigDecimal(String.valueOf(promotion.getWayParam())).divide(new BigDecimal("100")))));
							// payableFee = payableFee - inventory.getPrice() *
							// entry.getValue() * (1 - promotion.getWayParam() /
							// 100f);
							break;
						default:
							break;
						}
					}
					break;
				case ACHIEVEMONEY:

					if (entry.getValue() * inventory.getPrice() >= promotion.getCdtParam()) {
						discountStr += "满" + promotion.getCdtParam() + "元金额";
						// 倍数
						int n1 = (int) (entry.getValue() * inventory.getPrice() / promotion.getCdtParam());
						switch (promotion.getWay()) {
						case GIVEAWAY:
							if (n1 > promotion.getWayParam()) {
								n1 = promotion.getWayParam().intValue();
							}
							discountStr += "赠：";
							for (PromotionGoods good : promotion.getGoods()) {
								discountStr += good.getGoods() + "*" + n1 + " | ";
								rewards += good.getGoods() + "*" + n1 + " | ";
							}
							break;
						case MONEYREDUCTION:
							discountStr += "减免现金：" + promotion.getWayParam() + "*" + n1 + "元";
							payableFee = payableFee.subtract(new BigDecimal(String.valueOf(promotion.getWayParam())).multiply(new BigDecimal(String.valueOf(n1))));
							// payableFee = payableFee - promotion.getWayParam()
							// * n1;
							break;
						case PRICEDISCOUNT:
							discountStr += "享受：" + promotion.getWayParam() / 10f + "折优惠";
							payableFee = payableFee.subtract(new BigDecimal(String.valueOf(inventory.getPrice())).multiply(new BigDecimal(String.valueOf(entry.getValue())))
									.multiply(new BigDecimal("1").subtract(new BigDecimal(String.valueOf(promotion.getWayParam())).divide(new BigDecimal("100")))));
							// payableFee = payableFee - inventory.getPrice()
							// * entry.getValue()
							// * (1 - promotion.getWayParam() / 100f);
							break;
						default:
							break;
						}
					}
					break;
				default:
					break;
				}
			}
			StoreOrderItem item = new StoreOrderItem();
			item.setAmount(entry.getValue());
			item.setUnitPrice(inventory.getPrice());
			if (discountStr.endsWith(" | ")) {
				discountStr = discountStr.substring(0, discountStr.lastIndexOf(" | "));
			}
			item.setDiscountDisc(discountStr);
			StoreOrderItemPK soipk = new StoreOrderItemPK(order, product);
			item.setPk(soipk);
			set.add(item);
		}
		DecimalFormat format = new DecimalFormat("0.00");
		order.setPayablefee(new BigDecimal(format.format(payableFee.doubleValue())));
		// order.setPayablefee(payableFee >= 0 ? Float.valueOf(format
		// .format(payableFee)) : 0.0f);
		order.setProductTotalPrice(new BigDecimal(format.format(productTotalPrice.doubleValue())));
		if (rewards.endsWith(" | ")) {
			rewards = rewards.substring(0, rewards.lastIndexOf(" | "));
		}
		order.setRewards(rewards);
		order.setTotalPrice(new BigDecimal(format.format(totalPrice.doubleValue())));
		storeOrderRepository.save(order);
		storeOrderRepository.flush();
		return order;
	}

	@Override
	public StoreOrder placeOrder(Store store, Map<String, Integer> items, boolean isAgentHelped, MultipartFile sign) {
		return placeOrder(store, items, isAgentHelped, false, sign);
	}

	@Override
	public StoreOrder submitOrder(StoreOrder order) {
		Dealer dealer = order.getDealer();
		if (order.getState() != StoreOrderState.NOTSUBMIT) {
			throw new ResourceAccessException("此订单已经取消或者正在处理。");
		}
		for (StoreOrderItem item : order.getItems()) {
			inventoryService.updateAmount(dealer, item.getPk().getProduct(), -item.getAmount());
			inventoryService.updateSellVol(dealer, item.getPk().getProduct(), item.getAmount());
		}
		order.setState(StoreOrderState.NOTHANDLE);
		storeOrderRepository.save(order);
		return order;
	}

	@Override
	public void deleteTwoWeekAgoFinishedOrders() {
		long time = System.currentTimeMillis();
		long twoWeek = 2 * 7 * 24 * 60 * 60 * 1000;
		long twoWeekAgo = time - twoWeek;
		Date date = new Date(twoWeekAgo);
		storeOrderRepository.deleteByDateAndState(date, StoreOrderState.CANCELED);
		storeOrderRepository.deleteByDateAndState(date, StoreOrderState.FINISHHANDLE);
	}
}
