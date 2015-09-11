package com.nirvana.domain.dealer;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 每月收支统计
 * 
 * */
@Entity
@Table(name = "dealer_monthincomeandexpenditure")
public class MonthIncomeAndExpenditure {

	/** 联合主键 */
	@EmbeddedId
	private IncomeAndExpenditureId id;

	/** 收入。 */
	@Column(nullable = false)
	private Double income = 0d;

	/** 支出。 */
	@Column(nullable = false)
	private Double expenditure = 0d;

	@Version
	private Integer version;

	public MonthIncomeAndExpenditure() {
	}

	public IncomeAndExpenditureId getId() {
		return id;
	}

	public void setId(IncomeAndExpenditureId id) {
		this.id = id;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(Double expenditure) {
		this.expenditure = expenditure;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
