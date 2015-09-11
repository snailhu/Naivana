package com.nirvana.exception;

public class WrongCaptchaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "ÑéÖ¤Âë´íÎó";

	public WrongCaptchaException() {
		super(message);
	}

	public WrongCaptchaException(String message) {
		super(message);
	}

	public WrongCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongCaptchaException(Throwable cause) {
		super(cause);
	}

}
