package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.common.Brand;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Product;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.repository.common.BrandRepository;
import com.nirvana.repository.common.ChannelRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.service.ProductService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ProductServiceImpl implements ProductService {

	@Resource
	private BrandRepository brandRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private ChannelRepository channelRepository;

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
	public Page<Brand> getBrands(int page, int size) {
		return brandRepository.findAll(getPageable(page, size));
	}

	@Override
	public Page<Product> getProductByBrand(String brandName, int page, int size) {
		return productRepository.findByBrandName(brandName, getPageable(page, size));
	}

	@Override
	public Page<Product> getAllProducts(int page, int size) {
		return productRepository.findAll(getPageable(page, size));
	}

	@Override
	public Page<Channel> getAllChannels(int page, int size) {
		return channelRepository.findAll(getPageable(page, size));
	}

	@Override
	public Product getProductByCode(String code) {
		Product product = productRepository.findOne(code);
		if (product == null) {
			throw new RecordNotFoundException("此商品未找到。");
		}
		return product;
	}

}
