package com.nirvana.controller.app;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.nirvana.controller.dto.InventoryDTO;
import com.nirvana.controller.dto.app.AgentDealerCategoryDTO;
import com.nirvana.controller.dto.app.AgentStoreCategoryDTO;
import com.nirvana.controller.dto.app.AppSdoDTO;
import com.nirvana.controller.dto.app.BEAppDealerDTO;
import com.nirvana.controller.dto.app.BEAppStoreDTO;
import com.nirvana.controller.dto.app.CheckInventDealerDTO;
import com.nirvana.controller.dto.app.InventoriesCheckHistoryDTO;
import com.nirvana.controller.dto.app.MemorandumDTO;
import com.nirvana.controller.dto.web.WebAgentAreaDTO;
import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.Memorandum;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.AgentWorkService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.pojo.app.AppAgentNrGoalResult;
import com.nirvana.service.pojo.app.AppProductivityStatsResult;
import com.nirvana.service.pojo.app.AppSalesVolumeStatsResult;
import com.nirvana.service.pojo.app.InventoryChange;
import com.nirvana.utils.Assert;
import com.nirvana.utils.DateUtil;

/**
 * 业务员的杂务工作。
 * 
 */
@Controller
@RequestMapping(value = "/backend/app", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.BACKEND)
@NeedERole({ ERole.AGENT })
public class AgentChoresController {
	@Resource
	private AgentWorkService agentWorkService;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;

	private SimpleParser parser = new SimpleParser();

	/**
	 * 获取本月目标面板数据。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentarea/{agentareaId}/goals", method = { RequestMethod.GET })
	@ResponseBody
	public String getThisMonthGoal(@PathVariable("agentareaId") int agentareaId) {
		AppAgentNrGoalResult result = agentWorkService.getNrGoal(agentareaId);
		return new JsonResponseBean(result).toJson();
	}

	/**
	 * 获取我管理的小区
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/agentareas/my", method = { RequestMethod.GET })
	public String getManageAgentAreas() {
		List<AgentArea> areas = agentWorkService.getManageAgentAreas();
		JsonResponseBean bean = new JsonResponseBean();
		Converter<AgentArea, WebAgentAreaDTO> converter = DTOContext
				.getConverter(WebAgentAreaDTO.class);
		bean.setData(converter.convert(areas));
		return bean.toJson();
	}

	/**
	 * 获取我的本月SDO目标。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/agentareas/{agentareaId}/sdos/thismonth", method = { RequestMethod.GET })
	public String getThisMonthSdoGoals(
			@PathVariable("agentareaId") int agentareaId) {
		List<Sdo> sdos = agentWorkService.getThisMonthSdos(agentareaId);
		JsonResponseBean bean = new JsonResponseBean();
		Converter<Sdo, AppSdoDTO> converter = DTOContext
				.getConverter(AppSdoDTO.class);
		bean.setData(converter.convert(sdos));
		return bean.toJson();
	}

	/**
	 * 设置我的本周目标。
	 * 
	 * @param agentareaId
	 * @param nr
	 *            目标金额
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/weekgoals/thisweek", method = { RequestMethod.PUT })
	@ResponseBody
	public String setThisWeekGoal(@PathVariable("agentareaId") int agentareaId,
			@RequestParam(required = true, value = "nr") float nr) {
		agentWorkService.setThisWeekGoals(agentareaId, nr);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 设置我的本日目标。
	 * 
	 * @param agentareaId
	 * @param nr
	 *            目标金额
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/daygoals/today", method = { RequestMethod.PUT })
	@ResponseBody
	public String setTodayGoal(@PathVariable("agentareaId") int agentareaId,
			@RequestParam(required = true, value = "nr") float nr) {
		agentWorkService.setThisDayGoals(agentareaId, nr);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取今日的销售量业绩板数据。 今日拜访--目标显示--3
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/salesvolumes/today", method = { RequestMethod.GET })
	@ResponseBody
	public String getTodaySalesVolumeData(
			@PathVariable("agentareaId") int agentareaId) {
		AppSalesVolumeStatsResult asvsr = agentWorkService
				.getSalesVolumeStatsResult(agentareaId);
		asvsr = new AppSalesVolumeStatsResult();
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(asvsr);
		return bean.toJson();
	}

	/**
	 * 获取今日的生产力业绩板数据。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/productivity/today", method = { RequestMethod.GET })
	@ResponseBody
	public String getTodayProductivityData(
			@PathVariable("agentareaId") int agentareaId) {
		AppProductivityStatsResult apsr = agentWorkService
				.getProductivityStatsResult(agentareaId);
		apsr = new AppProductivityStatsResult();
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(apsr);
		return bean.toJson();
	}

	/**
	 * ################################小店的分类###################################
	 */

