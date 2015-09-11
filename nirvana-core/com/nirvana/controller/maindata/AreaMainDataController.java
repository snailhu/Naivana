package com.nirvana.controller.maindata;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.web.WebAAreaLazyDTO;
import com.nirvana.controller.dto.web.WebAreaLazyDTO;
import com.nirvana.controller.dto.web.WebDAreaLazyDTO;
import com.nirvana.controller.dto.web.WebMAreaLazyDTO;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.IgnoreClassRenderer;
import com.nirvana.pojo4json.json.Renderer;
import com.nirvana.service.AreaService;

/**
 * 区域主数据获取。
 * 
 * */
@Controller
@RequestMapping(value = "/maindata/api", produces = "application/json;charset=utf-8")
public class AreaMainDataController {

	@Resource
	private AreaService areaService;

	private Renderer RENDERER = new IgnoreClassRenderer();

	/**
	 * 获取所有大区数据。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bigareas", method = { RequestMethod.GET })
	public String getBigAreas() {
		List<BigArea> l = areaService.getBigAreas();
		Converter<BigArea, WebAreaLazyDTO> converter = DTOContext.getConverter(WebAreaLazyDTO.class);
		return RENDERER.render(converter.convert(l));
	}

	/**
	 * 根据大区id获取所有经理区。
	 * 
	 * @param id
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/bigareas/{id}/managerareas", method = { RequestMethod.GET })
	public String getManagerAreasByBigArea(@PathVariable("id") int id) {
		List<ManagerArea> l = areaService.getManagerAreasByBigAreaId(id);
		Converter<ManagerArea, WebMAreaLazyDTO> converter = DTOContext.getConverter(WebMAreaLazyDTO.class);
		return RENDERER.render(converter.convert(l));
	}

	/**
	 * 根据经理区ID获取所有主任区。
	 * 
	 * @param id
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/managerareas/{id}/directorareas", method = { RequestMethod.GET })
	public String getDirectorAreasByManagerArea(@PathVariable("id") int id) {
		List<DirectorArea> l = areaService.getDirectorAreasByManagerAreaId(id);
		Converter<DirectorArea, WebDAreaLazyDTO> converter = DTOContext.getConverter(WebDAreaLazyDTO.class);
		return RENDERER.render(converter.convert(l));
	}

	/**
	 * 根据主任区ID获取所有小区。
	 * 
	 * @param id
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/directorareas/{id}/agentareas", method = { RequestMethod.GET })
	public String getAgentAreasByDirectorArea(@PathVariable("id") int id) {
		List<AgentArea> l = areaService.getAgentAreasByDirectorAreaId(id);
		Converter<AgentArea, WebAAreaLazyDTO> converter = DTOContext.getConverter(WebAAreaLazyDTO.class);
		return RENDERER.render(converter.convert(l));
	}

	/**
	 * 获取所有渠道。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/channels", method = { RequestMethod.GET })
	public String getAllChannels() {
		return null;
	}

}
