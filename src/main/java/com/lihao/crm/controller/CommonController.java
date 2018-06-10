package com.lihao.crm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lihao.crm.entity.SysDepartment;
import com.lihao.crm.entity.SysDuty;
import com.lihao.crm.entity.SysEventType;
import com.lihao.crm.entity.SysInventoryRecordState;
import com.lihao.crm.entity.SysInventoryRecordType;
import com.lihao.crm.entity.SysInventoryType;
import com.lihao.crm.entity.SysProjectState;
import com.lihao.crm.entity.SysReportType;
import com.lihao.crm.entity.SysSex;
import com.lihao.crm.entity.SysTechnicalApplicationType;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.SysDepartmentRepository;
import com.lihao.crm.repository.SysDutyRepository;
import com.lihao.crm.repository.SysEventTypeRepository;
import com.lihao.crm.repository.SysInventoryRecordStateRepository;
import com.lihao.crm.repository.SysInventoryRecordTypeRepository;
import com.lihao.crm.repository.SysInventoryTypeRepository;
import com.lihao.crm.repository.SysProjectStateRepository;
import com.lihao.crm.repository.SysReportTypeRepository;
import com.lihao.crm.repository.SysSexRepository;
import com.lihao.crm.repository.SysTechnicalApplicationTypeRepository;
import com.lihao.crm.service.SysUserService;

@Controller
@RequestMapping("/common")
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	private SysSexRepository sysSexRepository;

	@Autowired
	private SysDepartmentRepository sysDepartmentRepository;

	@Autowired
	private SysDutyRepository sysDutyRepository;

	@Autowired
	private SysEventTypeRepository sysEventTypeRepository;

	@Autowired
	private SysProjectStateRepository sysProjectStateRepository;

	@Autowired
	private SysReportTypeRepository sysReportTypeRepository;

	@Autowired
	private SysTechnicalApplicationTypeRepository sysTechnicalApplicationTypeRepository;

	@Autowired
	private SysInventoryTypeRepository sysInventoryTypeRepository;

	@Autowired
	private SysInventoryRecordTypeRepository sysInventoryRecordTypeRepository;

	@Autowired
	private SysInventoryRecordStateRepository sysInventoryRecordStateRepository;

	@Autowired
	private SysUserService sysUserService;

	@GetMapping("loadAllSysSex")
	@ResponseBody
	public List<SysSex> loadAllSysSex() {
		return (List<SysSex>) sysSexRepository.findAll();
	}

	@GetMapping("loadAllSysDepartment")
	@ResponseBody
	public List<SysDepartment> loadAllSysDepartment() {
		return (List<SysDepartment>) sysDepartmentRepository.findAll();
	}

	@GetMapping("loadAllSysDuty")
	@ResponseBody
	public List<SysDuty> loadAllSysDuty() {
		return (List<SysDuty>) sysDutyRepository.findAll();
	}

	@GetMapping("loadAllSysEventType")
	@ResponseBody
	public List<SysEventType> loadAllSysEventType() {
		return (List<SysEventType>) sysEventTypeRepository.findAll();
	}

	@GetMapping("loadAllSysProjectState")
	@ResponseBody
	public List<SysProjectState> loadAllSysProjectState() {
		return (List<SysProjectState>) sysProjectStateRepository.findAll();
	}

	@GetMapping("loadAllSysReportType")
	@ResponseBody
	public List<SysReportType> loadAllSysReportType() {
		return (List<SysReportType>) sysReportTypeRepository.findAll();
	}

	@GetMapping("loadAllSysTechnicalApplicationType")
	@ResponseBody
	public List<SysTechnicalApplicationType> loadAllSysTechnicalApplicationType() {
		return (List<SysTechnicalApplicationType>) sysTechnicalApplicationTypeRepository.findAll();
	}

	@GetMapping("loadAllSysInventoryType")
	@ResponseBody
	public List<SysInventoryType> loadAllSysInventoryType() {
		return (List<SysInventoryType>) sysInventoryTypeRepository.findAll();
	}

	@GetMapping("loadAllSysInventoryRecordTypeRepository")
	@ResponseBody
	public List<SysInventoryRecordType> loadAllSysInventoryRecordTypeRepository() {
		return (List<SysInventoryRecordType>) sysInventoryRecordTypeRepository.findAll();
	}

	@GetMapping("loadAllSysInventoryRecordStateRepository")
	@ResponseBody
	public List<SysInventoryRecordState> loadAllSysInventoryRecordStateRepository() {
		return (List<SysInventoryRecordState>) sysInventoryRecordStateRepository.findAll();
	}

	@PostMapping("/modify-password")
	@ResponseBody
	public String modifyPassword(String newPassword, String confirmPassword) {
		logger.info("message " + String.format("%s %s", newPassword, confirmPassword));
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (newPassword.compareTo(confirmPassword) != 0) {
			return "两次密码不匹配";
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode(newPassword);
		logger.info("message result=" + result);

		user.setPassword("{bcrypt}" + result);
		sysUserService.save(user);
		return "success";
	}
}
