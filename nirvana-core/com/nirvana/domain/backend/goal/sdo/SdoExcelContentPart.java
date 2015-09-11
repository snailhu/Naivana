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
 * EXCEL内容块。
 * 
 * */
@Entity
@Table(name = "backend_goal_sdo_sdoexcelcontentpart")
public class SdoExcelContentPart {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 标题 */
	@Column(length = 1500)
	private String title;

	/** SDO元素 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "part_id")
	private List<SdoExcelContentPartElement> elements;

	public SdoExcelContentPart() {
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

	public List<SdoExcelContentPartElement> getElements() {
		return elements;
	}

	public void setElements(List<SdoExcelContentPartElement> elements) {
		this.elements = elements;
	}

}
