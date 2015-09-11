package com.nirvana.domain.backend;

import com.nirvana.utils.Assert;

public enum VisitNodeType {

	/** �ŵ� */
	DISTRIBUTE_STORE,

	/** ֱӪ�� */
	DIRECT_STORE;

	public static VisitNodeType parseVisitNodeType(String type) {
		Assert.notNull(type);
		if (type.equals("DISTRIBUTE_STORE")) {
			return DISTRIBUTE_STORE;
		} else if (type.equals("DIRECT_STORE")) {
			return DIRECT_STORE;
		} else {
			throw new IllegalArgumentException("type��������");
		}
	}

}
