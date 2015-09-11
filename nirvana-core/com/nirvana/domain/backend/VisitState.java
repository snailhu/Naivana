package com.nirvana.domain.backend;

/**
 * 拜访记录的枚举型。用于表示拜访记录的各个阶段。
 * 
 * */
public enum VisitState {

	/** 开始 */
	START {
		@Override
		public String getName() {
			return "开始";
		}
	},
	/** 门头照片已获取 */
	HEADPHOTOGOTTEN {
		@Override
		public String getName() {
			return "门头照片已获取";
		}
	},
	/** 主货架信息已获取 */
	MAINSHELFINFOGOTTEN {
		@Override
		public String getName() {
			return "主货架信息已获取";
		}
	},
	/** 设备信息已获取 */
	DEVICEINFOGOTTEN {
		@Override
		public String getName() {
			return "设备信息已获取";
		}
	},
	/** 第二陈列信息已获取 */
	SECONDSHELFINFOGOTTEN {
		@Override
		public String getName() {
			return "第二陈列信息已获取";
		}
	},
	/** 产品生动化信息已获取 */
	PRODUCTVIVIDPICGOTTEN {
		@Override
		public String getName() {
			return "产品生动化信息已获取";
		}
	},
	/** 已下单 */
	ORDERED {
		@Override
		public String getName() {
			return "已下单";
		}
	},
	/** 拜访完成 */
	FINISHED {
		@Override
		public String getName() {
			return "拜访完成";
		}
	};

	public abstract String getName();

	public static VisitState parseVisitState(VisitState state) {
		if (state.equals("START")) {
			return VisitState.START;
		} else if (state.equals("HEADPHOTOGOTTEN")) {
			return VisitState.HEADPHOTOGOTTEN;
		} else if (state.equals("MAINSHELFINFOGOTTEN")) {
			return VisitState.MAINSHELFINFOGOTTEN;
		} else if (state.equals("DEVICEINFOGOTTEN")) {
			return VisitState.DEVICEINFOGOTTEN;
		} else if (state.equals("SECONDSHELFINFOGOTTEN")) {
			return VisitState.SECONDSHELFINFOGOTTEN;
		} else if (state.equals("PRODUCTVIVIDPICGOTTEN")) {
			return VisitState.PRODUCTVIVIDPICGOTTEN;
		} else if (state.equals("ORDERED")) {
			return VisitState.ORDERED;
		} else if (state.equals("FINISHED")) {
			return VisitState.FINISHED;
		} else {
			throw new IllegalArgumentException("VisitState解析失败。参数不匹配。");
		}
	}

}