	/**
	 * 获取我的小店分类。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/storecategories", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyStorecategories(
			@PathVariable("agentareaId") int agentareaId) {
		List<AgentStoreCategory> list = agentWorkService
				.getStoreCategories(agentareaId);
		JsonResponseBean bean = new JsonResponseBean();
		Converter<AgentStoreCategory, AgentStoreCategoryDTO> converter = DTOContext
				.getConverter(AgentStoreCategoryDTO.class);
		bean.setData(converter.convert(list));
		return bean.toJson();
	}

	/**
	 * 获取手下门店的分类数据。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/stores", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyStores(@PathVariable("agentareaId") int agentareaId) {
		Map<String, List<Store>> categoryStores = agentWorkService
				.getStoresGroupByCategories(agentareaId);
		Converter<Store, BEAppStoreDTO> converter = DTOContext
				.getConverter(BEAppStoreDTO.class);
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(categoryStores));
		return bean.toJson();
	}

	/**
	 * 根据分类获取我的所有门店。
	 * 
	 * @param agentareaId
	 * @param category
	 *            分类名
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/storecategories/{name}/stores", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyStoresByCategory(
			@PathVariable("agentareaId") int agentareaId,
			@PathVariable("name") String category) {
		Assert.isvalidCategoryName(category);
		List<Store> stores = agentWorkService.getStoresByCategory(agentareaId,
				category);
		Converter<Store, BEAppStoreDTO> converter = DTOContext
				.getConverter(BEAppStoreDTO.class);
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(stores));
		return bean.toJson();
	}

	/**
	 * 添加新的分类。
	 * 
	 * @param agentareaId
	 * @param category
	 *            要添加的门店名称
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/storecategories", method = { RequestMethod.POST })
	@ResponseBody
	public String createNewStoreCategory(
			@PathVariable("agentareaId") int agentareaId,
			@RequestParam(required = true, value = "category") String category) {
		Assert.isvalidCategoryName(category);
		agentWorkService.addNewStoreCategory(agentareaId, category);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 删除分类。
	 * 
	 * @param agentareaId
	 * @param name
	 *            要删除的分类名
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/storecategories/{name}", method = { RequestMethod.DELETE })
	@ResponseBody
	public String deleteStoreCategory(
			@PathVariable(value = "agentareaId") int agentareaId,
			@PathVariable("name") String name) {
		Assert.isvalidCategoryName(name);
		agentWorkService.deleteStoreCategory(agentareaId, name);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 重命名分类。
	 * 
	 * @param agentareaId
	 * @param name
	 * @param newName
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/storecategories/{name}", method = { RequestMethod.PUT })
	@ResponseBody
	public String renameStoreCategory(
			@PathVariable("agentareaId") int agentareaId,
			@PathVariable(value = "name") String name,
			@RequestParam(required = true, value = "newname") String newName) {
		Assert.isvalidCategoryName(name);
		Assert.isvalidCategoryName(newName);
		agentWorkService.renameStoreCategory(agentareaId, name, newName);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 批量移动门店到某个分类。
	 * 
	 * @param agentareaId
	 * @param name
	 * @param batch
	 *            批量移动的门店,json格式为：{"ids":["a","b","c"]}
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/stores/movetocategory/{name}", method = { RequestMethod.PUT })
	@ResponseBody
	public String moveStoreToCategory(
			@PathVariable("agentareaId") int agentareaId,
			@PathVariable("name") String name,
			@RequestParam(required = true, value = "batch") String batch) {
		Assert.isvalidCategoryName(name);
		Assert.hasLength(batch);
		List<Long> storeIds = null;
		try {
			storeIds = parser.parseJSON(batch, new TypeToken<List<Long>>() {
			});
		} catch (Exception e) {
			throw new IllegalArgumentException("batch:" + batch);
		}
		if (storeIds == null)
			throw new IllegalArgumentException(batch);
		agentWorkService.moveStoresToCategory(agentareaId, storeIds, name);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ################################直营店的分类###################################
	 */

