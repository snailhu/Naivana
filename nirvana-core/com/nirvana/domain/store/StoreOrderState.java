package com.nirvana.domain.store;

import org.springframework.util.Assert;

public enum StoreOrderState {

	/** δ�ύ */
	NOTSUBMIT {
		@Override
		public String getName() {
			return "δ�ύ";
		}
	},

	/** δ���� */
	NOTHANDLE {
		@Override
		public String getName() {
			return "δ����";
		}
	},
	/** ������ */
	HANDLING {
		@Override
		public String getName() {
			return "������";
		}
	},
	/** ������� */
	FINISHHANDLE {
		@Override
		public String getName() {
			return "�������";
		}
	},

	/** ��ȡ�� */
	CANCELED {
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "��ȡ��";
		}
	};

	public abstract String getName();

	public static StoreOrderState parseStoreOrderState(String state) {
		Assert.hasLength(state, "��������Ϊ�ա�");
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
			throw new IllegalArgumentException("StoreOrderState����ʧ�ܡ�������ƥ�䡣");
		}
	};
}
