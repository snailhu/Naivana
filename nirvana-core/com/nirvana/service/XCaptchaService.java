package com.nirvana.service;

public interface XCaptchaService {

	public static final String CAPTCHA_PREFIX = "_c_";

	public static final String PHONE_PREFIX = "_p_";

	public static final String TIME_PREFIX = "_t_";

	/**
	 * ��ĳ�����뷢����֤�롣
	 * 
	 * */
	public void sendCaptcha(String phoneNum, int frequencySeconds);

	/**
	 * ��ĳ�����뷢����֤�롣
	 * 
	 * */
	public void sendCaptcha(String phoneNum);

	/**
	 * ������֤�롣
	 * 
	 * */
	public void saveCaptcha(String phoneNum, String captcha);

	/**
	 * ��֤�˺������֤�롣
	 * 
	 * */
	public boolean validate(String phoneNum, String captcha, boolean evict);

	/**
	 * ������롣
	 * 
	 * */
	public void savePhone(String username, String phoneNum, UserType type);

	/**
	 * ��ȡ���롣
	 * 
	 * */
	public String getPhone(String username, UserType type);

	public static enum UserType {

		USER_BACKEND {
			@Override
			public String getPrefix() {
				return "1_";
			}
		},
		USER_DEALER {
			@Override
			public String getPrefix() {
				return "2_";
			}
		},
		USER_STORE {
			@Override
			public String getPrefix() {
				return "3_";
			}
		};

		public abstract String getPrefix();
	}

}
