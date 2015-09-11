package com.nirvana.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.AmqpIllegalStateException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ����ԭ����amqp�ڽ���json����ת��Java����ʱ����Ҫ����ԭ��message��__TypeId__��ת����
 * ������д����Ϊ���滻�����__TypeId__Ϊ�����ߵ�<br/>
 * �������ͣ����Ҽ��뷺�͵��ж�.
 * 
 * @author sunhao(sunhao.java@gmail.com)
 * @version V1.0, 2014-11-3 19:09
 */
public class MessageListenerAdapter4Jackson extends MessageListenerAdapter {
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// Check whether the delegate is a MessageListener impl itself.
		// In that case, the adapter will simply act as a pass-through.
		Object delegate = getDelegate();
		if (delegate != this) {
			if (delegate instanceof ChannelAwareMessageListener) {
				if (channel != null) {
					((ChannelAwareMessageListener) delegate).onMessage(message, channel);
					return;
				} else if (!(delegate instanceof MessageListener)) {
					throw new AmqpIllegalStateException("MessageListenerAdapter cannot handle a "
							+ "ChannelAwareMessageListener delegate if it hasn't been invoked with a Channel itself");
				}
			}
			if (delegate instanceof MessageListener) {
				((MessageListener) delegate).onMessage(message);
				return;
			}
		}

		String methodName = getListenerMethodName(message, null);
		if (methodName == null) {
			throw new AmqpIllegalStateException("No default listener method specified: " + "Either specify a non-null value for the 'defaultListenerMethod' property or "
					+ "override the 'getListenerMethodName' method.");
		}

		Method[] methods = delegate.getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName) && method.getParameterTypes().length == 1) {
				String className = method.getParameterTypes()[0].getName();
				message.getMessageProperties().getHeaders().put(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME, className);

				// ���͵Ĳ�������(���ֻ��һ����������ô��ȡ��һ��)
				Type[] types = method.getGenericParameterTypes();
				if (types.length >= 0 && types[0] instanceof ParameterizedType) {
					// ���ڷ���
					ParameterizedType pType = (ParameterizedType) types[0];
					Type t = pType.getActualTypeArguments()[0];
					if (t instanceof Class) {
						// ��������
						message.getMessageProperties().getHeaders().put(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, ((Class<?>) t).getName());
					}
				}

				Object convertedMessage;

				convertedMessage = extractMessage(message);
				Object result = method.invoke(delegate, convertedMessage);
				if (result != null) {
					handleResult(result, message, channel);
				}
				return;
			}
		}
	}
}