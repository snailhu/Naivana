/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年6月3日 下午2:22:20
 */
package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.domain.dealer.MonthIncomeAndExpenditure;
import com.nirvana.utils.ValidataUtil;

/**
 * @Title:MonthIncomeAndExpenditureDTO.java
 * @Description:
 * @Version:v1.0
 */
public class MonthIncomeAndExpenditureDTO {
	private Double income = 0d;

	private Double expenditure = 0d;

	public MonthIncomeAndExpenditureDTO() {
	}

	public MonthIncomeAndExpenditureDTO(List<MonthIncomeAndExpenditure> list) {
		for (MonthIncomeAndExpenditure incomeAndExpenditure : list) {
			this.income += (incomeAndExpenditure.getIncome() == null ? 0
					: incomeAndExpenditure.getIncome());
			this.expenditure += (incomeAndExpenditure.getExpenditure() == null ? 0
					: incomeAndExpenditure.getExpenditure());
		}
		income = Double.parseDouble(ValidataUtil.DECIMALFORMAT.format(income));
		expenditure = Double.parseDouble(ValidataUtil.DECIMALFORMAT
				.format(expenditure));
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
}
