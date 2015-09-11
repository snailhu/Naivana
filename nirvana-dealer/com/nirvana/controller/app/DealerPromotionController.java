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
 * �����̴���
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
	 * ��ȡ���д�����Ϣ
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
	 * �༭������Ϣ
	 * 
	 * @param promotionId
	 * @param title
	 *            ����
	 * @param startDate
	 *            ��ʼʱ��
	 * @param endDate
	 *            ����ʱ��
	 * @param category
	 *            ��������
	 * @param products
	 *            ��Ʒ����
	 * @param pCondition
	 *            ��������
	 * @param cdtParam
	 *            ��������
	 * @param way
	 *            ������ʽ
	 * @param wayParam
	 *            ��ʽ����
	 * @param goods
	 *            ������Ʒ
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
			throw new IllegalArgumentException("��...�����ٷ���������������Ϊ1");
		}
		try {
			Date dstart = DateUtil.parseDate(String.valueOf(startDate),
					"yyyyMMdd");
			Date dend = DateUtil.parseDate(String.valueOf(endDate), "yyyyMMdd");
			if (dstart.after(dend)) {
				throw new IllegalArgumentException("����ʱ�䲻�����ڽ���ʱ��");
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("ʱ���ʽ����");
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
				throw new IllegalArgumentException("goods��ʽ����");
			}
			if (goodz.size() > 5) {
				throw new IllegalArgumentException("��Ʒ�������ƣ�5������");
			}
			for (String g : goodz) {
				if (g.length() > 20) {
					throw new IllegalArgumentException("��Ʒ�������ƣ�20����");
				}
			}
		}
		PromCondition condition = PromCondition.parsePromCondition(pCondition);
		PromWay promWay = PromWay.parsePromWay(way);
		switch (promWay) {
		case MONEYREDUCTION:
			String[] strs = String.valueOf(wayParam).split("\\.");
			if (strs.length > 1 && strs[1].length() > 2) {
				throw new IllegalArgumentException("�۸����Ϊ��λС��");
			}
			break;
		default:
			String[] strs2 = String.valueOf(wayParam).split("\\.");
			if (strs2.length > 1 && Integer.valueOf(strs2[1]) != 0) {
				throw new IllegalArgumentException("�ۿۻ��������ֻ��Ϊ����");
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
	 * ���ش���
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
	 * �½�������
	 * 
	 * @param title
	 *            ����
	 * @param startDate
	 *            ��ʼʱ��
	 * @param endDate
	 *            ����ʱ��
	 * @param category
	 *            ��������
	 * @param products
	 *            ��Ʒ����
	 * @param pCondition
	 *            ��������
	 * @param cdtParam
	 *            ��������
	 * @param way
	 *            ������ʽ
	 * @param wayParam
	 *            ��ʽ����
	 * @param goods
	 *            ������Ʒ
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
			throw new IllegalArgumentException("��...�����ٷ���������������Ϊ1");
		}
		try {
			Date dstart = DateUtil.parseDate(String.valueOf(startDate),
					"yyyyMMdd");
			Date dend = DateUtil.parseDate(String.valueOf(endDate), "yyyyMMdd");
			if (dstart.after(dend)) {
				throw new IllegalArgumentException("����ʱ�䲻�����ڽ���ʱ��");
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("ʱ���ʽ����");
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
				throw new IllegalArgumentException("goods��ʽ����");
			}
			if (goodz.size() > 5) {
				throw new IllegalArgumentException("��Ʒ�������ƣ�5������");
			}
			for (String g : goodz) {
				if (g.length() > 20) {
					throw new IllegalArgumentException("��Ʒ�������ƣ�20����");
				}
			}
		}
		PromCondition condition = PromCondition.parsePromCondition(pCondition);
		PromWay promWay = PromWay.parsePromWay(way);
		if(wayParam>999999){
			throw new IllegalArgumentException("wayParamΪ6λ��������");
		}
		switch (promWay) {
		case MONEYREDUCTION:
			String[] strs = String.valueOf(wayParam).split("\\.");
			if (strs.length > 1 && strs[1].length() > 2) {
				throw new IllegalArgumentException("�۸����Ϊ��λС��");
			}
			break;
		default:
			String[] strs2 = String.valueOf(wayParam).split("\\.");
			if (strs2.length > 1 && Integer.valueOf(strs2[1]) != 0) {
				throw new IllegalArgumentException("�ۿۻ��������ֻ��Ϊ����");
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
	 * ��ð��´�����Ϣ
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
