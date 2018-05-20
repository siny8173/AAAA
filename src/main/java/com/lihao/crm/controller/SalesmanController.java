package com.lihao.crm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lihao.crm.entity.Customer;
import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.SysCity;
import com.lihao.crm.entity.SysCustomerLevel;
import com.lihao.crm.entity.SysCustomerSource;
import com.lihao.crm.entity.SysCustomerType;
import com.lihao.crm.entity.SysInventoryRecordState;
import com.lihao.crm.entity.SysInventoryRecordType;
import com.lihao.crm.entity.SysProvince;
import com.lihao.crm.entity.SysRole;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.entity.TechnicalApplication;
import com.lihao.crm.entity.TechnicalApplicationReport;
import com.lihao.crm.service.CompanyService;
import com.lihao.crm.service.CustomerService;
import com.lihao.crm.service.EventService;
import com.lihao.crm.service.InventoryRecordService;
import com.lihao.crm.service.InventoryService;
import com.lihao.crm.service.ProjectService;
import com.lihao.crm.service.SysCityService;
import com.lihao.crm.service.SysCustomerLevelService;
import com.lihao.crm.service.SysCustomerSourceService;
import com.lihao.crm.service.SysCustomerTypeService;
import com.lihao.crm.service.SysProvinceService;
import com.lihao.crm.service.SysUserService;
import com.lihao.crm.service.TechnicalApplicationReportService;
import com.lihao.crm.service.TechnicalApplicationService;
import com.lihao.crm.entity.Event;
import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.InventoryRecord;
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

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private TechnicalApplicationService technicalApplicationService;

	@Autowired
	private TechnicalApplicationReportService technicalApplicationReportService;

	@Autowired
	private InventoryRecordService inventoryRecordService;
	
	@Autowired
	private CompanyService companyService;

	@GetMapping("loadAllCustomer")
	@ResponseBody
	public List<Customer> loadAllCustomer() {
		logger.info("message SalesmanController loadAllCustomer");
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Customer> customers = customerService.loadMine((SysUser) user);
		return customers;
	}
	
	@GetMapping("loadAllCustomer/{departmentId}")
	@ResponseBody
	public List<Customer> loadAllCustomer(@PathVariable Long departmentId) {
		logger.info("message SalesmanController loadAllCustomer departmentId=" + departmentId);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Department department = companyService.findDepartmentById(departmentId);
		List<Customer> customers = customerService.loadMineByDepartment((SysUser) user, department);
		return customers;
	}

	@GetMapping("loadAllTechnicist")
	@ResponseBody
	public List<SysUser> loadAllTechnicist() {
		logger.info("message SalesmanController loadAllTechnicist");
		SysUser user = new SysUser();
		user.setId(0l);
		user.setName("无");
		List<SysRole> roles = new ArrayList<SysRole>();
		user.setRoles(roles);
		List<SysUser> users = new ArrayList<>();
		users.add(user);
		List<SysUser> temp = sysUserService.findAllTechnicist();
		temp.forEach(s -> users.add(s));
		
		return users;
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
	public String addCustomer(Customer customer) {
		logger.info("SalesmanController addCustomer " + customer.getName());

		customer.setId(null);

		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		customer.setUser(user);
		customer.setIsDelete(false);
		customerService.save(customer);
		return "success";
	}

	@PostMapping("modCustomer")
	@ResponseBody
	public String modCustomer(Customer customer) {
		logger.info("SalesmanController modCustomer " + customer.getName());
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		customer.setUser((SysUser) user);
		customer.setIsDelete(false);

		customerService.save(customer);
		return "success";
	}

	@PostMapping("delCustomer")
	@ResponseBody
	public String delCustomer(Customer customer) {
		logger.info("SalesmanController delCustomer " + customer.getName());
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		customer.setUser((SysUser) user);
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

	@PostMapping("add-technical-application")
	@ResponseBody
	public String addTechnicalApplication(TechnicalApplication technicalApplication) {
		logger.info("SalesmanController addTechnicalApplication " + technicalApplication.getName());
		technicalApplication.setId(null);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		technicalApplication.setUser((SysUser) user);
		technicalApplicationService.save(technicalApplication);
		return "success";
	}

	@GetMapping("/main")
	private String main() {
		return "salesman/main";
	}

	@GetMapping("/event")
	private String event() {
		return "salesman/event";
	}

	@GetMapping("/project")
	private String project() {
		return "salesman/project";
	}

	@GetMapping("/technical-application")
	private String technicalApplication() {
		return "salesman/technical-application";
	}

	@GetMapping("/technical-application-grid")
	private String technicalApplicationGird(long id, Model model) {
		logger.info("SalesmanController technicalApplicationGird id=" + id);

		Project project = projectService.findById(id);
		model.addAttribute(project);

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<TechnicalApplication> technicalApplications = technicalApplicationService.loadMine((SysUser) user);
		model.addAttribute("technicalApplications", technicalApplications);
		return "salesman/technical-application-grid";
	}

	@GetMapping("/data/{id}")
	public ResponseEntity<byte[]> download(@PathVariable long id, HttpServletRequest request) {
		logger.info("SalesmanController download " + id);
		try {

			TechnicalApplicationReport report = technicalApplicationReportService.findById(id);

			InputStream in = new FileInputStream(new File("data/" + report.getUuid()));// 灏嗚鏂囦欢鍔犲叆鍒拌緭鍏ユ祦涔嬩腑
			byte[] body = null;
			body = new byte[in.available()];// 杩斿洖涓嬩竴娆″姝よ緭鍏ユ祦璋冪敤鐨勬柟娉曞彲浠ヤ笉鍙楅樆濉炲湴浠庢杈撳叆娴佽鍙栵紙鎴栬烦杩囷級鐨勪及璁″墿浣欏瓧鑺傛暟
			in.read(body);// 璇诲叆鍒拌緭鍏ユ祦閲岄潰
			in.close();

			String fileName = new String(report.getFilename().getBytes("gbk"), "iso8859-1");// 闃叉涓枃涔辩爜
			HttpHeaders headers = new HttpHeaders();// 璁剧疆鍝嶅簲澶�
			headers.add("Content-Disposition", "attachment;filename=" + fileName);
			HttpStatus statusCode = HttpStatus.OK;// 璁剧疆鍝嶅簲鍚�
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
			return response;
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();// 璁剧疆鍝嶅簲澶�
			HttpStatus statusCode = HttpStatus.BAD_REQUEST;// 璁剧疆鍝嶅簲鍚�
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(null, headers, statusCode);
			return response;
		}

	}

	@GetMapping("/inventory-application")
	private String inventoryApplication() {
		return "salesman/inventory-application";
	}

	// information
	@GetMapping("/customer-information")
	private String customerInformation() {
		return "salesman/customer-information";
	}

	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/loadAllInventory")
	@ResponseBody
	private List<Inventory> loadAllInventory() {
		return inventoryService.loadAll();
	}

	@GetMapping("/inventory-application-grid")
	private String inventoryApplicationGird(long id, Model model) {
		logger.info("SalesmanController inventoryApplicationGird id=" + id);

		Inventory inventory = inventoryService.findById(id);
		model.addAttribute(inventory);

		List<InventoryRecord> inventoryRecords = inventoryRecordService.findByInventory(inventory);
		model.addAttribute("inventoryRecords", inventoryRecords);
		return "salesman/inventory-application-grid";
	}

	@PostMapping("add-inventory-application")
	@ResponseBody
	public String addInventoryApplication(InventoryRecord inventoryRecord) {
		logger.info("SalesmanController addInventoryApplication " + inventoryRecord.getInventory().getName());
		inventoryRecord.setId(null);

		SysInventoryRecordState state = new SysInventoryRecordState();
		state.setId(1l);
		inventoryRecord.setState(state);

		SysInventoryRecordType type = new SysInventoryRecordType();
		type.setId(1l);
		inventoryRecord.setType(type);

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		inventoryRecord.setProposer((SysUser) user);

		inventoryRecordService.save(inventoryRecord);
		return "success";
	}
}
