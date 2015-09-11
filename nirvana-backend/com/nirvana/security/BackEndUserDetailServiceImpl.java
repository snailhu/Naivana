package com.nirvana.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.security.service.BackEndSecurityService;

/**
 * 用于验证的组件，用户登录和取得用户权限。
 * */
@Transactional
public class BackEndUserDetailServiceImpl implements UserDetailsService {

	@Resource
	private BackEndSecurityService backEndUserSysServiceImpl;

	/** 用户登录时加载用户详细信息，登录也会默认调整到此处 。 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		BackEndUser backEndUser;

		backEndUser = backEndUserSysServiceImpl.findUserByUserLogin(username);

		if (backEndUser == null) {
			throw new UsernameNotFoundException("用户名未找到。");
		}

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(backEndUser);

		User userdetail = new User(backEndUser.getUsername(), backEndUser.getPassword(), backEndUser.getEnabled(), backEndUser.getAccountNonExpired(),
				backEndUser.getCredentialsNonExpired(), backEndUser.getAccountNonLocked(), grantedAuths);
		return userdetail;
	}

	/**
	 * 获取用户所拥有的角色
	 * */
	private Set<GrantedAuthority> obtionGrantedAuthorities(BackEndUser user) {
		Set<ERole> roles = backEndUserSysServiceImpl.getUserRoles(user);
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (ERole role : roles) {
			authSet.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authSet;
	}
}