package com.nirvana.domain.backend.goal.sdo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SDO的EXCEL表格内容。
 * 
 * */
@Entity
@Table(name = "backend_goal_sdo_sdoexcelcontent")
public class SdoExcelContent {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 标题 */
	@Column(length = 1500)
	private String title;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	private List<SdoExcelContentPart> parts;

	public SdoExcelContent() {
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

	public List<SdoExcelContentPart> getParts() {
		return parts;
	}

	public void setParts(List<SdoExcelContentPart> parts) {
		this.parts = parts;
	}

}
