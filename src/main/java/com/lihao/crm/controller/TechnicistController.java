package com.lihao.crm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.entity.TechnicalApplication;
import com.lihao.crm.service.TechnicalApplicationService;

@Controller
@RequestMapping("/technicist")
public class TechnicistController {

	private static final Logger logger = LoggerFactory.getLogger(TechnicistController.class);
	
	@Autowired
	private TechnicalApplicationService technicalApplicationService;
	
	@GetMapping("/main")
	private String main(Model model) {
		logger.info("TechnicistController main");
		
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<TechnicalApplication> technicalApplications = technicalApplicationService.loadByTechnicist((SysUser) user);
		model.addAttribute("technicalApplications", technicalApplications);
		
		return "/technicist/main";
	}
}
