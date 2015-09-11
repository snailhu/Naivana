package com.nirvana.utils;

import java.text.DecimalFormat;
import java.util.Random;

import org.apache.commons.validator.routines.RegexValidator;

public class ValidataUtil {

	public static String validata() {
		Random rand = new Random();
		int result = 100000 + rand.nextInt(900000);
		return result + "";
	}

	public static final RegexValidator PHONEVALIDATOR = new RegexValidator("^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$");
	public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("0.00");
}
