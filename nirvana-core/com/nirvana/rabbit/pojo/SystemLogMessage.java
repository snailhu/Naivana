package com.nirvana.rabbit.pojo;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class SystemLogMessage implements Cloneable {

	private static final Logger LOGGER = Logger
			.getLogger(SystemLogMessage.class);
	private String level;
	private String message;
	private Timestamp timestamp;
	private Exception exception = null;

	public static SystemLogMessage systemLogMessage = new SystemLogMessage();

	public static SystemLogMessage getSystemLogMessage(String level,
			String message, Exception exception) {
		SystemLogMessage result = null;
		try {
			result = (SystemLogMessage) systemLogMessage.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error("CloneNotSupportedException", e);
		}
		result.setTimestamp(new Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		result.setLevel(level);
		result.setException(exception);
		result.setMessage(message);
		return result;
	}

	public SystemLogMessage(String level, String message, Exception exception) {
		this.level = level;
		this.message = message;
		this.exception = exception;
	}

	public SystemLogMessage(String level, String message) {
		this(level, message, null);
	}

	public SystemLogMessage() {
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
