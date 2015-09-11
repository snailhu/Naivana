package com.nirvana.controller.app;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.DealerOrderDTO;
import com.nirvana.controller.dto.StoreOrderDTO;
import com.nirvana.controller.dto.VisitRouteDTO;
import com.nirvana.controller.dto.app.BEAppStoreDTO;
import com.nirvana.domain.backend.VisitNodeType;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.AgentWorkService;
import com.nirvana.service.pojo.web.DeviceInfoData;
import com.nirvana.service.pojo.web.MainShelfInfoData;
import com.nirvana.service.pojo.web.SecondShelfInfoData;
import com.nirvana.utils.Assert;
import com.nirvana.utils.DateUtil;

/**
 * 业务员拜访相关工作
 * 
 * */
@Controller
@RequestMapping(value = "/backend/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.BACKEND)
@NeedERole({ ERole.AGENT })
public class AgentVisitController {

	@Resource
	private AgentWorkService agentWorkService;

	private final Parser parser = new SimpleParser();

	/**
	 * 获取我的本日拜访路线。今日拜访--拜访计划--4
	 * 
	 * @param id
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{id}/visitroutes/today", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyTodayVisitRoute(@PathVariable("id") int id) {

		VisitRoute vr = agentWorkService.getTodayVisitRoute(id);
		Converter<VisitRoute, VisitRouteDTO> converter = DTOContext.getConverter(VisitRouteDTO.class);
		return new JsonResponseBean(converter.convert(vr)).toJson();

	};

	/**
	 * 获取我管理的某个店的信息。 今日拜访--访前准备--5
	 * 
	 * @param id
	 *            门店ID
	 * 
	 * */
	@RequestMapping(value = "/stores/{id}", method = { RequestMethod.GET })
	public @ResponseBody
	String getMyStoreInformation(@PathVariable("id") long id) {
		Store store = agentWorkService.getOneStoreInfo(id);
		Converter<Store, BEAppStoreDTO> converter = DTOContext.getConverter(BEAppStoreDTO.class);
		return new JsonResponseBean(converter.convert(store)).toJson();
	};

	/**
	 * 拜访下订单。
	 * 
	 * @param visitNodeType
	 *            拜访类型（门店还是直营店）
	 * @param items
	 *            订单项目数据
	 * @param storeId
	 *            门店ID
	 * @param date
	 *            日期
	 * @param file
	 *            签名图片
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/palcevisitorder", method = { RequestMethod.POST })
	public String placeOrder(
			@RequestParam("visitNodeType") String visitNodeType,
			@RequestParam("items") String items,
			@RequestParam("storeId") long storeId,
			@RequestParam("date") String date,
			@RequestParam("picture") MultipartFile file) {
		Assert.hasLength(items);
		VisitNodeType type = VisitNodeType.parseVisitNodeType(visitNodeType);
		Map<String, Integer> ois = null;
		try {
			ois = parser.parseJSON(items, new TypeToken<Map<String, Integer>>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException("itms格式:Map<String, Integer>的json形式");
		}
		Date d = null;
		try {
			d = DateUtil.parseDate(date, "yyyy-MM-dd hh:mm:ss");
		} catch (ParseException e) {
			throw new IllegalArgumentException("date格式:yyyy-MM-dd hh:mm:ss");
		}

		StoreOrder order = agentWorkService.placeVisitOrder(type, storeId, d, ois, file);

		if (type.equals(VisitNodeType.DIRECT_STORE)) {
			return JsonResponseBean.getSuccessResponse().toJson();
		} else {
			Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
			return new JsonResponseBean(converter.convert(order)).toJson();
		}
	}

	/**
	 * 拜访线外订单
	 * 
	 * @param visitNodeType
	 *            拜访类型（门店还是直营店）
	 * @param items
	 *            订单项目数据
	 * @param storeId
	 *            门店ID
	 * @param file
	 *            签名图片
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/placeorder", method = { RequestMethod.POST })
	public String placeOrderWithoutVisit(
			@RequestParam("visitNodeType") String visitNodeType,
			@RequestParam("storeId") long storeId,
			@RequestParam("items") String items,
			@RequestParam(value = "picture", required = false) MultipartFile file) {

		Assert.hasLength(items, visitNodeType);

		VisitNodeType type = VisitNodeType.parseVisitNodeType(visitNodeType);
		Map<String, Integer> ois = null;
		try {
			ois = parser.parseJSON(items, new TypeToken<Map<String, Integer>>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException("{\"str\":1,\"str2\":2}");
		}

		StoreOrder order = agentWorkService.placeOrder(type, storeId, ois, file);

		if (type.equals(VisitNodeType.DIRECT_STORE)) {
			return JsonResponseBean.getSuccessResponse().toJson();
		} else {
			Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
			return new JsonResponseBean(converter.convert(order)).toJson();
		}
	}

	/**
	 * 提交门店订单
	 * 
	 * @param orderId
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/submitorder", method = { RequestMethod.PUT })
	public String submitStoreOrder(@RequestParam("orderId") long orderId) {
		agentWorkService.submitOrder(orderId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取我的所有门店订单
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/agentarea/{id}/orders/store", method = { RequestMethod.GET })
	public String getOrders(
			@PathVariable("id") int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<StoreOrder> p = agentWorkService.getAgentPagedStoreOrders(id, page, size);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取我的所有直营店订单
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/agentarea/{id}/orders/dealer", method = { RequestMethod.GET })
	public String getDealerOrders(
			@PathVariable("id") int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<DealerOrder> p = agentWorkService.getAgentPagedDealerOrders(id, page, size);
		Converter<DealerOrder, DealerOrderDTO> converter = DTOContext.getConverter(DealerOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取我的所有门店订单
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/agentarea/{id}/orders/store/nothelped", method = { RequestMethod.GET })
	public String getOrdersNotAgentHelped(
			@PathVariable("id") int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<StoreOrder> p = agentWorkService.getAgentPagedStoreOrdersNotAgentHelped(id, page, size);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取我的所有直营店订单
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/agentarea/{id}/orders/dealer/nothelped", method = { RequestMethod.GET })
	public String getDealerOrdersNotAgentHelped(
			@PathVariable("id") int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<DealerOrder> p = agentWorkService.getAgentPagedDealerOrdersNotAgentHelped(id, page, size);
		Converter<DealerOrder, DealerOrderDTO> converter = DTOContext.getConverter(DealerOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取我的所有门店订单
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * 
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/agentarea/{id}/orders/store/nothelped/filter", method = { RequestMethod.GET })
	public String getOrdersNotAgentHelpedByDate(
			@PathVariable("id") int id,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM-dd");
			end = DateUtil.parseDate(endDate, "yyyy-MM-dd");
			end.setDate(end.getDate()+1);
		} catch (Exception e) {
			throw new IllegalArgumentException("日期格式yyyy-MM-dd");
		}
		Page<StoreOrder> p = agentWorkService.getAgentPagedStoreOrdersNotAgentHelpedByDate(id, start, end, page, size);
		Converter<StoreOrder, StoreOrderDTO> converter = DTOContext.getConverter(StoreOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 获取我的所有直营店订单
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * 
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/agentarea/{id}/orders/dealer/nothelped/filter", method = { RequestMethod.GET })
	public String getDealerOrdersNotAgentHelpedByDate(
			@PathVariable("id") int id,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM-dd");
			end = DateUtil.parseDate(endDate, "yyyy-MM-dd");
			end.setDate(end.getDate()+1);
		} catch (Exception e) {
			throw new IllegalArgumentException("日期格式yyyy-MM-dd");
		}
		Page<DealerOrder> p = agentWorkService.getAgentPagedDealerOrdersNotAgentHelpedByDate(id, start, end, page, size);
		Converter<DealerOrder, DealerOrderDTO> converter = DTOContext.getConverter(DealerOrderDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * 拜访。（同步。）
	 * 
	 * @param date
	 * @param nodeType
	 * @param storeId
	 * @param longitude
	 * @param latitude
	 * @param mainShelfInfoData
	 * @param deviceInfoDatas
	 * @param secondShelfInfoData
	 * @param storeHeadPics
	 * @param mainShelfPics
	 * @param secondShelfPics
	 * @param devicePics
	 * @param filmPics
	 * @param posterPics
	 * @param lightPics
	 * @param otherPics
	 * @param isFinished
	 * @param finishDate
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/visitrecords/visit", method = { RequestMethod.POST })
	public String offLineVisit(
			@RequestParam("date") String date,
			@RequestParam("nodeType") String nodeType,
			@RequestParam("storeId") long storeId,
			@RequestParam("longitude") float longitude,
			@RequestParam("latitude") float latitude,
			@RequestParam(value = "mainShelfInfoData", required = false) String mainShelfInfoData,
			@RequestParam(value = "deviceInfoDatas", required = false) String deviceInfoDatas,
			@RequestParam(value = "secondShelfInfoData", required = false) String secondShelfInfoData,
			@RequestParam(value = "storeHeadPics", required = false) MultipartFile[] storeHeadPics,
			@RequestParam(value = "mainShelfPics", required = false) MultipartFile[] mainShelfPics,
			@RequestParam(value = "secondShelfPics", required = false) MultipartFile[] secondShelfPics,
			@RequestParam(value = "devicePics", required = false) MultipartFile[] devicePics,
			@RequestParam(value = "filmPics", required = false) MultipartFile[] filmPics,
			@RequestParam(value = "posterPics", required = false) MultipartFile[] posterPics,
			@RequestParam(value = "lightPics", required = false) MultipartFile[] lightPics,
			@RequestParam(value = "otherPics", required = false) MultipartFile[] otherPics,
			@RequestParam(value = "isFinished", required = false) Boolean isFinished,
			@RequestParam(value = "finishDate", required = false) String finishDate) {
		Date d = null;
		Date fd = null;
		try {
			if (date != null) {
				d = DateUtil.parseDate(date, "yyyy-MM-dd hh:mm:ss");
			}
			if (finishDate != null) {
				fd = DateUtil.parseDate(finishDate, "yyyy-MM-dd hh:mm:ss");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("date或finishDate格式错误");
		}
		VisitNodeType type = VisitNodeType.parseVisitNodeType(nodeType);

		MainShelfInfoData msid = null;
		try {
			msid = parser.parseJSON(mainShelfInfoData, MainShelfInfoData.class);
		} catch (Exception e) {
			throw new IllegalArgumentException("mainShelfInfoData 错误");
		}

		Set<DeviceInfoData> did = null;
		try {
			did = parser.parseJSON(deviceInfoDatas, new TypeToken<Set<DeviceInfoData>>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException("DeviceInfoData 错误");
		}

		SecondShelfInfoData ssid = null;
		try {
			ssid = parser.parseJSON(secondShelfInfoData, SecondShelfInfoData.class);
		} catch (Exception e) {
			throw new IllegalArgumentException("SecondShelfInfoData 错误");
		}
		agentWorkService.synchronizeVisitRecord(d, type, storeId, longitude, latitude, msid, did, ssid, storeHeadPics, mainShelfPics, secondShelfPics, devicePics, filmPics,
				posterPics, lightPics, otherPics, isFinished, fd);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

}
