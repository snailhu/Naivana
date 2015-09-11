package com.nirvana.domain.backend.usersys;

import java.util.HashSet;
import java.util.Set;

/** 角色数组。 预置父子角色关系。 */
public enum ERole {

	/** 用户 */
	USER {
		@Override
		public ERole getFather() {
			return null;
		}
	},

	/** 管理员 */
	ADMIN {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** 百事总部经理人员 */
	PEPSI_MANAGER {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** 总经理 */
	GM {
		@Override
		public ERole getFather() {
			return PEPSI_MANAGER;
		}
	},

	/** 市场拓展总监 */
	MDM {
		@Override
		public ERole getFather() {
			return PEPSI_MANAGER;
		}
	},

	/** 销售能力经理 */
	SISM {
		@Override
		public ERole getFather() {
			return PEPSI_MANAGER;
		}
	},

	/** 渠道经理 */
	CDM {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** 系统维护员 */
	SIS {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** 区域系统主管 */
	FSIS {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** 业务系统成员 */
	AGENTSYS {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** 大区经理 */
	UM {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** 经理区经理 */
	TDM {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** 文员 */
	CLERK {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** 主任区主任 */
	TDS {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** 业务员 */
	AGENT {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	};

	public abstract ERole getFather();

	/** 获得角色集 */
	public Set<ERole> getRoleSet() {
		Set<ERole> roles = new HashSet<ERole>();
		roles.add(this);
		ERole father = this.getFather();
		while (father != null) {
			roles.add(father);
			father = father.getFather();
		}
		return roles;
	}

	public String getRoleName() {
		return "BACKEND_" + this.toString();
	}

}
