package com.nirvana.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.goal.AgentWDNrGoal;
import com.nirvana.domain.common.Brand;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Product;
import com.nirvana.domain.common.SyncDate;
import com.nirvana.domain.common.SyncType;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderItem;
import com.nirvana.domain.dealer.DealerOrderItemPK;
import com.nirvana.domain.dealer.DealerOrderItemType;
import com.nirvana.domain.dealer.DealerOrderState;
import com.nirvana.domain.dealer.DealerStorefrontInfo;
import com.nirvana.erp.domain.PepsiCustomer;
import com.nirvana.erp.domain.PepsiCustomerHistory;
import com.nirvana.erp.domain.PepsiMarket;
import com.nirvana.erp.domain.PepsiOrderHead;
import com.nirvana.erp.domain.PepsiOrderHistory;
import com.nirvana.erp.domain.PepsiOrderLine;
import com.nirvana.erp.domain.PepsiProduct;
import com.nirvana.erp.domain.PepsiRegion;
import com.nirvana.erp.repository.PepsiCustomerHistoryRepository;
import com.nirvana.erp.repository.PepsiCustomerRepository;
import com.nirvana.erp.repository.PepsiMarketRepository;
import com.nirvana.erp.repository.PepsiOrderHeadRepository;
import com.nirvana.erp.repository.PepsiOrderHistoryRepository;
import com.nirvana.erp.repository.PepsiProductRepository;
import com.nirvana.erp.repository.PepsiRegionRepository;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.area.BigAreaRepository;
import com.nirvana.repository.backend.area.DirectorAreaRepository;
import com.nirvana.repository.backend.area.ManagerAreaRepository;
import com.nirvana.repository.common.BrandRepository;
import com.nirvana.repository.common.ChannelRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.common.SyncDateRepository;
import com.nirvana.repository.dealer.DealerOrderItemRepository;
import com.nirvana.repository.dealer.DealerOrderRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.DealerStockSerialNumberRepository;
import com.nirvana.service.AreaService;
import com.nirvana.service.DealerOrderService;
import com.nirvana.service.DealerService;
import com.nirvana.service.InventoryService;
import com.nirvana.service.MonthIncomeAndExpenditureService;
import com.nirvana.service.SynchronizeService;
import com.nirvana.utils.Assert;

@Service
@Transactional
public class SynchronizeServiceImpl implements SynchronizeService {

	@Resource
	private AreaService areaService;
	@Resource
	private DealerOrderService dealerOrderService;
	@Resource
	private DealerService dealerService;
	@Resource
	private PepsiOrderHeadRepository pepsiOrderHeadRepository;
	@Resource
	private PepsiProductRepository pepsiProductRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private PepsiCustomerRepository pepsiCustomerRepository;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private PepsiMarketRepository pepsiMarketRepository;
	@Resource
	private ChannelRepository channelRepository;
	@Resource
	private BrandRepository brandRepository;
	@Resource
	private PepsiRegionRepository pepsiRegionRepository;
	@Resource
	private AgentAreaRepository agentAreaRepository;
	@Resource
	private BigAreaRepository bigAreaRepository;
	@Resource
	private DirectorAreaRepository directorAreaRepository;
	@Resource
	private ManagerAreaRepository managerAreaRepository;
	@Resource
	private SyncDateRepository syncDateRepository;
	@Resource
	private PepsiOrderHistoryRepository pepsiOrderHistoryRepository;
	@Resource
	private DealerOrderRepository dealerOrderRepository;
	@Resource
	private DealerOrderItemRepository dealerOrderItemRepository;
	@Resource
	private PepsiCustomerHistoryRepository pepsiCustomerHistoryRepository;
	@Resource
	private DealerStockSerialNumberRepository dealerStockSerialNumberRepository;
	@Resource
	private InventoryService inventoryService;
	@Resource
	private MonthIncomeAndExpenditureService monthIncomeAndExpenditureService;

