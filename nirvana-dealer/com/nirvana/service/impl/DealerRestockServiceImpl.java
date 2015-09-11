package com.nirvana.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderState;
import com.nirvana.mongo.document.DealerOrderDocument;
import com.nirvana.mongo.repository.DealerOrderDocumentRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.DealerOrderRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.service.DealerOrderService;
import com.nirvana.service.DealerRestockService;
import com.nirvana.service.ProductService;

@Transactional
@Service
public class DealerRestockServiceImpl implements DealerRestockService {

	@Resource
	private DealerCurrentLoginService dealerCurrentAccountService;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private DealerOrderRepository dealerOrderRepository;
	@Resource
	private DealerOrderService dealerOrderService;
	@Resource
	private ProductService productService;
	@Resource
	private DealerOrderDocumentRepository dealerOrderDocumentRepository;

	private Pageable getPageable(int page, int size) {
		if (page <= 0) {
			page = 1;
		}
		if (size <= 0) {
			size = 20;
		}
		return new PageRequest(page - 1, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Product> getProduct(int page, int size) {
		return productService.getAllProducts(page, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Product> getProduct(String brand, int page, int size) {
		return productService.getProductByBrand(brand, page, size);
	}

	@Override
	public DealerOrder placeOrder(Map<String, Integer> skuData, MultipartFile sign) {
		Assert.notEmpty(skuData, "商品参数不能为空。");
		Assert.notNull(sign, "签名图片不能为空。");
		Dealer dealer = dealerCurrentAccountService.getCurrentLoginDealer();
		DealerOrder order = dealerOrderService.placeOrder(dealer, skuData, false, sign);
		dealerOrderRepository.save(order);
		return order;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<DealerOrder> getNotFinishedOrders(DealerOrderState state, int page, int size) {
		long dealerId = dealerCurrentAccountService.getCurrentLoginDealerId();
		Page<DealerOrder> orders;
		if (state == null) {
			orders = dealerOrderRepository.findByDealerIdOrderByIdDesc(dealerId, getPageable(page, size));
			return orders;
		}
		if (state == DealerOrderState.CANCELED || state == DealerOrderState.CLOSED) {
			throw new IllegalArgumentException("此Service不接受CANCELED/CLOSED的state参数。");
		}
		orders = dealerOrderRepository.findByDealerIdAndStateOrderByIdDesc(dealerId, state, getPageable(page, size));
		return orders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<DealerOrderDocument> getFinishedOrders(DealerOrderState state, int page, int size) {
		long dealerId = dealerCurrentAccountService.getCurrentLoginDealerId();
		Page<DealerOrderDocument> documents = null;
		if (state == null) {
			documents = dealerOrderDocumentRepository.findByDealerId(dealerId, getPageable(page, size));
			return documents;
		}
		if (state == DealerOrderState.CANCELED || state == DealerOrderState.CLOSED) {
			documents = dealerOrderDocumentRepository.findByDealerIdAndState(dealerId, state, getPageable(page, size));
			return documents;
		} else {
			throw new IllegalArgumentException("此Service不接受除了CANCELED/CLOSED以外的state参数。");
		}
	}
}
