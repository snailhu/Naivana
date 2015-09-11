package com.nirvana.service;

import org.springframework.data.domain.Page;

import com.nirvana.domain.common.Brand;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Product;

public interface ProductService {

	/**
	 * 获取所有品牌。
	 * 
	 * */
	public Page<Brand> getBrands(int page, int size);

	/**
	 * 根据品牌获取商品。
	 * 
	 * */
	public Page<Product> getProductByBrand(String brandName, int page, int size);

	/**
	 * 获取所有商品。
	 * 
	 * */
	public Page<Product> getAllProducts(int page, int size);

	/**
	 * 获取所有渠道。
	 * 
	 * */
	public Page<Channel> getAllChannels(int page, int size);

	/**
	 * 根据商品编号获取商品。
	 * 
	 * */
	public Product getProductByCode(String code);

}
