package com.nirvana.service;

import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.usersys.StoreUser;

public interface StoreCurrentAccountService {

	/**
	 * ��ȡ��ǰ��½���ŵ�ID��
	 * 
	 * */
	public long getCurrentLoginStoreId();

	/**
	 * ��ȡ��ǰ��½���ŵ�ID��
	 * 
	 * */
	public Store getCurrentLoginStore();

	/**
	 * ��ȡ��ǰ��½���û���
	 * 
	 * */
	public StoreUser getCurrentLoginUser();

	/**
	 * ��ȡ��ǰ��½���û�����
	 * 
	 * */
	public String getCurrentLoginUserName();
}
