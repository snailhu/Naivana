package com.nirvana.domain.backend;

import com.nirvana.utils.Assert;

/**
 * 位置的枚举型。主货架、次货架。
 * 
 * */
public enum PlacedPosition {

	/** 收银口 */
	CASHIERDESK {
		@Override
		public String getName() {
			return "收银口";
		}
	},
	/** 主通道 */
	MAIN {
		@Override
		public String getName() {
			return "主通道";
		}
	},
	/** 休闲食品区 */
	LEISUREFOOD {
		@Override
		public String getName() {
			return "休闲食品区";
		}
	},
	/** 饮料区 */
	DRINKS {
		@Override
		public String getName() {
			return "饮料区";
		}
	},
	/** 生鲜冷冻区 */
	FRESHANDFROZEN {
		@Override
		public String getName() {
			return "生鲜冷冻区";
		}
	};

	public abstract String getName();

	public static PlacedPosition parsePosition(String position) {
		Assert.notNull(position);
		if (position.equals("MAIN")) {
			return PlacedPosition.MAIN;
		} else if (position.equals("LEISUREFOOD")) {
			return PlacedPosition.LEISUREFOOD;
		} else if (position.equals("DRINKS")) {
			return PlacedPosition.DRINKS;
		} else if (position.equals("CASHIERDESK")) {
			return PlacedPosition.CASHIERDESK;
		} else if (position.equals("FRESHANDFROZEN")) {
			return PlacedPosition.FRESHANDFROZEN;
		} else {
			throw new IllegalArgumentException("Position解析失败。参数不匹配。");
		}
	};

}
