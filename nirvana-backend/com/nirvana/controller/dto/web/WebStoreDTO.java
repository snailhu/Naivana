package com.nirvana.controller.dto.web;

import com.nirvana.domain.store.Store;
import com.nirvana.pojo4json.BaseDTO;

public class WebStoreDTO extends BaseDTO<Store> {

	private String url = "xxx.com";
	private Long store_id = 1l;
	private String store_name = "名字";
	private String manager = "负责人";
	private String address = "地址";
	private String phone = "110";
	private String category = "分类";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public BaseDTO<Store> convert(Store domain) {
		WebStoreDTO dto = new WebStoreDTO();
		if (domain == null)
			return dto;
		dto.setStore_id(domain.getId());
		if (domain.getAgentCategory() != null) {
			dto.setCategory(domain.getAgentCategory().getCategoryName());
		}
		if (domain.getStorefrontInfo() != null) {
			dto.setStore_name(domain.getStorefrontInfo().getName());
			dto.setManager(domain.getStorefrontInfo().getManager());
			if (domain.getStorefrontInfo().getDeliveryAddr() != null)
				dto.setAddress(domain.getStorefrontInfo().getDeliveryAddr());
		}
		return dto;
	}

}
