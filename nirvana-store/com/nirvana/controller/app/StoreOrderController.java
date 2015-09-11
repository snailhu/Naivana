package com.nirvana.controller.app;

import java.text.ParseException;
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
import com.nirvana.controller.dto.InventoryDTO;
import com.nirvana.controller.dto.StoreOrderDTO;
import com.nirvana.controller.dto.StoreOrderDocumentDTO;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderState;
import com.nirvana.mongo.document.StoreOrderDocument;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedSRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.StoreRestrockService;
import com.nirvana.utils.DateUtil;

/**
 * 门店下单
 * 
 * */
@Controller
@RequestMapping(value = "/store/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.STORE)
@NeedSRole
public class StoreOrderController {

	@Resource
	StoreRestrockService storeRestrockService;

	/**
	 * 获取可购买商品。
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/products", method = { RequestMethod.GET })
	public String getAllProducts(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Inventory> pInventory = storeRestrockService.getProducts(page,
				size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		return new JsonResponseBean(converter.convert(pInventory)).toJson();
	}

	/**
	 * 根据品牌获取可购买商品。
	 * 
	 * @param brandName
	 *            品牌名
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/brands/{brandName}/products", method = { RequestMethod.GET })
	public String getProductByBrand(
			@PathVariable("brandName") String brandName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Inventory> pInventory = storeRestrockService.getProducts(
				brandName, page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		return new JsonResponseBean(converter.convert(pInventory)).toJson();
	}

	/**
	 * 根据商品编码获取商品。
	 * 
	 * @param code
	 *            商品编码
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/products/{code}", method = { RequestMethod.GET })
	public String getProductByCode(@PathVariable("code") String code) {
		Inventory inventory = storeRestrockService.getProduct(code);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		return new JsonResponseBean(converter.convert(inventory)).toJson();
	}

	/**
	 * 下订单
	 * 
	 * @param items
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public String placeOrder(@RequestParam("items") String items) {

		Parser parser = new SimpleParser();
		Map<String, Integer> map = null;
		try {
			map = parser.parseJSON(items,
					new TypeToken<Map<String, Integer>>() {
					});
		} catch (Exception e) {
			throw new IllegalArgumentException("items 格式：{“”：1，“”：10}");
		}
		StoreOrder order = storeRestrockService.placeOrder(map);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext
				.getConverter(StoreOrderDTO.class);

		return new JsonResponseBean(converter.convert(order)).toJson();
	}

	/**
	 * 提交订单
	 * 
	 * @param orderId
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/orders/{orderId}/submit", method = RequestMethod.POST)
	public String submitOrder(@PathVariable("orderId") long orderId) {

		StoreOrder order = storeRestrockService.submitOrder(orderId, null);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext
				.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(order)).toJson();
	}

	/**
	 * 取消订单
	 * 
	 * @param orderId
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.POST)
	public String cancelOrder(@PathVariable("orderId") long orderId) {
		storeRestrockService.cancelOrder(orderId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取订单详情
	 * 
	 * @param orderId
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public String placeOrder(@PathVariable("orderId") long orderId) {
		StoreOrder order = storeRestrockService.getOrder(orderId);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext
				.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(order)).toJson();
	}

	/**
	 * 获取订单列表
	 * 
	 * @param page
	 * @param size
	 * @param state
	 *            要获取的订单状态。
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getOrders(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size,
			@RequestParam(value = "state", required = false) String state) {

		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext
				.getConverter(StoreOrderDTO.class);
		if (state == null) {
			return new JsonResponseBean(converter.convert(storeRestrockService
					.getOrders(page, size))).toJson();
		} else {
			StoreOrderState orderState = StoreOrderState
					.parseStoreOrderState(state);
			if (StoreOrderState.CANCELED == orderState
					|| StoreOrderState.FINISHHANDLE == orderState) {
				return new JsonResponseBean(
						converter.convert(storeRestrockService
								.getOrdersByMongo(orderState, page, size)))
						.toJson();
			} else {
				return new JsonResponseBean(
						converter.convert(storeRestrockService.getOrders(
								orderState, page, size))).toJson();
			}
		}
	}

	/**
	 * 获取订单列表
	 * 
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param size
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/orders/bill", method = RequestMethod.GET)
	public String getOrdersBill(
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM-dd");
			end = DateUtil.parseDate(endDate, "yyyy-MM-dd");
		} catch (ParseException e) {
			throw new IllegalArgumentException("时间格式错误");
		}

		Page<StoreOrderDocument> p = storeRestrockService.getOrdersBill(start,
				end, page, size);
		Converter<StoreOrderDocument, StoreOrderDocumentDTO> converter = DTOContext
				.getConverter(StoreOrderDocumentDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 订单评价
	 * 
	 * @param orderId
	 * @param product
	 *            商品质量
	 * @param delivery
	 *            物流
	 * @param manner
	 *            服务态度
	 * @param note
	 *            留言
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/orders/{orderId}/appraise", method = RequestMethod.PUT)
	public String appraise(@PathVariable("orderId") long orderId,
			@RequestParam("product") int product,
			@RequestParam("delivery") int delivery,
			@RequestParam("manner") Integer manner,
			@RequestParam(value = "note", required = false) String note) {
		storeRestrockService.appraise(orderId, product, delivery, manner, note);
		return JsonResponseBean.getSuccessResponse().toJson();
	}
}
