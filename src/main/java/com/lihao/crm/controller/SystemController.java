package com.lihao.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@GetMapping("/login")
	private String login(Boolean error, Model model) {
		logger.info("message error=" + error);
		if(error != null && error == true) {
			model.addAttribute("loginError", true);
		}
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
