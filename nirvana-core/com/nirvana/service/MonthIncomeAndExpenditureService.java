package com.nirvana.service;

import com.nirvana.domain.dealer.Dealer;

public interface MonthIncomeAndExpenditureService {

	/**
	 * ���롣�̰߳�ȫ�ġ�
	 * 
	 * */
	void updateThisMonthIncome(Dealer dealer, double income);

	/**
	 * ֧�����̰߳�ȫ�ġ�
	 * 
	 * */
	void updateThisMonthExpenditure(Dealer dealer, double expenditure);
}
