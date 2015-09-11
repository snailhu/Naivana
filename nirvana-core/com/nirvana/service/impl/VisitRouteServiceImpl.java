package com.nirvana.service.impl;

import org.springframework.stereotype.Service;

import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.service.VisitRouteService;

@Service
public class VisitRouteServiceImpl implements VisitRouteService {

	@Override
	public int getTodayRouteCode() {
		long time = System.currentTimeMillis();
		return VisitRoute.findCode(time);
	}

}
