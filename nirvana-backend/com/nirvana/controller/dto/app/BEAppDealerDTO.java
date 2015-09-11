package com.nirvana.controller.dto.app;

import java.util.ArrayList;
import java.util.List;

import com.nirvana.domain.common.Device;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class BEAppDealerDTO extends BaseDTO<Dealer> {
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

	/** �ŵ������m2�� */
	private Integer squareMeters = 100;

	/** ��ַ��ϸ��Ϣ */
	private String addrDetail = "��ϸ��ַ";

	/** �ͻ���ַ */
	private String deliveryAddr = "�ͻ���ַ";

	public BEAppDealerDTO() {
		devices.add(new DeviceDTO());
	}

	@Override
	public BaseDTO<Dealer> convert(Dealer domain) {
		BEAppDealerDTO dto = new BEAppDealerDTO();
		if (domain == null) {
			dto.getDevices().add(new DeviceDTO());
			return dto;
		}
		dto.setCodeInERP(domain.getCodeInERP());
		dto.setId(domain.getId());
		// TODO:delete 100
		dto.setAgentAreaId(domain.getAgentArea() == null ? 100 : domain.getAgentArea().getId());
		dto.setNotes(domain.getNotes());
		Converter<Device, DeviceDTO> converter = DTOContext.getConverter(DeviceDTO.class);
		dto.setDevices(converter.convert(domain.getDevices()));
		DealerStorefrontInfo info = domain.getStorefrontInfo();
		if (info != null) {
			dto.setManager(info.getManager());
			dto.setName(info.getName());
		}
		if (domain.getCategory() != null) {
			dto.setAgentCategory(domain.getCategory().getCategoryName());
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

	public Integer getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(Integer squareMeters) {
		this.squareMeters = squareMeters;
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
