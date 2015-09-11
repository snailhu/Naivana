package com.nirvana.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备。
 * 
 * */
@Entity
@Table(name = "common_device")
public class Device {

	@Id
	@GeneratedValue
	private Long id;

	/** 设备码 */
	@Column(length = 20)
	private String code;

	public Device() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
