package com.nirvana.controller.web.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.PositionDto;
import com.nirvana.controller.dto.web.WebCreateAgentAreaDTO;
import com.nirvana.controller.dto.web.WebCreateBigAreaDTO;
import com.nirvana.controller.dto.web.WebCreateDirectAreaDTO;
import com.nirvana.controller.dto.web.WebCreateManagerAreaDTO;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.PersonnelManageService;
import com.nirvana.service.pojo.web.PositionData;

@Controller
@RequestMapping(value = "/backend/web/api", produces = "application/json;charset=utf-8")
public class PersonnelManageController {

	@Resource
	private PersonnelManageService personnelManageService;

	private SimpleParser parser = new SimpleParser();

	/**
	 * ������û���
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public String createEmployee(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("realName") String realName,
			@RequestParam("eCode") String eCode,
			@RequestParam("phoneNum") String phoneNum) {
		personnelManageService.createEmployee(username, password, realName, eCode, phoneNum);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �༭�û���
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.PUT)
	public String editEmployee(
			@PathVariable("employeeId") long employeeId,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam("realName") String realName,
			@RequestParam("eCode") String eCode,
			@RequestParam("phoneNum") String phoneNum,
			@RequestParam(value = "close", required = false) Boolean close) {
		personnelManageService.editEmployee(employeeId, password, realName, eCode, phoneNum, close);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���û����ְλ��
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/employees/{employeeId}/positions", method = RequestMethod.POST)
	public String addPositions(@PathVariable("employeeId") long employeeId, @RequestParam("datas") String datas) {
		List<PositionData> list = null;
		try {
			list = parser.parseJSON(datas, new TypeToken<List<PositionData>>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException("��������");
		}
		personnelManageService.addPositions(employeeId, list);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���û�ɾ��ְλ��
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/employees/{employeeId}/positions/{positionId}", method = RequestMethod.DELETE)
	public String deletePosition(@PathVariable("employeeId") long employeeId, @PathVariable("positionId") int positionId) {
		personnelManageService.deletePosition(employeeId, positionId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �ƶ�CR����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/agentareas/{areaId}/move", method = RequestMethod.PUT)
	public String moveAgentArea(@PathVariable("areaId") int areaId, @RequestParam("directAreaId") int directAreaId) {
		personnelManageService.moveAgentArea(areaId, directAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �ƶ���������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/directareas/{areaId}/move", method = RequestMethod.PUT)
	public String moveDirectArea(@PathVariable("areaId") int areaId, @RequestParam("managerAreaId") int managerAreaId) {
		personnelManageService.moveDirectorArea(areaId, managerAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �ƶ���������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/managerareas/{areaId}/move", method = RequestMethod.PUT)
	public String moveManagerArea(@PathVariable("areaId") int areaId, @RequestParam("bigAreaId") int bigAreaId) {
		personnelManageService.moveManagerArea(areaId, bigAreaId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �����µ�С����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/agentareas", method = RequestMethod.POST)
	public String createAgentArea(@RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("type") String type, @RequestParam("fatherId") int fatherId) {
		WorkType workType;
		try {
			workType = WorkType.valueOf(type);
		} catch (Exception e) {
			throw new IllegalArgumentException("WorkType������������");
		}
		AgentArea area = personnelManageService.createAgentArea(name, code, workType, fatherId);
		Converter<AgentArea, WebCreateAgentAreaDTO> converter = DTOContext.getConverter(WebCreateAgentAreaDTO.class);
		JsonResponseBean bean = new JsonResponseBean(converter.convert(area));
		return bean.toJson();
	}

	/**
	 * �����µ���������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/directareas", method = RequestMethod.POST)
	public String createDirectArea(@RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("fatherId") int fatherId) {
		DirectorArea area = personnelManageService.createDirectArea(name, code, fatherId);
		Converter<DirectorArea, WebCreateDirectAreaDTO> converter = DTOContext.getConverter(WebCreateDirectAreaDTO.class);
		JsonResponseBean bean = new JsonResponseBean(converter.convert(area));
		return bean.toJson();
	}

	/**
	 * �����µľ�������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/managerareas", method = RequestMethod.POST)
	public String createManagerArea(@RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("fatherId") int fatherId) {
		ManagerArea area = personnelManageService.createManagerArea(name, code, fatherId);
		Converter<ManagerArea, WebCreateManagerAreaDTO> converter = DTOContext.getConverter(WebCreateManagerAreaDTO.class);
		JsonResponseBean bean = new JsonResponseBean(converter.convert(area));
		return bean.toJson();
	}

	/**
	 * �����µĴ�����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bigareas", method = RequestMethod.POST)
	public String createBigArea(@RequestParam("name") String name, @RequestParam("code") String code) {
		BigArea area = personnelManageService.createBigArea(name, code);
		Converter<BigArea, WebCreateBigAreaDTO> converter = DTOContext.getConverter(WebCreateBigAreaDTO.class);
		JsonResponseBean bean = new JsonResponseBean(converter.convert(area));
		return bean.toJson();
	}

	/**
	 * ��ȡ�û�ְλ��Ϣ
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/position/{id}", method = RequestMethod.GET)
	public String getPositionInfo(@PathVariable("id") int id) {
		List<Position> position = personnelManageService.getPositon(id);
		Converter<Position, PositionDto> converter = DTOContext.getConverter(PositionDto.class);
		return new JsonResponseBean(converter.convert(position)).toJson();
	}

	/**
	 * �رմ�����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bigareas/{id}/close", method = RequestMethod.PUT)
	public String closeBigArea(@PathVariable("id") int id) {
		personnelManageService.closeBigArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bigareas/{id}/activate", method = RequestMethod.PUT)
	public String activateBigArea(@PathVariable("id") int id) {
		personnelManageService.activateBigArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �رվ�������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/managerareas/{id}/close", method = RequestMethod.PUT)
	public String closeManagerArea(@PathVariable("id") int id) {
		personnelManageService.closeManagerArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ���������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/managerareas/{id}/activate", method = RequestMethod.PUT)
	public String activateManagerArea(@PathVariable("id") int id) {
		personnelManageService.activateManangerArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �ر���������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/directareas/{id}/close", method = RequestMethod.PUT)
	public String closeDirectorArea(@PathVariable("id") int id) {
		personnelManageService.closeDirectorArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ������������
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/directareas/{id}/activate", method = RequestMethod.PUT)
	public String activateDirectorArea(@PathVariable("id") int id) {
		personnelManageService.activateDirectorArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �ر�С����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/agentareas/{id}/close", method = RequestMethod.PUT)
	public String closeAgentArea(@PathVariable("id") int id) {
		personnelManageService.closeAgentArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ����С����
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/agentareas/{id}/activate", method = RequestMethod.PUT)
	public String activateAgentArea(@PathVariable("id") int id) {
		personnelManageService.activateAgentArea(id);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

}
