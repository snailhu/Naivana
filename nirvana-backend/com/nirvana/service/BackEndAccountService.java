package com.nirvana.service;

/**
 * 后台用户的账户服务。
 * 
 * 登陆，注册，审核，启用等等。
 * 
 * */
public interface BackEndAccountService {

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
	 * @param username
	 *            登陆用户名（操作者）
	 * 
	 * */
	public void getCaptcha();

	/**
	 * 修改用户真实姓名。
	 * 
	 * @param username
	 *            登陆用户名（操作者）
	 * @param name
	 *            要修改为的名称
	 * @param captcha
	 *            验证码。
	 * 
	 * */
	public void editUserRealName(String name, String captcha);

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
	 * 通过绑定手机重置密码
	 * 
	 */
	public void resetPassword(String phone, String password);

}
