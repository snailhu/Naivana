package com.nirvana.domain.dealer;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * ÿ����֧ͳ��
 * 
 * */
@Entity
@Table(name = "dealer_monthincomeandexpenditure")
public class MonthIncomeAndExpenditure {

	/** �������� */
	@EmbeddedId
	private IncomeAndExpenditureId id;

	/** ���롣 */
	@Column(nullable = false)
	private Double income = 0d;

	/** ֧���� */
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
