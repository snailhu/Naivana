package com.nirvana.controller.dto.app;

import java.util.ArrayList;
import java.util.List;

import com.nirvana.domain.common.Device;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class BEAppStoreDTO extends BaseDTO<Store> {
	// TODO 待清除假数据
	/** 门店ID */
	private Long id = 1l;
	private Integer agentAreaId = 1;

	/** ERP中的客户编号 */
	private String codeInERP = "假codeInERP";

	/** 门店在业务员下的分类 */
	private String agentCategory = "假分类";

	private List<DeviceDTO> devices = new ArrayList<DeviceDTO>();

	/** 备忘 */
	private String notes = "假备注";

	/**
	 * 店面信息
	 */
	/** 店名 */
	private String name = "店名";

	/** 负责人姓名 */
	private String manager = "负责人姓名";

	/** 邮政编码 */
	private String postalCode = "邮编";

	/** 电话手机号码 */
	private String phoneNum = "电话号码";

	/** 邮箱 */
	private String email = "邮箱";

	private String faxNum = "传真";

	/** 地址详细信息 */
	private String addrDetail = "详细地址";

	/** 送货地址 */
	private String deliveryAddr = "送货地址";

	public BEAppStoreDTO() {
		devices.add(new DeviceDTO());
	}

	@Override
	public BaseDTO<Store> convert(Store domain) {
		BEAppStoreDTO dto = new BEAppStoreDTO();
		if (domain == null) {
			dto.getDevices().add(new DeviceDTO());
			return dto;
		}
		dto.setCodeInERP(domain.getCodeInERP());
		dto.setId(domain.getId());
		dto.setAgentAreaId(domain.getAgentArea() == null ? null : domain
				.getAgentArea().getId());
		dto.setNotes(domain.getNotes());
		Converter<Device, DeviceDTO> converter = DTOContext
				.getConverter(DeviceDTO.class);
		dto.setDevices(converter.convert(domain.getDevices()));
		StoreStorefrontInfo info = domain.getStorefrontInfo();
		if (info != null) {
			dto.setAddrDetail(info.getDeliveryAddr());

			dto.setDeliveryAddr(info.getDeliveryAddr());

			dto.setEmail(info.getEmail());
			dto.setFaxNum(info.getFaxNum());

			dto.setManager(info.getManager());
			dto.setName(info.getName());

			dto.setPhoneNum(info.getPhoneNum());
			dto.setPostalCode(info.getPostalCode());
		}
		if (domain.getAgentCategory() != null) {
			dto.setAgentCategory(domain.getAgentCategory().getCategoryName());
		}
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public String getAgentCategory() {
		return agentCategory;
	}

	public void setAgentCategory(String agentCategory) {
		this.agentCategory = agentCategory;
	}

	public List<DeviceDTO> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceDTO> devices) {
		this.devices = devices;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

	public Integer getAgentAreaId() {
		return agentAreaId;
	}

	public void setAgentAreaId(Integer agentAreaId) {
		this.agentAreaId = agentAreaId;
	}

}
