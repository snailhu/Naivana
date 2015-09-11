package com.nirvana.exception;

public class DataIntegrityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "数据完整性异常。";

	public DataIntegrityException() {
		super(message);
	}

	public DataIntegrityException(String message) {
		super(message);
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityException(Throwable cause) {
		super(cause);
	}

}
