package com.nirvana.exception;

public class BindNumUnexistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "�û�δ���ֻ��ţ����Ȱ��ֻ�";

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
