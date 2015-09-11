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
			throw new RecordNotFoundException("此用户未找到。");
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
			throw new RecordNotFoundException("此用户不存在。");
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
			throw new RecordNotFoundException("此用户不存在。");
		}
		List<Position> list = new ArrayList<Position>();
		list.addAll(employee.getPositions());
		return list;
	}

	/**
	 * 断言此职位在经理区之下。
	 * 
	 * */
	private void positionUnderManagerArea(Position position, ManagerArea area) {
		if (position == null || area == null) {
			throw new IllegalArgumentException("参数不能为空。");
		}
		PositionType type = position.getType();
		if (type.equals(PositionType.AGENT)) {
			AgentArea agentArea = position.getAgentArea();
			ManagerArea needArea = agentArea.getDirectorArea().getManagerArea();
			if (!needArea.equals(area)) {
				throw new ResourceAccessException("此用户无权操作。");
			}
		} else if (type.equals(PositionType.TDS)) {
			DirectorArea directorArea = position.getDirectorArea();
			ManagerArea needArea = directorArea.getManagerArea();
			if (!needArea.equals(area)) {
				throw new ResourceAccessException("此用户无权操作。");
			}
		} else if (type.equals(PositionType.TDM)) {
			ManagerArea needArea = position.getmManagerArea();
			if (!needArea.equals(area)) {
				throw new ResourceAccessException("此用户无权操作。");
			}
		} else {
			throw new ResourceAccessException("此用户无权操作。");
		}

	}

	/**
	 * 断言当前登陆用户可以操作此职位。
	 * 
	 * */
	private void currentLoginEmployeeCanOperatePosition(Position position) {
		Employee currentEmployee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.INFO_MINISTRY);
		if (mPositions.size() == 0) {
			mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.CLERK);
			if (mPositions.size() == 0) {
				throw new ResourceAccessException("此用户无权操作。");
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
			throw new RecordNotFoundException("此用户不存在。");
		}
		Position position = positionRepository.getOne(positionId);
		if (position == null) {
			throw new RecordNotFoundException("此职位不存在。");
		}
		if (position.getType() == PositionType.INFO_MINISTRY) {
			throw new ResourceAccessException("管理员权限无法删除。");
		}
		if (employee != position.getEmployee()) {
			throw new RelationNotMatchingException("此职位不属于此员工。");
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
			throw new RecordNotFoundException("此用户未找到。");
		}
		List<Position> mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.INFO_MINISTRY);
		if (mPositions.size() > 0) {
			for (PositionData data : datas) {
				Assert.notNull(data.getAreaId(), "areaId参数不能为空。");
				Assert.notNull(data.getType(), "type参数不能为空。");
				PositionType type = data.getType();
				if (type.equals(PositionType.INFO_MINISTRY)) {
					throw new ResourceAccessException("管理员权限无法添加。");
				}
				if (type.equals(PositionType.GM) || type.equals(PositionType.MDM) || type.equals(PositionType.SISM) || type.equals(PositionType.CDM)
						|| type.equals(PositionType.SIS)) {
					Position position = positionRepository.findByPositionType(type);
					if (position == null) {
						throw new DataIntegrityException(type.getName() + "职位未初始化。");
					}
					position.setEmployee(employee);
				} else if (type.equals(PositionType.FSIS) || type.equals(PositionType.UM)) {
					BigArea area = bigAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("大区未找到。");
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
						throw new RecordNotFoundException("经理区未找到。");
					}
					Position position;
					if (type.equals(PositionType.TDM)) {
						position = area.getManager();
					} else {
						List<Position> cPosition = positionRepository.findPositionByEmployeeIdAndPositionType(employeeId, PositionType.CLERK);
						if (cPosition.size() != 0) {
							throw new IllegalArgumentException("一个用户只能拥有一个文员职位。");
						}
						position = area.getClerk();
					}
					position.setEmployee(employee);
					positionRepository.save(position);
				} else if (type.equals(PositionType.TDS)) {
					DirectorArea area = directorAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("主任区未找到。");
					}
					Position position = area.getDirector();
					position.setEmployee(employee);
					positionRepository.save(position);
				} else if (type.equals(PositionType.AGENT)) {
					AgentArea area = agentAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("小区未找到。");
					}
					Position position = area.getAgent();
					position.setEmployee(employee);
					positionRepository.save(position);
				}
			}
		} else if (mPositions.size() == 0) {
			mPositions = positionRepository.findPositionByEmployeeIdAndPositionType(currentEmployee.getId(), PositionType.CLERK);
			if (mPositions.size() == 0) {
				throw new ResourceAccessException("此用户无权操作。");
			}
			Position cPosition = mPositions.get(0);
			ManagerArea managerArea = cPosition.getcManagerArea();
			for (PositionData data : datas) {
				Assert.notNull(data.getAreaId(), "areaId参数不能为空。");
				Assert.notNull(data.getType(), "type参数不能为空。");
				PositionType type = data.getType();
				if (type.equals(PositionType.TDS)) {
					DirectorArea area = directorAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("主任区未找到。");
					}
					ManagerArea needArea = area.getManagerArea();
					if (!managerArea.equals(needArea)) {
						throw new ResourceAccessException("此用户无权操作。");
					}
					Position position = area.getDirector();
					position.setEmployee(employee);
					positionRepository.save(position);
				} else if (type.equals(PositionType.AGENT)) {
					AgentArea area = agentAreaRepository.findOne(data.getAreaId());
					if (area == null) {
						throw new RecordNotFoundException("小区未找到。");
					}
					ManagerArea needArea = area.getDirectorArea().getManagerArea();
					if (!managerArea.equals(needArea)) {
						throw new ResourceAccessException("此用户无权操作。");
					}
					Position position = area.getAgent();
					position.setEmployee(employee);
					positionRepository.save(position);
				} else {
					throw new ResourceAccessException(type.getName() + "职位你无权操作。");
				}
			}
		}
	}

	@Override
	public void moveManagerArea(int managerAreaId, int bigAreaId) {
		ManagerArea managerArea = managerAreaRepository.findOne(managerAreaId);
		if (managerArea == null) {
			throw new RecordNotFoundException("此经理区不存在。");
		}
		BigArea bigArea = bigAreaRepository.findOne(bigAreaId);
		if (bigArea == null) {
			throw new RecordNotFoundException("此大区不存在。");
		}
		managerArea.setBigarea(bigArea);
		managerAreaRepository.save(managerArea);
	}

	@Override
	public void moveDirectorArea(int directorAreaId, int managerAreaId) {
		DirectorArea directorArea = directorAreaRepository.findOne(directorAreaId);
		if (directorArea == null) {
			throw new RecordNotFoundException("此主任区不存在。");
		}
		ManagerArea managerArea = managerAreaRepository.findOne(managerAreaId);
		if (managerArea == null) {
			throw new RecordNotFoundException("此经理区不存在。");
		}
		directorArea.setManagerArea(managerArea);
		directorAreaRepository.save(directorArea);
	}

	@Override
	public void moveAgentArea(int agentAreaId, int directorAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("此小区不存在。");
		}
		DirectorArea directorArea = directorAreaRepository.findOne(directorAreaId);
		if (directorArea == null) {
			throw new RecordNotFoundException("此主任区不存在。");
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
			throw new RecordExistedException("此小区编号已经存在。");
		}
		DirectorArea directorArea = directorAreaRepository.findOne(fatherId);
		if (directorArea == null) {
			throw new RecordNotFoundException("没有找到主任区");
		}
		agentArea = new AgentArea();
		// 初始化职位信息。
		Position agent = new Position();
		agent.setType(PositionType.AGENT);
		agentArea.setAgent(agent);
		// 初始化基本信息。
		agentArea.setAreaCode(code);
		agentArea.setDirectorArea(directorArea);
		agentArea.setDesc(desc);
		agentArea.setType(type);
		// 初始化周、日的目标。
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
			throw new RecordNotFoundException("父区域未找到。");
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
		Assert.hasLength(name, "参数不能为空。");
		Assert.hasLength(code, "参数不能为空。");
		ManagerArea area = new ManagerArea();
		BigArea father = bigAreaRepository.findOne(fatherId);
		if (father == null) {
			throw new RecordNotFoundException("父区域未找到。");
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
		Assert.hasLength(name, "参数不能为空。");
		Assert.hasLength(code, "参数不能为空。");
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
			throw new RecordNotFoundException("此大区不存在。");
		}
		area.setIsClosed(true);
		bigAreaRepository.save(area);
	}

	@Override
	public void closeManagerArea(int areaId) {
		ManagerArea area = managerAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("此经理区不存在。");
		}
		area.setIsClosed(true);
		managerAreaRepository.save(area);
	}

	@Override
	public void closeDirectorArea(int areaId) {
		DirectorArea area = directorAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("此经理区不存在。");
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
			throw new RecordNotFoundException("此大区不存在。");
		}
		area.setIsClosed(false);
		bigAreaRepository.save(area);
	}

	@Override
	public void activateManangerArea(int areaId) {
		ManagerArea area = managerAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("此经理区不存在。");
		}
		area.setIsClosed(false);
		managerAreaRepository.save(area);
	}

	@Override
	public void activateDirectorArea(int areaId) {
		DirectorArea area = directorAreaRepository.findOne(areaId);
		if (area == null) {
			throw new RecordNotFoundException("此经理区不存在。");
		}
		area.setIsClosed(false);
		directorAreaRepository.save(area);
	}

	@Override
	public void activateAgentArea(int areaId) {
		agentManageService.activateAgentArea(areaId);
	}
}
