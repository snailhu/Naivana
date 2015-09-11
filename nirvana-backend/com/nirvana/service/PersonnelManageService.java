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
	 * �����µ�Ա����
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param realName
	 *            ��ʵ����
	 * @param ECode
	 *            �û�����
	 * @param phoneNum
	 *            �󶨵��ֻ���
	 * 
	 * */
	public void createEmployee(String username, String password, String realName, String eCode, String phoneNum);

	/**
	 * �༭�û���
	 * 
	 * 
	 * */
	public void editEmployee(long employeeId, String password, String realName, String eCode, String phoneNum, Boolean close);

	/**
	 * ��ȡ���е�Ա����
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * */
	public Page<Employee> getEmployees(int page, int size);

	/**
	 * ��ȡ����Ա����
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * */
	public Employee getEmployee(long id);

	/**
	 * �����û���
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * */
	public void lockEmployee(String eCode);

	/**
	 * ��ȡĳ��Ա����ְλ��
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * */
	public List<Position> getPositon(long employeeId);

	/**
	 * ɾ��ĳ��Ա����ְλ��
	 * 
	 * Ȩ�ޣ�����Ա����Ա������Ա���������������Ա����ɾ�����Լ������ְλ��
	 * 
	 * */
	public void deletePosition(long employeeId, int positionId);

	/**
	 * ��ȡ���е�ְλ���͡�
	 * 
	 * */
	public List<PositionType> getAllPositionType();

	/**
	 * ��Ա�����ְλ��
	 * 
	 * Ȩ�ޣ�����Ա����Ա������Ա���������������Ա������ӷ��Լ������ְλ��
	 * 
	 * */
	public void addPositions(long employeeId, List<PositionData> datas);

	/**
	 * �ƶ���������
	 * 
	 * Ȩ�ޣ�����Ա��
	 * 
	 * */
	public void moveManagerArea(int managerAreaId, int bigAreaId);

	/**
	 * �ƶ���������
	 * 
	 * Ȩ�ޣ�����Ա��
	 * 
	 * */
	public void moveDirectorArea(int directorAreaId, int managerAreaId);

	/**
	 * �ƶ�С����
	 * 
	 * Ȩ�ޣ�����Ա��
	 * 
	 * */
	public void moveAgentArea(int agentAreaId, int directorAreaId);

	/**
	 * ����С����
	 * 
	 * */
	public AgentArea createAgentArea(String desc, String code, WorkType type, int fatherId);

	/**
	 * ������������
	 * 
	 * */
	public DirectorArea createDirectArea(String desc, String code, int fatherId);

	/**
	 * ������������
	 * 
	 * */
	public ManagerArea createManagerArea(String desc, String code, int fatherId);

	/**
	 * ����������
	 * 
	 * */
	public BigArea createBigArea(String desc, String code);

	/**
	 * �رմ�����
	 * 
	 * */
	public void closeBigArea(int areaId);

	/**
	 * �رվ�������
	 * 
	 * */
	public void closeManagerArea(int areaId);

	/**
	 * �ر���������
	 * 
	 * */
	public void closeDirectorArea(int areaId);

	/**
	 * �ر�С����
	 * 
	 * */
	public void closeAgentArea(int areaId);

	/**
	 * ���¼��������
	 * 
	 * */
	public void activateBigArea(int areaId);

	/**
	 * ���¼��������
	 * 
	 * */
	public void activateManangerArea(int areaId);

	/**
	 * ���¼�����������
	 * 
	 * */
	public void activateDirectorArea(int areaId);

	/**
	 * ���¼���С����
	 * 
	 * */
	public void activateAgentArea(int areaId);

}
