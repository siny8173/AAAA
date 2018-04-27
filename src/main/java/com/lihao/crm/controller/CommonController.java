package com.lihao.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lihao.crm.entity.SysDepartment;
import com.lihao.crm.entity.SysDuty;
import com.lihao.crm.entity.SysEventType;
import com.lihao.crm.entity.SysProjectState;
import com.lihao.crm.entity.SysReportType;
import com.lihao.crm.entity.SysSex;
import com.lihao.crm.entity.SysTechnicalApplicationType;
import com.lihao.crm.repository.SysDepartmentRepository;
import com.lihao.crm.repository.SysDutyRepository;
import com.lihao.crm.repository.SysEventTypeRepository;
import com.lihao.crm.repository.SysProjectStateRepository;
import com.lihao.crm.repository.SysReportTypeRepository;
import com.lihao.crm.repository.SysSexRepository;
import com.lihao.crm.repository.SysTechnicalApplicationTypeRepository;

@Controller
@RequestMapping("/common")
public class CommonController {

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
}
