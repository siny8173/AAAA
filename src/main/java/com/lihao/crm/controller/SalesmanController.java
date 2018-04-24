package com.lihao.crm.controller;

import java.util.ArrayList;
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

import com.lihao.crm.entity.Customer;
import com.lihao.crm.entity.SysCity;
import com.lihao.crm.entity.SysCustomerLevel;
import com.lihao.crm.entity.SysCustomerSource;
import com.lihao.crm.entity.SysCustomerType;
import com.lihao.crm.entity.SysProvince;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.service.CustomerService;
import com.lihao.crm.service.SysCityService;
import com.lihao.crm.service.SysCustomerLevelService;
import com.lihao.crm.service.SysCustomerSourceService;
import com.lihao.crm.service.SysCustomerTypeService;
import com.lihao.crm.service.SysProvinceService;
import com.lihao.crm.web.object.CustomerDto;
import com.lihao.crm.web.transform.CustomerTransform;

@Controller
@RequestMapping("/salesman")
public class SalesmanController {
	private static final Logger logger = LoggerFactory.getLogger(SalesmanController.class);

	@Autowired
	private SysProvinceService sysProvinceService;

	@Autowired
	private SysCityService sysCityService;

	@Autowired
	private SysCustomerLevelService sysCustomerLevelService;

	@Autowired
	private SysCustomerSourceService sysCustomerSourceService;

	@Autowired
	private SysCustomerTypeService sysCustomerTypeService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("loadAllCustomer")
	@ResponseBody
	public List<CustomerDto> loadAllCustomer() {
		logger.info("message SalesmanController loadAllCustomer");
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Customer> customers = customerService.loadMine((SysUser) user);
		
		List<CustomerDto> dtos = new ArrayList<>();	
		customers.forEach(customer -> dtos.add(CustomerTransform.Customer2Dto(customer)));
		return dtos;
	}

	@GetMapping("loadAllProvince")
	@ResponseBody
	public List<SysProvince> loadAllProvince() {
		logger.info("message SalesmanController loadAllProvince");
		return sysProvinceService.loadAll();
	}

	@GetMapping("loadCitis")
	@ResponseBody
	public List<SysCity> loadCitis(long id) {
		logger.info("message SalesmanController loadCitis by provinceId=" + id);
		return sysCityService.findAll(id);
	}

	@GetMapping("loadCustomerSource")
	@ResponseBody
	public List<SysCustomerSource> loadCustomerSource() {
		logger.info("message SalesmanController loadCustomerSource");
		return sysCustomerSourceService.loadAll();
	}

	@GetMapping("loadCustomerType")
	@ResponseBody
	public List<SysCustomerType> loadCustomerType() {
		logger.info("message SalesmanController loadCustomerType");
		return sysCustomerTypeService.loadAll();
	}

	@GetMapping("loadCustomerLevel")
	@ResponseBody
	public List<SysCustomerLevel> loadCustomerLevel() {
		logger.info("message SalesmanController loadCustomerLevel");
		return sysCustomerLevelService.loadAll();
	}

	@PostMapping("addCustomer")
	@ResponseBody
	public String addCustomer(CustomerDto customerDto) {
		logger.info("AdminController addCustomer " + customerDto.name);
		
		customerDto.customerId = null;
		customerDto.contactId = null;
		
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		customerDto.setUser((SysUser) user);
		
		Customer customer = CustomerTransform.Dto2SysUser(customerDto);
		
		customer.setIsDelete(false);
		customerService.save(customer);
		return "success";
	}

	@PostMapping("modCustomer")
	@ResponseBody
	public String modCustomer(CustomerDto customerDto) {
		logger.info("AdminController modCustomer " + customerDto.name);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		customerDto.setUser((SysUser) user);
		
		Customer customer = CustomerTransform.Dto2SysUser(customerDto);
		
		customer.setIsDelete(false);
		
		customerService.save(customer);
		return "success";
	}

	@PostMapping("delCustomer")
	@ResponseBody
	public String delCustomer(CustomerDto customerDto) {
		logger.info("AdminController delCustomer " + customerDto.name);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		customerDto.setUser((SysUser) user);
		
		Customer customer = CustomerTransform.Dto2SysUser(customerDto);
		
		customer.setIsDelete(true);
		
		customerService.save(customer);
		return "success";
	}

	@GetMapping("/main")
	private String main() {
		return "/salesman/main";
	}
}
