package com.nirvana.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.service.pojo.web.PositionData;

public interface PersonnelManageService {

	/**
	 * 创建新的员工。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param realName
	 *            真实姓名
	 * @param ECode
	 *            用户编码
	 * @param phoneNum
	 *            绑定的手机号
	 * 
	 * */
	public void createEmployee(String username, String password, String realName, String eCode, String phoneNum);

	/**
	 * 编辑用户。
	 * 
	 * 
	 * */
	public void editEmployee(long employeeId, String password, String realName, String eCode, String phoneNum, Boolean close);

	/**
	 * 获取所有的员工。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * */
	public Page<Employee> getEmployees(int page, int size);

	/**
	 * 获取单个员工。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * */
	public Employee getEmployee(long id);

	/**
	 * 锁定用户。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * */
	public void lockEmployee(String eCode);

	/**
	 * 获取某个员工的职位。
	 * 
	 * 权限：管理员，文员。
	 * 
	 * */
	public List<Position> getPositon(long employeeId);

	/**
	 * 删除某个员工的职位。
	 * 
	 * 权限：管理员，文员。管理员可以任意操作，文员不能删除非自己管理的职位。
	 * 
	 * */
	public void deletePosition(long employeeId, int positionId);

	/**
	 * 获取所有的职位类型。
	 * 
	 * */
	public List<PositionType> getAllPositionType();

	/**
	 * 给员工添加职位。
	 * 
	 * 权限：管理员，文员。管理员可以任意操作，文员不能添加非自己管理的职位。
	 * 
	 * */
	public void addPositions(long employeeId, List<PositionData> datas);

	/**
	 * 移动经理区。
	 * 
	 * 权限：管理员。
	 * 
	 * */
	public void moveManagerArea(int managerAreaId, int bigAreaId);

	/**
	 * 移动主任区。
	 * 
	 * 权限：管理员。
	 * 
	 * */
	public void moveDirectorArea(int directorAreaId, int managerAreaId);

	/**
	 * 移动小区。
	 * 
	 * 权限：管理员。
	 * 
	 * */
	public void moveAgentArea(int agentAreaId, int directorAreaId);

	/**
	 * 创建小区。
	 * 
	 * */
	public AgentArea createAgentArea(String desc, String code, WorkType type, int fatherId);

	/**
	 * 创建主任区。
	 * 
	 * */
	public DirectorArea createDirectArea(String desc, String code, int fatherId);

	/**
	 * 创建经理区。
	 * 
	 * */
	public ManagerArea createManagerArea(String desc, String code, int fatherId);

	/**
	 * 创建大区。
	 * 
	 * */
	public BigArea createBigArea(String desc, String code);

	/**
	 * 关闭大区。
	 * 
	 * */
	public void closeBigArea(int areaId);

	/**
	 * 关闭经理区。
	 * 
	 * */
	public void closeManagerArea(int areaId);

	/**
	 * 关闭主任区。
	 * 
	 * */
	public void closeDirectorArea(int areaId);

	/**
	 * 关闭小区。
	 * 
	 * */
	public void closeAgentArea(int areaId);

	/**
	 * 重新激活大区。
	 * 
	 * */
	public void activateBigArea(int areaId);

	/**
	 * 重新激活经理区。
	 * 
	 * */
	public void activateManangerArea(int areaId);

	/**
	 * 重新激活主任区。
	 * 
	 * */
	public void activateDirectorArea(int areaId);

	/**
	 * 重新激活小区。
	 * 
	 * */
	public void activateAgentArea(int areaId);

}
