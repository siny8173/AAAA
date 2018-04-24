package com.lihao.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

	@GetMapping("/login")
	private String login() {
		return "login";
	}
	
	@GetMapping("/")
	private String root() {
		return "index";
	}
	
	@GetMapping("/index")
	private String index() {
		return "index";
	}
}
