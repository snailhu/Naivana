package com.nirvana.exception;

public class RecordNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "���ݿ��¼δ�ҵ���";

	public RecordNotFoundException() {
		super(message);
	}

	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordNotFoundException(Throwable cause) {
		super(cause);
	}

}
