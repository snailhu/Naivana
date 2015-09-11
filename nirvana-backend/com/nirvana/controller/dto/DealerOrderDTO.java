package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderItem;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.DateUtil;
import com.nirvana.utils.ValidataUtil;

public class DealerOrderDTO extends BaseDTO<DealerOrder> {
	/** �����ţ����ӣ�2014070900001005 */
	private Long id;

	/** �����ľ����� */
	private Long dealerId;

	/** �����Ķ����� */
	private List<DealerOrderItemDTO> items;

	private String totalPrice;

	/** ERP�ж�����š� */
	private String codeInERP;

	/** �µ�ʱ�䡣 */
	private String enterDate;

	/** ϣ������ʱ�䡣 */
	private String wantDate;

	/** �����׶� */
	private String state;

	public DealerOrderDTO() {
	}

	@Override
	public BaseDTO<DealerOrder> convert(DealerOrder domain) {
		DealerOrderDTO dto = new DealerOrderDTO();
		if (domain == null) {
			return dto;
		}
		dto.setCodeInERP(domain.getCodeInERP());
		dto.setWantDate(domain.getWantDate() == null ? null : DateUtil
				.dateToString(domain.getWantDate()));
		dto.setEnterDate(domain.getEnterDate() == null ? null : DateUtil
				.dateToString(domain.getEnterDate()));
		dto.setState(domain.getState().name());
		dto.setDealerId(domain.getDealer().getId());
		dto.setId(domain.getOrderNo());
		dto.setTotalPrice(ValidataUtil.DECIMALFORMAT.format(domain
				.getTotalPrice().doubleValue()));
		Converter<DealerOrderItem, DealerOrderItemDTO> converter = DTOContext
				.getConverter(DealerOrderItemDTO.class);
		dto.setItems(converter.convert(domain.getItems()));
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public List<DealerOrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<DealerOrderItemDTO> items) {
		this.items = items;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getWantDate() {
		return wantDate;
	}

	public void setWantDate(String wantDate) {
		this.wantDate = wantDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
