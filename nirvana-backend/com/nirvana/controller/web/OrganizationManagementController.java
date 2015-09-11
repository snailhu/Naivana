package com.nirvana.controller.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.AreaService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.PersonnelManageService;
import com.nirvana.service.PromotionManageService;

@Controller
@RequestMapping("/backend")
@SecurityUnit(Warehouse.BACKEND)
public class OrganizationManagementController {
	@Resource
	private BackEndCurrentLoginService backend;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private PromotionManageService promotionManageService;
	@Resource
	private PersonnelManageService personnelManageService;
	@Resource
	private AgentManageService agentManageService;
	@Resource
	private AreaService areaService;

	@RequestMapping("/web/organization_management/list")
	@NeedERole({ ERole.ADMIN })
	public String organizationManagementList(HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "user");
		Page<Employee> pages = personnelManageService.getEmployees(page, size);
		List<Employee> employees = pages.getContent();
		int totalpage = pages.getTotalPages();
		request.setAttribute("employees", employees);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currentpage", page);
		return "organization_list";
	}

	@RequestMapping("/web/organization_management/change")
	@NeedERole({ ERole.ADMIN })
	public String organizationManagementChanage(HttpServletRequest request) {
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		List<BigArea> bigareas = agentManageService.getAllBigAreas();
		request.setAttribute("bigareas", bigareas);
		request.setAttribute("type", "change");
		return "organization_change";
	}

	@RequestMapping("/web/organization_management/add")
	@NeedERole({ ERole.ADMIN })
	public String organizationManagementAdd(HttpServletRequest request) {
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "user");
		return "organization_add";
	}

	@RequestMapping("/web/organization_management/{id}/edit")
	@NeedERole({ ERole.ADMIN })
	public String organizationManagementEdit(HttpServletRequest request, @PathVariable("id") int id) {
		Employee employee;
		try {
			employee = personnelManageService.getEmployee(id);
			List<BigArea> bigareas = areaService.getBigAreas();
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "user");
			request.setAttribute("bigareas", bigareas);
			request.setAttribute("employee", employee);
			return "organization_edit";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			return "404";
		}

	}

}
