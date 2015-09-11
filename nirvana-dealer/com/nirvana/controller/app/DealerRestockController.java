package com.nirvana.controller.app;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.DealerGetFinishedDealerOrderDTO;
import com.nirvana.controller.dto.DealerOrderDTO;
import com.nirvana.controller.dto.ProductDTO;
import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderState;
import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.mongo.document.DealerOrderDocument;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerRestockService;

/**
 * �����̽���
 * 
 * */
@Controller
@RequestMapping(value = "/dealer/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.DEALER)
@NeedDRole({ DRole.USER })
public class DealerRestockController {

	@Resource
	private DealerRestockService dealerRestockService;

	private Parser parser = new SimpleParser();

	/**
	 * ��ȡ��Ʒ��
	 * 
	 * @param page
	 * @param size
	 * 
	 */
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ResponseBody
	public String getProducts(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {

		Page<Product> products = dealerRestockService.getProduct(page, size);
		Converter<Product, ProductDTO> converter = DTOContext.getConverter(ProductDTO.class);
		return new JsonResponseBean(converter.convert(products)).toJson();
	}

	/**
	 * ����Ʒ�ƻ�ȡ��Ʒ��
	 * 
	 * @param brand
	 * @param page
	 * @param size
	 * 
	 */
	@RequestMapping(value = "/brands/{brand}/products", method = RequestMethod.GET)
	@ResponseBody
	public String getProducts(
			@PathVariable(value = "brand") String brand,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Assert.hasLength(brand, "brand��������Ϊ�ա�");
		Page<Product> products = dealerRestockService.getProduct(brand, page, size);
		Converter<Product, ProductDTO> converter = DTOContext.getConverter(ProductDTO.class);
		return new JsonResponseBean(converter.convert(products)).toJson();
	}

	/**
	 * �¶���
	 * 
	 * @param skuData
	 *            ������Ʒ����
	 * @param sign
	 *            ǩ��ͼƬ
	 */
	@RequestMapping(value = "/dealerorders", method = RequestMethod.POST)
	@ResponseBody
	public String placeOrderFromBuyCart(@RequestParam(value = "items") String skuData, @RequestParam("sign") MultipartFile sign) {
		Map<String, Integer> list = null;
		try {

			list = parser.parseJSON(skuData, new TypeToken<Map<String, Integer>>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException("skuData format error!");
		}
		DealerOrder order = dealerRestockService.placeOrder(list, sign);
		Converter<DealerOrder, DealerOrderDTO> converter = DTOContext.getConverter(DealerOrderDTO.class);
		return new JsonResponseBean(converter.convert(order)).toJson();
	}

	/**
	 * ��ȡ����
	 * 
	 * @param page
	 * @param size
	 * @param state
	 *            ����״̬: <br>
	 *            ��ȡ����ɶ�����ѡȡֵ��{UNFINISHED,PLANNED,GIVEN,RESERVED,DELIVERING,
	 *            PART_DELIVERED, DELIVERED}<br>
	 *            ��ȡδ��ɶ�����ѡȡֵ��{FINISHED,CLOSED,CANCELED}<br>
	 *            ����{FINISHED}��{UNFINISHED}�ֱ��ʾ��ȡ��������ɶ�������ȡ����δ��ɶ�����
	 * 
	 * */
	@RequestMapping(value = "/dealerorders", method = RequestMethod.GET)
	@ResponseBody
	public String getOrders(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
			@RequestParam("state") String state) {
		com.nirvana.utils.Assert.notNull(state);
		if (state.equals("FINISHED")) {
			Page<DealerOrderDocument> morders = dealerRestockService.getFinishedOrders(null, page, size);
			Converter<DealerOrderDocument, DealerGetFinishedDealerOrderDTO> converter = DTOContext.getConverter(DealerGetFinishedDealerOrderDTO.class);
			return new JsonResponseBean(converter.convert(morders)).toJson();
		} else if (state.equals("UNFINISHED")) {
			Page<DealerOrder> p = dealerRestockService.getNotFinishedOrders(null, page, size);
			Converter<DealerOrder, DealerOrderDTO> converter = DTOContext.getConverter(DealerOrderDTO.class);
			return new JsonResponseBean(converter.convert(p)).toJson();
		}

		DealerOrderState orderState = DealerOrderState.parseDealerOrderState(state);
		if (orderState == DealerOrderState.CANCELED || orderState == DealerOrderState.CLOSED) {
			Page<DealerOrderDocument> morders = dealerRestockService.getFinishedOrders(orderState, page, size);
			Converter<DealerOrderDocument, DealerGetFinishedDealerOrderDTO> converter = DTOContext.getConverter(DealerGetFinishedDealerOrderDTO.class);
			return new JsonResponseBean(converter.convert(morders)).toJson();
		} else {
			Page<DealerOrder> p = dealerRestockService.getNotFinishedOrders(orderState, page, size);
			Converter<DealerOrder, DealerOrderDTO> converter = DTOContext.getConverter(DealerOrderDTO.class);
			return new JsonResponseBean(converter.convert(p)).toJson();
		}
	}
}
