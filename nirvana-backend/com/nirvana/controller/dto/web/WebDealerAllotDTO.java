/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月22日 上午10:34:30
 */
package com.nirvana.controller.dto.web;

import java.util.List;

import com.nirvana.controller.dto.DealerDTO;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

/**
 * @Title:WebDealerAllotDTO.java
 * @Description:
 * @Version:v1.0
 */
public class WebDealerAllotDTO {
	List<DealerDTO> dealers;
	List<DealerDTO> dealersNotAlloted;

	public WebDealerAllotDTO(List<Dealer> dealers,
			List<Dealer> dealersNotAlloted) {
		super();
		Converter<Dealer, DealerDTO> converter = DTOContext
				.getConverter(DealerDTO.class);
		this.dealers = converter.convert(dealers);
		this.dealersNotAlloted = converter.convert(dealersNotAlloted);
	}

	public WebDealerAllotDTO() {
	}

	public List<DealerDTO> getDealers() {
		return dealers;
	}

	public void setDealers(List<DealerDTO> dealers) {
		this.dealers = dealers;
	}

	public List<DealerDTO> getDealersNotAlloted() {
		return dealersNotAlloted;
	}

	public void setDealersNotAlloted(List<DealerDTO> dealersNotAlloted) {
		this.dealersNotAlloted = dealersNotAlloted;
	}
}
