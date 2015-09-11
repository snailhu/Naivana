package com.nirvana.domain.common;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "common_syncdate")
public class SyncDate {

	@Id
	@GeneratedValue
	private Integer id;

	/** 客户数据同步日期。 */
	private Timestamp time = new Timestamp(System.currentTimeMillis());

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private SyncType type;

	public SyncDate() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public SyncType getType() {
		return type;
	}

	public void setType(SyncType type) {
		this.type = type;
	}

}
