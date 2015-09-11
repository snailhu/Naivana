package com.nirvana.controller.dto.app;

import com.nirvana.domain.backend.PepsiPromotionPic;
import com.nirvana.fdfs.FileOperator;
import com.nirvana.pojo4json.BaseDTO;

public class PepsiPromotionPicDTO extends BaseDTO<PepsiPromotionPic> {

	private String url;

	public PepsiPromotionPicDTO() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public BaseDTO<PepsiPromotionPic> convert(PepsiPromotionPic domain) {
		PepsiPromotionPicDTO dto = new PepsiPromotionPicDTO();
		dto.setUrl(FileOperator.getUrl(domain.getUrl()));
		return dto;
	}
}
