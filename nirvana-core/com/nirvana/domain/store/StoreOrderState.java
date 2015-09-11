package com.nirvana.domain.store;

import org.springframework.util.Assert;

public enum StoreOrderState {

	/** 未提交 */
	NOTSUBMIT {
		@Override
		public String getName() {
			return "未提交";
		}
	},

	/** 未处理 */
	NOTHANDLE {
		@Override
		public String getName() {
			return "未处理";
		}
	},
	/** 处理中 */
	HANDLING {
		@Override
		public String getName() {
			return "处理中";
		}
	},
	/** 处理完成 */
	FINISHHANDLE {
		@Override
		public String getName() {
			return "处理完成";
		}
	},

	/** 已取消 */
	CANCELED {
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "已取消";
		}
	};

	public abstract String getName();

	public static StoreOrderState parseStoreOrderState(String state) {
		Assert.hasLength(state, "参数不能为空。");
		if (state.equals("NOTHANDLE")) {
			return StoreOrderState.NOTHANDLE;
		} else if (state.equals("HANDLING")) {
			return StoreOrderState.HANDLING;
		} else if (state.equals("FINISHHANDLE")) {
			return StoreOrderState.FINISHHANDLE;
		} else if (state.equals("NOTSUBMIT")) {
			return StoreOrderState.NOTSUBMIT;
		} else if (state.equals("CANCELED")) {
			return StoreOrderState.CANCELED;
		} else {
			throw new IllegalArgumentException("StoreOrderState解析失败。参数不匹配。");
		}
	};
}
