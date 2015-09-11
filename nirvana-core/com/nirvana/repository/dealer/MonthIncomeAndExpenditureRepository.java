package com.nirvana.repository.dealer;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.IncomeAndExpenditureId;
import com.nirvana.domain.dealer.MonthIncomeAndExpenditure;
import com.nirvana.repository.NirvanaRepository;

public interface MonthIncomeAndExpenditureRepository extends NirvanaRepository<MonthIncomeAndExpenditure, IncomeAndExpenditureId> {

	List<MonthIncomeAndExpenditure> findByIdDealerIdAndIdDateBetween(long dealerId, int start, int end);

	@Modifying
	@Query("update MonthIncomeAndExpenditure set income=income+?1 where id=?2")
	void updateIncome(double income, IncomeAndExpenditureId id);

	@Modifying
	@Query("update MonthIncomeAndExpenditure set expenditure=expenditure+?1 where id=?2")
	void updateExpenditure(double expenditure, IncomeAndExpenditureId id);

}
