package com.nirvana.security.service;

import java.util.Set;

import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.domain.backend.usersys.ERole;

public interface BackEndSecurityService {

	/**
	 * �����û���ȡ���û�ӵ�е����еĽ�ɫ��
	 * 
	 * @param user
	 *            �û�ʵ��
	 * 
	 * @return �û�ӵ�е����н�ɫ��Set���ϡ�
	 * 
	 * */
	public Set<ERole> getUserRoles(BackEndUser user);

	/**
	 * �����û�����ȡ���û�ӵ�е����еĽ�ɫ��
	 * 
	 * @param userName
	 *            �û���
	 * 
	 * @return �û�ӵ�е����н�ɫ��Set���ϡ�
	 * 
	 * */
	public Set<ERole> getUserRoles(String userName);

	/**
	 * �����û���½���û��������û���
	 * 
	 * @param userName
	 *            �û���
	 * 
	 * @return ���ز��ҵ����û���
	 * 
	 * 
	 * */
	public BackEndUser findUserByUserLogin(String userName);

}
