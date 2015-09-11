package com.nirvana.controller.app;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.DealerPepsiPromotionDTO;
import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerPromotionService;

/**
 * 业务员获取百事促销信息。
 * 
 * */
@Controller
@RequestMapping(value = "/backend/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.BACKEND)
@NeedERole({ ERole.AGENT })
public class AgentPromotionController {
	@Resource
	DealerPromotionService dealerPromotionService;

	/**
	 * 获取百事促销信息
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/pepsipromotions", method = RequestMethod.GET)
	public String getPepsiPromotion(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<PepsiPromotion> promotions = dealerPromotionService.getPepsiPromotions(page, size);
		Converter<PepsiPromotion, DealerPepsiPromotionDTO> converter = DTOContext.getConverter(DealerPepsiPromotionDTO.class);
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(promotions));
		return bean.toJson();
	}
}
