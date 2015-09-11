package com.nirvana.test;

import com.nirvana.domain.backend.VisitRoute;

public class JavaTest {

	public static void main(String[] args) {

		int code = VisitRoute.findCode(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000);
		System.out.println(code);

	}
}
