package com.nirvana.controller.dto;

import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.pojo4json.BaseDTO;

public class StoreAccountInfoDTO extends BaseDTO<StoreUser> {

	private String username;

	private String bindPhone;

	private String realName;

	private String storeName;

	public StoreAccountInfoDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(String bindPhone) {
		this.bindPhone = bindPhone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public BaseDTO<StoreUser> convert(StoreUser domain) {
		StoreAccountInfoDTO dto = new StoreAccountInfoDTO();
		dto.setBindPhone(domain.getPhone());
		dto.setRealName(domain.getStore().getStorefrontInfo().getManager());
		dto.setStoreName(domain.getStore().getStorefrontInfo().getName());
		dto.setUsername(domain.getUsername());
		return dto;
	}
}
