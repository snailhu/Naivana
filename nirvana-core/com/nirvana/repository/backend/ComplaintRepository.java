package com.nirvana.repository.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nirvana.domain.backend.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
	Page<Complaint> findByOrderByCreateDateDesc(Pageable page);
}
