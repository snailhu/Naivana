package com.nirvana.service;

import com.nirvana.domain.common.Channel;

public interface CloudPushService {

	/**
	 * 推送消息。
	 * 
	 * */
	public void pushMessage(String channelId, int deviceType, String title, String description, String content);

	/**
	 * 广播消息到所有客户端。
	 * 
	 * */
	public void broadCast(String msg);

	/**
	 * 推送到某个渠道。
	 * 
	 * */
	public void pushToChannel(Channel channel, String title, String description, String content);

}
