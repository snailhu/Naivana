package com.nirvana.controller.dto;

import com.nirvana.domain.store.StoreOrder;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.utils.DateUtil;
import com.nirvana.utils.ValidataUtil;

public class BillingInDTO extends BaseDTO<StoreOrder> {
	private Long id;
	private String payablefee;
	private String createDate;

	@Override
	public BaseDTO<StoreOrder> convert(StoreOrder domain) {
		BillingInDTO dto = new BillingInDTO();
		if (domain == null)
			return dto;
		dto.setId(domain.getOrderNo());
		dto.setPayablefee(ValidataUtil.DECIMALFORMAT.format(domain
				.getPayablefee().doubleValue()));
		if (domain.getCreateDate() != null)
			dto.setCreateDate(DateUtil.dateToString(domain.getCreateDate(),
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
