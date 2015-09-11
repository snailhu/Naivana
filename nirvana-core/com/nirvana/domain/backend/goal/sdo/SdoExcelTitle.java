package com.nirvana.domain.backend.goal.sdo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SDO目标的EXCEL标题实体。
 * 
 * */
@Entity
@Table(name = "backend_goal_sdo_sdoexceltitle")
public class SdoExcelTitle {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 1标题 */
	@Column(length = 1500)
	private String title1;

	/** 3标题 */
	@Column(length = 1500)
	private String title3;

	/** 4标题 */
	@Column(length = 1500)
	private String title4;

	/** 5标题 */
	@Column(length = 1500)
	private String title5;

	/** 6标题 */
	@Column(length = 1500)
	private String title6;

	/** 7标题 */
	@Column(length = 1500)
	private String title7;

	/** 8标题 */
	@Column(length = 1500)
	private String title8;

	/** 9标题 */
	@Column(length = 1500)
	private String title9;

	/** 10标题 */
	@Column(length = 1500)
	private String title10;

	/** 11标题 */
	@Column(length = 1500)
	private String title11;

	/** 12标题 */
	@Column(length = 1500)
	private String title12;

	/** 13标题 */
	@Column(length = 1500)
	private String title13;

	/** 14标题 */
	@Column(length = 1500)
	private String title14;

	public SdoExcelTitle() {
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

}
