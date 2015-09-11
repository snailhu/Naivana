package com.nirvana.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test", produces = "application/json;charset=utf-8")
public class TestController {

	@ResponseBody
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String getUser(
			@RequestParam(value = "bool", required = false) boolean bool,
			@RequestParam(value = "Bool", required = false) Boolean Bool,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "Integer", required = false) Integer Integer,
			@RequestParam(value = "integer", required = false) int integer) {
		System.out.println(bool);
		System.out.println(Bool);
		System.out.println(str);
		System.out.println(Integer);
		System.out.println(integer);

		return "#####";

	}
}
