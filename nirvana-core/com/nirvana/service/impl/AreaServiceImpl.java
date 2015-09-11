package com.nirvana.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.repository.backend.AgentDealerCategoryRepository;
import com.nirvana.repository.backend.AgentStoreCategoryRepository;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.area.BigAreaRepository;
import com.nirvana.repository.backend.area.DirectorAreaRepository;
import com.nirvana.repository.backend.area.ManagerAreaRepository;
import com.nirvana.service.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	@Resource
	private BigAreaRepository bigAreaRepository;
	@Resource
	private ManagerAreaRepository managerAreaRepository;
	@Resource
	private DirectorAreaRepository directorAreaRepository;
	@Resource
	private AgentAreaRepository agentAreaRepository;
	@Resource
	private AgentStoreCategoryRepository agentStoreCategoryRepository;
	@Resource
	private AgentDealerCategoryRepository agentDealerCategoryRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<BigArea> getBigAreas() {
		return bigAreaRepository.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ManagerArea> getManagerAreasByBigAreaId(int areaId) {
		return managerAreaRepository.findByBigAreaId(areaId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DirectorArea> getDirectorAreasByManagerAreaId(int areaId) {
		return directorAreaRepository.findByManagerAreaId(areaId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AgentArea> getAgentAreasByDirectorAreaId(int areaId) {
		return agentAreaRepository.findByDirectorAreaId(areaId);
	}

	@Override
	public void initAgentArea(int areaId) {
		AgentArea area = agentAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("´ËCRÇøÎ´ÕÒµ½¡£");
		}

		AgentStoreCategory storeCategory = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(areaId, AgentStoreCategory.UNDEFINED);
		if (storeCategory == null) {
			storeCategory = new AgentStoreCategory();
			storeCategory.setArea(area);
			storeCategory.setCategoryName(AgentStoreCategory.UNDEFINED);
			agentStoreCategoryRepository.save(storeCategory);
		}

		AgentDealerCategory dealerCategory = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(areaId, AgentDealerCategory.UNDEFINED);
		if (dealerCategory == null) {
			dealerCategory = new AgentDealerCategory();
			dealerCategory.setArea(area);
			dealerCategory.setCategoryName(AgentDealerCategory.UNDEFINED);
			agentDealerCategoryRepository.save(dealerCategory);
		}

		Set<VisitRoute> routes = area.getRoutes();
		if (routes == null || routes.size() == 0) {
			routes = new HashSet<VisitRoute>();
			for (int i = 1; i <= 10; i++) {
				VisitRoute route = new VisitRoute();
				route.setArea(area);
				route.setCode(i);
				routes.add(route);
			}

			area.setRoutes(routes);
		}

		agentAreaRepository.saveAndFlush(area);

	}
}
