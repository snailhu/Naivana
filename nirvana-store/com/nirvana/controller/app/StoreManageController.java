package com.nirvana.controller.app;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.DealerDTO;
import com.nirvana.controller.dto.DealerNameDTO;
import com.nirvana.controller.dto.StoreStorefrontInfoDTO;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedSRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.StoreManageService;

/**
 * 门店杂务管理
 * 
 * */
@Controller
@RequestMapping(value = "/store/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.STORE)
@NeedSRole
public class StoreManageController {

	@Resource
	private StoreManageService storeManageService;

	/**
	 * 获取送货地址信息
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public String getAddr() {
		StoreStorefrontInfo info = storeManageService.getAddr();
		Converter<StoreStorefrontInfo, StoreStorefrontInfoDTO> converter = DTOContext
				.getConverter(StoreStorefrontInfoDTO.class);
		return new JsonResponseBean(converter.convert(info)).toJson();
	}

	/**
	 * 获取经销商名
	 */
	@ResponseBody
	@RequestMapping(value = "/dealers/my", method = RequestMethod.GET)
	public String getDealer() {
		Dealer name = storeManageService.getDealerName();
		Converter<Dealer, DealerNameDTO> converter = DTOContext
				.getConverter(DealerNameDTO.class);
		return new JsonResponseBean(converter.convert(name)).toJson();
	}

	/**
	 * 获取周边经销商
	 */
	@ResponseBody
	@RequestMapping(value = "/dealers/around", method = RequestMethod.GET)
	public String getDealersAround() {
		List<Dealer> l = storeManageService.getDealersAround();
		Converter<Dealer, DealerDTO> converter = DTOContext
				.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(l)).toJson();
	}

}
