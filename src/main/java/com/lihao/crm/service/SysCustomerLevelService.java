package com.lihao.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysCustomerLevel;
import com.lihao.crm.repository.SysCustomerLevelRepository;

@Service
public class SysCustomerLevelService {

	@Autowired
	SysCustomerLevelRepository sysCustomerLevleRepository;

	public List<SysCustomerLevel> loadAll() {
		return (List<SysCustomerLevel>) sysCustomerLevleRepository.findAll();
	}
}
