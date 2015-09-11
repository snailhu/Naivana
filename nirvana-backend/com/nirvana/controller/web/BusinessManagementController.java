package com.nirvana.controller.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.exception.UserNotLoginException;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.VisitRouteService;

@Controller
@RequestMapping("/backend")
@SecurityUnit(Warehouse.BACKEND)
public class BusinessManagementController {
	@Resource
	private BackEndCurrentLoginService backend;
	@Resource
	private AgentManageService agentManageService;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private VisitRouteService visitRouteService;


	@RequestMapping("/web/business_management/user_list")
	@NeedERole({ERole.CLERK,ERole.SIS})
	public String BusinessUserList(HttpServletRequest request) {
		String username = backEndCurrentLoginService.getCurrentLoginUser()
				.getEmployee().getName();
		request.setAttribute("type", "user");
		request.setAttribute("username", username);
		return "business_user_list";


	}

	@RequestMapping("/web/business_management/user_add")
	@NeedERole({ERole.SIS})
	public String BusninessUserAdd(HttpServletRequest request) {
			String username = backEndCurrentLoginService.getCurrentLoginUser()
					.getEmployee().getName();
			List<BigArea> bigAreas = agentManageService.getAllBigAreas();
			WorkType[] workType=WorkType.values();
			System.out.println(workType);
			request.setAttribute("username", username);
			request.setAttribute("bigAreas",bigAreas);
			request.setAttribute("type", "user");
			request.setAttribute("workType", workType);
			return "business_user_add";

	}

	@RequestMapping("/web/business_management/target_sales")
	@NeedERole({ERole.TDS,ERole.TDM,ERole.UM})
	public String BusninessTargetSales(HttpServletRequest request) {

		try {
			request.setAttribute("username", backend.getCurrentLoginUser()
					.getEmployee().getName());
			request.setAttribute("type", "target");
			List<Position> positions = agentManageService.getHasPositions();
			request.setAttribute("positions",positions);
			return "business_target_sales";
		} catch (UserNotLoginException e) {
			e.printStackTrace();
			return "login";
		}

	}

	@RequestMapping("/web/business_management/target_sdo")
	@NeedERole({ERole.TDS,ERole.SIS})
	public String BusninessTargetSdo(HttpServletRequest request) {
			request.setAttribute("username", backend.getCurrentLoginUser()
					.getEmployee().getName());
			List<DirectorArea> directorAreas=agentManageService.getManagedDirectorArea();
			request.setAttribute("directorAreas", directorAreas);
			request.setAttribute("type", "target");
			return "business_target_sdo";
	}

	@RequestMapping("/web/business_management/line_dis")
	@NeedERole({ERole.TDS})
	public String BusninessLineDis(HttpServletRequest request) {
			request.setAttribute("username", backend.getCurrentLoginUser()
					.getEmployee().getName());
			int code;
			try {
				code=visitRouteService.getTodayRouteCode();
			} catch (Exception e) {
				code=0;
			}
			request.setAttribute("code",code);
			request.setAttribute("type", "dis");
			List<DirectorArea> directorAreas=agentManageService.getManagedDirectorArea();
			request.setAttribute("directorAreas", directorAreas);
			return "business_line_dis";

	}


	@RequestMapping("/web/business_management/performance_sales")
	@NeedERole({ERole.TDS,ERole.TDM,ERole.UM,ERole.ADMIN,ERole.CDM,ERole.CLERK,
		ERole.GM,ERole.MDM,ERole.SISM,ERole.SIS})
	public String BusninessPerformanceSales(HttpServletRequest request) {
		try {
			request.setAttribute("username", backend.getCurrentLoginUser()
					.getEmployee().getName());
			request.setAttribute("type", "performance");
			return "business_performance_sales";
		} catch (UserNotLoginException e) {
			e.printStackTrace();
			return "login";
		}

	}

	
	@RequestMapping("/web/business_management/performance_productivity")
	@NeedERole({ERole.TDS,ERole.TDM,ERole.UM,ERole.ADMIN,ERole.CDM,ERole.CLERK,
		ERole.GM,ERole.MDM,ERole.SISM})
	public String BusninessPerformanceProductivity(HttpServletRequest request) {
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "performance");
			return "business_performance_productivity";
	}

	@RequestMapping("/web/business_management/visit")
	@NeedERole({ERole.TDS,ERole.TDM,ERole.UM,ERole.ADMIN,ERole.CDM,ERole.CLERK,
		ERole.GM,ERole.MDM,ERole.SISM})
	public String BusninessVisit(HttpServletRequest request) {
		request.setAttribute("username", backend.getCurrentLoginUser()
				.getEmployee().getName());
		List<Position> positions = agentManageService.getHasPositions();
		request.setAttribute("positions",positions);
		request.setAttribute("type", "visit");
		return "business_visit";
	}

}
