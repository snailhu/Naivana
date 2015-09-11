package com.nirvana.service;

import com.nirvana.domain.common.Channel;

public interface CloudPushService {

	/**
	 * ������Ϣ��
	 * 
	 * */
	public void pushMessage(String channelId, int deviceType, String title, String description, String content);

	/**
	 * �㲥��Ϣ�����пͻ��ˡ�
	 * 
	 * */
	public void broadCast(String msg);

	/**
	 * ���͵�ĳ��������
	 * 
	 * */
	public void pushToChannel(Channel channel, String title, String description, String content);

}
