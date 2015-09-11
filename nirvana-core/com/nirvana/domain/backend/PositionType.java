package com.nirvana.domain.backend;

import com.nirvana.domain.backend.usersys.ERole;

public enum PositionType {

	/** 业务员 */
	AGENT {
		@Override
		public int getLevel() {
			return 0;
		}

		@Override
		public ERole getRole() {
			return ERole.AGENT;
		}

		@Override
		public String getName() {
			return "业务员";
		}
	},

	/** 主任区主任 */
	TDS {
		@Override
		public int getLevel() {
			return 1;
		}

		@Override
		public ERole getRole() {
			return ERole.TDS;
		}

		@Override
		public String getName() {
			return "主任TDS";
		}
	},

	/** 文员 */
	CLERK {
		@Override
		public int getLevel() {
			return 2;
		}

		@Override
		public ERole getRole() {
			return ERole.CLERK;
		}

		@Override
		public String getName() {
			return "文员";
		}
	},

	/** 经理区经理 */
	TDM {
		@Override
		public int getLevel() {
			return 3;
		}

		@Override
		public ERole getRole() {
			return ERole.TDM;
		}

		@Override
		public String getName() {
			return "经理TDM";
		}
	},

	/** 大区经理 */
	UM {
		@Override
		public int getLevel() {
			return 4;
		}

		@Override
		public ERole getRole() {
			return ERole.UM;
		}

		@Override
		public String getName() {
			return "大区经理UM";
		}
	},

	/** 区域系统主管 */
	FSIS {

		@Override
		public int getLevel() {
			return 5;
		}

		@Override
		public ERole getRole() {
			return ERole.USER;
		}

		@Override
		public String getName() {
			return "区域系统主管FSIS";
		}
	},

	/** 系统维护员 */
	SIS {
		@Override
		public int getLevel() {
			return 6;
		}

		@Override
		public ERole getRole() {
			return ERole.SIS;
		}

		@Override
		public String getName() {
			return "系统维护员SIS";
		}
	},

	/** 资讯部 */
	INFO_MINISTRY {
		@Override
		public int getLevel() {
			return 7;
		}

		@Override
		public ERole getRole() {
			return ERole.ADMIN;
		}

		@Override
		public String getName() {
			return "资讯部";
		}
	},

	/** 渠道经理 */
	CDM {
		@Override
		public int getLevel() {
			return 8;
		}

		@Override
		public ERole getRole() {
			return ERole.CDM;
		}

		@Override
		public String getName() {
			return "渠道经理CDM";
		}
	},

	/** 销售能力经理 */
	SISM {
		@Override
		public int getLevel() {
			return 9;
		}

		@Override
		public ERole getRole() {
			return ERole.SISM;
		}

		@Override
		public String getName() {
			return "销售能力经理SISM";
		}
	},

	/** 市场拓展总监 */
	MDM {
		@Override
		public int getLevel() {
			return 10;
		}

		@Override
		public ERole getRole() {
			return ERole.MDM;
		}

		@Override
		public String getName() {
			return "市场拓展总监MDM";
		}
	},

	/** 总经理 */
	GM {
		@Override
		public int getLevel() {
			return 11;
		}

		@Override
		public ERole getRole() {
			return ERole.GM;
		}

		@Override
		public String getName() {
			return "总经理GM";
		}
	};

	public abstract int getLevel();

	public abstract ERole getRole();

	public abstract String getName();

}
