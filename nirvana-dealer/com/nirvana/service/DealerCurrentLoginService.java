package com.nirvana.service;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.usersys.DealerUser;

/**
 * ��ǰ��½��Service
 * 
 */
public interface DealerCurrentLoginService {

	/**
	 * ��ȡ��ǰ��½�ľ�����ID��
	 * 
	 * */
	public long getCurrentLoginDealerId();

	/**
	 * ��ȡ��ǰ��½�ľ����̡�
	 * 
	 * */
	public Dealer getCurrentLoginDealer();

	/**
	 * ��ȡ��ǰ��½�ľ������û���
	 * 
	 * */
	public DealerUser getCurrentLoginDealerUser();

	/**
	 * ��ȡ��ǰ��½���û�����
	 * 
	 * */
	public String getCurrentLoginUsername();

}
