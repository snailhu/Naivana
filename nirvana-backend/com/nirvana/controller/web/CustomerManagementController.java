package com.nirvana.controller.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.CustomerManageService;

@Controller
@RequestMapping("/backend")
@SecurityUnit(Warehouse.BACKEND)
public class CustomerManagementController {
	@Resource
	private BackEndCurrentLoginService backend;
	@Resource
	private AgentManageService agentManageService;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private CustomerManageService customerManageService;

	/* 经销商 */

	@RequestMapping("/web/customer_management/agency")
	@NeedERole({ ERole.CLERK })
	public String agencyManagement(HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		Page<Dealer> pages = customerManageService.getDealers(page, size);
		List<Dealer> dealers = pages.getContent();
		int totalpage = pages.getTotalPages();
		request.setAttribute("dealers", dealers);
		request.setAttribute("type", "agency");
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currentpage", page);
		return "customer_agency";
	}

	/* 门店用户 */
	@RequestMapping("/web/customer_management/shop")
	@NeedERole({ ERole.CLERK })
	public String shopManagement(HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		Page<Store> pages = customerManageService.getStores(page, size);
		List<Store> stores = pages.getContent();
		int totalpage = pages.getTotalPages();
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "shop");
		request.setAttribute("stores", stores);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currentpage", page);
		return "customer_shop";
	}

	/* 直营店 */
	@RequestMapping("/web/customer_management/direct_sale")
	@NeedERole({ ERole.CLERK })
	public String directSale(HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		Page<Dealer> pages = customerManageService.getDirectStores(page, size);
		List<Dealer> directStores = pages.getContent();
		int totalpage = pages.getTotalPages();
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "direct-sale");
		request.setAttribute("directStores", directStores);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currentpage", page);
		return "customer_direct_sale";
	}

	/* 经销商详细信息 */
	@RequestMapping("/web/customer_management/agency/{id}/detail")
	@NeedERole({ ERole.CLERK })
	public String agencyDetail(HttpServletRequest request, @PathVariable("id") long id) {
		Dealer dealer;
		try {
			dealer = customerManageService.getOneDealer(id);
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "agency");
			request.setAttribute("dealer", dealer);
			return "customer_agency_detail";
		} catch (Exception e) {
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			// TODO Auto-generated catch block
			return "404";
		}

	}

	/* 直营店详细信息 */
	@RequestMapping("/web/customer_management/direct_sale/{id}/detail")
	@NeedERole({ ERole.CLERK })
	public String directSaleDetail(HttpServletRequest request, @PathVariable("id") long id) {
		Dealer dealer;
		try {
			dealer = customerManageService.getOneDealer(id);
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "direct-sale");
			request.setAttribute("dealer", dealer);
			return "customer_direct_sale_detail";
		} catch (Exception e) {
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			// TODO Auto-generated catch block
			return "404";
		}

	}

	/* 门店详细信息 */
	@RequestMapping("/web/customer_management/shop/{id}/detail")
	@NeedERole({ ERole.CLERK })
	public String shopDetail(HttpServletRequest request, @PathVariable("id") long id) {
		Store store;
		try {
			store = customerManageService.getOneStore(id);
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			request.setAttribute("type", "shop");
			request.setAttribute("store", store);
			return "customer_shop_detail";
		} catch (Exception e) {
			request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
			// TODO Auto-generated catch block
			return "404";
		}

	}

	/* 添加门店 */
	@RequestMapping("/web/customer_management/shop/add")
	@NeedERole({ ERole.CLERK })
	public String shopAdd(HttpServletRequest request) {
		request.setAttribute("username", backend.getCurrentLoginUser().getEmployee().getName());
		request.setAttribute("type", "shop");
		return "customer_shop_add";
	}

}
