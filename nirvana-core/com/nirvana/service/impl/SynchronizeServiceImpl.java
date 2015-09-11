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
		if (family.equals("��ϲ")) {
			Brand brand = brandRepository.findOne("��ϲ");
			if (brand == null) {
				brand = new Brand();
				brand.setName("��ϲ");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����") || family.equals("����") || family.equals("����") || family.equals("����") || family.equals("������") || family.equals("����")) {
			Brand brand = brandRepository.findOne("�����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("�����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("���װ")) {
			Brand brand = brandRepository.findOne("���װ");
			if (brand == null) {
				brand = new Brand();
				brand.setName("���װ");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("�ѵ��ֱ���") || family.equals("�ѵ��ֱ���") || family.equals("�ѵ��ֱ���") || family.equals("�ѵ�����ݮ") || family.equals("�ѵ����̲�") || family.equals("�ѵ��ֽ���")
				|| family.equals("�ѵ�������")) {
			Brand brand = brandRepository.findOne("�ѵ���");
			if (brand == null) {
				brand = new Brand();
				brand.setName("�ѵ���");
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
		} else if (family.equals("9��ʿ") || family.equals("12��ʿ") || family.equals("16��ʿ") || family.equals("22��ʿ") || family.equals("12��ʿ��") || family.equals("16��ʿ��")
				|| family.equals("22��ʿ����")) {
			Brand brand = brandRepository.findOne("ֽ����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("ֽ����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("��MAX")) {
			Brand brand = brandRepository.findOne("��MAX");
			if (brand == null) {
				brand = new Brand();
				brand.setName("��MAX");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("���ͷ��ȴ�") || family.equals("���ͷ��۹�") || family.equals("���ͷ����") || family.equals("���ͷ׷���") || family.equals("���ͷ���â") || family.equals("���ͷ�����")
				|| family.equals("���ͷ�����") || family.equals("���ͷ������") || family.equals("���ͷ�â��") || family.equals("���ͷ�ѩ��") || family.equals("���ͷ�÷ݮ") || family.equals("���ͷ׳�ζ")
				|| family.equals("���ͷ�ƻ��")) {
			Brand brand = brandRepository.findOne("���ͷ�");
			if (brand == null) {
				brand = new Brand();
				brand.setName("���ͷ�");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("�����ʳ�") || family.equals("����ƻ��") || family.equals("����60%�ʳ�") || family.equals("��������") || family.equals("���ָƳ�") || family.equals("���ַ���")
				|| family.equals("���ֲ���") || family.equals("��������") || family.equals("����60%ƻ��")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("������")) {
			Brand brand = brandRepository.findOne("������");
			if (brand == null) {
				brand = new Brand();
				brand.setName("������");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("������")) {
			Brand brand = brandRepository.findOne("������");
			if (brand == null) {
				brand = new Brand();
				brand.setName("������");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("��������") || family.equals("����ԭζ") || family.equals("����Ӣʽ�̲�") || family.equals("�����̲�") || family.equals("��������̲�")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����ˮ")) {
			Brand brand = brandRepository.findOne("����ˮ");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����ˮ");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����ˮ")) {
			Brand brand = brandRepository.findOne("����ˮ");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����ˮ");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("��÷")) {
			Brand brand = brandRepository.findOne("��÷");
			if (brand == null) {
				brand = new Brand();
				brand.setName("��÷");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("ά��������") || family.equals("ά��������") || family.equals("ά��������")) {
			Brand brand = brandRepository.findOne("ά����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("ά����");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("�ʹ�����ζ") || family.equals("�ʹ���ƻ��") || family.equals("�ʹ����ȴ�")) {
			Brand brand = brandRepository.findOne("�ʹ���");
			if (brand == null) {
				brand = new Brand();
				brand.setName("�ʹ���");
				brandRepository.save(brand);
				return brand;
			}
			return brand;
		} else if (family.equals("����")) {
			Brand brand = brandRepository.findOne("����");
			if (brand == null) {
				brand = new Brand();
				brand.setName("����");
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

			/* ����BigArea */
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

			/* ����ManagerArea */
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

			/* ����DirectorArea */
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

			/* ����AgentArea */
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
			// ����storefrontInfo
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
					System.out.println("ͬ��IDΪ��" + customer.getCustomerId() + "�Ŀͻ�ʱ�����������˿ͻ���");
					System.out.println("����ԭ�򣺱��Ϊ" + customer.getTds() + "��������δ�ҵ���");

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
						System.out.println("ͬ��IDΪ��" + customer.getCustomerId() + "�Ŀͻ�ʱ�����������˿ͻ���");
						System.out.println("����ԭ�򣺱��Ϊ" + customer.getTds() + "��������δ�ҵ���");
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
	 *            �Ƿ���¶�������ͬ����
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
			// ɾ��������
			dealerOrderItemRepository.deleteByDealerOrderId(dealerOrder.getId());
		}
		dealerOrder.setDealer(dealer);
		dealerOrder.setEnterDate(pepsiOrderHead.getDateEntered());
		dealerOrder.setWantDate(pepsiOrderHead.getWantedDeliveryDate());

		// �Ƿ�����ɶ���
		DealerOrderState newState = DealerOrderState.fromERP(pepsiOrderHead.getState());
		boolean toFinish = false;
		if (dealerOrder.getState() != DealerOrderState.CLOSED && newState == DealerOrderState.CLOSED) {
			dealerOrder.setFinishDate(new Date());
			toFinish = true;
		}
		dealerOrder.setState(newState);

		// ��������
		Set<DealerOrderItem> items = new HashSet<DealerOrderItem>();
		BigDecimal totalPrice = new BigDecimal(0);
		for (PepsiOrderLine line : pepsiOrderHead.getLines()) {
			Product product = productRepository.findOne(line.getPartNo());

			DealerOrderItem item = new DealerOrderItem();
			DealerOrderItemPK pk = new DealerOrderItemPK();
			pk.setLineNo(Integer.parseInt(line.getId().getLineNo()));
			pk.setOrder(dealerOrder);
			item.setPk(pk);

			// ������Ʒ����
			int amount = line.getQty().intValue();
			item.setAmount(amount);
			// �����ɣ��������
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
				throw new RecordNotFoundException("����Ʒδ�ҵ���");
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
