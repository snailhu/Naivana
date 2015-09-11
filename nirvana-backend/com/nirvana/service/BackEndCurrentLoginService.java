package com.nirvana.service;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.usersys.BackEndUser;

/**
 * ��ǰ��¼�û��ķ���
 * 
 * */
public interface BackEndCurrentLoginService {

	/**
	 * ��ȡ��ǰ��½�û���Ա��ID��
	 * 
	 * */
	public Long getCurrentLoginEmployeeId();

	/**
	 * ��ȡ��ǰ��¼�û���Ա����
	 * 
	 * */
	public Employee getCurrentLoginEmployee();

	/**
	 * ��ȡ��ǰ��¼�û����û���
	 * 
	 * */
	public String getCurrentLoginUsername();

	/**
	 * ��ȡ��ǰ��¼���û���Ϣ��
	 * 
	 * */
	public BackEndUser getCurrentLoginUser();

}
