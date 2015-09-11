package com.nirvana.controller.dto.web;

import com.nirvana.pojo4json.BaseDTO;

public class WebPictureDTO extends BaseDTO<String> {
	private String url;

	@Override
	public BaseDTO<String> convert(String domain) {
		// TODO Auto-generated method stub
		WebPictureDTO dto = new WebPictureDTO();
		dto.setUrl(domain);
		return dto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
