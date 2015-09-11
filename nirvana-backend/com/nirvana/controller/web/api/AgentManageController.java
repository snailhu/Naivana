package com.nirvana.controller.web.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.DealerDTO;
import com.nirvana.controller.dto.InventoriesCheckHistoryDTO;
import com.nirvana.controller.dto.VisitRouteDTO;
import com.nirvana.controller.dto.web.WebAgentAreaDTO;
import com.nirvana.controller.dto.web.WebAgentAreaSdoGoalDTO;
import com.nirvana.controller.dto.web.WebBigAreaDTO;
import com.nirvana.controller.dto.web.WebBigAreaGoalResultDTO;
import com.nirvana.controller.dto.web.WebDealerAllotDTO;
import com.nirvana.controller.dto.web.WebDirectAreaGoalResultDTO;
import com.nirvana.controller.dto.web.WebDirectorAreaDTO;
import com.nirvana.controller.dto.web.WebGlobalGoalResultDTO;
import com.nirvana.controller.dto.web.WebManagerAreaDTO;
import com.nirvana.controller.dto.web.WebManagerAreaGoalResultDTO;
import com.nirvana.controller.dto.web.WebPositionDTO;
import com.nirvana.controller.dto.web.WebSdoDTO;
import com.nirvana.controller.dto.web.WebSdoExcelDTO;
import com.nirvana.controller.dto.web.WebStoreAllotDTO;
import com.nirvana.controller.dto.web.WebStoreDTO;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.domain.backend.goal.sdo.AgentAreaSdoGoal;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.backend.goal.sdo.SdoExcel;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.store.Store;
import com.nirvana.mongo.document.VisitRecordDocument;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DPage;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.pojo.web.BigAreaGoalResult;
import com.nirvana.service.pojo.web.DirectAreaGoalResult;
import com.nirvana.service.pojo.web.GlobalGoalResult;
import com.nirvana.service.pojo.web.ManagerAreaGoalResult;
import com.nirvana.service.pojo.web.NodeSet;
import com.nirvana.service.pojo.web.WebProductivityStatsResult;
import com.nirvana.service.pojo.web.WebSalesVolumeStatsResult;
import com.nirvana.utils.DateUtil;

@Controller
@RequestMapping(value = "/backend/web/api", produces = "application/json;charset=utf-8")
public class AgentManageController {
	@Resource
	AgentManageService agentManageService;

	Parser parser = new SimpleParser();

