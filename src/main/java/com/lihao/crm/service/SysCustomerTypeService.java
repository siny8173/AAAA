package com.lihao.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysCustomerType;
import com.lihao.crm.repository.SysCustomerTypeRepository;

@Service
public class SysCustomerTypeService {

	@Autowired
	SysCustomerTypeRepository sysCustomerTypeRepository;

	public List<SysCustomerType> loadAll() {
		return (List<SysCustomerType>) sysCustomerTypeRepository.findAll();
	}
}
