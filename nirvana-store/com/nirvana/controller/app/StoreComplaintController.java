package com.nirvana.controller.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedSRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerComplaintService;
import com.nirvana.utils.Assert;

/**
 * 门店用户投诉。
 * 
 * */
@Controller
@RequestMapping(value = "/store/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.STORE)
@NeedSRole
public class StoreComplaintController {

	@Resource
	private DealerComplaintService dealerComplaintService;

	/**
	 * 投诉。
	 * 
	 * @param type
	 * @param contact
	 * @param content
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/complaint", method = RequestMethod.POST)
	public String complaint(@RequestParam("type") String type,
			@RequestParam("contact") String contact,
			@RequestParam("content") String content) {
		Assert.notNull(type, contact, content);
		if (contact.contains("@")) {
			Assert.isvalidEmail(contact);
		} else {
			Assert.isvalidPhoneNumber(contact);
		}
		dealerComplaintService.complaint(type, contact, content);
		return JsonResponseBean.getSuccessResponse().toJson();
	}
}
