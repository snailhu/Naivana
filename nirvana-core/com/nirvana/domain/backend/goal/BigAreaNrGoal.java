package com.nirvana.domain.backend.goal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.BigArea;

@Entity
@Table(name = "backend_goal_bigareanrgoal")
public class BigAreaNrGoal {

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

	/** 所属的大区 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private BigArea area;

	/** 是否分配掉 */
	@Column(nullable = false)
	private Boolean isAlloted = false;

	public BigAreaNrGoal() {
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

	public BigArea getArea() {
		return area;
	}

	public void setArea(BigArea area) {
		this.area = area;
	}

	public Boolean getIsAlloted() {
		return isAlloted;
	}

	public void setIsAlloted(Boolean isAlloted) {
		this.isAlloted = isAlloted;
	}

}
