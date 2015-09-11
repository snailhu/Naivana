package com.nirvana.controller.web.api;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.BackEndAccountService;
import com.nirvana.utils.ValidataUtil;

@Controller
@RequestMapping(value = "/backend/web/api/account", produces = "application/json;charset=utf-8")
public class BackEndUserAccountController {

	@Resource
	private BackEndAccountService backEndAccountService;

	/**
	 * 验证手机号。
	 * 
	 * @param username
	 *            登陆用户名（操作者）
	 * @param number
	 *            要验证的手机号
	 * 
	 * 
	 * */
	@RequestMapping(value = "/verifyNumber", method = { RequestMethod.POST })
	@ResponseBody
	public String verifyPhoneNumber(@RequestParam("number") String number) {
		if (!ValidataUtil.PHONEVALIDATOR.isValid(number)) {
			throw new IllegalArgumentException("手机号码格式错误。");
		}
		backEndAccountService.verifyPhoneNumber(number);
		return JsonResponseBean.getSuccessResponse().toJson();

	}

	/**
	 * 绑定手机号。（后台生成一个验证码，短信发送到此号码。）
	 * 
	 * @param username
	 *            登陆用户名（操作者）
	 * @param captcha
	 *            验证码。
	 * 
	 * 
	 * */
	@RequestMapping(value = "/bindNumber", method = { RequestMethod.PATCH })
	@ResponseBody
	public String bindPhoneNumber(@RequestParam("captcha") String captcha) {
		if (StringUtils.isAnyBlank(captcha)) {
			throw new IllegalArgumentException("传入参数不能为空");
		}
		backEndAccountService.bindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取验证码。（后台生成一个验证码，短信发送给用户，并且保存此验证码在缓存中，缓存失效时间为1分钟。）
	 * 
	 * @param username
	 *            登陆用户名（操作者）
	 * 
	 * */
	@RequestMapping(value = "/getCaptcha", method = { RequestMethod.GET })
	@ResponseBody
	public String getCaptcha() {
		backEndAccountService.getCaptcha();
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 解绑手机
	 * 
	 * @param captcha
	 */
	@ResponseBody
	@RequestMapping(value = "/unbindNumber", method = RequestMethod.PUT)
	public String unbindPhone(@RequestParam("captcha") String captcha) {
		backEndAccountService.unbindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

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
	@RequestMapping(value = "/editRealName", method = { RequestMethod.PUT })
	@ResponseBody
	public String editUserRealName(@RequestParam("name") String name, @RequestParam("captcha") String captcha) {
		if (StringUtils.isAnyBlank(name, captcha)) {
			throw new IllegalArgumentException("传入参数不能为空");
		}
		backEndAccountService.editUserRealName(name, captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

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
	 * */
	@RequestMapping(value = "/editPassword", method = { RequestMethod.PUT })
	@ResponseBody
	public String editPassword(@RequestParam("password") String password, @RequestParam("captcha") String captcha) {
		if (StringUtils.isAnyBlank(password, captcha)) {
			throw new IllegalArgumentException("传入参数不能为空");
		}
		backEndAccountService.editPassword(password, captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 通过手机号找回密码。发送验证码。
	 * 
	 * */
	@RequestMapping(value = "/findback/getCaptcha", method = { RequestMethod.GET })
	@ResponseBody
	public String getCaptchaOfFindBackAccount(@RequestParam("number") String number) {
		if (StringUtils.isAnyBlank(number)) {
			throw new IllegalArgumentException("传入参数不能为空");
		}
		backEndAccountService.postCaptcha(number);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 通过手机号找回密码。重置密码。
	 * 
	 * */
	@RequestMapping(value = "/findback/resetPassword", method = { RequestMethod.PUT })
	@ResponseBody
	public String resetPasswordOfFindBackAccount(@RequestParam("number") String number, @RequestParam("captcha") String captcha, @RequestParam("password") String password) {
		if (StringUtils.isAnyBlank(number, password, captcha)) {
			throw new IllegalArgumentException("传入参数不能为空");
		}
		backEndAccountService.resetPassword(number, captcha, password);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

}
