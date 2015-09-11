package com.nirvana.utils;

import org.apache.commons.validator.routines.RegexValidator;

/**
 * ���ԡ�
 * 
 * */
public class Assert {

	/* ������ʽ��֤ */

	private static final RegexValidator USERNAME_VALIDATOR = new RegexValidator("^[a-zA-Z0-9]{1}[a-zA-Z0-9|-|_]{2,14}[a-zA-Z0-9]{1}$");

	private static final RegexValidator PASSWORD_VALIDATOR = new RegexValidator("^[0-9a-zA-Z]{6,16}$");

	private static final RegexValidator EMAIL_VALIDATOR = new RegexValidator("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	private static final RegexValidator PHONE_VALIDATOR = new RegexValidator("^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|177)[0-9]{8}$");

	private static final RegexValidator CATEGORYNAME_VALIDATOR = new RegexValidator("^[a-zA-Z0-9\u4e00-\u9fa5]{2,14}$");

	/* ������Ϣ */

	private static final String IS_TRUE = "���ʽ����Ϊ�档";

	private static final String IS_NULL = "��������Ϊ�ա�";

	private static final String NOT_NULL = "��������Ϊ�ա�";

	private static final String HAS_LENGTH = "��������Ϊ�ա�";

	private static final String VALID_PHONENUMBER = "�ֻ������ʽ����ȷ��";

	private static final String VALID_EMAIL = "�����ʽ����ȷ��";

	private static final String VALID_USERNAME = "�û�����ʽ����ȷ��";

	private static final String VALID_PASSWORD = "�����ʽ����ȷ������Ϊ6��16λ����ĸ������";

	private static final String VALID_CATEGORYNAME = "��������ʽ����ȷ,���������֡�";

	private static final String[] LENGTH_BETWEEN = { "�������ȱ������", "��", "֮�䡣" };

	private static final String[] LENGTH_LESSTHAN = { "�������ȱ���С��", "��" };

	private static final String[] LENGTH_LARGERTHAN = { "�������ȱ������", "��" };

	private static final String[] LENGTH_LESSTHAN_OR_EQUAL = { "�������ȱ���С�ڵ���", "��" };

	private static final String[] LENGTH_LARGERTHAN_OR_EQUAL = { "�������ȱ�����ڵ���", "��" };

	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isTrue(boolean expression) {
		if (!expression) {
			throw new IllegalArgumentException(IS_TRUE);
		}
	}

