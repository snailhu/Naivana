package com.nirvana.security.service;

import java.util.Set;

import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.domain.backend.usersys.ERole;

public interface BackEndSecurityService {

	/**
	 * 根据用户获取此用户拥有的所有的角色。
	 * 
	 * @param user
	 *            用户实体
	 * 
	 * @return 用户拥有的所有角色的Set集合。
	 * 
	 * */
	public Set<ERole> getUserRoles(BackEndUser user);

	/**
	 * 根据用户名获取此用户拥有的所有的角色。
	 * 
	 * @param userName
	 *            用户名
	 * 
	 * @return 用户拥有的所有角色的Set集合。
	 * 
	 * */
	public Set<ERole> getUserRoles(String userName);

	/**
	 * 根据用户登陆的用户名查找用户。
	 * 
	 * @param userName
	 *            用户名
	 * 
	 * @return 返回查找到的用户。
	 * 
	 * 
	 * */
	public BackEndUser findUserByUserLogin(String userName);

}
