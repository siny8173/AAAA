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
import com.lihao.crm.entity.SysInventoryRecordState;
import com.lihao.crm.entity.SysInventoryRecordType;
import com.lihao.crm.entity.SysInventoryType;
import com.lihao.crm.entity.SysProjectState;
import com.lihao.crm.entity.SysReportType;
import com.lihao.crm.entity.SysSex;
import com.lihao.crm.entity.SysTechnicalApplicationType;
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
	
	@Autowired
	private SysInventoryTypeRepository sysInventoryTypeRepository;
	
	@Autowired
	private SysInventoryRecordTypeRepository sysInventoryRecordTypeRepository;
	
	@Autowired
	private SysInventoryRecordStateRepository sysInventoryRecordStateRepository;
	
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
	
//	
	
	@GetMapping("loadAllSysInventoryRecordStateRepository")
	@ResponseBody
	public List<SysInventoryRecordState> loadAllSysInventoryRecordStateRepository() {
		return (List<SysInventoryRecordState>) sysInventoryRecordStateRepository.findAll();
	}
}
