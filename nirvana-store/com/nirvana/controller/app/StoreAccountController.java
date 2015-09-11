package com.nirvana.controller.app;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.StoreAccountInfoDTO;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedSRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.StoreAccountService;

import static com.nirvana.utils.Assert.*;

/**
 * �ŵ��˻���
 * 
 * */
@Controller
@RequestMapping(value = "/store/app/account", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.STORE)
@NeedSRole
public class StoreAccountController {

	@Resource
	StoreAccountService storeAccountService;

	/**
	 * ��ȡ�˻���Ϣ��
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public String getAccountInfo() {
		StoreUser user = storeAccountService.getInfo();
		Converter<StoreUser, StoreAccountInfoDTO> converter = DTOContext
				.getConverter(StoreAccountInfoDTO.class);
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(user));
		return bean.toJson();
	}

	/**
	 * ע��
	 * 
	 * @param username
	 * @param password
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String regester(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		isvalidUsername(username);
		isvalidPassword(password);
		storeAccountService.register(username, password);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��֤�ֻ��š�
	 * 
	 * @param number
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/verifyNumber", method = RequestMethod.GET)
	public String verifyPhoneNumber(@RequestParam("number") String number) {
		isvalidPhoneNumber(number);
		storeAccountService.varifyPhoneNumber(number);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���ֻ��š�����̨����һ����֤�룬���ŷ��͵��˺��롣��
	 * 
	 * @param captcha
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bindNumber", method = { RequestMethod.PUT })
	public String bindPhoneNumber(@RequestParam("captcha") String captcha) {
		notNull(captcha);
		storeAccountService.bindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��ȡ��֤�롣����̨����һ����֤�룬���ŷ��͸��û������ұ������֤���ڻ����У�����ʧЧʱ��Ϊ1���ӡ���
	 * 
	 * */
	@RequestMapping(value = "/getCaptcha", method = { RequestMethod.GET })
	@ResponseBody
	public String getCaptcha() {
		storeAccountService.getCaptcha();
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ����ֻ�
	 * 
	 * @param captcha
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/unbindNumber", method = RequestMethod.PUT)
	public String unbindPhone(@RequestParam("captcha") String captcha) {
		notNull(captcha);
		storeAccountService.unbindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��������ʱ��ȡ��֤��
	 * 
	 * @param number
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/findback/getCaptcha", method = RequestMethod.GET)
	public String getCaptcha4Forget(@RequestParam("number") String number) {
		isvalidPhoneNumber(number);
		storeAccountService.postCaptcha(number);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��������
	 * 
	 * @param number
	 * @param captcha
	 * @param password
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/findback/resetPassword", method = RequestMethod.PUT)
	public String resetPassword(@RequestParam("number") String number,
			@RequestParam("captcha") String captcha,
			@RequestParam("password") String password) {
		notNull(captcha);
		isvalidPassword(password);
		isvalidPhoneNumber(number);
		storeAccountService.resetPassword(password, captcha, number);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �޸�����
	 * 
	 * @param password
	 * @param newpwd
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/editPassword", method = RequestMethod.PUT)
	public String modifyPassword(@RequestParam("password") String password,
			@RequestParam("newpwd") String newpwd) {
//		isvalidPassword(password);
		isvalidPassword(newpwd);
		storeAccountService.editPassword(password, newpwd);
		SecurityContextHolder.clearContext();
		return JsonResponseBean.getSuccessResponse().toJson();
	}

}
