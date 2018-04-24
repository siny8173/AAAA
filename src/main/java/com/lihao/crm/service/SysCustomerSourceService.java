package com.lihao.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysCustomerSource;
import com.lihao.crm.repository.SysCustomerSourceRepository;

@Service
public class SysCustomerSourceService {

	@Autowired
	SysCustomerSourceRepository sysCustomerSourceRepository;

	public List<SysCustomerSource> loadAll() {
		return (List<SysCustomerSource>) sysCustomerSourceRepository.findAll();
	}
}
