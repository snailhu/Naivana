package com.nirvana.service;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.usersys.DealerUser;

/**
 * 当前登陆的Service
 * 
 */
public interface DealerCurrentLoginService {

	/**
	 * 获取当前登陆的经销商ID。
	 * 
	 * */
	public long getCurrentLoginDealerId();

	/**
	 * 获取当前登陆的经销商。
	 * 
	 * */
	public Dealer getCurrentLoginDealer();

	/**
	 * 获取当前登陆的经销商用户。
	 * 
	 * */
	public DealerUser getCurrentLoginDealerUser();

	/**
	 * 获取当前登陆的用户名。
	 * 
	 * */
	public String getCurrentLoginUsername();

}