	public static void isNull(Object object, String msg) {
		if (object != null) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void isNull(Object object) {
		isNull(object, IS_NULL);
	}

	public static void isNull(Object... objects) {
		for (Object o : objects) {
			isNull(o);
		}
	}

	public static void notNull(Object... objects) {
		for (Object o : objects) {
			notNull(o);
		}
	}

	public static void notNull(Object object, String msg) {
		if (object == null) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void notNull(Object object) {
		notNull(object, NOT_NULL);
	}

	private static void hasLength(String text, String msg) {
		if (text == null || text.length() <= 0) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void hasLength(String text) {
		hasLength(text, HAS_LENGTH);
	}

	public static void hasLength(String... texts) {
		for (String text : texts) {
			hasLength(text);
		}
	}

	/**
	 * ����text�ĳ��Ƚ���min��max֮�䡣
	 * 
	 * @param text
	 * @param min
	 *            �������Ҫ��Сֵ�ɴ�nullֵ��
	 * @param max
	 *            �������Ҫ���ֵ�ɴ�nullֵ��
	 * 
	 * */
	public static void lengthBetween(String text, Integer min, Integer max, String msg) {
		notNull(text);
		if (min == null && max == null) {
			return;
		}
		int length = text.length();
		if (min != null && max != null) {
			if (min > max) {
				throw new IllegalArgumentException("��Сֵ���ܴ������ֵ��");
			}
			if (length < min || length > max) {
				throw new IllegalArgumentException(msg);
			}
		}
		if (min == null && max != null) {
			if (length > max) {
				throw new IllegalArgumentException(msg);
			}
		}
		if (min != null && max == null) {
			if (length < min) {
				throw new IllegalArgumentException(msg);
			}
		}
	}

	/**
	 * ����text�ĳ��Ƚ���min��max֮�䡣
	 * 
	 * @param text
	 * @param min
	 *            �������Ҫ��Сֵ�ɴ�nullֵ��
	 * @param max
	 *            �������Ҫ���ֵ�ɴ�nullֵ��
	 * 
	 * */
	public static void lengthBetween(String text, Integer min, Integer max) {

		notNull(text);
		if (min == null && max == null) {
			return;
		}
		int length = text.length();
		if (min != null && max != null) {
			if (min > max) {
				throw new IllegalArgumentException("��Сֵ���ܴ������ֵ��");
			}
			if (length < min || length > max) {
				throw new IllegalArgumentException(LENGTH_BETWEEN[0] + min + LENGTH_BETWEEN[1] + max + LENGTH_BETWEEN[2]);
			}
		}
		if (min == null && max != null) {
			if (length > max) {
				throw new IllegalArgumentException(LENGTH_LESSTHAN_OR_EQUAL[0] + max + LENGTH_LESSTHAN_OR_EQUAL[1]);
			}
		}
		if (min != null && max == null) {
			if (length < min) {
				throw new IllegalArgumentException(LENGTH_LARGERTHAN_OR_EQUAL[0] + min + LENGTH_LARGERTHAN_OR_EQUAL[1]);
			}
		}
	}

	/**
	 * ����text���ȱ���С�ڲ���a�����ܵ��ڣ���ʹ��lengthBetween�������档
	 * 
	 * @see #lengthBetween(String, Integer, Integer, String)
	 * 
	 * */
	@Deprecated
	public static void lengthLessThan(String text, int a, String msg) {
		notNull(text);
		int length = text.length();
		if (length >= a) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * ����text���ȱ���С�ڲ���a�����ܵ��ڣ���ʹ��lengthBetween�������档
	 * 
	 * @see #lengthBetween(String, Integer, Integer)
	 * 
	 * */
	@Deprecated
	public static void lengthLessThan(String text, int a) {
		lengthLessThan(text, a, LENGTH_LESSTHAN[0] + a + LENGTH_LESSTHAN[1]);
	}

	/**
	 * ����text���ȱ�����ڲ���a�����ܵ��ڣ���ʹ��lengthBetween�������档
	 * 
	 * @see #lengthBetween(String, Integer, Integer, String)
	 * 
	 * */
	@Deprecated
	public static void lengthLargerThan(String text, int a, String msg) {
		notNull(text);
		int length = text.length();
		if (length >= a) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * ����text���ȱ�����ڲ���a�����ܵ��ڣ���ʹ��lengthBetween�������档
	 * 
	 * @see #lengthBetween(String, Integer, Integer)
	 * 
	 * */
	@Deprecated
	public static void lengthLargerThan(String text, int a) {
		lengthLargerThan(text, a, LENGTH_LARGERTHAN[0] + a + LENGTH_LARGERTHAN[1]);
	}

	public static void isvalidPhoneNumber(String number, String msg) {
		notNull(number);
		if (!PHONE_VALIDATOR.isValid(number)) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void isvalidPhoneNumber(String number) {
		isvalidPhoneNumber(number, VALID_PHONENUMBER);
	}

	public static void isvalidEmail(String email, String msg) {
		notNull(email);
		if (!EMAIL_VALIDATOR.isValid(email)) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void isvalidEmail(String email) {
		isvalidEmail(email, VALID_EMAIL);
	}

	public static void isvalidUsername(String username, String msg) {
		notNull(username);
		if (!USERNAME_VALIDATOR.isValid(username)) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void isvalidUsername(String username) {
		isvalidUsername(username, VALID_USERNAME);
	}

	public static void isvalidPassword(String password, String msg) {
		notNull(password);
		if (!PASSWORD_VALIDATOR.isValid(password)) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void isvalidPassword(String password) {
		isvalidPassword(password, VALID_PASSWORD);
	}

	public static void isvalidCategoryName(String categoryName, String msg) {
		notNull(categoryName);
		if (!CATEGORYNAME_VALIDATOR.isValid(categoryName)) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void isvalidCategoryName(String categoryName) {
		isvalidCategoryName(categoryName, VALID_CATEGORYNAME);
	}
}
