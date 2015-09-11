package com.nirvana.domain.dealer;

public enum DealerActState {

	/** 待审核 */
	WAITVERIFY {
		@Override
		public String getName() {
			return "待审核";
		}
	},
	/** 未通过 */
	REJECTED {
		@Override
		public String getName() {
			return "未通过";
		}
	},
	/** 已通过 */
	APPROVED {
		@Override
		public String getName() {
			return "已通过";
		}
	};

	public abstract String getName();

	public static DealerActState parseDealerActState(String state) {
		if (state.equals("WAITVERIFY")) {
			return DealerActState.WAITVERIFY;
		} else if (state.equals("REJECTED")) {
			return DealerActState.REJECTED;
		} else if (state.equals("APPROVED")) {
			return DealerActState.APPROVED;
		} else {
			throw new IllegalArgumentException("DealerActState解析失败。参数不匹配。");
		}
	};

}
