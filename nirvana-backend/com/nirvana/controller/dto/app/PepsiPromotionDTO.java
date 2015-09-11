package com.nirvana.controller.dto.app;

import java.util.List;
import com.nirvana.controller.dto.ChannelDTO;
import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PepsiPromotionPic;
import com.nirvana.domain.common.Channel;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class PepsiPromotionDTO extends BaseDTO<PepsiPromotion> {
	/** ID */

	private Integer id;

	/** 是否通过采用 */
	private String promotionState;

	/** 活动标题 */

	private String title;

	/** 开始日期,形式为20101125 */
	private Integer startDate;

	/** 结束日期 ,形式为20101125 */
	private Integer endDate;

	/** 活动奖励（文字说明） */

	private String reward;

	/** 活动针对的渠道 */

	private ChannelDTO channel;

	/** 签核文件图片 */

	private List<PepsiPromotionPicDTO> pics;

	@Override
	public BaseDTO<PepsiPromotion> convert(PepsiPromotion domain) {
		PepsiPromotionDTO pepsiPromotionDto = new PepsiPromotionDTO();
		if (domain == null) {
			return pepsiPromotionDto;
		}
		pepsiPromotionDto.setId(domain.getId());
		pepsiPromotionDto.setEndDate(domain.getEndDate());
		pepsiPromotionDto.setStartDate(domain.getStartDate());
		pepsiPromotionDto.setPromotionState(domain.getState() == null ? null : domain.getState().name());
		pepsiPromotionDto.setReward(domain.getReward());
		pepsiPromotionDto.setTitle(domain.getTitle());
		Converter<Channel, ChannelDTO> converter = DTOContext.getConverter(ChannelDTO.class);

		pepsiPromotionDto.setChannel(converter.convert(domain.getChannel()));

		Converter<PepsiPromotionPic, PepsiPromotionPicDTO> converter2 = DTOContext.getConverter(PepsiPromotionPicDTO.class);
		List<PepsiPromotionPicDTO> list = converter2.convert(domain.getPics());
		pepsiPromotionDto.setPics(list);

		return pepsiPromotionDto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public Integer getEndDate() {
		return endDate;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public List<PepsiPromotionPicDTO> getPics() {
		return pics;
	}

	public void setPics(List<PepsiPromotionPicDTO> pics) {
		this.pics = pics;
	}

	public String getPromotionState() {
		return promotionState;
	}

	public void setPromotionState(String promotionState) {
		this.promotionState = promotionState;
	}

	public ChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(ChannelDTO channel) {
		this.channel = channel;
	}

}
