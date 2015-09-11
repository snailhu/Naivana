package com.nirvana.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.nirvana.security.service.DealerSecurityService;

//1 ������Դ��Ȩ�޵Ķ�Ӧ��ϵ    

/**
 * �ù���������Ҫ���þ���ͨ��spring������IoC����securityMetadataSource��
 * securityMetadataSource�൱�ڱ������Զ����MyInvocationSecurityMetadataSourceService��
 * ��MyInvocationSecurityMetadataSourceService������������ݿ���ȡȨ�޺���Դ��װ�䵽HashMap�У�
 * ��Spring Securityʹ�ã�����Ȩ��У�顣
 * 
 * */
public class DealerSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Map<String, Collection<ConfigAttribute>> resourceMap = SecurityMetadataHolder.getDealerResourceMap();
	private RequestMatcher pathMatcher;

	@Resource
	private DealerSecurityService dealerSecurityService;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	// TODO Ŀǰ���㷨Ч��̫�͡�
	// ���޸ġ�
	// ������������Դ����Ҫ��Ȩ�ޣ���ɫ��
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		Iterator<String> it = resourceMap.keySet().iterator();
		while (it.hasNext()) {
			String resURL = it.next();
			pathMatcher = new AntPathRequestMatcher(resURL);
			if (pathMatcher.matches(((FilterInvocation) object).getRequest())) {
				Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);
				return returnCollection;
			}
		}
		return null;
	}

}