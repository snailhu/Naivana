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
 * 职位信息。
 * 
 * */
@Entity
@Table(name = "backend_positon")
public class Position {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 职位类型 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PositionType type;

	/** 所属的员工 */
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	/** 大区经理所属的大区 */
	@OneToOne(mappedBy = "manager")
	private BigArea mBigArea;

	/** 区域系统主管所属的大区 */
	@OneToOne(mappedBy = "fsis")
	private BigArea fBigArea;

	/** 文员所属经理区 */
	@OneToOne(mappedBy = "clerk")
	private ManagerArea cManagerArea;

	/** 经理区经理所属经理区 */
	@OneToOne(mappedBy = "manager")
	private ManagerArea mManagerArea;

	/** 主任所属的主任区 */
	@OneToOne(mappedBy = "director")
	private DirectorArea directorArea;

	/** 业务员所属的CR区 */
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
