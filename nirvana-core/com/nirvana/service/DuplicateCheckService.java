package com.nirvana.service;

/**
 * ����û��������ֻ����Ƿ��ظ���
 * 
 * */
public interface DuplicateCheckService {

	public boolean backEndUserNameExist(String username);

	public boolean backEndUserPhoneExist(String phone);

	public boolean dealerUserNameExist(String username);

	public boolean dealerUserPhoneExist(String phone);

	public boolean storeUserNameExist(String username);

	public boolean storeUserPhoneExist(String phone);

}
