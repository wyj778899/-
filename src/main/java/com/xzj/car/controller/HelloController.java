package com.xzj.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/first")
public class HelloController{
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}
}