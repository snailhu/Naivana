package com.nirvana.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ����
 * 
 * */
@Entity
@Table(name = "common_channel")
public class Channel {
	/** ID */
	@Id
	@Column(length = 10)
	public String code;

	/** �������� */
	@Column(length = 40, nullable = false)
	public String description;

	/** ���������� */
	@Column(length = 20, nullable = false)
	public String groupName;

	/** �汾 */
	@Column(length = 40, nullable = false)
	public String version;

	public Channel() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
