package com.nirvana.controller.dto;

import java.text.DecimalFormat;
import java.util.List;

import com.nirvana.domain.store.StoreOrder;
import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.DateUtil;

public class StoreOrderDTO extends BaseDTO<StoreOrder> {

	/** ������ */
	private Long id;

	/** ���������� */
	private Long dealerId;

	/** ����һ���ŵ� */
	private Long storeId;

	/** �Ƿ�Ϊҵ��Ա�����µ� */
	private Boolean isAgentHelped;

	/** ��������ʱ�� */
	private String createDate;

	/** ����״̬ */
	private String state;

	/** ��Ʒ�ܽ�� */
	private String productTotalPrice;

	/** �����ܽ�� */
	private String totalPrice;

	/** Ӧ����(ʵ����Ҫ֧���ķ���) */
	private String payablefee;

	/** ������ */
	private List<StoreOrderItemDTO> items;

	/** �ŵ�ǩ�� */
	private String signPic;

	/** ��Ʒ������� */
	private Integer product;

	/** ���� */
	private Integer delivery;

	/** ����̬�� */
	private Integer manner;

	/** ���� */
	private String words;

	/** ��ע */
	private String note;

	/** ��Ʒ */
	private String rewards;

	private StoreStorefrontInfoDTO store;

	@Override
	public BaseDTO<StoreOrder> convert(StoreOrder domain) {
		StoreOrderDTO dto = new StoreOrderDTO();
		if (domain == null) {
			return dto;
		}
		dto.setCreateDate(DateUtil.dateToString(domain.getCreateDate(), "yyyy/MM/dd"));
		if (domain.getDealer() != null) {
			dto.setDealerId(domain.getDealer().getId());
		}
		dto.setDelivery(domain.getDelivery());
		dto.setId(domain.getOrderNo());
		dto.setIsAgentHelped(domain.getIsAgentHelped());
		Converter<StoreOrderItem, StoreOrderItemDTO> converter = DTOContext.getConverter(StoreOrderItemDTO.class);
		dto.setItems(converter.convert(domain.getItems()));
		dto.setManner(domain.getManner());
		dto.setNote(domain.getNote());
		DecimalFormat format = new DecimalFormat("0.00");
		dto.setPayablefee(format.format(domain.getPayablefee()));
		dto.setProduct(domain.getProduct());
		dto.setProductTotalPrice(format.format(domain.getProductTotalPrice()));
		dto.setSignPic(domain.getSignPic());
		switch (domain.getState()) {
		case NOTHANDLE:
			dto.setState("δ�ͻ�");
			break;
		case HANDLING:
			dto.setState("�ͻ���");
			break;
		case FINISHHANDLE:
			dto.setState("���ջ�");
			break;
		default:
			dto.setState(domain.getState().getName());
			break;
		}

		if (domain.getStore() != null) {
			dto.setStoreId(domain.getStore().getId());
			Converter<StoreStorefrontInfo, StoreStorefrontInfoDTO> storeConverter = DTOContext.getConverter(StoreStorefrontInfoDTO.class);
			dto.setStore(storeConverter.convert(domain.getStore().getStorefrontInfo()));
		}
		dto.setTotalPrice(format.format(domain.getTotalPrice()));
		dto.setWords(domain.getWords());
		dto.setRewards(domain.getRewards());
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Boolean getIsAgentHelped() {
		return isAgentHelped;
	}

	public void setIsAgentHelped(Boolean isAgentHelped) {
		this.isAgentHelped = isAgentHelped;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProductTotalPrice() {
		return productTotalPrice;
	}

	public void setProductTotalPrice(String productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayablefee() {
		return payablefee;
	}

	public void setPayablefee(String payablefee) {
		this.payablefee = payablefee;
	}

	public List<StoreOrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<StoreOrderItemDTO> items) {
		this.items = items;
	}

	public String getSignPic() {
		return signPic;
	}

	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	public Integer getManner() {
		return manner;
	}

	public void setManner(Integer manner) {
		this.manner = manner;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public StoreStorefrontInfoDTO getStore() {
		return store;
	}

	public void setStore(StoreStorefrontInfoDTO store) {
		this.store = store;
	}
}
