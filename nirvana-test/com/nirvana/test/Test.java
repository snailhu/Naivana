/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015��5��26�� ����9:52:10
 */
package com.nirvana.test;

import com.nirvana.config.NirvanaConfig;

public class Test {

	public static void main(String[] args) {

		String prefix = NirvanaConfig.getProperty("fastdfs.file.prefix");

		System.out.println(prefix);

	}
}
