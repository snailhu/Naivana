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
 * �ݷ�·��ʵ�塣
 * 
 * ��CR�����ʼ��ʱ����ʼ������20��·��ʵ�壬��ʾ��CR�����еĶ�ʮ��·�ߡ�
 * �˶�ʮ��ʵ�岻�����Ӻͼ��١���Ӧ�ģ�ҵ��Ա�����еĹ���·��Ҳ�������Ӻͼ��١�����·�߲��ڴ��С�
 * 
 * */

@Entity
@Table(name = "backend_visitroute")
public class VisitRoute {

	/*
	 * ÿ��ҵ��Ա��Ӧ������·�ߴ��š�
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
			throw new IllegalArgumentException("���ڷ���һ�������ĳ�졣");
		}

		long week = 60 * 60 * 24 * 7 * 1000;

		long timeToTime = time - baseTime;

		if (timeToTime < 0) {
			throw new IllegalArgumentException("���ڱ�����2015-01-05֮��");
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

	/** ·�ߴ��ţ�����֮�󲻿��޸ġ� */
	@Column(updatable = false, nullable = false)
	private Integer code;

	/** ��·��������CR���� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private AgentArea area;

	/** ��·�߰����Ľڵ㡣 */
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
