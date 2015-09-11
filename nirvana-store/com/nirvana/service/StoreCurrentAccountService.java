package com.nirvana.service;

import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.usersys.StoreUser;

public interface StoreCurrentAccountService {

	/**
	 * 获取当前登陆的门店ID。
	 * 
	 * */
	public long getCurrentLoginStoreId();

	/**
	 * 获取当前登陆的门店ID。
	 * 
	 * */
	public Store getCurrentLoginStore();

	/**
	 * 获取当前登陆的用户。
	 * 
	 * */
	public StoreUser getCurrentLoginUser();

	/**
	 * 获取当前登陆的用户名。
	 * 
	 * */
	public String getCurrentLoginUserName();
}
