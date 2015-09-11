package com.nirvana.domain.common;

public enum LockType {

	/** 锁住SIS操作。 */
	SIS_OPERATE,

	/** 锁住NR目标操作。 */
	NRGOAL_OPERATE,

	/** 锁住同步客户操作。 */
	SYNC_CUSTOMER,

	/** 锁住同步订单操作。 */
	SYNC_ORDER;

}
