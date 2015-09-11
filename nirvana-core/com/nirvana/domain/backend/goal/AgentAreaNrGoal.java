package com.nirvana.domain.backend.goal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.AgentArea;

@Entity
@Table(name = "backend_goal_agentareanrgoal")
public class AgentAreaNrGoal {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 表示年月的整形，比如201006表示2010年06月。 */
	@Column(nullable = false)
	private Integer date;

	/** 销售额（元） */
	@Column(nullable = false)
	private Float nr;

	/** 所属的CR区 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private AgentArea area;

	public AgentAreaNrGoal() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Float getNr() {
		return nr;
	}

	public void setNr(Float nr) {
		this.nr = nr;
	}

	public AgentArea getArea() {
		return area;
	}

	public void setArea(AgentArea area) {
		this.area = area;
	}

}
