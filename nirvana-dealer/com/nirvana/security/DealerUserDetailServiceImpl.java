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

import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.security.service.DealerSecurityService;

/**
 * ������֤��������û���¼��ȡ���û�Ȩ�ޡ�
 * */
public class DealerUserDetailServiceImpl implements UserDetailsService {

	@Resource
	private DealerSecurityService dealerSecurityService;

	/** �û���¼ʱ�����û���ϸ��Ϣ����¼Ҳ��Ĭ�ϵ������˴� �� */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DealerUser dealerUser;

		dealerUser = dealerSecurityService.findUserByUserLogin(username);
		if (dealerUser == null) {
			throw new UsernameNotFoundException("�û���������");
		}

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(dealerUser);

		User userdetail = new User(dealerUser.getUsername(), dealerUser.getPassword(), dealerUser.getEnabled(), dealerUser.getAccountNonExpired(),
				dealerUser.getCredentialsNonExpired(), dealerUser.getAccountNonLocked(), grantedAuths);
		return userdetail;
	}

	/**
	 * ��ȡ�û���ӵ�еĽ�ɫ
	 * */
	private Set<GrantedAuthority> obtionGrantedAuthorities(DealerUser user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		if (user.getDealer().getIsDirect() == true) {
			authSet.add(new SimpleGrantedAuthority(DRole.DIRECTSTORE.getRoleName()));
			authSet.add(new SimpleGrantedAuthority(DRole.USER.getRoleName()));
		} else {
			authSet.add(new SimpleGrantedAuthority(DRole.DEALER.getRoleName()));
			authSet.add(new SimpleGrantedAuthority(DRole.USER.getRoleName()));
		}

		return authSet;
	}
}