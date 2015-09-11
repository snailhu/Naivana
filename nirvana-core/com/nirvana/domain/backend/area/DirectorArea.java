package com.nirvana.domain.backend.area;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.goal.DirectorAreaNrGoal;
import com.nirvana.domain.dealer.Dealer;

/**
 * ������ʵ�塣
 * 
 * */
@Entity
@Table(name = "backend_area_directarea")
public class DirectorArea {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** ���������� */
	@Column(length = 30, nullable = false)
	private String name;

	/** ���������� */
	@Column(nullable = false, length = 20, unique = true)
	private String areaCode;

	/** �������������ľ����� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private ManagerArea managerArea;

	/** ��������������CR�� */
	@OneToMany
	@JoinColumn(name = "area_id")
	private Set<AgentArea> agentAreas;

	/** �������������� */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "position_id")
	private Position director;

	/** ����������Ŀ���� */
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "area_id")
	private Set<DirectorAreaNrGoal> goals;

	/** ������������ľ����̺�ֱӪ�� */
	@OneToMany
	@JoinColumn(name = "directorarea_id")
	private Set<Dealer> dealers;

	/** �Ƿ�ر� */
	@Column(nullable = false)
	private Boolean isClosed = false;

	@Version
	private Integer version;

	public DirectorArea() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public ManagerArea getManagerArea() {
		return managerArea;
	}

	public void setManagerArea(ManagerArea managerArea) {
		this.managerArea = managerArea;
	}

	public Set<AgentArea> getAgentAreas() {
		return agentAreas;
	}

	public void setAgentAreas(Set<AgentArea> agentAreas) {
		this.agentAreas = agentAreas;
	}

	public Position getDirector() {
		return director;
	}

	public void setDirector(Position director) {
		this.director = director;
	}

	public Set<DirectorAreaNrGoal> getGoals() {
		return goals;
	}

	public void setGoals(Set<DirectorAreaNrGoal> goals) {
		this.goals = goals;
	}

	public Set<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(Set<Dealer> dealers) {
		this.dealers = dealers;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectorArea other = (DirectorArea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
