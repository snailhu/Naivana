package com.nirvana.service;

import com.nirvana.domain.store.usersys.StoreUser;

/**
 * һ���ŵ���˻���ط���
 * 
 * */
public interface StoreAccountService {

	/**
	 * ��ȡ�ҵ��˻���Ϣ��
	 * 
	 * */
	public StoreUser getInfo();

	/**
	 * ע�ᡣ
	 * 
	 * */
	public void register(String username, String password);

	/**
	 * ��֤�ֻ��š�
	 * 
	 * @param number
	 *            Ҫ��֤���ֻ���
	 * 
	 * */
	public void varifyPhoneNumber(String phoneNum);

	/**
	 * ���ֻ��š�����̨����һ����֤�룬���ŷ��͵��˺��롣��
	 * 
	 * @param captcha
	 *            ��֤�롣
	 * 
	 * */
	public void bindPhoneNumber(String captcha);

	/**
	 * ��ȡ��֤�롣����̨����һ����֤�룬���ŷ��͸��û������ұ������֤���ڻ����У�����ʧЧʱ��Ϊ1���ӡ���
	 * 
	 * @param username
	 *            ��½�û����������ߣ�
	 * 
	 * */
	public void getCaptcha();

	/**
	 * ����󶨡�
	 * 
	 * */
	public void unbindPhoneNumber(String captcha);

	/**
	 * �һ������ʱ��ͨ�����ֻ��ŷ�����֤�롣
	 * 
	 * @param phone
	 * 
	 * */
	public void postCaptcha(String phone);

	/**
	 * ͨ�����ֻ����������롣
	 * 
	 * */
	public void resetPassword(String phone, String captcha, String password);

	/**
	 * �޸����롣
	 * 
	 * */
	public void editPassword(String password, String newPassword);

}
