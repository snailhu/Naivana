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
 * ������Ͷ��ʵ�塣
 * 
 * */
@Entity
@Table(name = "backend_complaint")
public class Complaint {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** Ͷ������ */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	/** Ͷ������ */
	@Column(length = 200, nullable = false)
	private String content;

	/** ��ϵ��ʽ */
	@Column(length = 20)
	private String contact;

	/** Ͷ������ */
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
