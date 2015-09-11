package com.nirvana.exception;

public class RelationNotMatchingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "实体关系不匹配。";

	public RelationNotMatchingException() {
		super(message);
	}

	public RelationNotMatchingException(String message) {
		super(message);
	}

	public RelationNotMatchingException(String message, Throwable cause) {
		super(message, cause);
	}

	public RelationNotMatchingException(Throwable cause) {
		super(cause);
	}

}
