/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年3月6日 上午9:24:06
 */
package com.nirvana.controller.dto;

import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.pojo4json.BaseDTO;

/**
 * @Title:DealerStoreCategoryDTO.java
 * @Description:
 * @Version:v1.0
 */
public class DealerStoreCategoryDTO extends BaseDTO<DealerStoreCategory> {
	private Long id;

	/** 分类名 */
	private String name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<DealerStoreCategory> convert(DealerStoreCategory domain) {
		DealerStoreCategoryDTO dto = new DealerStoreCategoryDTO();
		if (domain == null)
			return dto;

		dto.setId(domain.getId());
		dto.setName(domain.getCategoryName());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
