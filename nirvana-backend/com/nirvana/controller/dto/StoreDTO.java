/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年5月27日 上午8:57:01
 */
package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Device;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.DateUtil;

/**
 * @Title:StoreDTO.java
 * @Description:
 * @Version:v1.0
 */
public class StoreDTO extends BaseDTO<Store> {
	/** 门店ID */
	private Long id;

	/** ERP中的客户编号 */
	private String codeInERP;

	/** 所属CR区（小区） */
	private Integer agentAreaId;

	/** 二阶经销商 */
	private Long dealerId;

	/** 门店店面信息 */
	private StoreStorefrontInfoDTO storefrontInfo;

	/** 门店在业务员下的分类 */
	private Long agentCategoryId;
	private String agentCategoryName;

	/** 门店在经销商下的分类 */
	private Long dealerCategoryId;
	private String dealerCategoryName = DealerStoreCategory.UNDEFINED;

	/** 渠道 */
	private ChannelDTO channel;

	/** 设备 */
	private List<DeviceDTO> devices;

	/** 备忘 */
	private String notes;

	/** 是否关闭 */
	private Boolean isClose = false;

	/** 关闭原因 */
	private String closeReason;

	/** 关闭日期 */
	private String closeDate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Store> convert(Store domain) {
		StoreDTO dto = new StoreDTO();
		if (domain == null) {
			return dto;
		}
		if (domain.getAgentArea() != null) {
			dto.setAgentAreaId(domain.getAgentArea().getId());
		}
		if (domain.getAgentCategory() != null) {
			dto.setAgentCategoryId(domain.getAgentCategory().getId());
			dto.setAgentCategoryName(domain.getAgentCategory()
					.getCategoryName());
		}
		Converter<Channel, ChannelDTO> channelConverter = DTOContext
				.getConverter(ChannelDTO.class);
		dto.setChannel(channelConverter.convert(domain.getChannel()));
		dto.setCloseDate(domain.getCloseDate() == null ? null : DateUtil
				.dateToString(domain.getCloseDate()));
		dto.setCloseReason(domain.getCloseReason());
		dto.setCodeInERP(domain.getCodeInERP());
		if (domain.getDealerCategory() != null) {
			dto.setDealerCategoryId(domain.getDealerCategory().getId());
			dto.setDealerCategoryName(domain.getDealerCategory()
					.getCategoryName());
		}
		if (domain.getDealer() != null) {
			dto.setDealerId(domain.getDealer().getId());
		}
		Converter<Device, DeviceDTO> deviceConverter = DTOContext
				.getConverter(DeviceDTO.class);
		dto.setDevices(deviceConverter.convert(domain.getDevices()));
		dto.setId(domain.getId());
		dto.setIsClose(domain.getIsClose());
		dto.setNotes(domain.getNotes());
		Converter<StoreStorefrontInfo, StoreStorefrontInfoDTO> infoConverter = DTOContext
				.getConverter(StoreStorefrontInfoDTO.class);
		dto.setStorefrontInfo(infoConverter.convert(domain.getStorefrontInfo()));

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

	public Integer getAgentAreaId() {
		return agentAreaId;
	}

	public void setAgentAreaId(Integer agentAreaId) {
		this.agentAreaId = agentAreaId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public StoreStorefrontInfoDTO getStorefrontInfo() {
		return storefrontInfo;
	}

	public void setStorefrontInfo(StoreStorefrontInfoDTO storefrontInfo) {
		this.storefrontInfo = storefrontInfo;
	}

	public Long getAgentCategoryId() {
		return agentCategoryId;
	}

	public void setAgentCategoryId(Long agentCategoryId) {
		this.agentCategoryId = agentCategoryId;
	}

	public String getAgentCategoryName() {
		return agentCategoryName;
	}

	public void setAgentCategoryName(String agentCategoryName) {
		this.agentCategoryName = agentCategoryName;
	}

	public Long getDealerCategoryId() {
		return dealerCategoryId;
	}

	public void setDealerCategoryId(Long dealerCategoryId) {
		this.dealerCategoryId = dealerCategoryId;
	}

	public String getDealerCategoryName() {
		return dealerCategoryName;
	}

	public void setDealerCategoryName(String dealerCategoryName) {
		this.dealerCategoryName = dealerCategoryName;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getIsClose() {
		return isClose;
	}

	public void setIsClose(Boolean isClose) {
		this.isClose = isClose;
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
}
