package com.nirvana.domain.backend.goal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.DirectorArea;

@Entity
@Table(name = "backend_goal_directorareanrgoal")
public class DirectorAreaNrGoal {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** ��ʾ���µ����Σ�����201006��ʾ2010��06�¡� */
	@Column(nullable = false)
	private Integer date;

	/** ���۶Ԫ�� */
	@Column(nullable = false)
	private Float nr;

	/** ������������ */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private DirectorArea area;

	/** �Ƿ����� */
	@Column(nullable = false)
	private Boolean isAlloted = false;

	public DirectorAreaNrGoal() {
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

	public DirectorArea getArea() {
		return area;
	}

	public void setArea(DirectorArea area) {
		this.area = area;
	}

	public Boolean getIsAlloted() {
		return isAlloted;
	}

	public void setIsAlloted(Boolean isAlloted) {
		this.isAlloted = isAlloted;
	}

}
