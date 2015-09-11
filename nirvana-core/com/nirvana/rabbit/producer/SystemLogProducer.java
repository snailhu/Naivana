package com.nirvana.rabbit.producer;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.nirvana.rabbit.pojo.SystemLogMessage;

public class SystemLogProducer {
	@Resource(name = "normalLogTemplate")
	RabbitTemplate normalTemplate;
	@Resource(name = "errLogTemplate")
	RabbitTemplate errTemplate;

	public void debug(String message) {
		normalTemplate.convertAndSend(SystemLogMessage.getSystemLogMessage(
				"debug", message, null));
	}

	public void info(String message) {
		normalTemplate.convertAndSend(SystemLogMessage.getSystemLogMessage(
				"info", message, null));
	}

	public void warning(String message) {
		normalTemplate.convertAndSend(SystemLogMessage.getSystemLogMessage(
				"warning", message, null));
	}

	public void error(String message, Exception exception) {
		errTemplate.convertAndSend(SystemLogMessage.getSystemLogMessage(
				"error", message, exception));
	}
}
