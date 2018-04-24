package com.lihao.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysProvince;
import com.lihao.crm.repository.SysProvinceRepository;

@Service
public class SysProvinceService {

	@Autowired
	SysProvinceRepository sysProvinceRepository;

	public List<SysProvince> loadAll() {
		return (List<SysProvince>) sysProvinceRepository.findAll();
	}
	
	public void save(SysProvince province) {
		sysProvinceRepository.save(province);
	}
}
