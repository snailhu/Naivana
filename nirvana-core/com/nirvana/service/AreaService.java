package com.nirvana.service;

import java.util.List;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;

/**
 * 关于区域的Service
 * 
 * */
public interface AreaService {

	/**
	 * 获取所有大区。
	 * 
	 * */
	public List<BigArea> getBigAreas();

	/**
	 * 获取此大区ID的所有经理区。
	 * 
	 * */
	public List<ManagerArea> getManagerAreasByBigAreaId(int areaId);

	/**
	 * 获取此经理区下的所有主任区。
	 * 
	 * */
	public List<DirectorArea> getDirectorAreasByManagerAreaId(int areaId);

	/**
	 * 获取此主任区下的所有CR区。
	 * 
	 * */
	public List<AgentArea> getAgentAreasByDirectorAreaId(int areaId);

	/**
	 * 初始化CR区。<br>
	 * 非线程安全，请保证不要让多个线程操作同一个areaId，如果有多个线程操作同一个areaId的情况需要进行加锁处理。
	 * 
	 * */
	public void initAgentArea(int areaId);

}
