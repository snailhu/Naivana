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

import com.nirvana.domain.store.usersys.SRole;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.security.service.StoreSecurityService;

public class StoreUserDetailServiceImpl implements UserDetailsService {
	@Resource
	private StoreSecurityService storeSecurityService;

	/** �û���¼ʱ�����û���ϸ��Ϣ����¼Ҳ��Ĭ�ϵ������˴� �� */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		StoreUser storeUser;

		storeUser = storeSecurityService.findUserByUserLogin(username);
		if (storeUser == null) {
			throw new UsernameNotFoundException("�û���������");
		}

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(storeUser);

		User userdetail = new User(storeUser.getUsername(), storeUser.getPassword(), storeUser.getEnabled(), storeUser.getAccountNonExpired(),
				storeUser.getCredentialsNonExpired(), storeUser.getAccountNonLocked(), grantedAuths);
		return userdetail;
	}

	/**
	 * ��ȡ�û���ӵ�еĽ�ɫ
	 * */
	private Set<GrantedAuthority> obtionGrantedAuthorities(StoreUser user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		authSet.add(new SimpleGrantedAuthority(SRole.USER.getRoleName()));
		return authSet;
	}
}
