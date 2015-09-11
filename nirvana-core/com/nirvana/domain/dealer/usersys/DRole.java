package com.nirvana.domain.dealer.usersys;

public enum DRole {

	/** �û��� */
	USER {

		@Override
		public DRole getFather() {
			return null;
		}

	},

	/** �����̡� */
	DEALER {

		@Override
		public DRole getFather() {
			return USER;
		}

	},

	/** ֱӪ�ꡣ */
	DIRECTSTORE {

		@Override
		public DRole getFather() {
			return USER;
		}

	};

	public abstract DRole getFather();

	public String getRoleName() {
		return "DEALER_" + this.toString();
	}

}
