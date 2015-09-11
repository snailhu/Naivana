package com.nirvana.service;

import org.springframework.data.domain.Page;

import com.nirvana.domain.common.Brand;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Product;

public interface ProductService {

	/**
	 * ��ȡ����Ʒ�ơ�
	 * 
	 * */
	public Page<Brand> getBrands(int page, int size);

	/**
	 * ����Ʒ�ƻ�ȡ��Ʒ��
	 * 
	 * */
	public Page<Product> getProductByBrand(String brandName, int page, int size);

	/**
	 * ��ȡ������Ʒ��
	 * 
	 * */
	public Page<Product> getAllProducts(int page, int size);

	/**
	 * ��ȡ����������
	 * 
	 * */
	public Page<Channel> getAllChannels(int page, int size);

	/**
	 * ������Ʒ��Ż�ȡ��Ʒ��
	 * 
	 * */
	public Product getProductByCode(String code);

}
