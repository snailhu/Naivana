package com.nirvana.erp.service.impl;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerStorefrontInfo;
import com.nirvana.erp.domain.PepsiCustomer;
import com.nirvana.erp.domain.PepsiOrderHead;
import com.nirvana.erp.exception.WebServiceException;
import com.nirvana.erp.repository.PepsiCustomerRepository;
import com.nirvana.erp.repository.PepsiMarketRepository;
import com.nirvana.erp.repository.PepsiOrderHeadRepository;
import com.nirvana.erp.repository.PepsiProductRepository;
import com.nirvana.erp.repository.PepsiRegionRepository;
import com.nirvana.erp.service.ErpService;
import com.nirvana.erp.wsdl.WsdlOperator;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.area.BigAreaRepository;
import com.nirvana.repository.backend.area.DirectorAreaRepository;
import com.nirvana.repository.backend.area.ManagerAreaRepository;
import com.nirvana.repository.common.BrandRepository;
import com.nirvana.repository.common.ChannelRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.service.AreaService;
import com.nirvana.service.DealerService;

@Service
public class ErpServiceImpl implements ErpService {

	@Resource
	private AreaService areaService;
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

	/**
	 * @param syncRegion
	 *            是否同步区域。
	 * 
	 * */
	@Override
	@Transactional
	public void syncCustomers(boolean syncRegion) {
		for (int i = 0; i < 50; i++) {
			System.out.println("当前同步到第：" + i + "页");
			Page<PepsiCustomer> pepsiCustomers = pepsiCustomerRepository.findAll(new PageRequest(i, 1000));
			for (PepsiCustomer customer : pepsiCustomers) {
				Dealer dealer = dealerRepository.findByCodeIERP(customer.getCustomerId());
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
							continue;
						}
						dealer.setDirectorArea(directorArea);
					}
					dealer.setCodeInERP(customer.getCustomerId());
					dealer.setVersion(customer.getObjversion());
					dealerRepository.save(dealer);
					dealerService.initDealer(dealer.getId());

				} else {
					if (!dealer.getVersion().equals(customer.getObjversion())) {
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
								continue;
							}
							dealer.setDirectorArea(directorArea);
						}
						dealer.setVersion(customer.getObjversion());
						dealerRepository.save(dealer);
					}
				}
			}
			dealerRepository.flush();
		}
	}

	@Override
	@Transactional
	public void syncLocalCustomers(boolean syncRegion) {
		List<Dealer> dealers = dealerRepository.findAll();
		for (Dealer dealer : dealers) {
			PepsiCustomer customer = pepsiCustomerRepository.findOne(dealer.getCodeInERP());

			if (dealer.getVersion() == null || (!dealer.getVersion().equals(customer.getObjversion()))) {
				DealerStorefrontInfo dealerStorefrontInfo = dealer.getStorefrontInfo();
				fillDealerStorefrontInfo(dealerStorefrontInfo, customer);

				Channel channel = channelRepository.findOne(customer.getMarketCode());
				if (channel != null) {
					dealer.setChannel(channel);
				}
				if (syncRegion) {
					DirectorArea directorArea = directorAreaRepository.findByAreaCode(customer.getTds());
					if (directorArea == null) {
						throw new RecordNotFoundException("主任区未找到。");
					}
					dealer.setDirectorArea(directorArea);
				}
				dealer.setVersion(customer.getObjversion());
				dealerRepository.save(dealer);
			}

		}
		dealerRepository.flush();
	}

	@Override
	public PepsiOrderHead placeOrder(String customerId, Integer addrNo, Map<String, Integer> skus) {
		String orderStr = "";
		String customerStr = "CUSTOMER_NO~" + customerId + "^";
		String addrStr = "SHIP_ADDR_NO~" + addrNo + "^END_ORDER~^";
		String skuStr = "";
		for (String productId : skus.keySet()) {
			skuStr = skuStr + "PART_NO~" + productId + "^BUY_QTY_DUE~" + skus.get(productId) + "^";
		}
		orderStr = customerStr + addrStr + skuStr;
		String result = null;
		try {
			result = WsdlOperator.requestImportOrder(orderStr);
		} catch (RemoteException e) {
			throw new WebServiceException("服务器内部错误，请稍后再试。");
		} catch (ServiceException e) {
			throw new WebServiceException("服务器内部错误，请稍后再试。");
		}
		if (result == null || result.equals("")) {
			throw new WebServiceException("未知错误。");
		}
		if (result.startsWith("ERROR:")) {
			throw new WebServiceException(result.substring(6));
		}
		PepsiOrderHead head = pepsiOrderHeadRepository.findOne(result);
		return head;
	}
}
