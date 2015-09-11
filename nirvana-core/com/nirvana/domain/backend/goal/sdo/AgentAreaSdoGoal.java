package com.nirvana.domain.backend.goal.sdo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.AgentArea;

@Entity
@Table(name = "backend_goal_sdo_agentareasdogoal")
public class AgentAreaSdoGoal {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** SDO目标实体。 */
	@ManyToMany
	@JoinTable(name = "backend_goal_sdo_agentareasdogoal_sdo", joinColumns = @JoinColumn(name = "goal_id"), inverseJoinColumns = @JoinColumn(name = "sdo_id"))
	private Set<Sdo> sdos;

	/** 所属的CR区。 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private AgentArea agentArea;

	/** 日期，年月。201008表示2010年8月。 */
	@Column(nullable = false)
	private Integer date;

	public AgentAreaSdoGoal() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Sdo> getSdos() {
		return sdos;
	}

	public void setSdos(Set<Sdo> sdos) {
		this.sdos = sdos;
	}

	public AgentArea getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(AgentArea agentArea) {
		this.agentArea = agentArea;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

}
