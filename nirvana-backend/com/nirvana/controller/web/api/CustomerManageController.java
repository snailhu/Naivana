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
	 * ��ȡĳ�������̵������ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա��SIS������Ա��
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
	 * ��ȡ���о����̵ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ���Ա,SIS,����Ա��
	 * 
	 * ��Աֻ���Կ����Լ���ӵ�еľ����̡�
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
	 * ��ȡ����ֱӪ��ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ���Ա,SIS,����Ա��
	 * 
	 * ��Աֻ���Կ����Լ���ӵ�е�ֱӪ�ꡣ
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
	 * �رվ����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ�SIS,����Ա��
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
	 * ���¿��ž����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ�SIS������Ա��
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/dealers/{id}/open", method = RequestMethod.PUT)
	public String openDealer(@PathVariable("id") long dealerId) {
		customerManageService.openDealer(dealerId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��ȡ�����ľ����̻���ֱӪ����Ϣ��
	 * 
	 * Ȩ�ޣ���Ա��SIS������Ա��
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
	 * ��ȡ�ɷ��侭���̵��ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
	 * �������̷����ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
	 * ��ERP���ֶ�ͬ���˾����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
	 * ͨ��ERPCODE��ERP���ֶ�ͬ���˾����̻���ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
	 * �༭�����̻���ֱӪ����˻���Ϣ��
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
			throw new IllegalArgumentException("��������Ϊ��");
		}
		if (password != null && !password.equals(pwdrepeat)) {
			throw new IllegalArgumentException("���벻ƥ��");
		}
		customerManageService.editDealerAccount(dealerId, username, password,
				phoneNum, isClose, closeRaason);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * ��ȡ�����ŵ�ķ�ҳ���ݡ�
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
	 * �ر��ŵ��û���
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
	 * ���¿���ĳ���ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/stores/{id}/open", method = RequestMethod.PUT)
	public String openStore(@PathVariable("id") long storeId) {
		customerManageService.openStore(storeId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �༭�ŵ��˻���Ϣ��
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * @param storeId
	 *            �ŵ�ID
	 * @param phoneNum
	 *            ��ϵ�绰
	 * @param password
	 *            ����
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
			throw new IllegalArgumentException("��������Ϊ��");
		}
		if (password != null && !password.equals(pwdrepeat)) {
			throw new IllegalArgumentException("���벻ƥ��");
		}
		customerManageService.editStoreAccount(storeId, username, password,
				phoneNum);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * �༭�ŵ������Ϣ��
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * @param storeId
	 *            �ŵ�ID
	 * 
	 * @param data
	 *            �ŵ������Ϣ
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
	 * ��ȡ�����ŵ���Ϣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
	 * 
	 * @param StoreId
	 *            �ŵ�ID
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
	 * ����µ��ŵꡣ
	 * 
	 * Ȩ�ޣ���Ա������Ա��
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
			throw new IllegalArgumentException("��������Ϊ��");
		}
		if (!password.equals(pwdrepeat)) {
			throw new IllegalArgumentException("���벻ƥ��");
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
