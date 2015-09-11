package com.nirvana.service;

/**
 * 检测用户名或者手机号是否重复。
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
