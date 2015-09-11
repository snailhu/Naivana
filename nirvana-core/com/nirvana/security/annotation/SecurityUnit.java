package com.nirvana.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SecurityUnit {

	public Warehouse value();

	public static enum Warehouse {
		BACKEND {
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "backend";
			}
		},
		DEALER {
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "dealer";
			}
		},
		STORE {
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "store";
			}
		};

		public abstract String getName();
	}

}
