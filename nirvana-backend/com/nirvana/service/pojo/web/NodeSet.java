package com.nirvana.service.pojo.web;

import com.nirvana.domain.backend.VisitNodeType;

/**
 * 包装节点数据。
 * */
public class NodeSet {

	private VisitNodeType type;

	private long id;

	public NodeSet(VisitNodeType type, long id) {
		this.type = type;
		this.id = id;
	}

	public NodeSet() {
	}

	public VisitNodeType getType() {
		return type;
	}

	public void setType(VisitNodeType type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
