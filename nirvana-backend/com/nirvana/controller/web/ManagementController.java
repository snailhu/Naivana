package com.nirvana.controller.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nirvana.domain.backend.Complaint;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.exception.UserNotLoginException;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.FeedbackManageService;


@Controller
@RequestMapping("/backend")
@SecurityUnit(Warehouse.BACKEND)
public class ManagementController {
	@Resource
	private BackEndCurrentLoginService backend;
	@Resource
	private AgentManageService agentManageService;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private FeedbackManageService feedbackManageService;
	
	@RequestMapping("/web/center")
	public String center(HttpServletRequest request){
		try {
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "shop");
			return "center";
		} catch (UserNotLoginException e) {
			e.printStackTrace();
			return "login";
		}
	}
	
	
	@RequestMapping("/web/feedback")
	@NeedERole({ERole.SIS,ERole.ADMIN,ERole.GM,ERole.MDM,ERole.SISM})
	public String feedback(HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size){
		Page <Complaint> pages = feedbackManageService.getComplaints(page, size);
		List <Complaint> complaints=pages.getContent();
		int totalPage=pages.getTotalPages();
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("complaints",complaints);
		request.setAttribute("totalpage",totalPage);
		request.setAttribute("currentpage",page);
		return "feedback";
	}
	
	@RequestMapping("/web/feedback/{id}/detail")
	@NeedERole({ERole.SIS,ERole.ADMIN,ERole.GM,ERole.MDM,ERole.SISM})
	public String feedbackDetail(HttpServletRequest request,
			@PathVariable("id") int id){
		Complaint complaint;
		complaint = feedbackManageService.getComplaint(id);
		if(complaint!=null){
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("complaint",complaint);
			return "feedback_detail";
		}
		else{
			return "404";
		}
		

		
	}
	
	
	@RequestMapping("/web/personal")
	public String personal(HttpServletRequest request){
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("user",backend.getCurrentLoginUser());
		return "personal";
	}
	
	@RequestMapping("/web/nopermissions")
	public String nopermissions(HttpServletRequest request){
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("user",backend.getCurrentLoginUser());
		return "NoPermissions";
	}
	
	
	
}
