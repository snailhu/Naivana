package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Device;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class DealerDTO extends BaseDTO<Dealer> {
	/** 经销商ID */
	private Long id;

	private String bindNum;

	/** ERP中的客户编号 */
	private String codeInERP;

	/** 经销商店面信息 */
	private DealerStorefrontInfoDTO storefrontInfo;

	/** 所属主任区 */
	private Integer directorAreaId;

	/** 是否直营 */
	private Boolean isDirect;

	/** 直营店所属的小区 */
	private Integer agentAreaId;

	/** 渠道 */
	private ChannelDTO channel;

	/** 设备 */
	private List<DeviceDTO> devices;

	/** 是否关闭 */
	private Boolean isClose;

	/** 直营店在业务员中的分类。 */
	private Long categoryId;
	private String categoryName;

	/** 备忘 */
	private String notes;

	/** 版本信息 */
	private String version;

	@Override
	public BaseDTO<Dealer> convert(Dealer domain) {
		DealerDTO dto = new DealerDTO();
		if (domain == null) {
			return dto;
		}
		if (domain.getUser() != null) {
			dto.setBindNum(domain.getUser().getPhone());
		}
		if (domain.getAgentArea() != null) {
			dto.setAgentAreaId(domain.getAgentArea().getId());
		}
		if (domain.getCategory() != null) {
			dto.setCategoryId(domain.getCategory().getId());
			dto.setCategoryName(domain.getCategory().getCategoryName());
		}
		Converter<Channel, ChannelDTO> channelConverter = DTOContext.getConverter(ChannelDTO.class);
		dto.setChannel(channelConverter.convert(domain.getChannel()));
		dto.setCodeInERP(domain.getCodeInERP());
		Converter<Device, DeviceDTO> deviceConverter = DTOContext.getConverter(DeviceDTO.class);
		dto.setDevices(deviceConverter.convert(domain.getDevices()));
		if (domain.getDirectorArea() != null) {
			dto.setDirectorAreaId(domain.getDirectorArea().getId());
		}
		dto.setId(domain.getId());
		dto.setIsClose(domain.getIsClose());
		dto.setIsDirect(domain.getIsDirect());
		dto.setNotes(domain.getNotes());
		Converter<DealerStorefrontInfo, DealerStorefrontInfoDTO> infoConverter = DTOContext.getConverter(DealerStorefrontInfoDTO.class);
		dto.setStorefrontInfo(infoConverter.convert(domain.getStorefrontInfo()));
		dto.setVersion(domain.getVersion());
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

	public DealerStorefrontInfoDTO getStorefrontInfo() {
		return storefrontInfo;
	}

	public void setStorefrontInfo(DealerStorefrontInfoDTO storefrontInfo) {
		this.storefrontInfo = storefrontInfo;
	}

	public Integer getDirectorAreaId() {
		return directorAreaId;
	}

	public void setDirectorAreaId(Integer directorAreaId) {
		this.directorAreaId = directorAreaId;
	}

	public Boolean getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(Boolean isDirect) {
		this.isDirect = isDirect;
	}

	public Integer getAgentAreaId() {
		return agentAreaId;
	}

	public void setAgentAreaId(Integer agentAreaId) {
		this.agentAreaId = agentAreaId;
	}

	public ChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(ChannelDTO channel) {
		this.channel = channel;
	}

	public List<DeviceDTO> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceDTO> devices) {
		this.devices = devices;
	}

	public Boolean getIsClose() {
		return isClose;
	}

	public void setIsClose(Boolean isClose) {
		this.isClose = isClose;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBindNum() {
		return bindNum;
	}

	public void setBindNum(String bindNum) {
		this.bindNum = bindNum;
	}
}
