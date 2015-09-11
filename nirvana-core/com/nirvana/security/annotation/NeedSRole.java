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

	/** 此项若设为true，则此资源不需要权限。默认为false。 */
	public boolean notNeed() default false;

}
