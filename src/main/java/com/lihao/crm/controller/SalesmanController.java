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
import com.lihao.crm.service.EventService;
import com.lihao.crm.service.ProjectService;
import com.lihao.crm.service.SysCityService;
import com.lihao.crm.service.SysCustomerLevelService;
import com.lihao.crm.service.SysCustomerSourceService;
import com.lihao.crm.service.SysCustomerTypeService;
import com.lihao.crm.service.SysProvinceService;
import com.lihao.crm.web.object.CustomerDto;
import com.lihao.crm.web.transform.CustomerTransform;

import com.lihao.crm.entity.Event;
import com.lihao.crm.entity.Project;

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

	@Autowired
	private EventService eventService;
	
	@Autowired
	private ProjectService projectService;

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
		logger.info("SalesmanController addCustomer " + customerDto.name);

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
		logger.info("SalesmanController modCustomer " + customerDto.name);
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
		logger.info("SalesmanController delCustomer " + customerDto.name);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		customerDto.setUser((SysUser) user);

		Customer customer = CustomerTransform.Dto2SysUser(customerDto);

		customer.setIsDelete(true);

		customerService.save(customer);
		return "success";
	}

	@GetMapping("loadAllEvent")
	@ResponseBody
	public List<Event> loadAllEvent() {
		logger.info("message SalesmanController loadAllEvent");
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return eventService.loadMine((SysUser) user);
	}

	@PostMapping("addEvent")
	@ResponseBody
	public String addEvent(Event event) {
		logger.info("SalesmanController addEvent " + event.getTitle());
		event.setId(null);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		event.setUser((SysUser) user);
		event.setIsDelete(false);
		eventService.save(event);
		return "success";
	}
	
	@PostMapping("modEvent")
	@ResponseBody
	public String modEvent(Event event) {
		logger.info("SalesmanController modEvent " + event.getTitle());
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		event.setUser((SysUser) user);
		event.setIsDelete(false);
		eventService.save(event);
		return "success";
	}
	
	@PostMapping("delEvent")
	@ResponseBody
	public String delEvent(Event event) {
		logger.info("SalesmanController delEvent " + event.getTitle());
		event = eventService.findById(event.getId());
		event.setIsDelete(true);
		eventService.save(event);
		return "success";
	}
	
	@GetMapping("loadAllProject")
	@ResponseBody
	public List<Project> loadAllProject() {
		logger.info("message SalesmanController loadAllProject");
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return projectService.loadMine((SysUser) user);
	}
	
	@PostMapping("addProject")
	@ResponseBody
	public String addProject(Project project) {
		logger.info("SalesmanController addProject " + project.getName());
		project.setId(null);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		project.setUser((SysUser) user);
		projectService.save(project);
		return "success";
	}
	
	@PostMapping("modProject")
	@ResponseBody
	public String modProject(Project project) {
		logger.info("SalesmanController modProject " + project.getName());
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		project.setUser((SysUser) user);
		project.setIsDelete(false);
		projectService.save(project);
		return "success";
	}
	
	@PostMapping("delProject")
	@ResponseBody
	public String delProject(Project project) {
		logger.info("SalesmanController delProject " + project.getName());
		project = projectService.findById(project.getId());
		project.setIsDelete(true);
		projectService.save(project);
		return "success";
	}

	@GetMapping("/main")
	private String main() {
		return "/salesman/main";
	}

	@GetMapping("/event")
	private String event() {
		return "/salesman/event";
	}
	
	@GetMapping("/project")
	private String project() {
		return "/salesman/project";
	}
	
	@GetMapping("/technical-application")
	private String technicalApplication() {
		return "/salesman/technical-application";
	}
}
