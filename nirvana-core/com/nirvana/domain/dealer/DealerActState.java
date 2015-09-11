package com.nirvana.domain.dealer;

public enum DealerActState {

	/** ����� */
	WAITVERIFY {
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
	/** ��ͨ�� */
	APPROVED {
		@Override
		public String getName() {
			return "��ͨ��";
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
			throw new IllegalArgumentException("DealerActState����ʧ�ܡ�������ƥ�䡣");
		}
	};

}
