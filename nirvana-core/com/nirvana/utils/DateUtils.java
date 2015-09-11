package com.nirvana.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * 判断两个日期是否为同一天。
	 * */
	public static boolean isOneDay(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}
		return false;
	}

	/**
	 * 由日期获取yyyyMMdd形式的int
	 * */
	public static int getIntAsyyyyMMdd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return year * 10000 + month * 100 + day;
	}

	/** 由日期获取yyyyMM形式的int */
	public static int getIntAsyyyyMM(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return year * 100 + month;
	}

	/**
	 * 把格式为"yyyy-MM-dd hh:mm:ss"的字符串解析为date
	 * */
	public static Date yyyyMMddhhmmss(String dateString) throws ParseException {
		Date date;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.parse(dateString);
		return date;
	}

	/**
	 * date to "yyyy-MM-dd hh:mm:ss"
	 * */
	public static String yyyyMMddhhmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestr = sdf.format(date);
		return timestr;
	}

	/**
	 * date to "yyyy-MM-dd"
	 * */
	public static String yyyyMMdd(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String timestr = sdf.format(date);
		return timestr;
	}

	/**
	 * "yyyy-MM-dd" to date
	 * */
	public static Date yyyyMMdd(String dateString) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateString);
		return date;
	}

}
