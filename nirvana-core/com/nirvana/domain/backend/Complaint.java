package com.nirvana.domain.backend;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 经销商投诉实体。
 * 
 * */
@Entity
@Table(name = "backend_complaint")
public class Complaint {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 投诉日期 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	/** 投诉内容 */
	@Column(length = 200, nullable = false)
	private String content;

	/** 联系方式 */
	@Column(length = 20)
	private String contact;

	/** 投诉类型 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ComplaintType type;

	public Complaint() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ComplaintType getType() {
		return type;
	}

	public void setType(ComplaintType type) {
		this.type = type;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
