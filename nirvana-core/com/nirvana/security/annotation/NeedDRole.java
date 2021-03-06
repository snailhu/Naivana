package com.nirvana.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nirvana.domain.dealer.usersys.DRole;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface NeedDRole {

	public DRole[] value() default DRole.USER;

	/** 此项若设为true，则此资源不需要权限。默认为false。 */
	public boolean notNeed() default false;

}
