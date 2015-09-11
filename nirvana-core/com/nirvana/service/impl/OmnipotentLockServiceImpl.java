package com.nirvana.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.common.LockType;
import com.nirvana.domain.common.OmnipotentLock;
import com.nirvana.repository.common.OmnipotentLockRepository;
import com.nirvana.service.OmnipotentLockService;

@Service
@Transactional
public class OmnipotentLockServiceImpl implements OmnipotentLockService {

	@Resource
	private OmnipotentLockRepository omnipotentLockRepository;

	@PostConstruct
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void init() {

		LockType[] types = LockType.values();
		for (LockType type : types) {
			OmnipotentLock sisLock = omnipotentLockRepository.findByLockType(type);
			if (sisLock == null) {
				sisLock = new OmnipotentLock(type);
				omnipotentLockRepository.save(sisLock);
			}
		}

	}

}
