package com.nirvana.domain.backend;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nirvana.domain.backend.area.AgentArea;

/**
 * 备忘录实体。
 * 
 * */

@Entity
@Table(name = "backend_memorandum")
public class Memorandum {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 内容 */
	@Column(length = 200, nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date editDate = new Date();

	/** 关联的用户 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private AgentArea area;

	public Memorandum() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AgentArea getArea() {
		return area;
	}

	public void setArea(AgentArea area) {
		this.area = area;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

}
