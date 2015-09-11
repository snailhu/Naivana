package com.nirvana.domain.dealer.usersys;

public enum DRole {

	/** 用户。 */
	USER {

		@Override
		public DRole getFather() {
			return null;
		}

	},

	/** 经销商。 */
	DEALER {

		@Override
		public DRole getFather() {
			return USER;
		}

	},

	/** 直营店。 */
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
