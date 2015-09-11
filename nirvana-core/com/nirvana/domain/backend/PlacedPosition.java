package com.nirvana.domain.backend;

import com.nirvana.utils.Assert;

/**
 * λ�õ�ö���͡������ܡ��λ��ܡ�
 * 
 * */
public enum PlacedPosition {

	/** ������ */
	CASHIERDESK {
		@Override
		public String getName() {
			return "������";
		}
	},
	/** ��ͨ�� */
	MAIN {
		@Override
		public String getName() {
			return "��ͨ��";
		}
	},
	/** ����ʳƷ�� */
	LEISUREFOOD {
		@Override
		public String getName() {
			return "����ʳƷ��";
		}
	},
	/** ������ */
	DRINKS {
		@Override
		public String getName() {
			return "������";
		}
	},
	/** �����䶳�� */
	FRESHANDFROZEN {
		@Override
		public String getName() {
			return "�����䶳��";
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
			throw new IllegalArgumentException("Position����ʧ�ܡ�������ƥ�䡣");
		}
	};

}
