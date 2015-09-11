package com.nirvana.controller.web.api;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.DealerDTO;
import com.nirvana.controller.dto.web.WebStoreDTO;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.CustomerManageService;
import com.nirvana.service.pojo.web.StoreInfoData;

@Controller
@RequestMapping(value = "/backend/web/api", produces = "application/json;charset=utf-8")
public class CustomerManageController {

	@Resource
	private CustomerManageService customerManageService;

	Parser parser = new SimpleParser();

	/**
	 * 获取某个经销商的下属门店。
	 * 
	 * 权限：文员，SIS，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/stores", method = RequestMethod.GET)
	public String getDealerStores(@PathVariable("id") long dealerId) {
		List<Store> stores = customerManageService.getDealerStores(dealerId);
		Converter<Store, WebStoreDTO> converter = DTOContext
				.getConverter(WebStoreDTO.class);
		return new JsonResponseBean(converter.convert(stores)).toJson();
	}

	/**
	 * 获取所有经销商的分页数据。
	 * 
	 * 权限：文员,SIS,管理员。
	 * 
	 * 文员只可以看到自己所拥有的经销商。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers", method = RequestMethod.GET)
	public String getDealers(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Dealer> pDealers = customerManageService.getDealers(page, size);
		Converter<Dealer, DealerDTO> converter = DTOContext
				.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(pDealers)).toJson();
	}

	/**
	 * 获取所有直营店的分页数据。
	 * 
	 * 权限：文员,SIS,管理员。
	 * 
	 * 文员只可以看到自己所拥有的直营店。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/direct", method = RequestMethod.GET)
	public String getDirectStores(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Dealer> pDealers = customerManageService.getDirectStores(page,
				size);
		Converter<Dealer, DealerDTO> converter = DTOContext
				.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(pDealers)).toJson();
	}

	/**
	 * 关闭经销商或者直营店。
	 * 
	 * 权限：SIS,管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/close", method = RequestMethod.PUT)
	public String closeDealer(@PathVariable("id") long dealerId,
			@RequestParam("closeReason") String closeReason) {
		customerManageService.closeDealer(dealerId, closeReason);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 重新开放经销商或者直营店。
	 * 
	 * 权限：SIS，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/open", method = RequestMethod.PUT)
	public String openDealer(@PathVariable("id") long dealerId) {
		customerManageService.openDealer(dealerId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取单个的经销商或者直营店信息。
	 * 
	 * 权限：文员，SIS，管理员。
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}", method = RequestMethod.GET)
	public String getOneDealer(@PathVariable("id") long dealerId) {
		Dealer dealer = customerManageService.getOneDealer(dealerId);
		Converter<Dealer, DealerDTO> converter = DTOContext
				.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealer)).toJson();
	}

	/**
	 * 获取可分配经销商的门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/notalloted", method = RequestMethod.GET)
	public String getNotAllotedStores() {
		List<Store> stores = customerManageService.getNotAllotedStores();
		Converter<Store, WebStoreDTO> converter = DTOContext
				.getConverter(WebStoreDTO.class);
		return new JsonResponseBean(converter.convert(stores)).toJson();
	}

	/**
	 * 给经销商分配门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/allot", method = RequestMethod.POST)
	public String allotStoresToDealer(@PathVariable("id") long dealerId,
			@RequestParam("outIds") String outIds,
			@RequestParam("inIds") String inIds) {
		List<Long> idsin = parser.parseJSON(inIds, new TypeToken<List<Long>>() {
		});
		List<Long> idsout = parser.parseJSON(outIds,
				new TypeToken<List<Long>>() {
				});
		customerManageService.allotStoresToDealer(dealerId, idsout, idsin);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 从ERP中手动同步此经销商或者直营店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/sync", method = RequestMethod.POST)
	public String synchronizeDealer(@PathVariable("id") long dealerId) {
		Dealer dealer = customerManageService.synchronizeDealer(dealerId);
		Converter<Dealer, DealerDTO> converter = DTOContext
				.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealer)).toJson();
	}

	/**
	 * 通过ERPCODE从ERP中手动同步此经销商或者直营店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/erp/sync", method = RequestMethod.POST)
	public String synchronizeDealer(@RequestParam("erpCode") String erpCode) {
		Dealer dealer = customerManageService.synchronizeDealer(erpCode);
		Converter<Dealer, DealerDTO> converter = DTOContext
				.getConverter(DealerDTO.class);
		return new JsonResponseBean(converter.convert(dealer)).toJson();
	}

	/**
	 * 编辑经销商或者直营店的账户信息。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/edit", method = RequestMethod.PUT)
	public String editDealerAccount(
			@PathVariable("id") long dealerId,
			@RequestParam("username") String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "pwdrepeat", required = false) String pwdrepeat,
			@RequestParam("phoneNum") String phoneNum,
			@RequestParam(value = "isClose", required = false) Boolean isClose,
			@RequestParam(value = "closeReason", required = false) String closeRaason) {
		if (StringUtils.isAnyBlank(username, phoneNum)) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (password != null && !password.equals(pwdrepeat)) {
			throw new IllegalArgumentException("密码不匹配");
		}
		customerManageService.editDealerAccount(dealerId, username, password,
				phoneNum, isClose, closeRaason);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取所有门店的分页数据。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	public String getStores(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Store> stores = customerManageService.getStores(page, size);
		Converter<Store, WebStoreDTO> converter = DTOContext
				.getConverter(WebStoreDTO.class);
		return new JsonResponseBean(converter.convert(stores)).toJson();
	}

	/**
	 * 关闭门店用户。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/{id}/close", method = RequestMethod.PUT)
	public String closeStore(@PathVariable("id") long storeId,
			@RequestParam("closeReason") String closeReason) {
		customerManageService.closeStore(storeId, closeReason);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 重新开放某个门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/{id}/open", method = RequestMethod.PUT)
	public String openStore(@PathVariable("id") long storeId) {
		customerManageService.openStore(storeId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 编辑门店账户信息。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * @param storeId
	 *            门店ID
	 * @param phoneNum
	 *            联系电话
	 * @param password
	 *            密码
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/{id}/edit", method = RequestMethod.PUT)
	public String editStoreAccount(
			@PathVariable("id") long storeId,
			@RequestParam("phoneNum") String phoneNum,
			@RequestParam("username") String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "pwdrepeat", required = false) String pwdrepeat) {
		if (StringUtils.isAnyBlank(phoneNum)) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (password != null && !password.equals(pwdrepeat)) {
			throw new IllegalArgumentException("密码不匹配");
		}
		customerManageService.editStoreAccount(storeId, username, password,
				phoneNum);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 编辑门店基础信息。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * @param storeId
	 *            门店ID
	 * 
	 * @param data
	 *            门店基础信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/{id}/info/edit", method = RequestMethod.PUT)
	public String editStoreInfo(
			@PathVariable("id") long storeId,
			@RequestParam("data") String data,
			@RequestParam(value = "isClose", required = false) Boolean isClose,
			@RequestParam(value = "closeReason", required = false) String closeRaason) {
		StoreInfoData info = parser.parseJSON(data, StoreInfoData.class);
		// customerManageService.editStoreInfo(storeId, info);
		customerManageService.editStoreInfoWithClose(storeId, info, isClose,
				closeRaason);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取单个门店信息
	 * 
	 * 权限：文员，管理员。
	 * 
	 * @param StoreId
	 *            门店ID
	 */
	@ResponseBody
	@RequestMapping(value = "/stores/{id}", method = RequestMethod.GET)
	public String getOneStore(@PathVariable("id") long storeId) {
		Store store = customerManageService.getOneStore(storeId);
		Converter<Store, WebStoreDTO> converter = DTOContext
				.getConverter(WebStoreDTO.class);
		return new JsonResponseBean(converter.convert(store)).toJson();
	}

