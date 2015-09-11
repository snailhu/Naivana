package com.nirvana.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.domain.backend.goal.AgentWDNrGoal;
import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.RecordExistedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.RelationNotMatchingException;
import com.nirvana.exception.ResourceAccessException;
import com.nirvana.repository.backend.EmployeeRepository;
import com.nirvana.repository.backend.PositionRepository;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.area.BigAreaRepository;
import com.nirvana.repository.backend.area.DirectorAreaRepository;
import com.nirvana.repository.backend.area.ManagerAreaRepository;
import com.nirvana.repository.backend.usersys.BackEndUserRepository;
import com.nirvana.service.AgentManageService;
import com.nirvana.service.AreaService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.PersonnelManageService;
import com.nirvana.service.pojo.web.PositionData;
import com.nirvana.utils.Assert;

@Service
@Transactional
public class PersonnelManageServiceImpl implements PersonnelManageService {

	@Resource
	private AreaService areaService;
	@Resource
	private AgentManageService agentManageService;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private BackEndUserRepository backEndUserRepository;
	@Resource
	private PositionRepository positionRepository;
	@Resource
	private BigAreaRepository bigAreaRepository;
	@Resource
	private ManagerAreaRepository managerAreaRepository;
	@Resource
	private DirectorAreaRepository directorAreaRepository;
	@Resource
	private AgentAreaRepository agentAreaRepository;

