package com.nirvana.controller.app;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.DealerStoreCategoryDTO;
import com.nirvana.controller.dto.MonthIncomeAndExpenditureDTO;
import com.nirvana.controller.dto.StoreDTO;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.MonthIncomeAndExpenditure;
import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.DealerManagementService;
import com.nirvana.utils.Assert;
import com.nirvana.utils.DateUtil;

/**
 * 经销商管理，包括门店管理，账务查询，积分查询。
 * 
 * */
@Controller
@RequestMapping(value = "/dealer/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.DEALER)
@NeedDRole({ DRole.DEALER })
public class DealerManagermentController {
	@Resource
	private DealerManagementService dealerManagementService;

	private Parser parser = new SimpleParser();

	/**
	 * 添加门店分类
	 * 
	 * @param categoryName
	 *            要添加的分类名
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storecategories", method = RequestMethod.POST)
	public String addNewStoreCategory(@RequestParam("categoryName") String categoryName) {
		Assert.isvalidCategoryName(categoryName);
		dealerManagementService.addNewStoreCategory(categoryName);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 删除门店分类。
	 * 
	 * @param name
	 *            要删除的分类名
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storecategories/{name}", method = RequestMethod.DELETE)
	public String deleteCatory(@PathVariable("name") String name) {
		dealerManagementService.deleteStoreCategory(name);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取所有门店分类。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storecategories", method = RequestMethod.GET)
	public String getCatories() {
		List<DealerStoreCategory> list = dealerManagementService.getStoreCategories();
		Converter<DealerStoreCategory, DealerStoreCategoryDTO> converter = DTOContext.getConverter(DealerStoreCategoryDTO.class);
		return new JsonResponseBean(converter.convert(list)).toJson();
	}

	/**
	 * 重命名分类。
	 * 
	 * @param name
	 * @param newName
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/storecategories/{name}", method = RequestMethod.PUT)
	public String renameCategory(@PathVariable("name") String name, @RequestParam("newName") String newName) {
		Assert.isvalidCategoryName(newName);
		dealerManagementService.renameStoreCategory(name, newName);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 批量移动门店到分类。
	 * 
	 * @param name
	 * @param storesMoveIn
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/movetocategory/{name}", method = RequestMethod.PUT)
	public String moveToCategory(@PathVariable("name") String name, @RequestParam("storesMoveIn") String storesMoveIn) {
		List<Long> storeIds = null;
		try {
			storeIds = parser.parseJSON(storesMoveIn, new TypeToken<List<Long>>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException("storesMoveIn参数格式错误");
		}
		dealerManagementService.moveToCategory(name, storeIds);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取我的所有门店的分组数据。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	public String getStoresGroupByCategory() {
		Map<String, List<Store>> data = dealerManagementService.getStoresGroupByCategory();
		Converter<Store, StoreDTO> converter = DTOContext.getConverter(StoreDTO.class);
		return new JsonResponseBean(converter.convert(data)).toJson();
	}

	/**
	 * 根据时间获取我的收入支出数据
	 * 
	 * @param startDate
	 * @param endDate
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bills/all", method = RequestMethod.GET)
	public String getMonthIncomeAndExpenditures(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM");
			end = DateUtil.parseDate(endDate, "yyyy-MM");
		} catch (Exception e) {
			throw new IllegalArgumentException("时间格式yyyy-MM");
		}
		List<MonthIncomeAndExpenditure> list = dealerManagementService.getMonthIncomeAndExpenditures(start, end);
		return new JsonResponseBean(new MonthIncomeAndExpenditureDTO(list)).toJson();
	}

}
