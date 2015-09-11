package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;

/**
 * ְλ��Ϣ��
 * 
 * */
@Entity
@Table(name = "backend_positon")
public class Position {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** ְλ���� */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PositionType type;

	/** ������Ա�� */
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	/** �������������Ĵ��� */
	@OneToOne(mappedBy = "manager")
	private BigArea mBigArea;

	/** ����ϵͳ���������Ĵ��� */
	@OneToOne(mappedBy = "fsis")
	private BigArea fBigArea;

	/** ��Ա���������� */
	@OneToOne(mappedBy = "clerk")
	private ManagerArea cManagerArea;

	/** �������������������� */
	@OneToOne(mappedBy = "manager")
	private ManagerArea mManagerArea;

	/** ���������������� */
	@OneToOne(mappedBy = "director")
	private DirectorArea directorArea;

	/** ҵ��Ա������CR�� */
	@OneToOne(mappedBy = "agent")
	private AgentArea agentArea;

	public Position() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PositionType getType() {
		return type;
	}

	public void setType(PositionType type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public BigArea getmBigArea() {
		return mBigArea;
	}

	public void setmBigArea(BigArea mBigArea) {
		this.mBigArea = mBigArea;
	}

	public BigArea getfBigArea() {
		return fBigArea;
	}

	public void setfBigArea(BigArea fBigArea) {
		this.fBigArea = fBigArea;
	}

	public ManagerArea getcManagerArea() {
		return cManagerArea;
	}

	public void setcManagerArea(ManagerArea cManagerArea) {
		this.cManagerArea = cManagerArea;
	}

	public ManagerArea getmManagerArea() {
		return mManagerArea;
	}

	public void setmManagerArea(ManagerArea mManagerArea) {
		this.mManagerArea = mManagerArea;
	}

	public ManagerArea getManagerArea() {
		if (type == PositionType.CLERK) {
			return getcManagerArea();
		} else if (type == PositionType.TDM) {
			return getmManagerArea();
		} else {
			return null;
		}
	}

	public DirectorArea getDirectorArea() {
		return directorArea;
	}

	public void setDirectorArea(DirectorArea directorArea) {
		this.directorArea = directorArea;
	}

	public AgentArea getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(AgentArea agentArea) {
		this.agentArea = agentArea;
	}

}
