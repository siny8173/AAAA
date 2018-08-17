package com.lihao.crm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.InventoryRecord;
import com.lihao.crm.entity.SysInventoryRecordState;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.entity.TechnicalApplication;
import com.lihao.crm.entity.TechnicalApplicationReport;
import com.lihao.crm.service.InventoryRecordService;
import com.lihao.crm.service.InventoryService;
import com.lihao.crm.service.TechnicalApplicationReportService;
import com.lihao.crm.service.TechnicalApplicationService;

@Controller
@RequestMapping("/technicist")
public class TechnicistController {

	private static final Logger logger = LoggerFactory.getLogger(TechnicistController.class);

	@Autowired
	private TechnicalApplicationService technicalApplicationService;

	@Autowired
	private TechnicalApplicationReportService technicalApplicationReportService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InventoryRecordService inventoryRecordService;

	@GetMapping("/main")
	private String main() {
		return "technicist/main";
	}

	@GetMapping("/technical-application")
	private String technicalApplication(Model model) {
		logger.info("TechnicistController technicalApplication");

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<TechnicalApplication> technicalApplications = technicalApplicationService.loadByTechnicist((SysUser) user);
		model.addAttribute("technicalApplications", technicalApplications);

		return "technicist/technical-application";
	}

	@GetMapping("/inventory")
	private String inventory() {
		return "technicist/inventory";
	}

	@GetMapping("/inventory-add")
	private String inventoryAdd() {
		return "technicist/inventory-add";
	}

	@GetMapping("loadAllInventory")
	@ResponseBody
	public List<Inventory> loadAllInventory() {
		logger.info("TechnicistController loadAllInventory ");
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return inventoryService.loadByUser((SysUser) user);
	}

	@PostMapping("addInventory")
	@ResponseBody
	public String addInventory(Inventory inventory) {
		logger.info("TechnicistController addInventory " + inventory.getName());
		inventory.setId(null);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		inventory.setUser((SysUser) user);
		inventoryService.save(inventory);
		return "success";
	}

	@PostMapping("modInventory")
	@ResponseBody
	public String modInventory(Inventory inventory) {
		logger.info("TechnicistController modInventory " + inventory.getName());
		Inventory temp =  inventoryService.findById(inventory.getId());
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(temp.getUser().getId().equals(user.getId()) == false) {
			logger.warn(String.format("TechnicistController modInventory the inventory belong to %ld %s", temp.getUser().getId(), temp.getUser().getName()));
			logger.warn(String.format("TechnicistController modInventory the inventory not belong to %ld %s", user.getId(), user.getName()));
			return "failed";
		}
		
		inventoryService.save(inventory);
		return "success";
	}

	@GetMapping("/inventory-application")
	private String inventoryApplication() {
		return "technicist/inventory-application";
	}

	@PostMapping("/upload")
	@ResponseBody
	private String upload(long projectId, HttpServletRequest request, String description, MultipartFile file) {
		logger.info("TechnicistController upload description=" + description);
		try {

			TechnicalApplication technicalApplication = technicalApplicationService.findById(projectId);

			if (technicalApplication == null) {
				logger.info("TechnicistController upload not find TechnicalApplication by id=" + projectId);
				return "error";
			}

			if (!file.isEmpty()) {
				String path = request.getServletContext().getRealPath("/images/");
				String filename = file.getOriginalFilename();
				File filepath = new File(path, filename);

				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}

				File temp = new File(path + File.separator + filename);
				file.transferTo(temp);

				String uuid = UUID.randomUUID().toString();

				File dest = new File("data", uuid);

				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();
				}
				
				OutputStream os = new FileOutputStream(dest);
				Files.copy(temp.toPath(), os); 
				
				boolean flag = temp.delete();
				logger.info("message delete file " + flag);

				TechnicalApplicationReport report = new TechnicalApplicationReport();

				report.setFilename(filename);
				report.setUuid(uuid);
				report.setApplication(technicalApplication);

				Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				report.setUser((SysUser) user);
				List<TechnicalApplicationReport> reports = technicalApplication.getReports();
				if (reports == null)
					reports = new ArrayList<>();
				reports.add(report);

				technicalApplication.setReports(reports);

				technicalApplicationService.save(technicalApplication);
				return "success";
			} else {
				return "error";
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}

	@GetMapping("/data/{id}")
	public ResponseEntity<byte[]> download(@PathVariable long id, HttpServletRequest request) {
		logger.info("TechnicistController download " + id);
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

	@GetMapping("/inventory-application-grid")
	private String inventoryApplicationGird(long id, Model model) {
		logger.info("SalesmanController inventoryApplicationGird id=" + id);

		Inventory inventory = inventoryService.findById(id);
		model.addAttribute(inventory);

		List<InventoryRecord> inventoryRecords = inventoryRecordService.findByInventory(inventory);
		model.addAttribute("inventoryRecords", inventoryRecords);
		return "technicist/inventory-application-grid";
	}

	@GetMapping("/inventory-application-deal")
	@ResponseBody
	private String inventoryApplicationDeal(long id, boolean agree) {
		logger.info("SalesmanController inventoryApplicationDeal " + String.format("id=%d, agree=%b", id, agree));

		InventoryRecord inventoryRecord = inventoryRecordService.findById(id);
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Inventory temp =  inventoryRecord.getInventory();
		if(temp.getUser().getId().equals(user.getId()) == false) {
			logger.warn(String.format("TechnicistController inventoryApplicationDeal the inventory belong to %ld %s", temp.getUser().getId(), temp.getUser().getName()));
			logger.warn(String.format("TechnicistController inventoryApplicationDeal the inventory not belong to %ld %s", user.getId(), user.getName()));
			return "failed";
		}
		
		inventoryRecord.setApprover((SysUser) user);

		inventoryRecord.setTimeApproach(new Date());

		SysInventoryRecordState state = new SysInventoryRecordState();

		if (agree)
			state.setId(2l);
		else
			state.setId(3l);
		inventoryRecord.setState(state);
		
		inventoryRecordService.save(inventoryRecord);
		return "success";
	}
}
