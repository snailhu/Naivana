package com.nirvana.domain.backend;

/**
 * �����˽׶Ρ�
 * */
public enum ActivityState {
	/** ����� */
	WAITVERIFY {
		@Override
		public String getName() {
			return "�����";
		}
	},
	/** ����� */
	VERIFING {
		@Override
		public String getName() {
			return "�����";
		}
	},
	/** δͨ�� */
	REJECTED {
		@Override
		public String getName() {
			return "δͨ��";
		}
	},
	/** �ѹ��� */
	EXPIRED {
		@Override
		public String getName() {
			return "�ѹ���";
		}
	},
	/** �ѷ��� */
	RELEASED {
		@Override
		public String getName() {
			return "�ѷ���";
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
			throw new IllegalArgumentException("ActivityState����ʧ�ܡ�������ƥ�䡣");
		}
	};

}
