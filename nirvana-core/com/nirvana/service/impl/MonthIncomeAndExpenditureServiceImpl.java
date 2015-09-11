package com.nirvana.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.IncomeAndExpenditureId;
import com.nirvana.domain.dealer.MonthIncomeAndExpenditure;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.MonthIncomeAndExpenditureRepository;
import com.nirvana.service.MonthIncomeAndExpenditureService;
import com.nirvana.utils.Assert;
import com.nirvana.utils.DateUtil;

@Service
@Transactional
public class MonthIncomeAndExpenditureServiceImpl implements MonthIncomeAndExpenditureService {

	@Resource
	private MonthIncomeAndExpenditureRepository monthIncomeAndExpenditureRepository;
	@Resource
	private DealerRepository dealerRepository;

	@Override
	public void updateThisMonthIncome(Dealer dealer, double income) {
		IncomeAndExpenditureId id = getId(dealer);
		monthIncomeAndExpenditureRepository.updateIncome(income, id);
	}

	private IncomeAndExpenditureId getId(Dealer dealer) {
		Assert.notNull(dealer);
		IncomeAndExpenditureId id = new IncomeAndExpenditureId();
		id.setDealer(dealer);
		id.setDate(Integer.parseInt(DateUtil.dateToString(new Date(), "yyyyMM")));
		MonthIncomeAndExpenditure monthIncomeAndExpenditure = monthIncomeAndExpenditureRepository.findOne(id);
		if (monthIncomeAndExpenditure == null) {
			dealer = dealerRepository.findOne(dealer.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			monthIncomeAndExpenditure = new MonthIncomeAndExpenditure();
			monthIncomeAndExpenditure.setId(id);
			monthIncomeAndExpenditureRepository.saveAndFlush(monthIncomeAndExpenditure);
		}
		return id;
	}

	@Override
	public void updateThisMonthExpenditure(Dealer dealer, double expenditure) {
		IncomeAndExpenditureId id = getId(dealer);
		monthIncomeAndExpenditureRepository.updateExpenditure(expenditure, id);
	}

}
