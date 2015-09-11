package com.nirvana.controller.app;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.AppraisesDTO;
import com.nirvana.controller.dto.StoreOrderDTO;
import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerBusinessService;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.service.pojo.Appraises;
import com.nirvana.utils.DateUtil;

/**
 * 经销商处理门店订货的相关业务。
 * 
 * */
@Controller
@RequestMapping(value = "/dealer/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.DEALER)
@NeedDRole({ DRole.DEALER })
public class DealerBusinessController {

	@Resource
	private DealerBusinessService dealerBusinessService;
	@Resource
	private DealerCurrentLoginService dealerCurrentAccountService;

	private Parser parser = new SimpleParser();

	/**
	 * 获取未处理订单
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storeorders/unhandled", method = RequestMethod.GET)
	public String getUnhandledOrders(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Page<StoreOrder> p = dealerBusinessService.getUnhandledOrders(page, size);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取处理中订单
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storeorders/handling", method = RequestMethod.GET)
	public String getHandlingOrders(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Page<StoreOrder> p = dealerBusinessService.getHandlingOrders(page, size);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取已完成订单
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storeorders/finished", method = RequestMethod.GET)
	public String getFinishedOrders(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<StoreOrder> p = dealerBusinessService.getFinishedOrders(page, size);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取未处理订单
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/storeorders/unhandled/filter", method = RequestMethod.GET)
	public String getUnhandledOrdersByDate(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM-dd");
			end = DateUtil.parseDate(endDate, "yyyy-MM-dd");
			end.setDate(end.getDate() + 1);
		} catch (Exception e) {
			throw new IllegalArgumentException("日期格式yyyy-MM-dd");
		}

		Page<StoreOrder> p = dealerBusinessService.getUnhandledOrdersByDate(page, size, start, end);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取处理中订单
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/storeorders/handling/filter", method = RequestMethod.GET)
	public String getHandlingOrdersByDate(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM-dd");
			end = DateUtil.parseDate(endDate, "yyyy-MM-dd");
			end.setDate(end.getDate() + 1);
		} catch (Exception e) {
			throw new IllegalArgumentException("日期格式yyyy-MM-dd");
		}
		Page<StoreOrder> p = dealerBusinessService.getHandlingOrdersByDate(page, size, start, end);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取已完成订单
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/storeorders/finished/filter", method = RequestMethod.GET)
	public String getFinishedOrdersByDate(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM-dd");
			end = DateUtil.parseDate(endDate, "yyyy-MM-dd");
			end.setDate(end.getDate() + 1);
		} catch (Exception e) {
			throw new IllegalArgumentException("日期格式yyyy-MM-dd");
		}
		Page<StoreOrder> p = dealerBusinessService.getFinishedOrdersByDate(page, size, start, end);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 去处理订单
	 * 
	 * @param orderId
	 * @param note
	 *            附加信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storeorders/{orderId}/handle", method = RequestMethod.PUT)
	public String handleOrder(@PathVariable("orderId") long orderId, @RequestParam(value = "note", required = false) String note) {
		dealerBusinessService.handleOrder(orderId, note);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 完成订单
	 * 
	 * @param orderId
	 * @param sku
	 *            修改的商品数量（如果有）
	 * @param gifts
	 *            赠品
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storeorders/{orderId}/finish", method = RequestMethod.PUT)
	public String finishOrder(
			@PathVariable("orderId") Long orderId,
			@RequestParam(value = "payPrice", required = false) Double payPrice,
			@RequestParam(value = "sku", required = false) String sku,
			@RequestParam(value = "gifts", required = false) String gifts,
			@RequestParam(value = "note", required = false) String note) {
		Map<String, Integer> skuData = null;
		try {
			skuData = parser.parseJSON(sku, new TypeToken<Map<String, Integer>>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		dealerBusinessService.finishOrder(orderId, skuData, payPrice, gifts, note);
		// dealerBusinessService.finishOrder(orderId, skuData,payPrice gifts,
		// note);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取订单评价。
	 * 
	 * @param orderId
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storeorders/{orderId}/appraises", method = RequestMethod.GET)
	public String getAppraises(@PathVariable("orderId") Long orderId) {
		Appraises appraises = dealerBusinessService.getAppraises(orderId);
		Converter<Appraises, AppraisesDTO> converter = DTOContext.getConverter(AppraisesDTO.class);
		return new JsonResponseBean(converter.convert(appraises)).toJson();
	}

}
