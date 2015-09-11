package com.nirvana.domain.backend;

import com.nirvana.utils.Assert;

/**
 * 陈列方式的枚举型。用于表示第二陈列信息中的陈列方式。
 * 
 * */
public enum DisplayType {

	/** 端架 */
	ONSHELF {
		@Override
		public String getName() {
			return "端架";
		}
	},
	/** 地堆 */
	ABOVEGROUND {
		@Override
		public String getName() {
			return "地堆";
		}
	},
	/** 专用货架 */
	SPECIALSHELF {
		@Override
		public String getName() {
			return "专用货架";
		}
	},
	/** 其他 */
	OTHER {
		@Override
		public String getName() {
			return "其他";
		}
	};

	public abstract String getName();

	public static DisplayType parseDisplayType(String type) {
		Assert.notNull(type);
		if (type.equals("ONSHELF")) {
			return DisplayType.ONSHELF;
		} else if (type.equals("ABOVEGROUND")) {
			return DisplayType.ABOVEGROUND;
		} else if (type.equals("SPECIALSHELF")) {
			return DisplayType.SPECIALSHELF;
		} else if (type.equals("OTHER")) {
			return DisplayType.OTHER;
		} else {
			throw new IllegalArgumentException("DisplayType解析失败。参数不匹配。");
		}
	};

}
