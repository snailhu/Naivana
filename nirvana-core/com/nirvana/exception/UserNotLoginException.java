package com.nirvana.exception;

public class UserNotLoginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "�û�δ��¼";

	public UserNotLoginException() {
		super(message);
	}

	public UserNotLoginException(String message) {
		super(message);
	}

	public UserNotLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotLoginException(Throwable cause) {
		super(cause);
	}

}