	/**
	 * 获取我的直营店分类。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/dealercategories", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyDealerCategorys(
			@PathVariable("agentareaId") int agentareaId) {
		List<AgentDealerCategory> list = agentWorkService
				.getDealerCategories(agentareaId);
		JsonResponseBean bean = new JsonResponseBean();
		Converter<AgentDealerCategory, AgentDealerCategoryDTO> converter = DTOContext
				.getConverter(AgentDealerCategoryDTO.class);
		bean.setData(converter.convert(list));
		return bean.toJson();
	}

	/**
	 * 获取手下门店的分类数据。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/dealers", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyDealers(@PathVariable("agentareaId") int agentareaId) {
		Map<String, List<Dealer>> categoryDealers = agentWorkService
				.getDealersGroupByCategories(agentareaId);
		Converter<Dealer, BEAppDealerDTO> converter = DTOContext
				.getConverter(BEAppDealerDTO.class);
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(categoryDealers));
		return bean.toJson();
	}

	/**
	 * 根据分类获取我的所有门店。
	 * 
	 * @param agentareaId
	 * @param category
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/dealercategories/{name}/dealers", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyDealersByCategory(
			@PathVariable("agentareaId") int agentareaId,
			@PathVariable("name") String category) {
		Assert.isvalidCategoryName(category);
		List<Dealer> dealers = agentWorkService.getDealersByCategory(
				agentareaId, category);
		Converter<Dealer, BEAppDealerDTO> converter = DTOContext
				.getConverter(BEAppDealerDTO.class);
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(dealers));
		return bean.toJson();
	}

	/**
	 * 添加新的分类。
	 * 
	 * @param agentareaId
	 * @param category
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/dealercategories", method = { RequestMethod.POST })
	@ResponseBody
	public String createNewDealerCategory(
			@PathVariable("agentareaId") int agentareaId,
			@RequestParam(required = true, value = "category") String category) {
		Assert.isvalidCategoryName(category);
		agentWorkService.addNewDealerCategory(agentareaId, category);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 删除分类。
	 * 
	 * @param agentareaId
	 * @param name
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/dealercategories/{name}", method = { RequestMethod.DELETE })
	@ResponseBody
	public String deleteDealerCategory(
			@PathVariable(value = "agentareaId") int agentareaId,
			@PathVariable("name") String name) {
		Assert.isvalidCategoryName(name);
		agentWorkService.deleteDealerCategory(agentareaId, name);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 重命名分类。
	 * 
	 * @param agentareaId
	 * @param name
	 * @param newName
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/dealercategories/{name}", method = { RequestMethod.PUT })
	@ResponseBody
	public String reNameDealerCategory(
			@PathVariable("agentareaId") int agentareaId,
			@PathVariable(value = "name") String name,
			@RequestParam(required = true, value = "newname") String newName) {
		Assert.isvalidCategoryName(name);
		Assert.isvalidCategoryName(newName);
		agentWorkService.renameDealerCategory(agentareaId, name, newName);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 批量移动门店到某个分类。
	 * 
	 * @param agentareaId
	 * @param name
	 * @param batch
	 *            批量的移动的门店，格式为：{"ids":["a","b","c"]}
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/dealers/movetocategory/{name}", method = { RequestMethod.PUT })
	@ResponseBody
	public String moveDealerToCategory(
			@PathVariable("agentareaId") int agentareaId,
			@PathVariable("name") String name,
			@RequestParam(required = true, value = "batch") String batch) {
		Assert.isvalidCategoryName(name);
		Assert.hasLength(batch);
		List<Long> storeIds = null;
		try {
			storeIds = parser.parseJSON(batch, new TypeToken<List<Long>>() {
			});
		} catch (Exception e) {
			throw new IllegalArgumentException("batch:" + batch);
		}
		if (storeIds == null)
			throw new IllegalArgumentException(batch);
		agentWorkService.moveDealersToCategory(agentareaId, storeIds, name);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取我的所有备忘录。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/memorandums", method = { RequestMethod.GET })
	@ResponseBody
	public String getMyMemorandums(@PathVariable("agentareaId") int agentareaId) {
		List<Memorandum> memorandums = agentWorkService
				.getMemorandums(agentareaId);
		Converter<Memorandum, MemorandumDTO> converter = DTOContext
				.getConverter(MemorandumDTO.class);
		JsonResponseBean bean = new JsonResponseBean();
		bean.setData(converter.convert(memorandums));
		return bean.toJson();
	}

	/**
	 * 添加备忘录。
	 * 
	 * @param agentareaId
	 *            小区ID
	 * @param content
	 *            内容
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/memorandums", method = { RequestMethod.POST })
	@ResponseBody
	public String addMemorandum(@PathVariable("agentareaId") int agentareaId,
			@RequestParam(required = true, value = "content") String content) {
		Assert.lengthBetween(content, 1, 200);
		agentWorkService.addMemorandum(agentareaId, content);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 修改备忘录。
	 * 
	 * @param agentareaId
	 *            小区ID
	 * @param memorandumId
	 *            备忘录ID
	 * @param content
	 *            内容
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/memorandums/{memorandumId}", method = { RequestMethod.PUT })
	@ResponseBody
	public String editMemorandum(@PathVariable("agentareaId") int agentareaId,
			@PathVariable(value = "memorandumId") long memorandumId,
			@RequestParam(required = true, value = "content") String content) {
		Assert.lengthBetween(content, 1, 200);
		agentWorkService.editMemorandum(agentareaId, memorandumId, content);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 删除备忘录。
	 * 
	 * @param agentareaId
	 *            小区ID
	 * @param memorandumId
	 *            备忘录ID
	 * 
	 * */
	@RequestMapping(value = "/agentareas/{agentareaId}/memorandums/{memorandumId}", method = { RequestMethod.DELETE })
	@ResponseBody
	public String deleteMemorandum(
			@PathVariable("agentareaId") int agentareaId,
			@PathVariable(value = "memorandumId") long memorandumId) {
		agentWorkService.deleteMemorandum(agentareaId, memorandumId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 库存盘点。
	 * 
	 * @param dealerId
	 *            盘点的经销商ID
	 * @param changes
	 *            盘点的数据
	 * @param sign
	 *            经销商签字图片
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{dealerId}/inventories/check", method = { RequestMethod.POST })
	public String checkInventories(@PathVariable("dealerId") long dealerId,
			@RequestParam("changes") String changes,
			@RequestParam("sign") MultipartFile sign) {
		Assert.hasLength(changes);
		List<InventoryChange> list = null;
		try {
			list = parser.parseJSON(changes,
					new TypeToken<List<InventoryChange>>() {
					});
		} catch (Exception e) {
			throw new IllegalArgumentException("参数解析错误。");
		}
		for (InventoryChange c : list) {
			String[] strs = String.valueOf(c.getPrice()).split("\\.");
			if (strs.length > 1 && strs[1].length() > 2) {
				throw new IllegalArgumentException("价格最多为两位小数");
			}
		}
		agentWorkService.checkInventores(dealerId, list, sign);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取库存盘点记录。
	 * 
	 * @param dealerId
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{dealerId}/inventorycheckhistories", method = { RequestMethod.GET })
	public String getInventoryCheckHistory(
			@PathVariable("dealerId") long dealerId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {

		Date start;
		Date end;
		try {
			start = DateUtil.parseDate(startDate, "yyyy-MM-dd");
			end = DateUtil.parseDate(endDate, "yyyy-MM-dd");
			long time = end.getTime() + 24 * 60 * 60 * 1000;
			end = new Date(time);
		} catch (Exception e) {
			throw new IllegalArgumentException("时间格式yyyy-MM-dd");
		}

		Page<InventoriesCheckHistory> histories = agentWorkService
				.getCheckHistories(dealerId, start, end, page, size);
		Converter<InventoriesCheckHistory, InventoriesCheckHistoryDTO> converter = DTOContext
				.getConverter(InventoriesCheckHistoryDTO.class);
		JsonResponseBean bean = new JsonResponseBean(
				converter.convert(histories));
		return bean.toJson();
	}

	/**
	 * 获取业务员关联经销商。
	 * 
	 * @param agentareaId
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/agentareas/{agentareaId}/relationdealers", method = { RequestMethod.GET })
	public String getRelationDealers(
			@PathVariable("agentareaId") int agentareaId) {
		List<Dealer> dealers = agentWorkService.getRelationDealers(agentareaId);
		Converter<Dealer, CheckInventDealerDTO> converter = DTOContext
				.getConverter(CheckInventDealerDTO.class);
		JsonResponseBean bean = new JsonResponseBean(converter.convert(dealers));
		return bean.toJson();
	}

	/**
	 * 获取小店关联经销商的商品。
	 * 
	 * @param storeId
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/{storeId}/dealerProducts", method = { RequestMethod.GET })
	public String getStoresDealersProduct(@PathVariable("storeId") long storeId) {
		List<Inventory> inventories = agentWorkService
				.getStoresDealersProducts(storeId);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		JsonResponseBean bean = new JsonResponseBean(
				converter.convert(inventories));
		return bean.toJson();
	}

	/**
	 * 获取经销商库存。
	 * 
	 * @param dealerId
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{dealerId}/inventories", method = { RequestMethod.GET })
	public String getDealersForUpdate(
			@PathVariable("dealerId") long dealerId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Inventory> inventories = agentWorkService.getInventories(dealerId,
				page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		JsonResponseBean bean = new JsonResponseBean(
				converter.convert(inventories));
		return bean.toJson();
	}

	/**
	 * 根据品牌名获取经销商库存。
	 * 
	 * @param dealerId
	 * @param brandName
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{dealerId}/brands/{brandName}/inventories", method = { RequestMethod.GET })
	public String getDealersForUpdate(
			@PathVariable("dealerId") long dealerId,
			@PathVariable("brandName") String brandName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Inventory> inventories = agentWorkService.getInventoriesByBrand(
				dealerId, brandName, page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		JsonResponseBean bean = new JsonResponseBean(
				converter.convert(inventories));
		return bean.toJson();
	}

	/**
	 * 获取经销商库存。
	 * 
	 * @param dealerId
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{dealerId}/inventories/forupdate", method = { RequestMethod.GET })
	public String getInventoriesForUpdate(
			@PathVariable("dealerId") long dealerId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Inventory> inventories = agentWorkService.getInventories4Update(
				dealerId, page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		JsonResponseBean bean = new JsonResponseBean(
				converter.convert(inventories));
		return bean.toJson();
	}

	/**
	 * 根据品牌名获取经销商库存。
	 * 
	 * @param dealerId
	 * @param brandName
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{dealerId}/brands/{brandName}/inventories/forupdate", method = { RequestMethod.GET })
	public String getInventoriesForUpdate(
			@PathVariable("dealerId") long dealerId,
			@PathVariable("brandName") String brandName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Inventory> inventories = agentWorkService
				.getInventoriesByBrand4Update(dealerId, brandName, page, size);
		Converter<Inventory, InventoryDTO> converter = DTOContext
				.getConverter(InventoryDTO.class);
		JsonResponseBean bean = new JsonResponseBean(
				converter.convert(inventories));
		return bean.toJson();
	}

}
