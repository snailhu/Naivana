package com.nirvana.controller.web.api;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.web.WebComplaintDTO;
import com.nirvana.domain.backend.Complaint;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.FeedbackManageService;

@Controller
@RequestMapping(value = "/backend/web/api", produces = "application/json;charset=utf-8")
public class FeedbackManageController {

	@Resource
	private FeedbackManageService feedbackManageService;

	/**
	 * 获取所有反馈的分页信息。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/complaints", method = { RequestMethod.GET })
	public String getComplaints(@RequestParam("page") int page, @RequestParam("size") int size) {

		Page<Complaint> complaints = feedbackManageService.getComplaints(page, size);
		Converter<Complaint, WebComplaintDTO> converter = DTOContext.getConverter(WebComplaintDTO.class);
		return new JsonResponseBean(converter.convert(complaints)).toJson();

	}

	/**
	 * 获取某条反馈信息。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/complaints/{complaintId}", method = { RequestMethod.GET })
	public String getComplaint(@PathVariable(value = "complaintId") int complaintId) {
		Complaint complaint = feedbackManageService.getComplaint(complaintId);
		Converter<Complaint, WebComplaintDTO> converter = DTOContext.getConverter(WebComplaintDTO.class);
		return new JsonResponseBean(converter.convert(complaint)).toJson();

	}

	/**
	 * 删除掉某条反馈信息。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/complaints/{complaintId}", method = { RequestMethod.DELETE })
	public String deleteComplaint(@PathVariable(value = "complaintId") int complaintId) {

		feedbackManageService.deleteComplaint(complaintId);

		return JsonResponseBean.getSuccessResponse().toJson();
	}

}
