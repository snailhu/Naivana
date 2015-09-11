package com.nirvana.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final int RET_SECOND = 1;
	public static final int RET_MINITE = 2;
	public static final int RET_HOUR = 3;
	public static final int RET_DAY = 4;

	public static Date parseDate(String strDate, String pattern) throws ParseException {
		if (pattern == null)
			pattern = DEFAULT_FORMAT;
		DateFormat format = new SimpleDateFormat(pattern);
		return format.parse(strDate);
	}

	public static Date parseDate(String strDate) throws ParseException {
		return parseDate(strDate, DEFAULT_FORMAT);
	}

	public static String dateToString(Date date, String pattern) {
		if (pattern == null)
			pattern = DEFAULT_FORMAT;
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String dateToString(Date date) {
		DateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
		return format.format(date);
	}

	public static float minus(Date d1, Date d2, int ret) {
		if (d1 == null || d2 == null)
			throw new NullPointerException();
		long d1long = d1.getTime();
		long d2long = d2.getTime();
		float result = 0;
		switch (ret) {
		case RET_SECOND:
			result = (d1long - d2long) / 1000;
			break;
		case RET_MINITE:
			result = (d1long - d2long) / 60000f;
			break;
		case RET_HOUR:
			result = (d1long - d2long) / 3600000f;
			break;
		case RET_DAY:
			result = (d1long - d2long) / 86400000f;
			break;

		default:
			throw new UnsupportedOperationException("ret:" + ret + "²»Ö§³Ö");
		}
		return result;
	}

	public static String minus(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			throw new NullPointerException();
		long d1long = d1.getTime();
		long d2long = d2.getTime();
		Date d = new Date(d1long - d2long);
		String hour = String.valueOf((int) minus(d1, d2, RET_HOUR));

		return hour + dateToString(d, ":mm:ss");
	}

}
