package com.nirvana.service;

import java.util.List;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.StoreStorefrontInfo;

public interface StoreManageService {

	/**
	 * 获取地址。
	 * 
	 * */
	public StoreStorefrontInfo getAddr();

	/**
	 * 获取经销商姓名。
	 * 
	 * */
	public Dealer getDealerName();

	/**
	 * 获取周围经销商。
	 * 
	 * */
	public List<Dealer> getDealersAround();

}
