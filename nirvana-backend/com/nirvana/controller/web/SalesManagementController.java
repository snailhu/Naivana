package com.nirvana.controller.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PromotionState;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.domain.common.Channel;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.ProductService;
import com.nirvana.service.PromotionManageService;

@Controller
@RequestMapping("/backend")
@SecurityUnit(Warehouse.BACKEND)
public class SalesManagementController {
	@Resource
	private BackEndCurrentLoginService backend;
	@Resource
	private AgentManageService agentManageService;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private PromotionManageService promotionManageService;
	@Resource
	private ProductService productService;

	@RequestMapping("/web/sales_management/publish")
	@NeedERole({ ERole.SIS })
	public String SalesManageManagementPublish(HttpServletRequest request) {
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		List<Channel> channel = productService.getAllChannels(1, 200).getContent();
		request.setAttribute("channels", channel);
		request.setAttribute("type", "publish");
		return "sales_publish";
	}

	@RequestMapping("/web/sales_management/history")
	@NeedERole({ ERole.SIS, ERole.MDM })
	public String shopManagementHistory(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<PepsiPromotion> pagePromotion = promotionManageService.getHistoryPromotions(PromotionState.ADOPTED, page, size);
		List<PepsiPromotion> promotions = pagePromotion.getContent();
		int totalpage = pagePromotion.getTotalPages();
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "history");
		request.setAttribute("promotions", promotions);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currentpage", page);
		return "sales_history";

	}

	@RequestMapping("/web/sales_management/{id}/detail")
	@NeedERole({ ERole.SIS, ERole.MDM })
	public String saleskDetail(HttpServletRequest request, @PathVariable("id") int id) {

		PepsiPromotion promotion;
		try {
			promotion = promotionManageService.getOnePromotion(id);
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "history");
			request.setAttribute("promotion", promotion);
			return "sales_detail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			return "404";

		}

	}

	@RequestMapping("/web/sales_management/review")
	@NeedERole({ERole.MDM})
	public String salesreview(HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<PepsiPromotion> pagePromotion = promotionManageService.getHistoryPromotions(PromotionState.WAITVERIFY, page, size);
		List<PepsiPromotion> promotions=pagePromotion.getContent();
		int totalpage=pagePromotion.getTotalPages();
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "review");
		request.setAttribute("promotions", promotions);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currentpage", page);
		return "sales_review";
	}

	@RequestMapping("/web/sales_management/{id}/review")
	@NeedERole({ERole.MDM})
	public String review(HttpServletRequest request,
			@PathVariable("id") int id){
		PepsiPromotion promotion;
		try {
			promotion = promotionManageService.getOnePromotion(id);
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "review");
			request.setAttribute("promotion", promotion);
			return "sales_review_detail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			return "404";

		}

	}
	
	@RequestMapping("/web/sales_management/rejected")
	@NeedERole({ERole.SIS})
	public String salesREJECTED(HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<PepsiPromotion> pagePromotion = promotionManageService.getHistoryPromotions(PromotionState.REJECTED, page, size);
		List<PepsiPromotion> promotions=pagePromotion.getContent();
		int totalpage=pagePromotion.getTotalPages();
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "rejected");
		request.setAttribute("promotions", promotions);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currentpage", page);
		return "sales_rejected";
	}
	
	@RequestMapping("/web/sales_management/{id}/rejected")
	@NeedERole({ERole.SIS})
	public String rejected(HttpServletRequest request,
			@PathVariable("id") int id){
		PepsiPromotion promotion;
		try {
			promotion = promotionManageService.getOnePromotion(id);
			List<Channel> channel = productService.getAllChannels(1, 200).getContent();
			request.setAttribute("channels", channel);
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "rejected");
			request.setAttribute("promotion", promotion);
			return "sales_rejected_detail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			return "404";
		}

	}
	

}
