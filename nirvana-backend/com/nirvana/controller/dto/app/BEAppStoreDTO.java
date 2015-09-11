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
	// TODO �����������
	/** �ŵ�ID */
	private Long id = 1l;
	private Integer agentAreaId = 1;

	/** ERP�еĿͻ���� */
	private String codeInERP = "��codeInERP";

	/** �ŵ���ҵ��Ա�µķ��� */
	private String agentCategory = "�ٷ���";

	private List<DeviceDTO> devices = new ArrayList<DeviceDTO>();

	/** ���� */
	private String notes = "�ٱ�ע";

	/**
	 * ������Ϣ
	 */
	/** ���� */
	private String name = "����";

	/** ���������� */
	private String manager = "����������";

	/** �������� */
	private String postalCode = "�ʱ�";

	/** �绰�ֻ����� */
	private String phoneNum = "�绰����";

	/** ���� */
	private String email = "����";

	private String faxNum = "����";

	/** ��ַ��ϸ��Ϣ */
	private String addrDetail = "��ϸ��ַ";

	/** �ͻ���ַ */
	private String deliveryAddr = "�ͻ���ַ";

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
