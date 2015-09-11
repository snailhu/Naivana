/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年2月27日 上午8:40:47
 */
package com.nirvana.service;

/**
 * 经销商反馈
 * 
 */
public interface DealerComplaintService {

	/**
	 * 反馈建议。
	 * 
	 * */
	public void complaint(String type, String contact, String content);
}
