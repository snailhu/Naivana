package com.nirvana.exception;

public class BindNumUnexistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "用户未绑定手机号，请先绑定手机";

	public BindNumUnexistException() {
		super(message);
	}

	public BindNumUnexistException(String message) {
		super(message);
	}

	public BindNumUnexistException(String message, Throwable cause) {
		super(message, cause);
	}

	public BindNumUnexistException(Throwable cause) {
		super(cause);
	}

}