	/**
	 * 添加新的门店。
	 * 
	 * 权限：文员，管理员。
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores", method = RequestMethod.POST)
	public String addNewStore(
			@RequestParam("username") String username,
			@RequestParam("bindNum") String bindNum,
			@RequestParam("password") String password,
			@RequestParam("pwdrepeat") String pwdrepeat,
			@RequestParam(value = "codeInERP", required = false) String codeInERP,
			@RequestParam("manager") String manager,
			// @RequestParam("addrDetail") String addrDetail,
			@RequestParam("email") String email,
			@RequestParam("faxNum") String faxNum,
			@RequestParam("name") String name,
			// @RequestParam("pCity") String pCity,
			@RequestParam("postalCode") String postalCode,
			@RequestParam("phoneNum") String phoneNum,
			@RequestParam("deliveryAddr") String deliveryAddr) {
		if (StringUtils.isAnyBlank(username, password, pwdrepeat, manager,
				phoneNum, deliveryAddr)) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (!password.equals(pwdrepeat)) {
			throw new IllegalArgumentException("密码不匹配");
		}
		StoreInfoData data = new StoreInfoData();
		// data.setAddrDetail(addrDetail);
		data.setCodeInERP(codeInERP);
		data.setDeliveryAddr(deliveryAddr);
		data.setEmail(email);
		data.setFaxNum(faxNum);
		data.setManager(manager);
		data.setName(name);
		// data.setpCity(pCity);
		data.setPhoneNum(phoneNum);
		data.setPostalCode(postalCode);
		customerManageService.addNewStore(username, password, phoneNum, data);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

}
