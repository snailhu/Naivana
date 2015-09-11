package com.nirvana.service;

import com.nirvana.domain.dealer.Dealer;

/**
 * 经销商的账户相关服务。
 * 
 * 登陆，注册，查看信息等等。
 * 
 * */
public interface DealerAccountService {

	/**
	 * 获取经销商信息
	 * 
	 */
	public Dealer getDealer();

	/**
	 * 验证手机号。
	 * 
	 * @param number
	 *            要验证的手机号
	 * 
	 * */
	public void verifyPhoneNumber(String number);

	/**
	 * 绑定手机号。（后台生成一个验证码，短信发送到此号码。）
	 * 
	 * @param captcha
	 *            验证码。
	 * 
	 * */
	public void bindPhoneNumber(String captcha);

	/**
	 * 获取验证码。（后台生成一个验证码，短信发送给用户，并且保存此验证码在缓存中，缓存失效时间为1分钟。）
	 * 
	 * 
	 * */
	public void getCaptcha();

	/**
	 * 解除绑定。
	 * 
	 * */
	public void unbindPhoneNumber(String captcha);

	/**
	 * 修改密码。（需要验证码。）
	 * 
	 * @param username
	 *            登陆用户名（操作者）
	 * @param password
	 *            要修改为的密码
	 * @param captcha
	 *            验证码。
	 * 
	 * 
	 * */
	public void editPassword(String password, String captcha);

	/**
	 * 找回密码的时候通过绑定手机号发送验证码。
	 * 
	 * @param phone
	 * 
	 * */
	public void postCaptcha(String phone);

	/**
	 * 通过绑定手机号重置密码。
	 * 
	 * @param phone
	 * @param captcha
	 * @param password
	 * 
	 * */
	public void resetPassword(String phone, String captcha, String password);

	/**
	 * 绑定云推送ID.
	 * 
	 * */
	public void bindChannelId(String channelId, int deviceType);

}
