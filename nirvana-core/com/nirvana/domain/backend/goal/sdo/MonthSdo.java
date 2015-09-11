package com.nirvana.domain.backend.goal.sdo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 每月的SDO实体。
 * 
 * */
@Entity
@Table(name = "backend_goal_sdo_monthsdo")
public class MonthSdo {

	/** 形式为201205的年月作为主键。 */
	@Id
	private Integer date;

	/** 所有的SDO项 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "sdo_date")
	private List<Sdo> sdos;

	public MonthSdo() {
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public List<Sdo> getSdos() {
		return sdos;
	}

	public void setSdos(List<Sdo> sdos) {
		this.sdos = sdos;
	}

}
