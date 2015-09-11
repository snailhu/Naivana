package com.nirvana.exception;

public class ServerIOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "·þÎñÆ÷IOÒì³£¡£";

	public ServerIOException() {
		super(message);
	}

	public ServerIOException(String message) {
		super(message);
	}

	public ServerIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerIOException(Throwable cause) {
		super(cause);
	}

}
