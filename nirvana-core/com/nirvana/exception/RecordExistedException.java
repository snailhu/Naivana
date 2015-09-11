package com.nirvana.exception;

public class RecordExistedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "数据库记录已经存在。";

	public RecordExistedException() {
		super(message);
	}

	public RecordExistedException(String message) {
		super(message);
	}

	public RecordExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordExistedException(Throwable cause) {
		super(cause);
	}

}
