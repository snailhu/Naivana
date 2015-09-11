package com.nirvana.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.nirvana.config.NirvanaConfig;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.service.CloudPushService;
import com.nirvana.utils.Assert;

@Service
public class CloudPushServiceImpl implements CloudPushService {

	public static final int DEVICETYPE_IOS = 4;
	public static final int DEVICETYPE_ANDROID = 3;

	private static BaiduPushClient androidPushClient = null;
	private static BaiduPushClient iosPushClient = null;

	@Resource
	private DealerRepository dealerRepository;

	static {

		String androidApiKey = NirvanaConfig.getProperty("push.android.apiKey");
		String androidSecretKey = NirvanaConfig.getProperty("push.android.secretKey");

		String iosApiKey = NirvanaConfig.getProperty("push.ios.apiKey");
		String iosSecretKey = NirvanaConfig.getProperty("push.ios.secretKey");

		Assert.notNull(androidApiKey, androidSecretKey, iosApiKey, iosSecretKey);

		PushKeyPair androidPair = new PushKeyPair(androidApiKey, androidSecretKey);
		androidPushClient = new BaiduPushClient(androidPair, BaiduPushConstants.CHANNEL_REST_URL);
		androidPushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		PushKeyPair iosPair = new PushKeyPair(iosApiKey, iosSecretKey);
		iosPushClient = new BaiduPushClient(iosPair, BaiduPushConstants.CHANNEL_REST_URL);
		iosPushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

	}

	private void pushMessageToAndroid(String channelId, String title, String description, String content) {

		try {
			JSONObject notification = new JSONObject();
			notification.put("title", title);
			notification.put("description", description);
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 3);
			// notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("content", content); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId).addMsgExpires(new Integer(3600)). // message有效时间
					addMessageType(1).// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
					// deviceType =>3:android, 4:ios
					addMessage(notification.toString()).addDeviceType(3);
			PushMsgToSingleDeviceResponse response = androidPushClient.pushMsgToSingleDevice(request);
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				try {
					throw e;
				} catch (PushClientException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				try {
					throw e;
				} catch (PushServerException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}

	private void pushMessageToIOS(String channelId, String title, String description, String content) {

		try {
			JSONObject notification = new JSONObject();

			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", title);
			jsonAPS.put("sound", "cat.caf");
			jsonAPS.put("content-available", 1);
			notification.put("aps", jsonAPS);

			notification.put("msg", description + ":" + content);

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId).addMsgExpires(new Integer(3600)) // 设置message的有效时间
					// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
					.addMessageType(1)
					// IOS,DeployStatus =>1:Developer2:Production.
					.addMessage(notification.toString()).addDeployStatus(1)
					// deviceType => 3:android, 4:ios
					.addDeviceType(4);
			PushMsgToSingleDeviceResponse response = iosPushClient.pushMsgToSingleDevice(request);
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				try {
					throw e;
				} catch (PushClientException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				try {
					throw e;
				} catch (PushServerException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}

	private void broadCastToAndroid(String msg) {

		try {
			PushMsgToAllRequest request = new PushMsgToAllRequest().addMsgExpires(new Integer(3600)).addMessageType(0).addMessage(msg)
					.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			PushMsgToAllResponse response = androidPushClient.pushMsgToAll(request);
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: " + response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}

	private void broadCastToIOS(String msg) {

		try {
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", msg);
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");

			PushMsgToAllRequest request = new PushMsgToAllRequest().addMsgExpires(new Integer(3600)).addMessageType(1).addMessage(notification.toString())
					.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDepolyStatus(1).addDeviceType(4);
			PushMsgToAllResponse response = iosPushClient.pushMsgToAll(request);
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: " + response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}

	}

	@Override
	public void pushMessage(String channelId, int deviceType, String title, String description, String content) {
		if (deviceType == DEVICETYPE_ANDROID) {
			pushMessageToAndroid(channelId, title, description, content);
		} else if (deviceType == DEVICETYPE_IOS) {
			pushMessageToIOS(channelId, title, description, content);
		} else {
			throw new IllegalArgumentException("deviceType参数不正确。");
		}
	}

	@Override
	public void broadCast(String msg) {
		broadCastToAndroid(msg);
		broadCastToIOS(msg);
	}

	@Override
	public void pushToChannel(Channel channel, String title, String description, String content) {
		// 根据渠道推送
		List<Dealer> list = dealerRepository.findChannelIdByChannel(channel.getCode());
		for (Dealer dealer : list) {
			if (dealer.getChannelId() == null || dealer.getDeviceType() == null) {
				continue;
			}
			if (dealer.getDeviceType() == DEVICETYPE_ANDROID) {
				pushMessageToAndroid(dealer.getChannelId(), title, description, content);
			}
			if (dealer.getDeviceType() == DEVICETYPE_IOS) {
				pushMessageToIOS(dealer.getChannelId(), title, description, content);
			}
		}

	}
}