	@Override
	public void createEmployee(String username, String password, String realName, String eCode, String phoneNum) {
		Assert.hasLength(username, password, realName, eCode, phoneNum);

		Employee employee = new Employee();
		employee.setECode(eCode);
		employee.setName(realName);
		employeeRepository.save(employee);

		BackEndUser user = new BackEndUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phoneNum);
		user.setEmployee(employee);
		backEndUserRepository.save(user);
	}

	@Override
	public void editEmployee(long employeeId, String password, String realName, String eCode, String phoneNum, Boolean close) {
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			throw new RecordNotFoundException("���û�δ�ҵ���");
		}

		Assert.hasLength(realName, eCode, phoneNum);

		employee.setECode(eCode);
		employee.setName(realName);
		employeeRepository.save(employee);

		BackEndUser user = employee.getUser();
		if (user == null) {
			throw new DataIntegrityException();
		}

		if (password != null && !password.equals("")) {
			user.setPassword(password);
		}
		user.setPhone(phoneNum);
		if (close != null) {
			user.setAccountNonLocked(!close);
		}
		backEndUserRepository.save(user);
	}

	private Pageable getPageRequest(int page, int size) {
		if (page < 1) {
			page = 0;
		}
		if (size <= 0) {
			size = 30;
		}
		return new PageRequest(page - 1, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Employee> getEmployees(int page, int size) {
		return employeeRepository.findAll(getPageRequest(page, size));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Employee getEmployee(long id) {
		Employee employee = employeeRepository.findOne(id);
		if (employee == null) {
			throw new RecordNotFoundException();
		}
		return employee;
	}

	@Override
	public void lockEmployee(String eCode) {
		Employee employee = employeeRepository.findByEcode(eCode);
		if (employee == null) {
			throw new RecordNotFoundException("���û������ڡ�");
		}
		BackEndUser user = employee.getUser();
		user.setAccountNonLocked(false);
		backEndUserRepository.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Position> getPositon(long employeeId) {
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			throw new RecordNotFoundException("���û������ڡ�");
		}
		List<Position> list = new ArrayList<Position>();
		list.addAll(employee.getPositions());
		return list;
	}

	/**
	 * ���Դ�ְλ�ھ�����֮�¡�
	 * 
	 * */
	private void positionUnderManagerArea(Position position, ManagerArea area) {
		if (position == null || area == null) {
			throw new IllegalArgumentException("��������Ϊ�ա�");
		}
		PositionType type = position.getType();
		if (type.equals(PositionType.AGENT)) {
			AgentArea agentArea = position.getAgentArea();
			ManagerArea needArea = agentArea.getDirectorArea().getManagerArea();
			if (!needArea.equals(area)) {
				throw new ResourceAccessException("���û���Ȩ������");
			}
		} else if (type.equals(PositionType.TDS)) {
			DirectorArea directorArea = position.getDirectorArea();
			ManagerArea needArea = directorArea.getManagerArea();
			if (!needArea.equals(area)) {
				throw new ResourceAccessException("���û���Ȩ������");
			}
		} else if (type.equals(PositionType.TDM)) {
			ManagerArea needArea = position.getmManagerArea();
			if (!needArea.equals(area)) {
				throw new ResourceAccessException("���û���Ȩ������");
			}
		} else {
			throw new ResourceAccessException("���û���Ȩ������");
		}

	}

	/**
	 * ���Ե�ǰ��½�û����Բ�����ְλ��
	 * 
	 * */
	private void currentLoginEmployeeCanOperatePosition(Position position) {
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.INFO_MINISTRY);
		if (mPositions.size() == 0) {
			mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.CLERK);
			if (mPositions.size() == 0) {
				throw new ResourceAccessException("���û���Ȩ������");
			}
			Position cPosition = mPositions.get(0);
			ManagerArea area = cPosition.getcManagerArea();
			positionUnderManagerArea(position, area);
		}
	}

	@Override
	public void deletePosition(long employeeId, int positionId) {
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			throw new RecordNotFoundException("���û������ڡ�");
		}
		Position position = positionRepository.getOne(positionId);
		if (position == null) {
			throw new RecordNotFoundException("��ְλ�����ڡ�");
		}
		if (position.getType() == PositionType.INFO_MINISTRY) {
			throw new ResourceAccessException("����ԱȨ���޷�ɾ����");
		}
		if (employee != position.getEmployee()) {
			throw new RelationNotMatchingException("��ְλ�����ڴ�Ա����");
		}
		currentLoginEmployeeCanOperatePosition(position);
		position.setEmployee(null);
		positionRepository.save(position);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PositionType> getAllPositionType() {
		List<PositionType> list = new ArrayList<PositionType>();
		list.add(PositionType.GM);
		list.add(PositionType.MDM);
		list.add(PositionType.SISM);
		list.add(PositionType.CDM);
		list.add(PositionType.INFO_MINISTRY);
		list.add(PositionType.SIS);
		list.add(PositionType.FSIS);
		list.add(PositionType.UM);
		list.add(PositionType.TDM);
		list.add(PositionType.CLERK);
		list.add(PositionType.TDS);
		list.add(PositionType.AGENT);
		return list;
	}

	@Override
	public void addPositions(long employeeId, List<PositionData> datas) {
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			throw new RecordNotFoundException("���û�δ�ҵ���");
		}
		List<Position> mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.INFO_MINISTRY);
		if (mPositions.size() > 0) {
			for (PositionData data : datas) {
				Assert.notNull(data.getAreaId(), "areaId��������Ϊ�ա�");
				Assert.notNull(data.getType(), "type��������Ϊ�ա�");
				PositionType type = data.getType();
				if (type.equals(PositionType.INFO_MINISTRY)) {
					throw new ResourceAccessException("����ԱȨ���޷���ӡ�");
				}
				if (type.equals(PositionType.GM) || type.equals(PositionType.MDM) || type.equals(PositionType.SISM) || type.equals(PositionType.CDM)
						|| type.equals(PositionType.SIS)) {
					Position position = positionRepository.findByPositionType(type);
					if (position == null) {
						throw new DataIntegrityException(type.getName() + "ְλδ��ʼ����");
					}
					position.setEmployee(employee);
				} else if (type.equals(PositionType.FSIS) || type.equals(PositionType.UM)) {
					BigArea area = bigAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("����δ�ҵ���");
					}
					Position position;
					if (type.equals(PositionType.FSIS)) {
						position = area.getFsis();
					} else {
						position = area.getManager();
					}
					position.setEmployee(employee);
					positionRepository.save(position);
				} else if (type.equals(PositionType.TDM) || type.equals(PositionType.CLERK)) {
					ManagerArea area = managerAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("������δ�ҵ���");
					}
					Position position;
					if (type.equals(PositionType.TDM)) {
						position = area.getManager();
					} else {
						List<Position> cPosition = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.CLERK);
						if (cPosition.size() != 0) {
							throw new IllegalArgumentException("һ���û�ֻ��ӵ��һ����Աְλ��");
						}
						position = area.getClerk();
					}
					position.setEmployee(employee);
					positionRepository.save(position);
				} else if (type.equals(PositionType.TDS)) {
					DirectorArea area = directorAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("������δ�ҵ���");
					}
					Position position = area.getDirector();
					position.setEmployee(employee);
					positionRepository.save(position);
				} else if (type.equals(PositionType.AGENT)) {
					AgentArea area = agentAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("С��δ�ҵ���");
					}
					Position position = area.getAgent();
					position.setEmployee(employee);
					positionRepository.save(position);
				}
			}
		} else if (mPositions.size() == 0) {
			mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.CLERK);
			if (mPositions.size() == 0) {
				throw new ResourceAccessException("���û���Ȩ������");
			}
			Position cPosition = mPositions.get(0);
			ManagerArea managerArea = cPosition.getcManagerArea();
			for (PositionData data : datas) {
				Assert.notNull(data.getAreaId(), "areaId��������Ϊ�ա�");
				Assert.notNull(data.getType(), "type��������Ϊ�ա�");
				PositionType type = data.getType();
				if (type.equals(PositionType.TDS)) {
					DirectorArea area = directorAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("������δ�ҵ���");
					}
					ManagerArea needArea = area.getManagerArea();
					if (!managerArea.equals(needArea)) {
						throw new ResourceAccessException("���û���Ȩ������");
					}
					Position position = area.getDirector();
					position.setEmployee(employee);
					positionRepository.save(position);
				} else if (type.equals(PositionType.AGENT)) {
					AgentArea area = agentAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("С��δ�ҵ���");
					}
					ManagerArea needArea = area.getDirectorArea().getManagerArea();
					if (!managerArea.equals(needArea)) {
						throw new ResourceAccessException("���û���Ȩ������");
					}
					Position position = area.getAgent();
					position.setEmployee(employee);
					positionRepository.save(position);
				} else {
					throw new ResourceAccessException(type.getName() + "ְλ����Ȩ������");
				}
			}
		}
	}

	@Override
	public void moveManagerArea(int managerAreaId, int bigAreaId) {
		ManagerArea managerArea = managerAreaRepository.findOne(managerAreaId);
		if (managerArea == null) {
			throw new RecordNotFoundException("�˾����������ڡ�");
		}
		BigArea bigArea = bigAreaRepository.findOne(bigAreaId);
		if (bigArea == null) {
			throw new RecordNotFoundException("�˴��������ڡ�");
		}
		managerArea.setBigarea(bigArea);
		managerAreaRepository.save(managerArea);
	}

	@Override
	public void moveDirectorArea(int directorAreaId, int managerAreaId) {
		DirectorArea directorArea = directorAreaRepository.findOne(directorAreaId);
		if (directorArea == null) {
			throw new RecordNotFoundException("�������������ڡ�");
		}
		ManagerArea managerArea = managerAreaRepository.findOne(managerAreaId);
		if (managerArea == null) {
			throw new RecordNotFoundException("�˾����������ڡ�");
		}
		directorArea.setManagerArea(managerArea);
		directorAreaRepository.save(directorArea);
	}

	@Override
	public void moveAgentArea(int agentAreaId, int directorAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��С�������ڡ�");
		}
		DirectorArea directorArea = directorAreaRepository.findOne(directorAreaId);
		if (directorArea == null) {
			throw new RecordNotFoundException("�������������ڡ�");
		}
		agentArea.setDirectorArea(directorArea);
		agentAreaRepository.save(agentArea);
	}

	@Override
	public AgentArea createAgentArea(String desc, String code, WorkType type, int fatherId) {
		Assert.hasLength(code, desc);
		Assert.notNull(type);
		AgentArea agentArea = agentAreaRepository.findByAreaCode(code);
		if (agentArea != null) {
			throw new RecordExistedException("��С������Ѿ����ڡ�");
		}
		DirectorArea directorArea = directorAreaRepository.findOne(fatherId);
		if (directorArea == null) {
			throw new RecordNotFoundException("û���ҵ�������");
		}
		agentArea = new AgentArea();
		// ��ʼ��ְλ��Ϣ��
		Position agent = new Position();
		agent.setType(PositionType.AGENT);
		agentArea.setAgent(agent);
		// ��ʼ��������Ϣ��
		agentArea.setAreaCode(code);
		agentArea.setDirectorArea(directorArea);
		agentArea.setDesc(desc);
		agentArea.setType(type);
		// ��ʼ���ܡ��յ�Ŀ�ꡣ
		AgentWDNrGoal wdNrGoal = new AgentWDNrGoal();
		wdNrGoal.setDayNr(0F);
		wdNrGoal.setWeekNr(0F);
		agentArea.setWdNrGoal(wdNrGoal);
		agentAreaRepository.save(agentArea);
		areaService.initAgentArea(agentArea.getId());
		return agentArea;
	}

	@Override
	public DirectorArea createDirectArea(String name, String code, int fatherId) {
		Assert.hasLength(name, code);
		DirectorArea area = new DirectorArea();
		ManagerArea father = managerAreaRepository.findOne(fatherId);
		if (father == null) {
			throw new RecordNotFoundException("������δ�ҵ���");
		}
		area.setManagerArea(father);
		area.setName(name);
		area.setAreaCode(code);
		Position director = new Position();
		director.setType(PositionType.TDS);
		area.setDirector(director);
		return directorAreaRepository.save(area);
	}

	@Override
	public ManagerArea createManagerArea(String name, String code, int fatherId) {
		Assert.hasLength(name, "��������Ϊ�ա�");
		Assert.hasLength(code, "��������Ϊ�ա�");
		ManagerArea area = new ManagerArea();
		BigArea father = bigAreaRepository.findOne(fatherId);
		if (father == null) {
			throw new RecordNotFoundException("������δ�ҵ���");
		}
		area.setName(name);
		area.setAreaCode(code);
		area.setBigarea(father);
		Position manager = new Position();
		Position clerk = new Position();
		manager.setType(PositionType.TDM);
		clerk.setType(PositionType.CLERK);
		area.setManager(manager);
		area.setClerk(clerk);
		return managerAreaRepository.save(area);
	}

	@Override
	public BigArea createBigArea(String name, String code) {
		Assert.hasLength(name, "��������Ϊ�ա�");
		Assert.hasLength(code, "��������Ϊ�ա�");
		BigArea area = new BigArea();
		area.setName(name);
		area.setAreaCode(code);
		Position manager = new Position();
		Position fsis = new Position();
		manager.setType(PositionType.UM);
		fsis.setType(PositionType.FSIS);
		area.setManager(manager);
		area.setFsis(fsis);
		return bigAreaRepository.save(area);
	}

	@Override
	public void closeBigArea(int areaId) {
		BigArea area = bigAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("�˴��������ڡ�");
		}
		area.setIsClosed(true);
		bigAreaRepository.save(area);
	}

	@Override
	public void closeManagerArea(int areaId) {
		ManagerArea area = managerAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("�˾����������ڡ�");
		}
		area.setIsClosed(true);
		managerAreaRepository.save(area);
	}

	@Override
	public void closeDirectorArea(int areaId) {
		DirectorArea area = directorAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("�˾����������ڡ�");
		}
		area.setIsClosed(true);
		directorAreaRepository.save(area);
	}

	@Override
	public void closeAgentArea(int areaId) {
		agentManageService.closeAgentArea(areaId);
	}

	@Override
	public void activateBigArea(int areaId) {
		BigArea area = bigAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("�˴��������ڡ�");
		}
		area.setIsClosed(false);
		bigAreaRepository.save(area);
	}

	@Override
	public void activateManangerArea(int areaId) {
		ManagerArea area = managerAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("�˾����������ڡ�");
		}
		area.setIsClosed(false);
		managerAreaRepository.save(area);
	}

	@Override
	public void activateDirectorArea(int areaId) {
		DirectorArea area = directorAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("�˾����������ڡ�");
		}
		area.setIsClosed(false);
		directorAreaRepository.save(area);
	}

	@Override
	public void activateAgentArea(int areaId) {
		agentManageService.activateAgentArea(areaId);
	}
}
