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
	 * ��֤�ֻ��š�
	 * 
	 * @param username
	 *            ��½�û����������ߣ�
	 * @param number
	 *            Ҫ��֤���ֻ���
	 * 
	 * 
	 * */
	@RequestMapping(value = "/verifyNumber", method = { RequestMethod.POST })
	@ResponseBody
	public String verifyPhoneNumber(@RequestParam("number") String number) {
		if (!ValidataUtil.PHONEVALIDATOR.isValid(number)) {
			throw new IllegalArgumentException("�ֻ������ʽ����");
		}
		backEndAccountService.verifyPhoneNumber(number);
		return JsonResponseBean.getSuccessResponse().toJson();

	}

	/**
	 * ���ֻ��š�����̨����һ����֤�룬���ŷ��͵��˺��롣��
	 * 
	 * @param username
	 *            ��½�û����������ߣ�
	 * @param captcha
	 *            ��֤�롣
	 * 
	 * 
	 * */
	@RequestMapping(value = "/bindNumber", method = { RequestMethod.PATCH })
	@ResponseBody
	public String bindPhoneNumber(@RequestParam("captcha") String captcha) {
		if (StringUtils.isAnyBlank(captcha)) {
			throw new IllegalArgumentException("�����������Ϊ��");
		}
		backEndAccountService.bindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��ȡ��֤�롣����̨����һ����֤�룬���ŷ��͸��û������ұ������֤���ڻ����У�����ʧЧʱ��Ϊ1���ӡ���
	 * 
	 * @param username
	 *            ��½�û����������ߣ�
	 * 
	 * */
	@RequestMapping(value = "/getCaptcha", method = { RequestMethod.GET })
	@ResponseBody
	public String getCaptcha() {
		backEndAccountService.getCaptcha();
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ����ֻ�
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
	@RequestMapping(value = "/editRealName", method = { RequestMethod.PUT })
	@ResponseBody
	public String editUserRealName(@RequestParam("name") String name, @RequestParam("captcha") String captcha) {
		if (StringUtils.isAnyBlank(name, captcha)) {
			throw new IllegalArgumentException("�����������Ϊ��");
		}
		backEndAccountService.editUserRealName(name, captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

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
	 * */
	@RequestMapping(value = "/editPassword", method = { RequestMethod.PUT })
	@ResponseBody
	public String editPassword(@RequestParam("password") String password, @RequestParam("captcha") String captcha) {
		if (StringUtils.isAnyBlank(password, captcha)) {
			throw new IllegalArgumentException("�����������Ϊ��");
		}
		backEndAccountService.editPassword(password, captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ͨ���ֻ����һ����롣������֤�롣
	 * 
	 * */
	@RequestMapping(value = "/findback/getCaptcha", method = { RequestMethod.GET })
	@ResponseBody
	public String getCaptchaOfFindBackAccount(@RequestParam("number") String number) {
		if (StringUtils.isAnyBlank(number)) {
			throw new IllegalArgumentException("�����������Ϊ��");
		}
		backEndAccountService.postCaptcha(number);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ͨ���ֻ����һ����롣�������롣
	 * 
	 * */
	@RequestMapping(value = "/findback/resetPassword", method = { RequestMethod.PUT })
	@ResponseBody
	public String resetPasswordOfFindBackAccount(@RequestParam("number") String number, @RequestParam("captcha") String captcha, @RequestParam("password") String password) {
		if (StringUtils.isAnyBlank(number, password, captcha)) {
			throw new IllegalArgumentException("�����������Ϊ��");
		}
		backEndAccountService.resetPassword(number, captcha, password);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

}
