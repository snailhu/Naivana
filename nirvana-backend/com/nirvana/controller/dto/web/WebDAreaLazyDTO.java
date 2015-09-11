/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年6月4日 下午3:15:09
 */
package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:WebAreaLazyDTO.java
 * @Description:
 * @Version:v1.0
 */
public class WebDAreaLazyDTO extends BaseDTO<DirectorArea> {
	private String title;
	private boolean isLazy = true;
	private String key;
	private boolean icon;
	private boolean unselectable = true;
	private boolean hideCheckbox = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<DirectorArea> convert(DirectorArea domain) {
		WebDAreaLazyDTO dto = new WebDAreaLazyDTO();
		if (domain == null) {
			return dto;
		}
		dto.setKey("directorArea-" + domain.getId());
		dto.setTitle(domain.getName());
		dto.setIcon(domain.getIsClosed());
		return dto;
	}
	
	public boolean isIcon() {
		return icon;
	}

	public void setIcon(boolean icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isLazy() {
		return isLazy;
	}

	public void setLazy(boolean isLazy) {
		this.isLazy = isLazy;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isUnselectable() {
		return unselectable;
	}

	public void setUnselectable(boolean unselectable) {
		this.unselectable = unselectable;
	}

	public boolean isHideCheckbox() {
		return hideCheckbox;
	}

	public void setHideCheckbox(boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}
}
