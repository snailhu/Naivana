package com.nirvana.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.nirvana.exception.SendValidationException;
import com.nirvana.exception.ValidateTooFrequentlyException;
import com.nirvana.service.XCaptchaService;
import com.nirvana.utils.HttpPost;
import com.nirvana.utils.ValidataUtil;

@Service
public class XCaptchaServiceImpl implements XCaptchaService {

	@Resource
	private CacheManager cacheManager;

	private Cache cache = null;

	private static final String CACHE_NAME = "captcha";

	@Override
	public void sendCaptcha(String phoneNum, int frequencySeconds) {
		// 检查验证码发送间隔
		ValueWrapper vw = cache.get(TIME_PREFIX + phoneNum);
		if (vw != null) {
			long time = (Long) vw.get();
			long now = System.currentTimeMillis();
			if (now < (time + 60 * 1000)) {
				throw new ValidateTooFrequentlyException();
			}
		}
		cache.put(TIME_PREFIX + phoneNum, (Long) System.currentTimeMillis());
		String validata = ValidataUtil.validata();
		// 发送
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "NFTB700210");// 此处填写用户账号
		map.put("scode", "205897");// 此处填写用户密码
		map.put("mobile", phoneNum);// 此处填写发送号码
		map.put("tempid", "MB-2014092718");// 此处填写模板短信编号
		map.put("content", "@1@=" + validata);// 此处填写模板短信内容
		String temp = HttpPost.doPost("http://222.185.228.25:8000/msm/sdk/http/sendsms.jsp", map, "GBK");
		if (temp == null || temp.contains("ERROR")) {
			throw new SendValidationException();
		}
		cache.put(CAPTCHA_PREFIX + phoneNum, validata);
	}

	@Override
	public void sendCaptcha(String phoneNum) {
		sendCaptcha(phoneNum, 60);
	}

	@Override
	public void saveCaptcha(String phoneNum, String captcha) {
		cache.put(CAPTCHA_PREFIX + phoneNum, captcha);
	}

	@Override
	public boolean validate(String phoneNum, String captcha, boolean evict) {
		ValueWrapper vw = cache.get(CAPTCHA_PREFIX + phoneNum);
		if (vw == null) {
			return false;
		}
		if (captcha == null || !captcha.equalsIgnoreCase(String.valueOf(vw.get()))) {
			return false;
		}
		if (evict) {
			cache.evict(CAPTCHA_PREFIX + phoneNum);
		}
		return true;
	}

	@Override
	public void savePhone(String username, String phoneNum, UserType type) {
		cache.put(PHONE_PREFIX + type.getPrefix() + username, phoneNum);
	}

	@Override
	public String getPhone(String username, UserType type) {
		ValueWrapper vw = cache.get(PHONE_PREFIX + type.getPrefix() + username);
		cache.evict(PHONE_PREFIX + type.getPrefix() + username);
		if (vw == null) {
			return null;
		}
		return String.valueOf(vw.get());
	}

	@PostConstruct
	private void init() {
		cache = cacheManager.getCache(CACHE_NAME);
	}

}
