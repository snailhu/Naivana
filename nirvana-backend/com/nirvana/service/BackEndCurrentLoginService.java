package com.nirvana.service;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.usersys.BackEndUser;

/**
 * 当前登录用户的服务。
 * 
 * */
public interface BackEndCurrentLoginService {

	/**
	 * 获取当前登陆用户的员工ID。
	 * 
	 * */
	public Long getCurrentLoginEmployeeId();

	/**
	 * 获取当前登录用户的员工。
	 * 
	 * */
	public Employee getCurrentLoginEmployee();

	/**
	 * 获取当前登录用户的用户名
	 * 
	 * */
	public String getCurrentLoginUsername();

	/**
	 * 获取当前登录的用户信息。
	 * 
	 * */
	public BackEndUser getCurrentLoginUser();

}
