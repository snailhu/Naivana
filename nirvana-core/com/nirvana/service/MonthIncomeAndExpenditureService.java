package com.nirvana.service;

import com.nirvana.domain.dealer.Dealer;

public interface MonthIncomeAndExpenditureService {

	/**
	 * 收入。线程安全的。
	 * 
	 * */
	void updateThisMonthIncome(Dealer dealer, double income);

	/**
	 * 支出。线程安全的。
	 * 
	 * */
	void updateThisMonthExpenditure(Dealer dealer, double expenditure);
}
