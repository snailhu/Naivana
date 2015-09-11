package com.nirvana.domain.backend;

import com.nirvana.utils.Assert;

public enum VisitNodeType {

	/** 门店 */
	DISTRIBUTE_STORE,

	/** 直营店 */
	DIRECT_STORE;

	public static VisitNodeType parseVisitNodeType(String type) {
		Assert.notNull(type);
		if (type.equals("DISTRIBUTE_STORE")) {
			return DISTRIBUTE_STORE;
		} else if (type.equals("DIRECT_STORE")) {
			return DIRECT_STORE;
		} else {
			throw new IllegalArgumentException("type解析错误。");
		}
	}

}
