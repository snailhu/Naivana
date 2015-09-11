package com.nirvana.controller.dto.web;

import com.nirvana.domain.backend.goal.sdo.SdoExcelTitle;
import com.nirvana.pojo4json.BaseDTO;

public class WebSdoExcelTitleDTO extends BaseDTO<SdoExcelTitle> {

	/** ID */
	private Integer id=1;

	/** 1标题 */
	private String title1="xx";

	/** 3标题 */
	private String title3="xx";

	/** 4标题 */
	private String title4="xx";

	/** 5标题 */
	private String title5="xx";

	/** 6标题 */
	private String title6="xx";

	/** 7标题 */
	private String title7="xx";

	/** 8标题 */
	private String title8="xx";

	/** 9标题 */
	private String title9="xx";

	/** 10标题 */
	private String title10="xx";

	/** 11标题 */
	private String title11="xx";

	/** 12标题 */
	private String title12="xx";

	/** 13标题 */
	private String title13="xx";

	/** 14标题 */
	private String title14="xx";

	public WebSdoExcelTitleDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public String getTitle4() {
		return title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	public String getTitle5() {
		return title5;
	}

	public void setTitle5(String title5) {
		this.title5 = title5;
	}

	public String getTitle6() {
		return title6;
	}

	public void setTitle6(String title6) {
		this.title6 = title6;
	}

	public String getTitle7() {
		return title7;
	}

	public void setTitle7(String title7) {
		this.title7 = title7;
	}

	public String getTitle8() {
		return title8;
	}

	public void setTitle8(String title8) {
		this.title8 = title8;
	}

	public String getTitle9() {
		return title9;
	}

	public void setTitle9(String title9) {
		this.title9 = title9;
	}

	public String getTitle10() {
		return title10;
	}

	public void setTitle10(String title10) {
		this.title10 = title10;
	}

	public String getTitle11() {
		return title11;
	}

	public void setTitle11(String title11) {
		this.title11 = title11;
	}

	public String getTitle12() {
		return title12;
	}

	public void setTitle12(String title12) {
		this.title12 = title12;
	}

	public String getTitle13() {
		return title13;
	}

	public void setTitle13(String title13) {
		this.title13 = title13;
	}

	public String getTitle14() {
		return title14;
	}

	public void setTitle14(String title14) {
		this.title14 = title14;
	}

	@Override
	public WebSdoExcelTitleDTO convert(SdoExcelTitle domain) {
		WebSdoExcelTitleDTO dto = new WebSdoExcelTitleDTO();
		if(domain==null){
			return dto;
		}
		dto.setId(domain.getId());
		dto.setTitle1(domain.getTitle1());
		dto.setTitle3(domain.getTitle3());
		dto.setTitle4(domain.getTitle4());
		dto.setTitle5(domain.getTitle5());
		dto.setTitle6(domain.getTitle6());
		dto.setTitle7(domain.getTitle7());
		dto.setTitle8(domain.getTitle8());
		dto.setTitle9(domain.getTitle9());
		dto.setTitle10(domain.getTitle10());
		dto.setTitle11(domain.getTitle11());
		dto.setTitle12(domain.getTitle12());
		dto.setTitle13(domain.getTitle13());
		dto.setTitle14(domain.getTitle14());
		return dto;
	}

}
