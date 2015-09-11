package com.nirvana.domain.backend.goal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.AgentArea;

/**
 * 业务员的本周销量目标实体。
 * 
 * 仅仅存储，没什么业务意义。
 * 
 * */

@Entity
@Table(name = "backend_goal_agentdaysnrgoal")
public class AgentWDNrGoal {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 关联的业务员 */
	@OneToOne(mappedBy = "wdNrGoal")
	private AgentArea area;

	/** 日目标销售额 */
	@Column(nullable = false)
	private Float dayNr = 0F;

	/** 周目标销售额 */
	@Column(nullable = false)
	private Float weekNr = 0F;

	public AgentWDNrGoal() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AgentArea getArea() {
		return area;
	}

	public void setArea(AgentArea area) {
		this.area = area;
	}

	public Float getDayNr() {
		return dayNr;
	}

	public void setDayNr(Float dayNr) {
		this.dayNr = dayNr;
	}

	public Float getWeekNr() {
		return weekNr;
	}

	public void setWeekNr(Float weekNr) {
		this.weekNr = weekNr;
	}

}
