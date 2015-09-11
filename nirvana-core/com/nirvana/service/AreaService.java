package com.nirvana.service;

import java.util.List;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;

/**
 * ���������Service
 * 
 * */
public interface AreaService {

	/**
	 * ��ȡ���д�����
	 * 
	 * */
	public List<BigArea> getBigAreas();

	/**
	 * ��ȡ�˴���ID�����о�������
	 * 
	 * */
	public List<ManagerArea> getManagerAreasByBigAreaId(int areaId);

	/**
	 * ��ȡ�˾������µ�������������
	 * 
	 * */
	public List<DirectorArea> getDirectorAreasByManagerAreaId(int areaId);

	/**
	 * ��ȡ���������µ�����CR����
	 * 
	 * */
	public List<AgentArea> getAgentAreasByDirectorAreaId(int areaId);

	/**
	 * ��ʼ��CR����<br>
	 * ���̰߳�ȫ���뱣֤��Ҫ�ö���̲߳���ͬһ��areaId������ж���̲߳���ͬһ��areaId�������Ҫ���м�������
	 * 
	 * */
	public void initAgentArea(int areaId);

}
