package com.nirvana.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nirvana.domain.backend.Complaint;

/**
 * 反馈信息service.
 * 
 * */
public interface FeedbackManageService {

	/**
	 * 获取所有反馈的分页信息。
	 * 
	 * */
	public Page<Complaint> getComplaints(int page, int size);

	/**
	 * 获取某条反馈信息。
	 * 
	 * */
	public Complaint getComplaint(int complaintId);

	/**
	 * 删除掉某条反馈信息。
	 * 
	 * */
	public void deleteComplaint(int complaintId);

	/**
	 * 发送EMAIL.
	 * 
	 * */
	public void sendToEmail(String email, List<Integer> complaintIds);

}
