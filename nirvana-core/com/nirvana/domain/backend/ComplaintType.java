package com.nirvana.domain.backend;

public enum ComplaintType {
	DELIVERY {
		@Override
		public String getName() {
			return "配送问题";
		}
	},
	SERVICE {
		@Override
		public String getName() {
			return "服务问题";
		}
	},
	LOGISTICS {
		@Override
		public String getName() {
			return "物流问题";
		}
	},
	ACTIVITY {
		@Override
		public String getName() {
			return "活动问题";
		}
	},
	OTHER {
		@Override
		public String getName() {
			return "其他问题";
		}
	};
	public abstract String getName();

	public static ComplaintType parseComplaintType(String type) {
		for (ComplaintType t : values()) {
			if (t.name().equalsIgnoreCase(type)) {
				return t;
			}
		}
		throw new IllegalArgumentException("ComplaintType解析失败。参数不匹配。");
	}
}
