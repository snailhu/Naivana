package com.nirvana.domain.backend;

/**
 * 活动的审核阶段。
 * */
public enum ActivityState {
	/** 待审核 */
	WAITVERIFY {
		@Override
		public String getName() {
			return "待审核";
		}
	},
	/** 审核中 */
	VERIFING {
		@Override
		public String getName() {
			return "审核中";
		}
	},
	/** 未通过 */
	REJECTED {
		@Override
		public String getName() {
			return "未通过";
		}
	},
	/** 已过期 */
	EXPIRED {
		@Override
		public String getName() {
			return "已过期";
		}
	},
	/** 已发布 */
	RELEASED {
		@Override
		public String getName() {
			return "已发布";
		}
	};

	public abstract String getName();

	public static ActivityState parseActivityState(String state) {
		if (state.equals("WAITVERIFY")) {
			return ActivityState.WAITVERIFY;
		} else if (state.equals("VERIFING")) {
			return ActivityState.VERIFING;
		} else if (state.equals("REJECTED")) {
			return ActivityState.REJECTED;
		} else if (state.equals("EXPIRED")) {
			return ActivityState.EXPIRED;
		} else if (state.equals("RELEASED")) {
			return ActivityState.RELEASED;
		} else {
			throw new IllegalArgumentException("ActivityState解析失败。参数不匹配。");
		}
	};

}