	@ResponseBody
	@RequestMapping(value = "/area/directorareas/{id}/sonsdos", method = RequestMethod.GET)
	public String getAgentAreaSdoGoals(@PathVariable("id") int directAreaId, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (year <= 1700 || year > 9999 || month <= 0 || month > 12) {
			throw new IllegalArgumentException("year，month参数错误");
		}
		List<AgentAreaSdoGoal> list = agentManageService.getAgentAreaSdoGoals(directAreaId, year, month);
		Converter<AgentAreaSdoGoal, WebAgentAreaSdoGoalDTO> converter = DTOContext.getConverter(WebAgentAreaSdoGoalDTO.class);
		return new JsonResponseBean(converter.convert(list)).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/dealers/both", method = RequestMethod.GET)
	public String getAgentAreaDealersBoth(@PathVariable("id") int agentAreaId) {
		List<Dealer> dealers = agentManageService.getAgentAreaDealers(agentAreaId);
		List<Dealer> dealersCanAllot = agentManageService.getCanAllotedDealers(agentAreaId);
		return new JsonResponseBean(new WebDealerAllotDTO(dealers, dealersCanAllot)).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/directstores/both", method = RequestMethod.GET)
	public String getAgentAreaDirectStoresBoth(@PathVariable("id") int agentAreaId) {
		List<Dealer> dealers = agentManageService.getAgentAreaDirectStores(agentAreaId);
		List<Dealer> dealersCanAllot = agentManageService.getCanAllotedDirectStores(agentAreaId);
		return new JsonResponseBean(new WebDealerAllotDTO(dealers, dealersCanAllot)).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/stores/both", method = RequestMethod.GET)
	public String getAgentAreaStoresBoth(@PathVariable("id") int agentAreaId) {
		List<Store> stores = agentManageService.getAgentAreaStores(agentAreaId);
		List<Store> storesCanAllot = agentManageService.getCanAllotedStores(agentAreaId);
		return new JsonResponseBean(new WebStoreAllotDTO(stores, storesCanAllot)).toJson();
	}

	/**
	 * 获取此小区已管理的经销商。
	 * 
	 * 权限：文员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/dealers", method = RequestMethod.GET)
	public String getAgentAreaDealers(@PathVariable("id") int agentAreaId) {
		List<Dealer> dealers = agentManageService.getAgentAreaDealers(agentAreaId);
		Converter<Dealer, DealerDTO> converter = DTOContext.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealers)).toJson();
	}

	/**
	 * 获取可分配给此小区的经销商。
	 * 
	 * 权限：文员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/dealers/canallot", method = RequestMethod.GET)
	public String getCanAllotedDealers(@PathVariable("id") int agentAreaId) {
		List<Dealer> dealers = agentManageService.getCanAllotedDealers(agentAreaId);
		Converter<Dealer, DealerDTO> converter = DTOContext.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealers)).toJson();
	}

	/**
	 * 获取此小区已管理的直营店。
	 * 
	 * 权限：文员 。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/directstores", method = RequestMethod.GET)
	public String getAgentAreaDirectStores(@PathVariable("id") int agentAreaId) {
		List<Dealer> dealers = agentManageService.getAgentAreaDirectStores(agentAreaId);
		Converter<Dealer, DealerDTO> converter = DTOContext.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealers)).toJson();
	}

	/**
	 * 获取可分配给此小区的直营店。
	 * 
	 * 权限：文员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/directstores/canallot", method = RequestMethod.GET)
	public String getCanAllotedDirectStores(@PathVariable("id") int agentAreaId) {
		List<Dealer> dealers = agentManageService.getCanAllotedDirectStores(agentAreaId);
		Converter<Dealer, DealerDTO> converter = DTOContext.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealers)).toJson();
	}

	/**
	 * 获取此小区已管理的小店。
	 * 
	 * 权限：文员 。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/stores", method = RequestMethod.GET)
	public String getAgentAreaStores(@PathVariable("id") int agentAreaId) {
		List<Store> stores = agentManageService.getAgentAreaStores(agentAreaId);
		Converter<Store, WebStoreDTO> converter = DTOContext.getConverter(WebStoreDTO.class);
		return new JsonResponseBean(converter.convert(stores)).toJson();
	}

	/**
	 * 获取可分配给此小区的小店。
	 * 
	 * 权限：文员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/stores/canallot", method = RequestMethod.GET)
	public String getCanAllotedStores(@PathVariable("id") int agentAreaId) {
		List<Store> stores = agentManageService.getCanAllotedStores(agentAreaId);
		Converter<Store, WebStoreDTO> converter = DTOContext.getConverter(WebStoreDTO.class);
		return new JsonResponseBean(converter.convert(stores)).toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/area/directorareas/managed", method = RequestMethod.GET)
	public String getManagedDirectorArea() {
		List<DirectorArea> list = agentManageService.getManagedDirectorArea();
		Converter<DirectorArea, WebDirectorAreaDTO> converter = DTOContext.getConverter(WebDirectorAreaDTO.class);
		return new JsonResponseBean(converter.convert(list)).toJson();
	}

	/**
	 * 获取管理的职位。（SIS,大区经理,经理区经理,主任区经理）
	 * 
	 * 权限：SIS,大区经理，经理区经理，主任区经理。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/haspositions", method = RequestMethod.GET)
	public String getHasPositions() {
		List<Position> list = agentManageService.getHasPositions();
		Converter<Position, WebPositionDTO> converter = DTOContext.getConverter(WebPositionDTO.class);
		return new JsonResponseBean(converter.convert(list)).toJson();
	}

	/**
	 * 获取分页的小区数据。
	 * 
	 * 权限：SIS,文员。
	 * 
	 * @param employeeId
	 *            操作者员工ID，如果操作者为SIS,那么将看到所有小区，如果操作者为文员，那么将看到自己下面的小区。
	 * @param page
	 *            页数
	 * @param size
	 *            分页大小
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas", method = RequestMethod.GET)
	public String getAgentAreas(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<AgentArea> pArea = agentManageService.getAgentAreas(page, size);
		Converter<AgentArea, WebAgentAreaDTO> converter = DTOContext.getConverter(WebAgentAreaDTO.class);
		return new JsonResponseBean(converter.convert(pArea)).toJson();
	}

	/**
	 * 关闭小区。
	 * 
	 * 权限：SIS。
	 * 
	 * @param agentAreaId
	 *            小区ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/close", method = RequestMethod.PUT)
	public String closeAgentArea(@PathVariable("id") int agentAreaId) {
		agentManageService.closeAgentArea(agentAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 重新激活小区。
	 * 
	 * 权限：SIS。
	 * 
	 * @param agentAreaId
	 *            小区ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/active", method = RequestMethod.PUT)
	public String activateAgentArea(@PathVariable("id") int agentAreaId) {
		agentManageService.activateAgentArea(agentAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 编辑小区，给小区指定业务员。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * @param agentAreaId
	 *            小区ID
	 * @param ECode
	 *            员工编码
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/edit", method = RequestMethod.PUT)
	public String editAgentArea(@PathVariable("id") int agentAreaId, @RequestParam("eCode") String ECode) {
		agentManageService.editAgentArea(agentAreaId, ECode);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 创建新小区。
	 * 
	 * 权限：SIS。
	 * 
	 * @param areaCode
	 *            小区编号
	 * @param directorAreaId
	 *            所属的主任区
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas", method = RequestMethod.POST)
	public String createAgentArea(
			@RequestParam("areaCode") String areaCode,
			@RequestParam("type") String type,
			@RequestParam("areaName") String areaName,
			@RequestParam("directorAreaId") int directorAreaId) {
		WorkType workType;
		try {
			workType = WorkType.valueOf(type);
		} catch (Exception e) {
			throw new IllegalArgumentException("WorkType参数解析错误。");
		}
		agentManageService.createAgentArea(areaCode, areaName, workType, directorAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 给小区分配经销商。
	 * 
	 * 权限：文员。
	 * 
	 * @param agentAreaId
	 * @param outIds
	 * @param inIds
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/allotdealer", method = RequestMethod.PUT)
	public String allotDealersToAgentArea(
			@PathVariable("id") int agentAreaId,
			@RequestParam(value = "outIds", required = false) String outIds,
			@RequestParam(value = "inIds", required = false) String inIds) {
		List<Long> out = null;
		List<Long> in = null;
		if (outIds != null) {
			out = parser.parseJSON(outIds, new TypeToken<List<Long>>() {});
		}
		if (inIds != null) {
			in = parser.parseJSON(inIds, new TypeToken<List<Long>>() {});
		}
		agentManageService.allotDealersToAgentArea(agentAreaId, out, in);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 给小区分配直营店。
	 * 
	 * 权限：文员。
	 * 
	 * @param agentAreaId
	 * @param outIds
	 * @param inIds
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/allotdirectstores", method = RequestMethod.PUT)
	public String allotDirectStoresToAgentArea(
			@PathVariable("id") int agentAreaId,
			@RequestParam(value = "outIds", required = false) String outIds,
			@RequestParam(value = "inIds", required = false) String inIds) {
		List<Long> out = null;
		List<Long> in = null;
		if (outIds != null) {
			out = parser.parseJSON(outIds, new TypeToken<List<Long>>() {});
		}
		if (inIds != null) {
			in = parser.parseJSON(inIds, new TypeToken<List<Long>>() {});
		}
		agentManageService.allotDirectStoresToAgentArea(agentAreaId, out, in);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 给小区分配门店。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/allotstores", method = RequestMethod.PUT)
	public String allotStoresToAgentArea(
			@PathVariable("id") int agentAreaId,
			@RequestParam(value = "outIds", required = false) String outIds,
			@RequestParam(value = "inIds", required = false) String inIds) {
		List<Long> out = null;
		List<Long> in = null;
		if (outIds != null) {
			out = parser.parseJSON(outIds, new TypeToken<List<Long>>() {});
		}
		if (inIds != null) {
			in = parser.parseJSON(inIds, new TypeToken<List<Long>>() {});
		}
		agentManageService.allotStoresToAgentArea(agentAreaId, out, in);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 给所有大区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并且版本号+1。
	 * 
	 * 权限：SIS。
	 * 
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            目标金额
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/bigarea/goal", method = RequestMethod.PUT)
	public String setBigAreaNrGoal(@RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("nrs") String nrs) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setBigAreaNrGoal(year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 根据年月份给自己管辖下的所有经理区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并将版本号设为与父记录相同。
	 * 
	 * 权限：大区经理（UM）。
	 * 
	 * @param employeeId
	 *            分配目标的大区经理ID（操作者）
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            保存了操作者下属所有经理区的nr值的map,key为经理区ID,value为NR值。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/managerarea/goal", method = RequestMethod.PUT)
	public String setManagerAreaNrGoals(@RequestParam("bigAreaId") int bigAreaId, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("nrs") String nrs) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setManagerAreaNrGoals(bigAreaId, year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 根据年月份给自己管辖下的所有主任区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并将版本号设为与父记录相同。
	 * 
	 * 权限：经理区经理（TDM）。
	 * 
	 * @param employeeId
	 *            分配目标的经理区经理ID（操作者）
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            保存了操作者下属所有主任区的nr值的map,key为主任区ID,value为NR值。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/directorarea/goal", method = RequestMethod.PUT)
	public String setDirectorAreaNrGoals(
			@RequestParam("managerAreaId") int managerAreaId,
			@RequestParam("year") int year,
			@RequestParam("month") int month,
			@RequestParam("nrs") String nrs) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setDirectorAreaNrGoals(managerAreaId, year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 根据年月份给自己管辖下的所有CR区设定销量目标，如果记录不存在，则创建记录，如果记录存在则修改记录，并将版本号设为与父记录相同。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            分配目标的主任区经理ID（操作者）
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @param nrs
	 *            保存了操作者下属所有CR区的nr值的map,key为CR区ID,value为NR值。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentarea/goal", method = RequestMethod.PUT)
	public String setAgentAreaNrGoals(
			@RequestParam("directAreaId") int directAreaId,
			@RequestParam("year") int year,
			@RequestParam("month") int month,
			@RequestParam("nrs") String nrs) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setAgentAreaNrGoals(directAreaId, year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * SIS获取几个大区的销量目标。
	 * 
	 * 权限:（SIS）。
	 * 
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/globalgoal", method = RequestMethod.GET)
	public String getGlobalGoal(@RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		GlobalGoalResult result = agentManageService.getGlobalGoal(year, month);
		Converter<GlobalGoalResult, WebGlobalGoalResultDTO> converter = DTOContext.getConverter(WebGlobalGoalResultDTO.class);
		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * 大区经理获取大区的销量目标。
	 * 
	 * 权限:大区经理（UM）。
	 * 
	 * @param employeeId
	 *            大区经理ID（操作者）
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/bigarea/{id}/goal", method = RequestMethod.GET)
	public String getBigAreaGoal(@PathVariable("id") int bigAreaId, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		BigAreaGoalResult result = agentManageService.getBigAreaGoal(bigAreaId, year, month);
		Converter<BigAreaGoalResult, WebBigAreaGoalResultDTO> converter = DTOContext.getConverter(WebBigAreaGoalResultDTO.class);

		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * 经理区经理获取经理区的销量目标。
	 * 
	 * 权限：经理区经理（TDM）。
	 * 
	 * @param employeeId
	 *            经理区经理ID（操作者）
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/managerarea/{id}/goal", method = RequestMethod.GET)
	public String getManagerAreaNrGoal(@PathVariable("id") int managerAreaId, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		ManagerAreaGoalResult result = agentManageService.getManagerAreaNrGoal(managerAreaId, year, month);
		Converter<ManagerAreaGoalResult, WebManagerAreaGoalResultDTO> converter = DTOContext.getConverter(WebManagerAreaGoalResultDTO.class);
		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * 主任区经理获取主任区的销量目标。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任区主任ID（操作者）
	 * @param date
	 *            日期
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/directorarea/{id}/goal", method = RequestMethod.GET)
	public String getDirectorAreaGoal(@PathVariable("id") int directAreaId, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		DirectAreaGoalResult result = agentManageService.getDirectorAreaGoal(directAreaId, year, month);
		Converter<DirectAreaGoalResult, WebDirectAreaGoalResultDTO> converter = DTOContext.getConverter(WebDirectAreaGoalResultDTO.class);
		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * 设置某月的SDO目标。
	 * 
	 * 权限：SIS
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param file
	 *            上传的EXCEL
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdo", method = RequestMethod.POST)
	public String setMonthSdo(@RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("file") MultipartFile file) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		String fileName = file.getOriginalFilename();
		String fileSuffix = fileName.split("\\.")[1];
		if (("xls".equals(fileSuffix)) || ("xlsx".equals(fileSuffix))) {
			agentManageService.setMonthSdo(year, month, file);
			return JsonResponseBean.getSuccessResponse().toJson();
		} else {
			throw new IllegalArgumentException("传入文件格式错误");
		}

	}

	/**
	 * 获取年月的SdoExcel.
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdo/excel", method = RequestMethod.GET)
	public String getSdoExcel(@RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		SdoExcel excel = agentManageService.getSdoExcel(year, month);
		Converter<SdoExcel, WebSdoExcelDTO> converter = DTOContext.getConverter(WebSdoExcelDTO.class);
		return new JsonResponseBean(converter.convert(excel)).toJson();
	}

	/**
	 * 获取某年月的SDO目标。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdos", method = RequestMethod.GET)
	public String getSdos(@RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		List<Sdo> sdos = agentManageService.getSdos(year, month);
		Converter<Sdo, WebSdoDTO> converter = DTOContext.getConverter(WebSdoDTO.class);
		return new JsonResponseBean(converter.convert(sdos)).toJson();
	}

	/**
	 * 主任给CR区分配SDO目标。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任的员工ID。
	 * @param agentAreaId
	 *            要分配的CR区ID
	 * @param sdoIds
	 *            分配的SDO。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdo/agentareas/{id}", method = RequestMethod.PUT)
	public String setSdos(@PathVariable("id") int agentAreaId, @RequestParam("sdoIds") String sdoIds, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		List<Long> sdoIdList = parser.parseJSON(sdoIds, new TypeToken<List<Long>>() {});
		agentManageService.setSdos(agentAreaId, sdoIdList, year, month);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取主任下面的所有CR区。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任的员工ID（操作者）
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/directorareas/{id}/agentareas", method = RequestMethod.GET)
	public String getDirectorAgentAreas(@PathVariable("id") int directAreaId) {
		List<AgentArea> agentAreas = agentManageService.getDirectorAgentAreas(directAreaId);
		Converter<AgentArea, WebAgentAreaDTO> converter = DTOContext.getConverter(WebAgentAreaDTO.class);
		return new JsonResponseBean(converter.convert(agentAreas)).toJson();
	}

	/**
	 * 获取路线。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任区主任。
	 * @param agentAreaId
	 *            CR区ID
	 * @param routeCode
	 *            线路代号
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/visitroutes/{routeid}", method = RequestMethod.GET)
	public String getVisitRoute(@PathVariable("id") int agentAreaId, @PathVariable("routeid") int routeCode) {
		VisitRoute visitRoute = agentManageService.getVisitRoute(agentAreaId, routeCode);
		Converter<VisitRoute, VisitRouteDTO> converter = DTOContext.getConverter(VisitRouteDTO.class);
		return new JsonResponseBean(converter.convert(visitRoute)).toJson();
	}

	/**
	 * 获取所有不在此路线中的门店。
	 * 
	 * 权限：主任区主任(TDS)。
	 * 
	 * @param employeeId
	 *            操作的主任ID
	 * @param agentAreaId
	 *            要操作的CR区ID
	 * @param routeCode
	 *            操作的线路代号
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/visitroutes/{routeid}/storesnotin", method = RequestMethod.GET)
	public String getNotInThisRouteStores(
			@PathVariable("id") int agentAreaId,
			@PathVariable("routeid") int routeCode,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Store> stores = agentManageService.getNotInThisRouteStores(agentAreaId, routeCode, page, size);
		Converter<Store, WebStoreDTO> converter = DTOContext.getConverter(WebStoreDTO.class);
		return new JsonResponseBean(converter.convert(stores)).toJson();
	}

	/**
	 * 获取所有不在此路线中的直营店。
	 * 
	 * 权限：主任区主任(TDS)。
	 * 
	 * @param employeeId
	 *            操作的主任ID
	 * @param agentAreaId
	 *            要操作的CR区ID
	 * @param routeCode
	 *            操作的线路代号
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/visitroutes/{routeid}/dealersnotin", method = RequestMethod.GET)
	public String getNotInThisRouteDealers(
			@PathVariable("id") int agentAreaId,
			@PathVariable("routeid") int routeCode,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Dealer> dealers = agentManageService.getNotInThisRouteDealers(agentAreaId, routeCode, page, size);
		Converter<Dealer, DealerDTO> converter = DTOContext.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealers)).toJson();
	}

	/**
	 * 主任给业务员分配路线。
	 * 
	 * 权限：主任区主任（TDS）。
	 * 
	 * @param employeeId
	 *            主任区主任员工ID（操作者）
	 * @param agentAreaId
	 *            CR区的ID
	 * @param routeCode
	 *            线路代号
	 * @param sets
	 *            包装了门店类型和ID的参数。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/visitroutes/{routeid}", method = RequestMethod.POST)
	public String setAgentVisitRoute(@PathVariable("id") int agentAreaId, @PathVariable("routeid") int routeCode, @RequestParam("nodesets") String sets) {
		List<NodeSet> nodeSets = parser.parseJSON(sets, new TypeToken<List<NodeSet>>() {});
		agentManageService.setAgentVisitRoute(agentAreaId, routeCode, nodeSets);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 根据区域筛选选项获取某年月到某年月的销量统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param startYear
	 *            开始年份
	 * @param startMonth
	 *            开始月份
	 * @param endYear
	 *            结束年份
	 * @param endMonth
	 *            结束月份
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/WebSalesVolumeStatsResult/month", method = RequestMethod.GET)
	public String getSalesVolStatisResultsByMonthToMonth(
			@RequestParam("startYear") int startYear,
			@RequestParam("startMonth") int startMonth,
			@RequestParam("endYear") int endYear,
			@RequestParam("endMonth") int endMonth,
			@RequestParam(value = "bigAreaId", required = false) Integer bigAreaId,
			@RequestParam(value = "manageAreaId", required = false) Integer manageAreaId,
			@RequestParam(value = "dirAreaId", required = false) Integer dirAreaId,
			@RequestParam(value = "agentAreaId", required = false) Integer agentAreaId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (startMonth > 12 || startMonth < 1 || endMonth > 12 || endMonth < 1) {
			throw new IllegalArgumentException("month错误");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId不能全为空");
		}
		Page<WebSalesVolumeStatsResult> result = agentManageService.getSalesVolStatisResultsByMonthToMonth(startYear, startMonth, endYear, endMonth, bigAreaId, manageAreaId,
				dirAreaId, agentAreaId, page, size);
		// TODO
		List<WebSalesVolumeStatsResult> l = new ArrayList<WebSalesVolumeStatsResult>();
		result = new PageImpl<WebSalesVolumeStatsResult>(l, new PageRequest(1, 10), 1);

		DPage<WebSalesVolumeStatsResult> dp = new DPage<WebSalesVolumeStatsResult>(result);
		return new JsonResponseBean(dp).toJson();
	}

	/**
	 * 根据区域筛选选项获取本月某天到某天的销量统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param startDay
	 *            起始天
	 * @param endDay
	 *            结束天
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/WebSalesVolumeStatsResult/day", method = RequestMethod.GET)
	public String getSalesVolStatisResultsByDayToDay(
			@RequestParam("startDay") int startDay,
			@RequestParam("endDay") int endDay,
			@RequestParam(value = "bigAreaId", required = false) Integer bigAreaId,
			@RequestParam(value = "manageAreaId", required = false) Integer manageAreaId,
			@RequestParam(value = "dirAreaId", required = false) Integer dirAreaId,
			@RequestParam(value = "agentAreaId", required = false) Integer agentAreaId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (startDay > 31 || startDay < 1 || endDay > 31 || endDay < 1) {
			throw new IllegalArgumentException("day错误");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId不能全为空");
		}
		Page<WebSalesVolumeStatsResult> result = agentManageService.getSalesVolStatisResultsByDayToDay(startDay, endDay, bigAreaId, manageAreaId, dirAreaId, agentAreaId, page,
				size);
		// TODO ...
		List<WebSalesVolumeStatsResult> l = new ArrayList<WebSalesVolumeStatsResult>();
		result = new PageImpl<WebSalesVolumeStatsResult>(l, new PageRequest(1, 10), 1);
		DPage<WebSalesVolumeStatsResult> dp = new DPage<WebSalesVolumeStatsResult>(result);
		return new JsonResponseBean(dp).toJson();
	}

	/**
	 * 根据区域筛选选项获取某年月到某年月的生产力统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param startYear
	 *            开始年份
	 * @param startMonth
	 *            开始月份
	 * @param endYear
	 *            结束年份
	 * @param endMonth
	 *            结束月份
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/WebProductivityStatsResult/month", method = RequestMethod.GET)
	public String getSalesProducStatsResultsByMonthToMonth(
			@RequestParam("startYear") int startYear,
			@RequestParam("startMonth") int startMonth,
			@RequestParam("endYear") int endYear,
			@RequestParam("endMonth") int endMonth,
			@RequestParam(value = "bigAreaId", required = false) Integer bigAreaId,
			@RequestParam(value = "manageAreaId", required = false) Integer manageAreaId,
			@RequestParam(value = "dirAreaId", required = false) Integer dirAreaId,
			@RequestParam(value = "agentAreaId", required = false) Integer agentAreaId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (startMonth > 12 || startMonth < 1 || endMonth > 12 || endMonth < 1) {
			throw new IllegalArgumentException("month错误");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId不能全为空");
		}
		Page<WebProductivityStatsResult> result = agentManageService.getSalesProducStatsResultsByMonthToMonth(startYear, startMonth, endYear, endMonth, bigAreaId, manageAreaId,
				dirAreaId, agentAreaId, page, size);
		// TODO ...
		List<WebProductivityStatsResult> l = new ArrayList<WebProductivityStatsResult>();
		result = new PageImpl<WebProductivityStatsResult>(l, new PageRequest(1, 10), 1);

		DPage<WebProductivityStatsResult> dp = new DPage<WebProductivityStatsResult>(result);
		return new JsonResponseBean(dp).toJson();
	}

	/**
	 * 根据区域筛选选项获取某月某天到某天的生产力统计。其中区域ID参数可以为NULL，但是不能全为NULL。
	 * 
	 * 权限：公司的所有员工。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param month
	 *            某月
	 * @param startDay
	 *            开始天
	 * @param endDay
	 *            结束天
	 * @param bigAreaId
	 *            大区ID
	 * @param manageAreaId
	 *            经理区ID
	 * @param dirAreaId
	 *            主任区ID
	 * @param agentAreaId
	 *            CR区ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/WebProductivityStatsResult/day", method = RequestMethod.GET)
	public String getSalesProducStatsResultsByDayToDay(
			@RequestParam("month") int month,
			@RequestParam("startDay") int startDay,
			@RequestParam("endDay") int endDay,
			@RequestParam(value = "bigAreaId", required = false) Integer bigAreaId,
			@RequestParam(value = "manageAreaId", required = false) Integer manageAreaId,
			@RequestParam(value = "dirAreaId", required = false) Integer dirAreaId,
			@RequestParam(value = "agentAreaId", required = false) Integer agentAreaId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month错误");
		}
		if (startDay > 31 || startDay < 1 || endDay > 31 || endDay < 1) {
			throw new IllegalArgumentException("day错误");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId不能全为空");
		}
		Page<WebProductivityStatsResult> result = agentManageService.getSalesProducStatsResultsByDayToDay(startDay, endDay, bigAreaId, manageAreaId, dirAreaId, agentAreaId, page,
				size);
		// TODO ...
		List<WebProductivityStatsResult> l = new ArrayList<WebProductivityStatsResult>();
		result = new PageImpl<WebProductivityStatsResult>(l, new PageRequest(1, 10), 1);
		DPage<WebProductivityStatsResult> dp = new DPage<WebProductivityStatsResult>(result);
		return new JsonResponseBean(dp).toJson();
	}

	/**
	 * 获取拜访记录。
	 * 
	 * @param employeeId
	 *            此查询操作者ID
	 * @param agentId
	 *            业务员ID
	 * @param startDate
	 *            开始日期，形式为8位int,例如20141015表示日期2014.10.15
	 * @param endDate
	 *            结束日期，形式为8位int,例如20141015表示日期2014.10.15
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/visitrecords", method = RequestMethod.GET)
	public String getVisitRecords(
			@PathVariable("id") int agentAreaId,
			@RequestParam("startDate") int startDate,
			@RequestParam("endDate") int endDate,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		return null;
	}

	/**
	 * 获取所有大区。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/bigareas", method = RequestMethod.GET)
	public String getAllBigAreas() {
		List<BigArea> bigAreas = agentManageService.getAllBigAreas();
		Converter<BigArea, WebBigAreaDTO> converter = DTOContext.getConverter(WebBigAreaDTO.class);
		return new JsonResponseBean(converter.convert(bigAreas)).toJson();
	}

	/**
	 * 获取此大区ID的所有经理区。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/bigareas/{id}/managerareas", method = RequestMethod.GET)
	public String getManagerAreas(@PathVariable("id") int bigAreaId) {
		List<ManagerArea> managerAreas = agentManageService.getManagerAreas(bigAreaId);
		Converter<ManagerArea, WebManagerAreaDTO> converter = DTOContext.getConverter(WebManagerAreaDTO.class);
		return new JsonResponseBean(converter.convert(managerAreas)).toJson();
	}

	/**
	 * 获取此经理区下的所有主任区。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/managerareas/{id}/directareas", method = RequestMethod.GET)
	public String getDirectorAreas(@PathVariable("id") int managerAreaId) {
		List<DirectorArea> directorAreas = agentManageService.getDirectorAreas(managerAreaId);
		Converter<DirectorArea, WebDirectorAreaDTO> converter = DTOContext.getConverter(WebDirectorAreaDTO.class);
		return new JsonResponseBean(converter.convert(directorAreas)).toJson();
	}

	/**
	 * 获取此主任区下的所有CR区。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/directareas/{id}/agentareas", method = RequestMethod.GET)
	public String getAgentAreas(@PathVariable("id") int directAreaId) {
		List<AgentArea> agentAreas = agentManageService.getAgentAreas(directAreaId);
		Converter<AgentArea, WebAgentAreaDTO> converter = DTOContext.getConverter(WebAgentAreaDTO.class);
		return new JsonResponseBean(converter.convert(agentAreas)).toJson();
	}

	/**
	 * 获取所有WorkType。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/worktypes", method = RequestMethod.GET)
	public String getWorkTypes() {
		WorkType[] types = WorkType.values();
		return new JsonResponseBean(types).toJson();
	}

	@SuppressWarnings("deprecation")
	/**
	 * 库存盘点记录
	 */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/inventoryhistories", method = RequestMethod.GET)
	public String getInventoryHistoeies(
			@PathVariable("id") Long id,
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
		} catch (ParseException e) {
			throw new IllegalArgumentException("时间格式yyyy-MM-dd");
		}

		Page<InventoriesCheckHistory> histories = agentManageService.getInventoriesHistory(id, start, end, page, size);
		Converter<InventoriesCheckHistory, InventoriesCheckHistoryDTO> converter = DTOContext.getConverter(InventoriesCheckHistoryDTO.class);
		return new JsonResponseBean(converter.convert(histories)).toJson();
	}
}
