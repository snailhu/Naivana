package com.nirvana.controller.app;

import java.util.Date;
import java.util.List;

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
import com.nirvana.controller.dto.InventoriesCheckHistoryDTO;
import com.nirvana.controller.dto.InventoryDTO;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerInventoryService;
import com.nirvana.service.pojo.InventoryCheck;
import com.nirvana.utils.DateUtil;

/**
 * �����̿����ء�
 * 
 * */
@Controller
@RequestMapping(value = "/dealer/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.DEALER)
@NeedDRole({ DRole.DEALER })
public class DealerInventoryController {
	@Resource
	private DealerInventoryService dealerInventoryService;

	Parser parser = new SimpleParser();

	/**
	 * ��ȡ�ҵ����п�档
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/inventories", method = RequestMethod.GET)
	public String getInventories(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {

		Page<Inventory> p = dealerInventoryService.getInventories(page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * ����Ʒ�ƻ�ȡ�ҵĿ�档
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/brands/{name}/inventories", method = RequestMethod.GET)
	public String getInventoriesByBrand(
			@PathVariable("name") String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Page<Inventory> p = dealerInventoryService.getInventoriesByBrand(name,
				page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * ��ȡ�ҵĿ�棨���ڸ��¡���
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/inventories/forupdate", method = RequestMethod.GET)
	public String getInventoriesForUpdate(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {

		Page<Inventory> p = dealerInventoryService.getInventoriesForUpdate(
				page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * ����Ʒ�ƻ�ȡ�ҵĿ�棨���ڸ��¡���
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/brands/{name}/inventories/forupdate", method = RequestMethod.GET)
	public String getInventoriesByBrandForUpdate(
			@PathVariable("name") String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		Page<Inventory> p = dealerInventoryService
				.getInventoriesByBrandForUpdate(name, page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

	/**
	 * ����̵㡣
	 * 
	 * @param updates
	 *            �̵������
	 * @param sign
	 *            ǩ��ͼƬ
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/inventories", method = RequestMethod.POST)
	public String updateInventories(@RequestParam("updates") String updates,
			@RequestParam(value = "sign") MultipartFile sign) {
		List<InventoryCheck> pojos = null;
		try {
			pojos = parser.parseJSON(updates,
					new TypeToken<List<InventoryCheck>>() {
					});
		} catch (Exception e) {
			throw new IllegalArgumentException("updates������������");
		}
		for (InventoryCheck c : pojos) {
			String[] strs = String.valueOf(c.getPrice()).split("\\.");
			if (strs.length > 1 && strs[1].length() > 2) {
				throw new IllegalArgumentException("�۸����Ϊ��λС��");
			}
		}
		dealerInventoryService.updateInventories(pojos, sign);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��ȡ����̵���ʷ��
	 * 
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param size
	 * 
	 * */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/inventorycheckhistories", method = RequestMethod.GET)
	public String getInventoriesHistory(
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
			throw new IllegalArgumentException("ʱ���ʽyyyy-MM-dd");
		}
		Page<InventoriesCheckHistory> p = dealerInventoryService
				.getInventoriesHistory(start, end, page, size);
		Converter<InventoriesCheckHistory, InventoriesCheckHistoryDTO> converter = DTOContext
				.getConverter(InventoriesCheckHistoryDTO.class);
		return new JsonResponseBean(converter.convert(p)).toJson();
	}

}
