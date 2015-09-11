package com.nirvana.rabbit.consumer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.nirvana.rabbit.pojo.SystemLogMessage;

@Component
public class LogListener {

	private static final Logger LOGGER = Logger.getLogger(LogListener.class);

	public void handleErrorLog(SystemLogMessage message) {
		LOGGER.error(message.getMessage(), message.getException());
	}

	public void handleNormalLog(SystemLogMessage message) {
		String level = message.getLevel();
		if ("error".equalsIgnoreCase(level)) {
			LOGGER.error(message.getMessage(), message.getException());
		} else if ("warning".equalsIgnoreCase(level)) {
			LOGGER.warn(message.getMessage());
		} else if ("info".equalsIgnoreCase(level)) {
			LOGGER.info(message.getMessage());
		} else if ("debug".equalsIgnoreCase(level)) {
			LOGGER.debug(message.getMessage());
		} else {
			LOGGER.warn("UNKNOW LOG LEVEL,LOG MESSAGE AS WARN");
			LOGGER.warn(message.getMessage(), message.getException());
		}
	}

}
