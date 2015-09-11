package com.nirvana.controller.app;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.DealerPromotionDTO;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedSRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.StorePromotionService;

/**
 * 门店获取经销商促销信息。
 * 
 * */
@Controller
@RequestMapping(value = "/store/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.STORE)
@NeedSRole
public class StorePromotionController {
	@Resource
	StorePromotionService storePromotionService;

	/**
	 * 获取经销商促销。
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/promotions", method = RequestMethod.GET)
	public String placeOrder(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<DealerPromotion> p = storePromotionService.getPromotions(page, size);
		Converter<DealerPromotion, DealerPromotionDTO> converter = DTOContext.getConverter(DealerPromotionDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

}
