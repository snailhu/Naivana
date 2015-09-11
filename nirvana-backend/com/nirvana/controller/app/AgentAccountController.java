package com.nirvana.controller.app;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.BackEndAccountService;
import com.nirvana.utils.Assert;

/**
 * 业务员的账户管理
 * 
 * */
@Controller
@RequestMapping(value = "/backend/app/account", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.BACKEND)
@NeedERole({ ERole.AGENT })
public class AgentAccountController {

	@Resource
	private BackEndAccountService backEndAccountService;

	/**
	 * 验证手机号。
	 * 
	 * @param number
	 *            要验证的手机号
	 * 
	 * */
	@RequestMapping(value = "/verifyNumber", method = { RequestMethod.POST })
	@ResponseBody
	public String verifyPhoneNumber(@RequestParam("number") String number) {
		Assert.isvalidPhoneNumber(number);
		backEndAccountService.verifyPhoneNumber(number);
		return JsonResponseBean.getSuccessResponse().toJson();

	}

	/**
	 * 绑定手机号。（后台生成一个验证码，短信发送到此号码。）
	 * 
	 * @param captcha
	 * 
	 * */
	@RequestMapping(value = "/bindNumber", method = { RequestMethod.PATCH })
	@ResponseBody
	public String bindPhoneNumber(@RequestParam("captcha") String captcha) {
		Assert.hasLength(captcha);
		backEndAccountService.bindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取验证码。（后台生成一个验证码，短信发送给用户，并且保存此验证码在缓存中，缓存失效时间为1分钟。）
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
		Assert.hasLength(captcha);
		backEndAccountService.unbindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 修改用户真实姓名。
	 * 
	 * @param name
	 *            要修改为的名称
	 * @param captcha
	 *            验证码。
	 * 
	 * */
	@RequestMapping(value = "/editRealName", method = { RequestMethod.PUT })
	@ResponseBody
	public String editUserRealName(@RequestParam("name") String name, @RequestParam("captcha") String captcha) {
		Assert.hasLength(name, captcha);
		backEndAccountService.editUserRealName(name, captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 修改密码。（需要验证码。）
	 * 
	 * @param password
	 *            要修改为的密码
	 * @param captcha
	 *            验证码。
	 * 
	 * */
	@RequestMapping(value = "/editPassword", method = { RequestMethod.PUT })
	@ResponseBody
	public String editPassword(@RequestParam("password") String password, @RequestParam("captcha") String captcha) {
		Assert.hasLength(captcha);
		Assert.isvalidPassword(password);
		backEndAccountService.editPassword(password, captcha);
		SecurityContextHolder.clearContext();
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 通过手机号找回密码。发送验证码。
	 * 
	 * @param number
	 * 
	 * */
	@RequestMapping(value = "/findback/getCaptcha", method = { RequestMethod.GET })
	@ResponseBody
	public String getCaptchaOfFindBackAccount(@RequestParam("number") String number) {
		Assert.isvalidPhoneNumber(number);
		backEndAccountService.postCaptcha(number);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 通过手机号找回密码。重置密码。
	 * 
	 * @param number
	 * @param captcha
	 * @param password
	 * 
	 * */
	@RequestMapping(value = "/findback/resetPassword", method = { RequestMethod.PUT })
	@ResponseBody
	public String resetPasswordOfFindBackAccount(@RequestParam("number") String number, @RequestParam("captcha") String captcha, @RequestParam("password") String password) {
		Assert.hasLength(captcha);
		Assert.isvalidPhoneNumber(number);
		Assert.isvalidPassword(password);
		backEndAccountService.resetPassword(number, captcha, password);
		return JsonResponseBean.getSuccessResponse().toJson();
	}
}
