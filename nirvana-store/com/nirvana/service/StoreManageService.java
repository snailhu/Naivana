package com.nirvana.service;

import java.util.List;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.StoreStorefrontInfo;

public interface StoreManageService {

	/**
	 * ��ȡ��ַ��
	 * 
	 * */
	public StoreStorefrontInfo getAddr();

	/**
	 * ��ȡ������������
	 * 
	 * */
	public Dealer getDealerName();

	/**
	 * ��ȡ��Χ�����̡�
	 * 
	 * */
	public List<Dealer> getDealersAround();

}
