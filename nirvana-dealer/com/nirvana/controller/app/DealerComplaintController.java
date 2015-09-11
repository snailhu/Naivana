package com.nirvana.controller.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerComplaintService;
import com.nirvana.utils.Assert;

/**
 * ������Ͷ�ߡ�
 * 
 * */
@Controller
@RequestMapping(value = "/dealer/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.DEALER)
@NeedDRole({ DRole.USER })
public class DealerComplaintController {
	@Resource
	DealerComplaintService dealerComplaintService;

	/**
	 * Ͷ��
	 * 
	 * @param type
	 *            ����
	 * @param contact
	 *            ��ϵ��ʽ
	 * @param content
	 *            ����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/complaint", method = RequestMethod.POST)
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
