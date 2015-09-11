package com.nirvana.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nirvana.domain.store.usersys.SRole;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface NeedSRole {

	public SRole[] value() default SRole.USER;

	/** ��������Ϊtrue�������Դ����ҪȨ�ޡ�Ĭ��Ϊfalse�� */
	public boolean notNeed() default false;

}
