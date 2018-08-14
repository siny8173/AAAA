package com.lihao.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

	@GetMapping("/login")
	private String login(Boolean error, Model model) {
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
