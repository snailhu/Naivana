package com.nirvana.service;

import com.nirvana.domain.dealer.Dealer;

/**
 * �����̵��˻���ط���
 * 
 * ��½��ע�ᣬ�鿴��Ϣ�ȵȡ�
 * 
 * */
public interface DealerAccountService {

	/**
	 * ��ȡ��������Ϣ
	 * 
	 */
	public Dealer getDealer();

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
	 * 
	 * */
	public void getCaptcha();

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
	 * ��������ID.
	 * 
	 * */
	public void bindChannelId(String channelId, int deviceType);

}
