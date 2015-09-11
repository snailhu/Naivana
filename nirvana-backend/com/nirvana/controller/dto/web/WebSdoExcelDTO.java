package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.goal.sdo.SdoExcel;
import com.nirvana.domain.backend.goal.sdo.SdoExcelContent;
import com.nirvana.domain.backend.goal.sdo.SdoExcelTitle;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;

public class WebSdoExcelDTO extends BaseDTO<SdoExcel> {

	/** 形式为201205的年月作为主键。 */
	private Integer date=201205;

	/** Excel的标题栏 */
	private WebSdoExcelTitleDTO title=new WebSdoExcelTitleDTO();

	/** Excel的内容栏 */
	private WebSdoExcelContentDTO content=new WebSdoExcelContentDTO();

	public WebSdoExcelDTO() {
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public WebSdoExcelTitleDTO getTitle() {
		return title;
	}

	public void setTitle(WebSdoExcelTitleDTO title) {
		this.title = title;
	}

	public WebSdoExcelContentDTO getContent() {
		return content;
	}

	public void setContent(WebSdoExcelContentDTO content) {
		this.content = content;
	}

	@Override
	public WebSdoExcelDTO convert(SdoExcel domain) {
		WebSdoExcelDTO dto = new WebSdoExcelDTO();
		if(domain==null){
			return dto;
		}
		dto.setDate(domain.getDate());
		Converter<SdoExcelTitle, WebSdoExcelTitleDTO> converter1 = DTOContext.getConverter(WebSdoExcelTitleDTO.class);
		Converter<SdoExcelContent, WebSdoExcelContentDTO> converter2 = DTOContext.getConverter(WebSdoExcelContentDTO.class);
		dto.setContent(converter2.convert(domain.getContent()));
		dto.setTitle(converter1.convert(domain.getTitle()));
		return dto;
	}
}
