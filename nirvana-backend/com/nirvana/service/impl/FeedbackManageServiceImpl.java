package com.nirvana.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Complaint;
import com.nirvana.repository.backend.ComplaintRepository;
import com.nirvana.service.FeedbackManageService;

@Service
public class FeedbackManageServiceImpl implements FeedbackManageService {

	@Resource
	private ComplaintRepository complaintRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Complaint> getComplaints(int page, int size) {
		if (page < 1 || size < 1) {
			throw new IllegalArgumentException("page或者size参数不能小于1.");
		}
		Pageable pageable = new PageRequest(page - 1, size);
		Page<Complaint> complaints = complaintRepository.findByOrderByCreateDateDesc(pageable);
		return complaints;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Complaint getComplaint(int complaintId) {

		Complaint complaint = complaintRepository.findOne(complaintId);
		return complaint;
	}

	@Override
	public void deleteComplaint(int complaintId) {

		complaintRepository.delete(complaintId);
	}

	@Override
	public void sendToEmail(String email, List<Integer> complaintIds) {
		// TODO Auto-generated method stub

	}

}
