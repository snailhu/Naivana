package com.nirvana.domain.backend;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import com.nirvana.domain.backend.area.AgentArea;

/**
 * 拜访路线实体。
 * 
 * 在CR区域初始化时将初始化生成20个路线实体，表示此CR区固有的二十条路线。
 * 此二十个实体不会增加和减少。对应的，业务员所具有的固有路线也不会增加和减少。额外路线不在此列。
 * 
 * */

@Entity
@Table(name = "backend_visitroute")
public class VisitRoute {

	/*
	 * 每个业务员对应的五条路线代号。
	 */
	public static final int CODE_A1 = 1;
	public static final int CODE_A2 = 2;
	public static final int CODE_A3 = 3;
	public static final int CODE_A4 = 4;
	public static final int CODE_A5 = 5;
	public static final int CODE_A6 = 6;
	public static final int CODE_A7 = 7;
	public static final int CODE_A8 = 8;
	public static final int CODE_A9 = 9;
	public static final int CODE_A10 = 10;

	public static Long baseTime = 1420387200000L;

	public static int findCode(long time) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 1 || dayOfWeek > 5) {
			throw new IllegalArgumentException("日期非周一至周五的某天。");
		}

		long week = 60 * 60 * 24 * 7 * 1000;

		long timeToTime = time - baseTime;

		if (timeToTime < 0) {
			throw new IllegalArgumentException("日期必须在2015-01-05之后。");
		}

		int weekSum = (int) (timeToTime / week);

		if ((weekSum & 1) != 0) {
			return dayOfWeek;
		} else {
			return dayOfWeek + 5;
		}
	}

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 路线代号，生成之后不可修改。 */
	@Column(updatable = false, nullable = false)
	private Integer code;

	/** 此路线所属的CR区域。 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private AgentArea area;

	/** 此路线包括的节点。 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "route_id")
	@OrderBy("pk.serialNum ASC")
	private List<VisitRouteNode> nodes;

	@Version
	private Integer version;

	public VisitRoute() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public AgentArea getArea() {
		return area;
	}

	public void setArea(AgentArea area) {
		this.area = area;
	}

	public List<VisitRouteNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<VisitRouteNode> nodes) {
		this.nodes = nodes;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
