package com.nirvana.domain.backend.area;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.goal.BigAreaNrGoal;

/**
 * 大区实体。
 * 
 * */
@Entity
@Table(name = "backend_area_bigarea")
public class BigArea {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 大区名称 */
	@Column(length = 30, nullable = false)
	private String name;

	/** CR区编码 */
	@Column(nullable = false, length = 20, unique = true)
	private String areaCode;

	/** 此大区下面的经理区 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_id")
	private Set<ManagerArea> managerAreas;

	/** 此大区的目标项 */
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "area_id")
	private Set<BigAreaNrGoal> goals;

	/** 大区的大区经理 */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "mPosition_id")
	private Position manager;

	/** 区域系统主管 */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "fPosition_id")
	private Position fsis;

	/** 是否关闭 */
	@Column(nullable = false)
	private Boolean isClosed = false;

	@Version
	private Integer version;

	public BigArea() {
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

	public Set<ManagerArea> getManagerAreas() {
		return managerAreas;
	}

	public void setManagerAreas(Set<ManagerArea> managerAreas) {
		this.managerAreas = managerAreas;
	}

	public Set<BigAreaNrGoal> getGoals() {
		return goals;
	}

	public void setGoals(Set<BigAreaNrGoal> goals) {
		this.goals = goals;
	}

	public Position getManager() {
		return manager;
	}

	public void setManager(Position manager) {
		this.manager = manager;
	}

	public Position getFsis() {
		return fsis;
	}

	public void setFsis(Position fsis) {
		this.fsis = fsis;
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
		BigArea other = (BigArea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
