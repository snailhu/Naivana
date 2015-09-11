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
 * ������֤��������û���¼��ȡ���û�Ȩ�ޡ�
 * */
@Transactional
public class BackEndUserDetailServiceImpl implements UserDetailsService {

	@Resource
	private BackEndSecurityService backEndUserSysServiceImpl;

	/** �û���¼ʱ�����û���ϸ��Ϣ����¼Ҳ��Ĭ�ϵ������˴� �� */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		BackEndUser backEndUser;

		backEndUser = backEndUserSysServiceImpl.findUserByUserLogin(username);

		if (backEndUser == null) {
			throw new UsernameNotFoundException("�û���δ�ҵ���");
		}

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(backEndUser);

		User userdetail = new User(backEndUser.getUsername(), backEndUser.getPassword(), backEndUser.getEnabled(), backEndUser.getAccountNonExpired(),
				backEndUser.getCredentialsNonExpired(), backEndUser.getAccountNonLocked(), grantedAuths);
		return userdetail;
	}

	/**
	 * ��ȡ�û���ӵ�еĽ�ɫ
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