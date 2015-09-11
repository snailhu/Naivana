package com.nirvana.controller.web.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.DuplicateCheckService;

@Controller
@RequestMapping(value = "/backend/web/api/checkexist", produces = "application/json;charset=utf-8")
public class DuplicateCheckController {

	@Resource
	private DuplicateCheckService duplicateCheckService;

	@ResponseBody
	@RequestMapping(value = "/backend/username", method = { RequestMethod.GET })
	public String backEndUserNameExist(String username) {
		boolean result = duplicateCheckService.backEndUserNameExist(username);
		return new JsonResponseBean(result).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/backend/phone", method = { RequestMethod.GET })
	public String backEndUserPhoneExist(String phone) {
		boolean result = duplicateCheckService.backEndUserPhoneExist(phone);
		return new JsonResponseBean(result).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/dealer/username", method = { RequestMethod.GET })
	public String dealerUserNameExist(String username) {
		boolean result = duplicateCheckService.dealerUserNameExist(username);
		return new JsonResponseBean(result).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/dealer/phone", method = { RequestMethod.GET })
	public String dealerUserPhoneExist(String phone) {
		boolean result = duplicateCheckService.dealerUserPhoneExist(phone);
		return new JsonResponseBean(result).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/store/username", method = { RequestMethod.GET })
	public String storeUserNameExist(String username) {
		boolean result = duplicateCheckService.storeUserNameExist(username);
		return new JsonResponseBean(result).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/store/phone", method = { RequestMethod.GET })
	public String storeUserPhoneExist(String phone) {
		boolean result = duplicateCheckService.storeUserPhoneExist(phone);
		return new JsonResponseBean(result).toJson();
	}
}