	private Brand getBrand(String family) {
		if (family.equals("七喜")) {
			Brand brand = brandRepository.findOne("七喜");
			if (brand == null) {
				brand = new Brand();
				brand.setName("七喜");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("美橙") || family.equals("美青") || family.equals("美西") || family.equals("美葡") || family.equals("美蜜柚") || family.equals("西瓜")) {
			Brand brand = brandRepository.findOne("美年达");
			if (brand == null) {
				brand = new Brand();
				brand.setName("美年达");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("百事")) {
			Brand brand = brandRepository.findOne("百事");
			if (brand == null) {
				brand = new Brand();
				brand.setName("百事");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("轻怡")) {
			Brand brand = brandRepository.findOne("轻怡");
			if (brand == null) {
				brand = new Brand();
				brand.setName("轻怡");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("混包装")) {
			Brand brand = brandRepository.findOne("混包装");
			if (brand == null) {
				brand = new Brand();
				brand.setName("混包装");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("佳得乐冰柠") || family.equals("佳得乐冰橙") || family.equals("佳得乐冰橘") || family.equals("佳得乐蓝莓") || family.equals("佳得乐绿茶") || family.equals("佳得乐浆果")
				|| family.equals("佳得乐西柚")) {
			Brand brand = brandRepository.findOne("佳得乐");
			if (brand == null) {
				brand = new Brand();
				brand.setName("佳得乐");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("COMMON")) {
			Brand brand = brandRepository.findOne("COMMON");
			if (brand == null) {
				brand = new Brand();
				brand.setName("COMMON");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("9安士") || family.equals("12安士") || family.equals("16安士") || family.equals("22安士") || family.equals("12安士盖") || family.equals("16安士盖")
				|| family.equals("22安士杯盖")) {
			Brand brand = brandRepository.findOne("纸杯盖");
			if (brand == null) {
				brand = new Brand();
				brand.setName("纸杯盖");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("极MAX")) {
			Brand brand = brandRepository.findOne("极MAX");
			if (brand == null) {
				brand = new Brand();
				brand.setName("极MAX");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("果缤纷热带") || family.equals("果缤纷蜜瓜") || family.equals("果缤纷香橙") || family.equals("果缤纷法风") || family.equals("果缤纷香芒") || family.equals("果缤纷葡醉")
				|| family.equals("果缤纷柠乐") || family.equals("果缤纷奇异果") || family.equals("果缤纷芒桃") || family.equals("果缤纷雪葡") || family.equals("果缤纷梅莓") || family.equals("果缤纷橙味")
				|| family.equals("果缤纷苹果")) {
			Brand brand = brandRepository.findOne("果缤纷");
			if (brand == null) {
				brand = new Brand();
				brand.setName("果缤纷");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("都乐鲜橙") || family.equals("都乐苹果") || family.equals("都乐60%鲜橙") || family.equals("都乐葡萄") || family.equals("都乐钙橙") || family.equals("都乐番茄")
				|| family.equals("都乐菠萝") || family.equals("都乐西柚") || family.equals("都乐60%苹果")) {
			Brand brand = brandRepository.findOne("都乐");
			if (brand == null) {
				brand = new Brand();
				brand.setName("都乐");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("激浪")) {
			Brand brand = brandRepository.findOne("激浪");
			if (brand == null) {
				brand = new Brand();
				brand.setName("激浪");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("极度")) {
			Brand brand = brandRepository.findOne("极度");
			if (brand == null) {
				brand = new Brand();
				brand.setName("极度");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("草清润")) {
			Brand brand = brandRepository.findOne("草清润");
			if (brand == null) {
				brand = new Brand();
				brand.setName("草清润");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("草温润")) {
			Brand brand = brandRepository.findOne("草温润");
			if (brand == null) {
				brand = new Brand();
				brand.setName("草温润");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("柠檬")) {
			Brand brand = brandRepository.findOne("柠檬");
			if (brand == null) {
				brand = new Brand();
				brand.setName("柠檬");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("立顿柠檬") || family.equals("立顿原味") || family.equals("立顿英式奶茶") || family.equals("立顿绿茶") || family.equals("立顿香草奶茶")) {
			Brand brand = brandRepository.findOne("立顿");
			if (brand == null) {
				brand = new Brand();
				brand.setName("立顿");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("冰纯水")) {
			Brand brand = brandRepository.findOne("冰纯水");
			if (brand == null) {
				brand = new Brand();
				brand.setName("冰纯水");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("盐汽水")) {
			Brand brand = brandRepository.findOne("盐汽水");
			if (brand == null) {
				brand = new Brand();
				brand.setName("盐汽水");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("乌梅")) {
			Brand brand = brandRepository.findOne("乌梅");
			if (brand == null) {
				brand = new Brand();
				brand.setName("乌梅");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("维动力菠萝") || family.equals("维动力柠檬") || family.equals("维动力蜜桃")) {
			Brand brand = brandRepository.findOne("维动力");
			if (brand == null) {
				brand = new Brand();
				brand.setName("维动力");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("鲜果粒橙味") || family.equals("鲜果粒苹果") || family.equals("鲜果粒热带")) {
			Brand brand = brandRepository.findOne("鲜果粒");
			if (brand == null) {
				brand = new Brand();
				brand.setName("鲜果粒");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("美桃")) {
			Brand brand = brandRepository.findOne("美桃");
			if (brand == null) {
				brand = new Brand();
				brand.setName("美桃");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else {
			Brand brand = brandRepository.findOne(Brand.BRAND_DEFAULT);
			if (brand == null) {
				brand = new Brand();
				brand.setName(Brand.BRAND_DEFAULT);
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		}
	}

	@Override
	public void globalInitialization() {
		// TODO Auto-generated method stub

	}

	private void fillDealerStorefrontInfo(DealerStorefrontInfo info, PepsiCustomer customer) {
		String bigAddr = customer.getAdd1();
		String[] strings = bigAddr.split("\n");
		String addr1 = strings[0].substring(5).trim();
		String addr2 = strings[1].substring(5).trim();
		String code = strings[2].substring(5).trim();
		String city = strings[3].substring(4).trim();
		String country = strings[4].substring(4).trim();
		String town = strings[5].substring(4).trim();
		info.setContactType(customer.getMethodId());
		info.setContactValue(customer.getValueV());
		info.setCustGrp(customer.getCustGrp());
		info.setInvoiceType(customer.getPepsiInvoiceType());
		info.setManager(customer.getContact());
		info.setName(customer.getName());
		info.setWarehouse(customer.getDeliveryWarehouse());
		info.setRegisterAddr(addr1);
		info.setBusinessAddr(addr2);
		info.setCity(city);
		info.setCountry(country);
		info.setTown(town);
		info.setPostCode(code);
	}

	@Override
	public void syncRegions() {
		List<PepsiRegion> regions = pepsiRegionRepository.findAll();
		for (PepsiRegion region : regions) {

			/* 操作BigArea */
			BigArea bigArea = bigAreaRepository.findByAreaCode(region.getUmCode());
			if (bigArea == null) {
				bigArea = new BigArea();
				bigArea.setAreaCode(region.getUmCode());
				bigArea.setName(region.getUmDesc());
				Position manager = new Position();
				manager.setType(PositionType.UM);
				bigArea.setManager(manager);
				Position fsis = new Position();
				fsis.setType(PositionType.FSIS);
				bigArea.setFsis(fsis);
				bigAreaRepository.save(bigArea);
			}

			/* 操作ManagerArea */
			ManagerArea managerArea = managerAreaRepository.findByAreaCode(region.getTdmCode());
			if (managerArea == null) {
				managerArea = new ManagerArea();
				managerArea.setAreaCode(region.getTdmCode());
				managerArea.setName(region.getTdmDesc());
				managerArea.setBigarea(bigArea);
				Position clerk = new Position();
				clerk.setType(PositionType.CLERK);
				managerArea.setClerk(clerk);
				Position manager = new Position();
				manager.setType(PositionType.TDM);
				managerArea.setManager(manager);
				managerAreaRepository.save(managerArea);
			}

			/* 操作DirectorArea */
			DirectorArea directorArea = directorAreaRepository.findByAreaCode(region.getRegionCode());
			if (directorArea == null) {
				directorArea = new DirectorArea();
				directorArea.setAreaCode(region.getRegionCode());
				directorArea.setName(region.getRegionDesc());
				directorArea.setManagerArea(managerArea);
				Position director = new Position();
				director.setType(PositionType.TDS);
				directorArea.setDirector(director);
				directorAreaRepository.save(directorArea);
			}

			/* 操作AgentArea */
			AgentArea agentArea = agentAreaRepository.findByAreaCode(region.getDistrictCode());
			if (agentArea == null) {
				agentArea = new AgentArea();
				agentArea.setAreaCode(region.getDistrictCode());
				agentArea.setDesc(region.getDistDesc());
				agentArea.setDirectorArea(directorArea);
				Position agent = new Position();
				agent.setType(PositionType.AGENT);
				agentArea.setAgent(agent);
				AgentWDNrGoal agentWDNrGoal = new AgentWDNrGoal();
				agentArea.setWdNrGoal(agentWDNrGoal);
				agentAreaRepository.save(agentArea);
				areaService.initAgentArea(agentArea.getId());
			}

			agentAreaRepository.flush();

		}
	}

	@Override
	public void syncChannels() {
		List<PepsiMarket> pepsiMarkets = pepsiMarketRepository.findAll();
		for (PepsiMarket market : pepsiMarkets) {
			Channel channel = channelRepository.findOne(market.getMarketCode());
			if (channel == null) {
				channel = new Channel();
				channel.setCode(market.getMarketCode());
				channel.setDescription(market.getDescription());
				channel.setGroupName(market.getGroup1());
				channel.setVersion(market.getObjversion());
				channelRepository.save(channel);
			} else {
				if (!channel.getVersion().equals(market.getObjversion())) {
					channel.setDescription(market.getDescription());
					channel.setGroupName(market.getGroup1());
					channel.setVersion(market.getObjversion());
					channelRepository.save(channel);
				}
			}
		}
		channelRepository.flush();
	}

	@Override
	public void syncProducts() {
		List<PepsiProduct> pepsiProducts = pepsiProductRepository.findAll();
		for (PepsiProduct pepsiProduct : pepsiProducts) {
			Product product = productRepository.findOne(pepsiProduct.getPartNo());
			if (product == null) {
				product = new Product();
				product.setCode(pepsiProduct.getPartNo());
				product.setCommodity(pepsiProduct.getPrimeCommodity());
				product.setDescription(pepsiProduct.getDescription());
				product.setBrand(getBrand(pepsiProduct.getProductFamily()));
				product.setStandardConv(pepsiProduct.getPepsiStandardConv().floatValue());
				product.setTrsptConv(pepsiProduct.getPepsiTransportConv().floatValue());
				product.setVersion(pepsiProduct.getObjversion());
				productRepository.save(product);
			} else {
				if (!(pepsiProduct.getObjversion()).equals(product.getVersion())) {
					product.setCode(pepsiProduct.getPartNo());
					product.setCommodity(pepsiProduct.getPrimeCommodity());
					product.setDescription(pepsiProduct.getDescription());
					product.setBrand(getBrand(pepsiProduct.getProductFamily()));
					product.setStandardConv(pepsiProduct.getPepsiStandardConv().floatValue());
					product.setTrsptConv(pepsiProduct.getPepsiTransportConv().floatValue());
					product.setVersion(pepsiProduct.getObjversion());
					productRepository.save(product);
				}
			}
		}
		productRepository.flush();
	}

	@Override
	public void syncPoints() {
		// TODO Auto-generated method stub

	}

	@Override
	public void syncCustomer(String customerId, boolean syncRegion) {
		Dealer dealer = dealerRepository.findByCodeIERP(customerId);
		PepsiCustomer customer = pepsiCustomerRepository.findOne(customerId);
		if (dealer == null) {
			dealer = new Dealer();
			// 设置storefrontInfo
			DealerStorefrontInfo dealerStorefrontInfo = new DealerStorefrontInfo();
			fillDealerStorefrontInfo(dealerStorefrontInfo, customer);
			dealer.setStorefrontInfo(dealerStorefrontInfo);
			Channel channel = channelRepository.findOne(customer.getMarketCode());
			if (channel != null) {
				dealer.setChannel(channel);
			}
			if (syncRegion) {
				DirectorArea directorArea = directorAreaRepository.findByAreaCode(customer.getTds());
				if (directorArea == null) {
					System.out.println("同步ID为：" + customer.getCustomerId() + "的客户时出错。将跳过此客户。");
					System.out.println("错误原因：编号为" + customer.getTds() + "的主任区未找到。");

				}
				dealer.setDirectorArea(directorArea);
			}
			dealer.setCodeInERP(customer.getCustomerId());
			dealer.setVersion(customer.getObjversion());
			dealerRepository.save(dealer);
			dealerService.initDealer(dealer.getId());

		} else {
			if (dealer.getDirectorArea().getAreaCode() == customer.getTds()) {
				DealerStorefrontInfo dealerStorefrontInfo = dealer.getStorefrontInfo();
				fillDealerStorefrontInfo(dealerStorefrontInfo, customer);

				Channel channel = channelRepository.findOne(customer.getMarketCode());
				if (channel != null) {
					dealer.setChannel(channel);
				}
				if (syncRegion) {
					DirectorArea directorArea = directorAreaRepository.findByAreaCode(customer.getTds());
					if (directorArea == null) {
						System.out.println("同步ID为：" + customer.getCustomerId() + "的客户时出错。将跳过此客户。");
						System.out.println("错误原因：编号为" + customer.getTds() + "的主任区未找到。");
					}
					dealer.setDirectorArea(directorArea);
				}
				dealer.setVersion(customer.getObjversion());
				dealerRepository.save(dealer);
			}
		}

	}

	public void syncCustomers(boolean syncRegion) {
		SyncDate syncDate = syncDateRepository.findByTypePessimisticLocked(SyncType.SYNC_CUSTOMER);
		Timestamp now = pepsiCustomerHistoryRepository.findMaxTime();
		Timestamp last = syncDate.getTime();
		List<PepsiCustomerHistory> histories = pepsiCustomerHistoryRepository.findByDateToDate(last, now);
		for (PepsiCustomerHistory history : histories) {
			syncCustomer(history.getId().getCustomerNo(), syncRegion);
		}
		syncDate.setTime(now);
		syncDateRepository.save(syncDate);
	}

	/**
	 * @param syncNew
	 *            是否对新订单进行同步。
	 * 
	 * */
	private void syncDealerOrder(String orderNo, boolean syncNew) {
		PepsiOrderHead pepsiOrderHead = pepsiOrderHeadRepository.findOne(orderNo);
		Assert.notNull(pepsiOrderHead);
		Dealer dealer = dealerRepository.findByCodeIERP(pepsiOrderHead.getCustomerNo());
		Assert.notNull(dealer);
		DealerOrder dealerOrder = dealerOrderRepository.findByCodeInERP(orderNo);
		if (dealerOrder == null) {
			if (syncNew) {
				long orderId = dealerOrderService.operateNewOrderNo(dealer);
				dealerOrder = new DealerOrder();
				dealerOrder.setOrderNo(orderId);
				dealerOrder.setCodeInERP(orderNo);
				dealerOrder.setIsAgentHelped(false);
				dealerOrder.setIsInLine(false);
			} else {
				return;
			}
		} else {
			// 删除订单项
			dealerOrderItemRepository.deleteByDealerOrderId(dealerOrder.getId());
		}
		dealerOrder.setDealer(dealer);
		dealerOrder.setEnterDate(pepsiOrderHead.getDateEntered());
		dealerOrder.setWantDate(pepsiOrderHead.getWantedDeliveryDate());

		// 是否有完成动作
		DealerOrderState newState = DealerOrderState.fromERP(pepsiOrderHead.getState());
		boolean toFinish = false;
		if (dealerOrder.getState() != DealerOrderState.CLOSED && newState == DealerOrderState.CLOSED) {
			dealerOrder.setFinishDate(new Date());
			toFinish = true;
		}
		dealerOrder.setState(newState);

		// 处理订单项
		Set<DealerOrderItem> items = new HashSet<DealerOrderItem>();
		BigDecimal totalPrice = new BigDecimal(0);
		for (PepsiOrderLine line : pepsiOrderHead.getLines()) {
			Product product = productRepository.findOne(line.getPartNo());

			DealerOrderItem item = new DealerOrderItem();
			DealerOrderItemPK pk = new DealerOrderItemPK();
			pk.setLineNo(Integer.parseInt(line.getId().getLineNo()));
			pk.setOrder(dealerOrder);
			item.setPk(pk);

			// 操作商品数量
			int amount = line.getQty().intValue();
			item.setAmount(amount);
			// 如果完成，操作库存
			if (toFinish) {
				inventoryService.updateAmount(dealer, product, amount);
			}

			if (line.getDiscount() != null) {
				item.setDiscount(line.getDiscount().floatValue());
			}

			if (line.getPointsAmount() != null) {
				item.setPoints(line.getPointsAmount().intValue());
			}

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

		dealerOrder.setTotalPrice(totalPrice);
		if (toFinish) {
			monthIncomeAndExpenditureService.updateThisMonthExpenditure(dealer, totalPrice.doubleValue());
		}

		dealerOrderRepository.save(dealerOrder);

	}

	@Override
	public void syncDealerOrders(boolean syncNew) {
		SyncDate syncDate = syncDateRepository.findByTypePessimisticLocked(SyncType.SYNC_DEALERORDER);
		Timestamp now = pepsiOrderHistoryRepository.findMaxTime();
		Timestamp last = syncDate.getTime();
		List<PepsiOrderHistory> histories = pepsiOrderHistoryRepository.findByDateToDate(last, now);
		for (PepsiOrderHistory history : histories) {
			syncDealerOrder(history.getId().getOrderNo(), syncNew);
		}
		syncDate.setTime(now);
		syncDateRepository.save(syncDate);
	}

	@PostConstruct
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void init() {
		SyncType[] types = SyncType.values();
		for (SyncType type : types) {
			SyncDate date = syncDateRepository.findByType(type);
			if (date == null) {
				date = new SyncDate();
				date.setType(type);
				syncDateRepository.save(date);
			}
		}
	}
}
