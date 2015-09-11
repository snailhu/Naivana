package com.nirvana.controller.app;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.DealerPepsiPromotionDTO;
import com.nirvana.controller.dto.DealerPromotionDTO;
import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.PromCondition;
import com.nirvana.domain.dealer.PromWay;
import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerPromotionService;
import com.nirvana.utils.DateUtil;

/**
 * 经销商促销
 * 
 * */
@Controller
@RequestMapping(value = "/dealer/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.DEALER)
public class DealerPromotionController {

	@Resource
	private DealerPromotionService dealerPromotionService;

	private SimpleParser parser = new SimpleParser();

	/**
	 * 获取所有促销信息
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/promotions", method = RequestMethod.GET)
	@NeedDRole({ DRole.DEALER })
	public String getMyPromotions(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Page<DealerPromotion> p = dealerPromotionService.getMyPromotions(page,
				size);
		Converter<DealerPromotion, DealerPromotionDTO> converter = DTOContext
				.getConverter(DealerPromotionDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 编辑促销信息
	 * 
	 * @param promotionId
	 * @param title
	 *            标题
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param category
	 *            分类数据
	 * @param products
	 *            产品数据
	 * @param pCondition
	 *            促销条件
	 * @param cdtParam
	 *            条件参数
	 * @param way
	 *            促销方式
	 * @param wayParam
	 *            方式参数
	 * @param goods
	 *            奖励物品
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/promotions/{promotionId}", method = RequestMethod.PUT)
	@NeedDRole({ DRole.DEALER })
	public String editMyPromotion(
			@PathVariable("promotionId") long promotionId,
			@RequestParam(required = true, value = "title") String title,
			@RequestParam(required = true, value = "startDate") int startDate,
			@RequestParam(required = true, value = "endDate") int endDate,
			@RequestParam(required = true, value = "category") String category,
			@RequestParam(required = true, value = "products") String products,
			@RequestParam(required = true, value = "condition") String pCondition,
			@RequestParam(required = true, value = "cdtParam") int cdtParam,
			@RequestParam(required = true, value = "way") String way,
			@RequestParam(required = true, value = "wayParam") float wayParam,
			@RequestParam(required = false, value = "goods") String goods) {
		if (cdtParam <= 0) {
			throw new IllegalArgumentException("满...条件促发促销的数字最少为1");
		}
		try {
			Date dstart = DateUtil.parseDate(String.valueOf(startDate),
					"yyyyMMdd");
			Date dend = DateUtil.parseDate(String.valueOf(endDate), "yyyyMMdd");
			if (dstart.after(dend)) {
				throw new IllegalArgumentException("发起时间不能晚于结束时间");
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("时间格式错误");
		}
		Set<String> productCodes = parser.parseJSON(products,
				new TypeToken<Set<String>>() {
				});
		Set<String> goodz = null;
		if (goods != null) {
			try {
				goodz = parser.parseJSON(goods, new TypeToken<Set<String>>() {
				});
			} catch (Exception e) {
				throw new IllegalArgumentException("goods格式不对");
			}
			if (goodz.size() > 5) {
				throw new IllegalArgumentException("赠品种类限制：5种以内");
			}
			for (String g : goodz) {
				if (g.length() > 20) {
					throw new IllegalArgumentException("赠品字数限制：20以内");
				}
			}
		}
		PromCondition condition = PromCondition.parsePromCondition(pCondition);
		PromWay promWay = PromWay.parsePromWay(way);
		switch (promWay) {
		case MONEYREDUCTION:
			String[] strs = String.valueOf(wayParam).split("\\.");
			if (strs.length > 1 && strs[1].length() > 2) {
				throw new IllegalArgumentException("价格最多为两位小数");
			}
			break;
		default:
			String[] strs2 = String.valueOf(wayParam).split("\\.");
			if (strs2.length > 1 && Integer.valueOf(strs2[1]) != 0) {
				throw new IllegalArgumentException("折扣或最大数量只能为整数");
			}
			break;
		}
		List<String> catList = parser.parseJSON(category,
				new TypeToken<List<String>>() {
				});
		dealerPromotionService.editDealerPromotion(title, promotionId,
				startDate, endDate, catList, productCodes, condition, cdtParam,
				promWay, wayParam, goodz);

		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 撤回促销
	 * 
	 * @param promotionId
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/promotions/{promotionId}", method = RequestMethod.DELETE)
	@NeedDRole({ DRole.DEALER })
	public String withdrawMyPromotion(
			@PathVariable("promotionId") long promotionId) {
		dealerPromotionService.withdrawDealerPromotion(promotionId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 新建促销。
	 * 
	 * @param title
	 *            标题
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param category
	 *            分类数据
	 * @param products
	 *            产品数据
	 * @param pCondition
	 *            促销条件
	 * @param cdtParam
	 *            条件参数
	 * @param way
	 *            促销方式
	 * @param wayParam
	 *            方式参数
	 * @param goods
	 *            奖励物品
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/promotions", method = RequestMethod.POST)
	@NeedDRole({ DRole.DEALER })
	public String createPromotion(
			@RequestParam(required = true, value = "title") String title,
			@RequestParam(required = true, value = "startDate") int startDate,
			@RequestParam(required = true, value = "endDate") int endDate,
			@RequestParam(required = true, value = "category") String category,
			@RequestParam(required = true, value = "products") String products,
			@RequestParam(required = true, value = "condition") String pCondition,
			@RequestParam(required = true, value = "cdtParam") int cdtParam,
			@RequestParam(required = true, value = "way") String way,
			@RequestParam(required = true, value = "wayParam") float wayParam,
			@RequestParam(required = false, value = "goods") String goods) {
		if (cdtParam <= 0) {
			throw new IllegalArgumentException("满...条件促发促销的数字最少为1");
		}
		try {
			Date dstart = DateUtil.parseDate(String.valueOf(startDate),
					"yyyyMMdd");
			Date dend = DateUtil.parseDate(String.valueOf(endDate), "yyyyMMdd");
			if (dstart.after(dend)) {
				throw new IllegalArgumentException("发起时间不能晚于结束时间");
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("时间格式错误");
		}
		Set<String> productCodes = parser.parseJSON(products,
				new TypeToken<Set<String>>() {
				});
		Set<String> goodz = null;
		if (goods != null) {
			try {
				goodz = parser.parseJSON(goods, new TypeToken<Set<String>>() {
				});
			} catch (Exception e) {
				throw new IllegalArgumentException("goods格式不对");
			}
			if (goodz.size() > 5) {
				throw new IllegalArgumentException("赠品种类限制：5种以内");
			}
			for (String g : goodz) {
				if (g.length() > 20) {
					throw new IllegalArgumentException("赠品字数限制：20以内");
				}
			}
		}
		PromCondition condition = PromCondition.parsePromCondition(pCondition);
		PromWay promWay = PromWay.parsePromWay(way);
		if(wayParam>999999){
			throw new IllegalArgumentException("wayParam为6位以下数字");
		}
		switch (promWay) {
		case MONEYREDUCTION:
			String[] strs = String.valueOf(wayParam).split("\\.");
			if (strs.length > 1 && strs[1].length() > 2) {
				throw new IllegalArgumentException("价格最多为两位小数");
			}
			break;
		default:
			String[] strs2 = String.valueOf(wayParam).split("\\.");
			if (strs2.length > 1 && Integer.valueOf(strs2[1]) != 0) {
				throw new IllegalArgumentException("折扣或最大数量只能为整数");
			}
			break;
		}
		List<String> catList = parser.parseJSON(category,
				new TypeToken<List<String>>() {
				});
		dealerPromotionService.createDealerPromotion(title, startDate, endDate,
				catList, productCodes, condition, cdtParam, promWay, wayParam,
				goodz);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获得百事促销信息
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/pepsipromotions", method = RequestMethod.GET)
	@NeedDRole({ DRole.USER })
	public String getPepsiPromotion(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Page<PepsiPromotion> promotions = dealerPromotionService
				.getPepsiPromotions(page, size);
		Converter<PepsiPromotion, DealerPepsiPromotionDTO> converter = DTOContext
				.getConverter(DealerPepsiPromotionDTO.class);

		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(promotions));
		return bean.toJson();
	}

}
