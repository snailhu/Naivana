package com.nirvana.domain.backend.goal.sdo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SDO项。
 * 
 * */
@Entity
@Table(name = "backend_goal_sdo_sdo")
public class Sdo {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** SDO目标名称。 */
	@Column(length = 1500)
	private String name;

	/** 执行标准 */
	@Column(length = 1500)
	private String criterion;

	/** 计算方式 */
	@Column(length = 1500)
	private String method;

	/** 目标值 */
	@Column(length = 1500)
	private String value;

	public Sdo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCriterion() {
		return criterion;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
