package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.goal.sdo.SdoExcelContentPartElement;
import com.nirvana.pojo4json.BaseDTO;

public class WebSdoExcelContentPartElementDTO extends BaseDTO<SdoExcelContentPartElement> {

	/** ID */
	private Integer id=1;

	private String p3="df";

	private String p4="df";

	private String p5="df";

	private String p6="df";

	private String p7="df";

	private String p8="df";

	private String p9="df";

	private String p10="df";

	private String p11="df";

	private String p12="df";

	private String p13="df";

	private String p14="df";

	public WebSdoExcelContentPartElementDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public String getP7() {
		return p7;
	}

	public void setP7(String p7) {
		this.p7 = p7;
	}

	public String getP8() {
		return p8;
	}

	public void setP8(String p8) {
		this.p8 = p8;
	}

	public String getP9() {
		return p9;
	}

	public void setP9(String p9) {
		this.p9 = p9;
	}

	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}

	public String getP11() {
		return p11;
	}

	public void setP11(String p11) {
		this.p11 = p11;
	}

	public String getP12() {
		return p12;
	}

	public void setP12(String p12) {
		this.p12 = p12;
	}

	public String getP13() {
		return p13;
	}

	public void setP13(String p13) {
		this.p13 = p13;
	}

	public String getP14() {
		return p14;
	}

	public void setP14(String p14) {
		this.p14 = p14;
	}

	@Override
	public WebSdoExcelContentPartElementDTO convert(SdoExcelContentPartElement domain) {
		WebSdoExcelContentPartElementDTO dto = new WebSdoExcelContentPartElementDTO();
		if(domain==null){
			return dto;
		}
		dto.setId(domain.getId());
		dto.setP3(domain.getP3());
		dto.setP4(domain.getP4());
		dto.setP5(domain.getP5());
		dto.setP6(domain.getP6());
		dto.setP7(domain.getP7());
		dto.setP8(domain.getP8());
		dto.setP9(domain.getP9());
		dto.setP10(domain.getP10());
		dto.setP11(domain.getP11());
		dto.setP12(domain.getP12());
		dto.setP13(domain.getP13());
		dto.setP14(domain.getP14());
		return dto;
	}

}
