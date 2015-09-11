package com.nirvana.controller.dto.app;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.pojo4json.BaseDTO;

public class CheckInventDealerDTO extends BaseDTO<Dealer> {

	private Long dealerId;

	private String dealerName;

	private String code;

	public CheckInventDealerDTO() {
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public BaseDTO<Dealer> convert(Dealer domain) {
		CheckInventDealerDTO dto = new CheckInventDealerDTO();
		dto.setCode(domain.getCodeInERP());
		dto.setDealerId(domain.getId());
		dto.setDealerName(domain.getStorefrontInfo().getName());
		return dto;
	}

}
