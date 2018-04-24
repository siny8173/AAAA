package com.lihao.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysCity;
import com.lihao.crm.repository.SysCityRepository;

@Service
public class SysCityService {

	@Autowired
	SysCityRepository sysCityRepository;

	public List<SysCity> loadAll() {
		return (List<SysCity>) sysCityRepository.findAll();
	}
	
	public List<SysCity> findAll(long provinceId) {
		return sysCityRepository.findAllByProvinceId(provinceId);
	}
	
	public void save(SysCity city) {
		sysCityRepository.save(city);
	}
}
