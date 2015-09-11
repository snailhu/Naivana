package com.nirvana.domain.backend.goal.sdo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * SDO的Excel表格实体。
 * 
 * */
@Entity
@Table(name = "backend_goal_sdo_sdoexcel")
public class SdoExcel {

	/** 形式为201205的年月作为主键。 */
	@Id
	private Integer date;

	/** Excel的标题栏 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "title_id")
	private SdoExcelTitle title;

	/** Excel的内容栏 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	private SdoExcelContent content;

	public SdoExcel() {
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public SdoExcelTitle getTitle() {
		return title;
	}

	public void setTitle(SdoExcelTitle title) {
		this.title = title;
	}

	public SdoExcelContent getContent() {
		return content;
	}

	public void setContent(SdoExcelContent content) {
		this.content = content;
	}

}
