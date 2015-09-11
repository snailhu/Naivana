package com.nirvana.service;

/**
 * ��̨�û����˻�����
 * 
 * ��½��ע�ᣬ��ˣ����õȵȡ�
 * 
 * */
public interface BackEndAccountService {

	/**
	 * ��֤�ֻ��š�
	 * 
	 * @param number
	 *            Ҫ��֤���ֻ���
	 * 
	 * */
	public void verifyPhoneNumber(String number);

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
	 * �޸��û���ʵ������
	 * 
	 * @param username
	 *            ��½�û����������ߣ�
	 * @param name
	 *            Ҫ�޸�Ϊ������
	 * @param captcha
	 *            ��֤�롣
	 * 
	 * */
	public void editUserRealName(String name, String captcha);

	/**
	 * ����󶨡�
	 * 
	 * */
	public void unbindPhoneNumber(String captcha);

	/**
	 * �޸����롣����Ҫ��֤�롣��
	 * 
	 * @param username
	 *            ��½�û����������ߣ�
	 * @param password
	 *            Ҫ�޸�Ϊ������
	 * @param captcha
	 *            ��֤�롣
	 * 
	 * 
	 * */
	public void editPassword(String password, String captcha);

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
	 * @param phone
	 * @param captcha
	 * @param password
	 * 
	 * */
	public void resetPassword(String phone, String captcha, String password);

	/**
	 * ͨ�����ֻ���������
	 * 
	 */
	public void resetPassword(String phone, String password);

}
