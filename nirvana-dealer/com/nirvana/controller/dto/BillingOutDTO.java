package com.nirvana.controller.dto;

import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.utils.DateUtil;
import com.nirvana.utils.ValidataUtil;

public class BillingOutDTO extends BaseDTO<DealerOrder> {
	private Long id;
	private String payablefee;
	private String createDate;

	@Override
	public BaseDTO<DealerOrder> convert(DealerOrder domain) {
		BillingOutDTO dto = new BillingOutDTO();
		if (domain == null)
			return dto;
		dto.setId(domain.getOrderNo());
		dto.setPayablefee(ValidataUtil.DECIMALFORMAT.format(domain
				.getTotalPrice().doubleValue()));
		dto.setCreateDate(DateUtil.dateToString(domain.getEnterDate(),
				"yyyy/MM/dd"));
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayablefee() {
		return payablefee;
	}

	public void setPayablefee(String payablefee) {
		this.payablefee = payablefee;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
