package com.nirvana.domain.backend;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class VisitRouteNodePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3622400810889756701L;

	/** 节点与路线为多对一关系 */
	@ManyToOne()
	@JoinColumn(name = "route_id")
	private VisitRoute route;

	/** 序号 */
	private Integer serialNum;

	public VisitRouteNodePK() {
	}

	public VisitRoute getRoute() {
		return route;
	}

	public void setRoute(VisitRoute route) {
		this.route = route;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((route.getId() == null) ? 0 : route.getId().hashCode()) + 3 * ((serialNum == null) ? 0 : serialNum.hashCode());
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
		final VisitRouteNodePK other = (VisitRouteNodePK) obj;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route)) {
			return false;
		}
		if (serialNum == null) {
			if (other.serialNum != null)
				return false;
		} else if (!serialNum.equals(other.serialNum)) {
			return false;
		}
		return true;
	}
}
