package com.nirvana.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Complaint;
import com.nirvana.domain.backend.ComplaintType;
import com.nirvana.repository.backend.ComplaintRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.service.DealerComplaintService;

@Service
@Transactional
public class DealerComplaintServiceImpl implements DealerComplaintService {

	@Resource
	private ComplaintRepository complaintRepository;
	@Resource
	private DealerRepository dealerRepository;

	@Override
	public void complaint(String type, String contact, String content) {
		ComplaintType t = ComplaintType.parseComplaintType(type);
		Complaint complaint = new Complaint();
		complaint.setContact(contact);
		complaint.setContent(content);
		complaint.setCreateDate(new Date());
		complaint.setType(t);
		complaintRepository.save(complaint);
	}

}
