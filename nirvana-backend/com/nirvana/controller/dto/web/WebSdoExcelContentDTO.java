package com.nirvana.controller.dto.web;

import java.util.ArrayList;
import java.util.List;

import com.nirvana.domain.backend.goal.sdo.SdoExcelContent;
import com.nirvana.domain.backend.goal.sdo.SdoExcelContentPart;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class WebSdoExcelContentDTO extends BaseDTO<SdoExcelContent> {

	/** ID */
	private Integer id=1;

	/** БъЬт */
	private String title="sd";

	private List<WebSdoExcelContentPartDTO> parts=new ArrayList<WebSdoExcelContentPartDTO>();

	public WebSdoExcelContentDTO() {
		//TODO:shanchu
		parts.add(new WebSdoExcelContentPartDTO());
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

	public List<WebSdoExcelContentPartDTO> getParts() {
		return parts;
	}

	public void setParts(List<WebSdoExcelContentPartDTO> parts) {
		this.parts = parts;
	}

	@Override
	public WebSdoExcelContentDTO convert(SdoExcelContent domain) {
		WebSdoExcelContentDTO dto = new WebSdoExcelContentDTO();
		if(domain==null){
			return dto;
		}
		Converter<SdoExcelContentPart, WebSdoExcelContentPartDTO> converter = DTOContext.getConverter(WebSdoExcelContentPartDTO.class);
		dto.setId(domain.getId());
		dto.setTitle(domain.getTitle());
		dto.setParts(converter.convert(domain.getParts()));
		return dto;
	}
}
