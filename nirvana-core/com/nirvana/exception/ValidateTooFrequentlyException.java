/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年2月3日 下午1:49:59
 */
package com.nirvana.exception;

/**
 * @Title:ValidateTooFrequentlyException.java
 * @Description:
 * @Version:v1.0
 */
public class ValidateTooFrequentlyException extends RuntimeException{

	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "发送验证码太频繁";

	public ValidateTooFrequentlyException() {
		super(message);
	}

	public ValidateTooFrequentlyException(String message) {
		super(message);
	}

	public ValidateTooFrequentlyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidateTooFrequentlyException(Throwable cause) {
		super(cause);
	}
}
