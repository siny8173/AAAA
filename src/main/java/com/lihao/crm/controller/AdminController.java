package com.lihao.crm.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lihao.crm.entity.SysCity;
import com.lihao.crm.entity.SysProvince;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.service.SysCityService;
import com.lihao.crm.service.SysProvinceService;
import com.lihao.crm.service.SysUserService;
import com.lihao.crm.service.WebAdministrativeRegionService;
import com.lihao.crm.web.object.SysUserDto;
import com.lihao.crm.web.transform.SysUserTransform;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysProvinceService sysProvinceService;
	
	@Autowired
	SysCityService sysCityService;

	@Autowired
	private WebAdministrativeRegionService webAdministrativeRegionService;

	@GetMapping("updateAdministrativeRegion")
	@ResponseBody
	public String updateAdministrativeRegion() {
		
		String jsonProvinces = webAdministrativeRegionService.getProvince("4d68afbbf33f002d");
		logger.info("message " + jsonProvinces);

		JSONObject jsonObject = (JSONObject) JSON.parse(jsonProvinces);
		JSONArray arrayProvince = (JSONArray) jsonObject.get("result");

		for (int i = 0; i < arrayProvince.size(); i++) {
			JSONObject objProvince = arrayProvince.getJSONObject(i);
			int provinceId = objProvince.getIntValue("id");

			SysProvince province = new SysProvince();

			province.setId((long) provinceId);
			province.setName(objProvince.getString("name"));
			sysProvinceService.save(province);
			Set<SysCity> cities = new HashSet<>();
			province.setCities(cities);

			String jsonCities = webAdministrativeRegionService.getCity("4d68afbbf33f002d", provinceId);
			logger.info("message " + jsonCities);

			JSONObject objCities = (JSONObject) JSON.parse(jsonCities);

			JSONArray arrayCities = objCities.getJSONArray("result");
			for (int j = 0; arrayCities != null && j <  arrayCities.size(); j++) {
				JSONObject objCity = arrayCities.getJSONObject(j);
				
				int cityId = objCity.getIntValue("id");

				String cityName = objCity.getString("name");

				SysCity city = new SysCity();
				city.setId((long) cityId);
				city.setName(cityName);	
				city.setProvinceId(province.getId());
				cities.add(city);
				
				sysCityService.save(city);
			}
			
			sysProvinceService.save(province);
		}

		return "success";
	}

	@GetMapping("loadAllSysUser")
	@ResponseBody
	public List<SysUserDto> loadAllSysUser() {
		List<SysUser> sysUsers = sysUserService.findAll();	
		List<SysUserDto> dtos = new ArrayList<>();	
		sysUsers.forEach(sysUser -> dtos.add(SysUserTransform.SysUser2Dto(sysUser)));
		return dtos;
	}

	@PostMapping("addSysUser")
	@ResponseBody
	public String addSysUser(SysUserDto sysUserDto) {
		logger.info("AdminController addSysUser " + sysUserDto.name);
		sysUserDto.userId = null;
		sysUserDto.contactId = null;
		SysUser sysUser = SysUserTransform.Dto2SysUser(sysUserDto);
		sysUserService.save(sysUser);
		return "success";
	}

	@PostMapping("modSysUser")
	@ResponseBody
	public String modSysUser(SysUserDto sysUserDto) {
		logger.info("AdminController modSysUser " + sysUserDto.name);
		SysUser sysUser = SysUserTransform.Dto2SysUser(sysUserDto);
		sysUserService.save(sysUser);
		return "success";
	}

	@PostMapping("delSysUser")
	@ResponseBody
	public String delSysUser(SysUserDto sysUserDto) {
		logger.info("AdminController delSysUser " + sysUserDto.name);
		SysUser sysUser = SysUserTransform.Dto2SysUser(sysUserDto);
		sysUserService.delete(sysUser);
		return "success";
	}

	@GetMapping("/main")
	private String main() {
		return "admin/main";
	}
	
	@GetMapping("/system-user")
	private String systemUser() {
		return "admin/system-user";
	}
	
	@GetMapping("/system-tools")
	private String systemTools() {
		return "admin/system-tools";
	}
}
