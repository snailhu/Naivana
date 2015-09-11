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
import com.nirvana.domain.backend.goal.ManagerAreaNrGoal;

/**
 * ������ʵ�塣
 * 
 * */
@Entity
@Table(name = "backend_area_managerarea")
public class ManagerArea {

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

	/** �˾����������Ĵ��� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private BigArea bigarea;

	/** �˾����������������� */
	@OneToMany
	@JoinColumn(name = "area_id")
	private Set<DirectorArea> directorAreas;

	/** �˾�������Ŀ���� */
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "area_id")
	private Set<ManagerAreaNrGoal> goals;

	/** �˾���������Ա */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "c_position_id")
	private Position clerk;

	/** �˾������ľ��� */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "m_position_id")
	private Position manager;

	/** �Ƿ�ر� */
	@Column(nullable = false)
	private Boolean isClosed = false;

	@Version
	private Integer version;

	public ManagerArea() {
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

	public BigArea getBigarea() {
		return bigarea;
	}

	public void setBigarea(BigArea bigarea) {
		this.bigarea = bigarea;
	}

	public Set<DirectorArea> getDirectorAreas() {
		return directorAreas;
	}

	public void setDirectorAreas(Set<DirectorArea> directorAreas) {
		this.directorAreas = directorAreas;
	}

	public Set<ManagerAreaNrGoal> getGoals() {
		return goals;
	}

	public void setGoals(Set<ManagerAreaNrGoal> goals) {
		this.goals = goals;
	}

	public Position getClerk() {
		return clerk;
	}

	public void setClerk(Position clerk) {
		this.clerk = clerk;
	}

	public Position getManager() {
		return manager;
	}

	public void setManager(Position manager) {
		this.manager = manager;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		final ManagerArea other = (ManagerArea) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}
