package com.nirvana.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.mongo.repository.VisitRecordDocumentRepository;
import com.nirvana.repository.backend.VisitRouteRepository;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.statistics.DayAgentStatisticsRepository;
import com.nirvana.repository.backend.statistics.MonthAgentStatisticsRepository;
import com.nirvana.repository.dealer.DealerOrderRepository;
import com.nirvana.repository.store.StoreOrderRepository;
import com.nirvana.service.StatisticsService;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	private AgentAreaRepository agentAreaRepository;
	@Resource
	private DealerOrderRepository dealerOrderRepository;
	@Resource
	private StoreOrderRepository storeOrderRepository;
	@Resource
	private DayAgentStatisticsRepository dayAgentStatisticsRepository;
	@Resource
	private MonthAgentStatisticsRepository monthAgentStatisticsRepository;
	@Resource
	private VisitRouteRepository visitRouteRepository;
	@Resource
	private VisitRecordDocumentRepository visitRecordDocumentRepository;

	@Override
	public void daysAgentStatistics() {
		// TODO Auto-generated method stub

	}

	@Override
	public void monthsAgentstatistics() {
		// TODO Auto-generated method stub

	}

}
