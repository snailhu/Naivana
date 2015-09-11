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
			throw new IllegalArgumentException("year��month��������");
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
	 * ��ȡ��С���ѹ���ľ����̡�
	 * 
	 * Ȩ�ޣ���Ա��
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
	 * ��ȡ�ɷ������С���ľ����̡�
	 * 
	 * Ȩ�ޣ���Ա��
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
	 * ��ȡ��С���ѹ����ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա ��
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
	 * ��ȡ�ɷ������С����ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա��
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
	 * ��ȡ��С���ѹ����С�ꡣ
	 * 
	 * Ȩ�ޣ���Ա ��
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
	 * ��ȡ�ɷ������С����С�ꡣ
	 * 
	 * Ȩ�ޣ���Ա��
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
	 * ��ȡ�����ְλ����SIS,��������,����������,����������
	 * 
	 * Ȩ�ޣ�SIS,����������������������������
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
	 * ��ȡ��ҳ��С�����ݡ�
	 * 
	 * Ȩ�ޣ�SIS,��Ա��
	 * 
	 * @param employeeId
	 *            ������Ա��ID�����������ΪSIS,��ô����������С�������������Ϊ��Ա����ô�������Լ������С����
	 * @param page
	 *            ҳ��
	 * @param size
	 *            ��ҳ��С
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
	 * �ر�С����
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param agentAreaId
	 *            С��ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/close", method = RequestMethod.PUT)
	public String closeAgentArea(@PathVariable("id") int agentAreaId) {
		agentManageService.closeAgentArea(agentAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���¼���С����
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param agentAreaId
	 *            С��ID
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/active", method = RequestMethod.PUT)
	public String activateAgentArea(@PathVariable("id") int agentAreaId) {
		agentManageService.activateAgentArea(agentAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �༭С������С��ָ��ҵ��Ա��
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * @param agentAreaId
	 *            С��ID
	 * @param ECode
	 *            Ա������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/agentareas/{id}/edit", method = RequestMethod.PUT)
	public String editAgentArea(@PathVariable("id") int agentAreaId, @RequestParam("eCode") String ECode) {
		agentManageService.editAgentArea(agentAreaId, ECode);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ������С����
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param areaCode
	 *            С�����
	 * @param directorAreaId
	 *            ������������
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
			throw new IllegalArgumentException("WorkType������������");
		}
		agentManageService.createAgentArea(areaCode, areaName, workType, directorAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��С�����侭���̡�
	 * 
	 * Ȩ�ޣ���Ա��
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
	 * ��С������ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա��
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
	 * ��С�������ŵꡣ
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
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
	 * �����д����趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�����Ұ汾��+1��
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            Ŀ����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/bigarea/goal", method = RequestMethod.PUT)
	public String setBigAreaNrGoal(@RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("nrs") String nrs) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setBigAreaNrGoal(year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �������·ݸ��Լ���Ͻ�µ����о������趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�������汾����Ϊ�븸��¼��ͬ��
	 * 
	 * Ȩ�ޣ���������UM����
	 * 
	 * @param employeeId
	 *            ����Ŀ��Ĵ�������ID�������ߣ�
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            �����˲������������о�������nrֵ��map,keyΪ������ID,valueΪNRֵ��
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/managerarea/goal", method = RequestMethod.PUT)
	public String setManagerAreaNrGoals(@RequestParam("bigAreaId") int bigAreaId, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("nrs") String nrs) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setManagerAreaNrGoals(bigAreaId, year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �������·ݸ��Լ���Ͻ�µ������������趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�������汾����Ϊ�븸��¼��ͬ��
	 * 
	 * Ȩ�ޣ�����������TDM����
	 * 
	 * @param employeeId
	 *            ����Ŀ��ľ���������ID�������ߣ�
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            �����˲���������������������nrֵ��map,keyΪ������ID,valueΪNRֵ��
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
			throw new IllegalArgumentException("month����");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setDirectorAreaNrGoals(managerAreaId, year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �������·ݸ��Լ���Ͻ�µ�����CR���趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�������汾����Ϊ�븸��¼��ͬ��
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ����Ŀ�������������ID�������ߣ�
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            �����˲�������������CR����nrֵ��map,keyΪCR��ID,valueΪNRֵ��
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
			throw new IllegalArgumentException("month����");
		}
		Map<Integer, Float> nrsMap = parser.parseJSON(nrs, new TypeToken<Map<Integer, Float>>() {});
		agentManageService.setAgentAreaNrGoals(directAreaId, year, month, nrsMap);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * SIS��ȡ��������������Ŀ�ꡣ
	 * 
	 * Ȩ��:��SIS����
	 * 
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/globalgoal", method = RequestMethod.GET)
	public String getGlobalGoal(@RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		GlobalGoalResult result = agentManageService.getGlobalGoal(year, month);
		Converter<GlobalGoalResult, WebGlobalGoalResultDTO> converter = DTOContext.getConverter(WebGlobalGoalResultDTO.class);
		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * ���������ȡ����������Ŀ�ꡣ
	 * 
	 * Ȩ��:��������UM����
	 * 
	 * @param employeeId
	 *            ��������ID�������ߣ�
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/bigarea/{id}/goal", method = RequestMethod.GET)
	public String getBigAreaGoal(@PathVariable("id") int bigAreaId, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		BigAreaGoalResult result = agentManageService.getBigAreaGoal(bigAreaId, year, month);
		Converter<BigAreaGoalResult, WebBigAreaGoalResultDTO> converter = DTOContext.getConverter(WebBigAreaGoalResultDTO.class);

		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * �����������ȡ������������Ŀ�ꡣ
	 * 
	 * Ȩ�ޣ�����������TDM����
	 * 
	 * @param employeeId
	 *            ����������ID�������ߣ�
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/managerarea/{id}/goal", method = RequestMethod.GET)
	public String getManagerAreaNrGoal(@PathVariable("id") int managerAreaId, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		ManagerAreaGoalResult result = agentManageService.getManagerAreaNrGoal(managerAreaId, year, month);
		Converter<ManagerAreaGoalResult, WebManagerAreaGoalResultDTO> converter = DTOContext.getConverter(WebManagerAreaGoalResultDTO.class);
		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * �����������ȡ������������Ŀ�ꡣ
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ����������ID�������ߣ�
	 * @param date
	 *            ����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/area/directorarea/{id}/goal", method = RequestMethod.GET)
	public String getDirectorAreaGoal(@PathVariable("id") int directAreaId, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		DirectAreaGoalResult result = agentManageService.getDirectorAreaGoal(directAreaId, year, month);
		Converter<DirectAreaGoalResult, WebDirectAreaGoalResultDTO> converter = DTOContext.getConverter(WebDirectAreaGoalResultDTO.class);
		return new JsonResponseBean(converter.convert(result)).toJson();
	}

	/**
	 * ����ĳ�µ�SDOĿ�ꡣ
	 * 
	 * Ȩ�ޣ�SIS
	 * 
	 * @param year
	 *            ��
	 * @param month
	 *            ��
	 * @param file
	 *            �ϴ���EXCEL
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdo", method = RequestMethod.POST)
	public String setMonthSdo(@RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("file") MultipartFile file) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		String fileName = file.getOriginalFilename();
		String fileSuffix = fileName.split("\\.")[1];
		if (("xls".equals(fileSuffix)) || ("xlsx".equals(fileSuffix))) {
			agentManageService.setMonthSdo(year, month, file);
			return JsonResponseBean.getSuccessResponse().toJson();
		} else {
			throw new IllegalArgumentException("�����ļ���ʽ����");
		}

	}

	/**
	 * ��ȡ���µ�SdoExcel.
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdo/excel", method = RequestMethod.GET)
	public String getSdoExcel(@RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		SdoExcel excel = agentManageService.getSdoExcel(year, month);
		Converter<SdoExcel, WebSdoExcelDTO> converter = DTOContext.getConverter(WebSdoExcelDTO.class);
		return new JsonResponseBean(converter.convert(excel)).toJson();
	}

	/**
	 * ��ȡĳ���µ�SDOĿ�ꡣ
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdos", method = RequestMethod.GET)
	public String getSdos(@RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		List<Sdo> sdos = agentManageService.getSdos(year, month);
		Converter<Sdo, WebSdoDTO> converter = DTOContext.getConverter(WebSdoDTO.class);
		return new JsonResponseBean(converter.convert(sdos)).toJson();
	}

	/**
	 * ���θ�CR������SDOĿ�ꡣ
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ���ε�Ա��ID��
	 * @param agentAreaId
	 *            Ҫ�����CR��ID
	 * @param sdoIds
	 *            �����SDO��
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/sdo/agentareas/{id}", method = RequestMethod.PUT)
	public String setSdos(@PathVariable("id") int agentAreaId, @RequestParam("sdoIds") String sdoIds, @RequestParam("year") int year, @RequestParam("month") int month) {
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("month����");
		}
		List<Long> sdoIdList = parser.parseJSON(sdoIds, new TypeToken<List<Long>>() {});
		agentManageService.setSdos(agentAreaId, sdoIdList, year, month);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��ȡ�������������CR����
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ���ε�Ա��ID�������ߣ�
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
	 * ��ȡ·�ߡ�
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ���������Ρ�
	 * @param agentAreaId
	 *            CR��ID
	 * @param routeCode
	 *            ��·����
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
	 * ��ȡ���в��ڴ�·���е��ŵꡣ
	 * 
	 * Ȩ�ޣ�����������(TDS)��
	 * 
	 * @param employeeId
	 *            ����������ID
	 * @param agentAreaId
	 *            Ҫ������CR��ID
	 * @param routeCode
	 *            ��������·����
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
	 * ��ȡ���в��ڴ�·���е�ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ�����������(TDS)��
	 * 
	 * @param employeeId
	 *            ����������ID
	 * @param agentAreaId
	 *            Ҫ������CR��ID
	 * @param routeCode
	 *            ��������·����
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
	 * ���θ�ҵ��Ա����·�ߡ�
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ����������Ա��ID�������ߣ�
	 * @param agentAreaId
	 *            CR����ID
	 * @param routeCode
	 *            ��·����
	 * @param sets
	 *            ��װ���ŵ����ͺ�ID�Ĳ�����
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
	 * ��������ɸѡѡ���ȡĳ���µ�ĳ���µ�����ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param startYear
	 *            ��ʼ���
	 * @param startMonth
	 *            ��ʼ�·�
	 * @param endYear
	 *            �������
	 * @param endMonth
	 *            �����·�
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
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
			throw new IllegalArgumentException("month����");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId����ȫΪ��");
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
	 * ��������ɸѡѡ���ȡ����ĳ�쵽ĳ�������ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param startDay
	 *            ��ʼ��
	 * @param endDay
	 *            ������
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
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
			throw new IllegalArgumentException("day����");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId����ȫΪ��");
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
	 * ��������ɸѡѡ���ȡĳ���µ�ĳ���µ�������ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param startYear
	 *            ��ʼ���
	 * @param startMonth
	 *            ��ʼ�·�
	 * @param endYear
	 *            �������
	 * @param endMonth
	 *            �����·�
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
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
			throw new IllegalArgumentException("month����");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId����ȫΪ��");
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
	 * ��������ɸѡѡ���ȡĳ��ĳ�쵽ĳ���������ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param month
	 *            ĳ��
	 * @param startDay
	 *            ��ʼ��
	 * @param endDay
	 *            ������
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
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
			throw new IllegalArgumentException("month����");
		}
		if (startDay > 31 || startDay < 1 || endDay > 31 || endDay < 1) {
			throw new IllegalArgumentException("day����");
		}
		if (bigAreaId == null && manageAreaId == null && dirAreaId == null && agentAreaId == null) {
			throw new IllegalArgumentException("areaId����ȫΪ��");
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
	 * ��ȡ�ݷü�¼��
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param agentId
	 *            ҵ��ԱID
	 * @param startDate
	 *            ��ʼ���ڣ���ʽΪ8λint,����20141015��ʾ����2014.10.15
	 * @param endDate
	 *            �������ڣ���ʽΪ8λint,����20141015��ʾ����2014.10.15
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
	 * ��ȡ���д�����
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
	 * ��ȡ�˴���ID�����о�������
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
	 * ��ȡ�˾������µ�������������
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
	 * ��ȡ���������µ�����CR����
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
	 * ��ȡ����WorkType��
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
	 * ����̵��¼
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
			throw new IllegalArgumentException("ʱ���ʽyyyy-MM-dd");
		}

		Page<InventoriesCheckHistory> histories = agentManageService.getInventoriesHistory(id, start, end, page, size);
		Converter<InventoriesCheckHistory, InventoriesCheckHistoryDTO> converter = DTOContext.getConverter(InventoriesCheckHistoryDTO.class);
		return new JsonResponseBean(converter.convert(histories)).toJson();
	}
}
