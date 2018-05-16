package com.lihao.crm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lihao.crm.entity.Company;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.service.CompanyService;

@Controller
@RequestMapping("/salesman")
public class SalesmanCompanyController {
	private static final Logger logger = LoggerFactory.getLogger(SalesmanCompanyController.class);

	@Autowired
	private CompanyService companyService;

	@GetMapping("/company")
	private String inventoryApplication() {
		return "salesman/company";
	}

	@GetMapping("/load-company")
	@ResponseBody
	private List<Company> loadCompany() {
		logger.info("SalesmanCompanyController addCompany ");
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return companyService.loadMine(user);
	}

	@PostMapping("/add-company")
	@ResponseBody
	private String addCompany(Company company) {
		logger.info("SalesmanCompanyController addCompany " + company.getName());
		company.setId(null);
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		company.setUser(user);
		companyService.add(company);
		return "success";
	}

	@PostMapping("/mod-company")
	@ResponseBody
	private String modCompany(Company company) {
		logger.info("SalesmanCompanyController modCompany " + company.getName());
		Company companyTemp = companyService.findById(company.getId());

		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (companyTemp.getUser().getId().equals(user.getId()) == false)
			return "failed";

		company.setUser(user);
		companyService.save(company);
		return "success";
	}

	@PostMapping("/del-company")
	@ResponseBody
	private String delCompany(Company company) {
		logger.info("SalesmanCompanyController delCompany " + company.getName());
		companyService.save(company);
		return "success";
	}

}
