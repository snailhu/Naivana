package com.nirvana.controller.app;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.DealerDTO;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerAccountService;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.utils.Assert;

/**
 * �������˻���
 * 
 * */
@Controller
@RequestMapping(value = "/dealer/app/account", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.DEALER)
@NeedDRole({ DRole.USER })
public class DealerAccountController {
	@Resource
	DealerAccountService dealerAccountService;
	@Resource
	DealerCurrentLoginService dealerCurrentLoginService;

	/**
	 * ��ȡ�˻���Ϣ��
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getDealer() {
		Dealer dealer = dealerAccountService.getDealer();
		Converter<Dealer, DealerDTO> converter = DTOContext.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealer)).toJson();
	}

	/**
	 * ��channelId,���������͡�
	 * 
	 * @param channelId
	 * @param type
	 *            �豸���ͣ�Android=3��IOS=4.
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bindChannelId", method = RequestMethod.POST)
	public String bindChannelId(@RequestParam("channelId") String channelId, @RequestParam("type") int type) {
		Assert.lengthBetween(channelId, 1, 30);
		if (type != 3 && type != 4) {
			throw new IllegalArgumentException("�豸����ֻ��Ϊ3����4��");
		}
		dealerAccountService.bindChannelId(channelId, type);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �°��ֻ�������֤����֤�ֻ���
	 * 
	 * @param number
	 */
	@ResponseBody
	@RequestMapping(value = "/verifyNumber", method = RequestMethod.GET)
	public String verifyPhoneNumber(@RequestParam("number") String number) {
		Assert.isvalidPhoneNumber(number);
		dealerAccountService.verifyPhoneNumber(number);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���ֻ�
	 * 
	 * @param captcha
	 */
	@ResponseBody
	@RequestMapping(value = "/bindNumber", method = RequestMethod.PUT)
	public String bindPhoneNumber(@RequestParam("captcha") String captcha) {
		dealerAccountService.bindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���а��ֻ� ��ȡ��֤��
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
	public String getCaptcha() {
		dealerAccountService.getCaptcha();
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
		dealerAccountService.unbindPhoneNumber(captcha);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �޸�����
	 * 
	 * @param captcha
	 * @param password
	 */
	@ResponseBody
	@RequestMapping(value = "/editPassword", method = RequestMethod.PUT)
	public String editPassword(@RequestParam("captcha") String captcha, @RequestParam("password") String password) {
		Assert.isvalidPassword(password);
		dealerAccountService.editPassword(password, captcha);
		SecurityContextHolder.clearContext();
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��������ʱ��ȡ��֤��
	 * 
	 * @param number
	 */
	@ResponseBody
	@RequestMapping(value = "/findback/getCaptcha", method = RequestMethod.GET)
	public String getCaptcha4Forget(@RequestParam("number") String number) {
		Assert.isvalidPhoneNumber(number);
		dealerAccountService.postCaptcha(number);
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
	public String resetPassword(@RequestParam("number") String number, @RequestParam("captcha") String captcha, @RequestParam("password") String password) {

		Assert.isvalidPassword(password);
		Assert.isvalidPhoneNumber(number);
		dealerAccountService.resetPassword(number, captcha, password);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

}