/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��2��3�� ����1:49:59
 */
package com.nirvana.exception;

/**
 * @Title:ValidateTooFrequentlyException.java
 * @Description:
 * @Version:v1.0
 */
public class SendValidationException extends RuntimeException {

	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "��֤�뷢��ʧ��";

	public SendValidationException() {
		super(message);
	}

	public SendValidationException(String message) {
		super(message);
	}

	public SendValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendValidationException(Throwable cause) {
		super(cause);
	}
}
