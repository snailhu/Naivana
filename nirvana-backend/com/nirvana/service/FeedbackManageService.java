package com.nirvana.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nirvana.domain.backend.Complaint;

/**
 * ������Ϣservice.
 * 
 * */
public interface FeedbackManageService {

	/**
	 * ��ȡ���з����ķ�ҳ��Ϣ��
	 * 
	 * */
	public Page<Complaint> getComplaints(int page, int size);

	/**
	 * ��ȡĳ��������Ϣ��
	 * 
	 * */
	public Complaint getComplaint(int complaintId);

	/**
	 * ɾ����ĳ��������Ϣ��
	 * 
	 * */
	public void deleteComplaint(int complaintId);

	/**
	 * ����EMAIL.
	 * 
	 * */
	public void sendToEmail(String email, List<Integer> complaintIds);

}
