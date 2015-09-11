package com.nirvana.controller.dto.web;

import java.util.ArrayList;
import java.util.List;

import com.nirvana.domain.backend.goal.sdo.SdoExcelContentPart;
import com.nirvana.domain.backend.goal.sdo.SdoExcelContentPartElement;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class WebSdoExcelContentPartDTO extends BaseDTO<SdoExcelContentPart> {

	/** ID */
	private Integer id=1;

	/** ±êÌâ */
	private String title="sd";

	/** SDOÔªËØ */
	private List<WebSdoExcelContentPartElementDTO> elements=new ArrayList<WebSdoExcelContentPartElementDTO>();

	public WebSdoExcelContentPartDTO() {
		//TODO shan chu
		elements.add(new WebSdoExcelContentPartElementDTO());
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

	public List<WebSdoExcelContentPartElementDTO> getElements() {
		return elements;
	}

	public void setElements(List<WebSdoExcelContentPartElementDTO> elements) {
		this.elements = elements;
	}

	@Override
	public WebSdoExcelContentPartDTO convert(SdoExcelContentPart domain) {
		WebSdoExcelContentPartDTO dto = new WebSdoExcelContentPartDTO();
		if(domain==null){
			return dto;
		}
		dto.setId(domain.getId());
		dto.setTitle(domain.getTitle());
		Converter<SdoExcelContentPartElement, WebSdoExcelContentPartElementDTO> converter = DTOContext.getConverter(WebSdoExcelContentPartElementDTO.class);
		dto.setElements(converter.convert(domain.getElements()));
		return dto;
	}

}
