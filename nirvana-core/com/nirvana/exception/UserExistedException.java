package com.nirvana.exception;

public class UserExistedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "用户已经存在";

	public UserExistedException() {
		super(message);
	}

	public UserExistedException(String message) {
		super(message);
	}

	public UserExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistedException(Throwable cause) {
		super(cause);
	}

}
