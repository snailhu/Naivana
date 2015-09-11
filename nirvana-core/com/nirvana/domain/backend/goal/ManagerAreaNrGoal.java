package com.nirvana.domain.backend.goal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.ManagerArea;

@Entity
@Table(name = "backend_goal_managerareanrgoal")
public class ManagerAreaNrGoal {

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

	/** �����ľ����� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private ManagerArea area;

	/** �Ƿ����� */
	@Column(nullable = false)
	private Boolean isAlloted = false;

	public ManagerAreaNrGoal() {
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

	public ManagerArea getArea() {
		return area;
	}

	public void setArea(ManagerArea area) {
		this.area = area;
	}

	public Boolean getIsAlloted() {
		return isAlloted;
	}

	public void setIsAlloted(Boolean isAlloted) {
		this.isAlloted = isAlloted;
	}

}
