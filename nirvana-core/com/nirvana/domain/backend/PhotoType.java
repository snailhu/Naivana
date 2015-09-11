package com.nirvana.domain.backend;

/**
 * 图片类型。
 * 
 * */
public enum PhotoType {

	/** 门头 */
	STOREHEAD {
		@Override
		public String getName() {
			return "门头";
		}
	},
	/** 主货架 */
	MAINSHELF {
		@Override
		public String getName() {
			return "主货架";
		}
	},
	/** 第二陈列 */
	SECONDSHELF {
		@Override
		public String getName() {
			return "第二陈列";
		}
	},
	/** 设备 */
	DEVICE {
		@Override
		public String getName() {
			return "设备";
		}
	},
	/** 围膜 */
	FILM {
		@Override
		public String getName() {
			return "围膜";
		}
	},
	/** 海报 */
	POSTER {
		@Override
		public String getName() {
			return "海报";
		}
	},
	/** 灯罩 */
	LIGHT {
		@Override
		public String getName() {
			return "灯罩";
		}
	},
	/** 其他 */
	OTHER {
		@Override
		public String getName() {
			return "其他";
		}
	},
	/** 产品生动化，包括围膜，海报，灯罩，其他。 */
	VIVID {
		@Override
		public String getName() {
			return "生动化";
		}
	};

	public abstract String getName();

}
