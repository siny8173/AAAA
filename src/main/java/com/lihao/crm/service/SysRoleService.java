package com.lihao.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysRole;
import com.lihao.crm.repository.SysRoleRepository;

@Service
public class SysRoleService {

	@Autowired
	SysRoleRepository sysRoleRepository;

	
	public List<SysRole> findAll() {
		return (List<SysRole>) sysRoleRepository.findAll();
	}
	
	public List<SysRole> findByIds(String[] ids) {
		List<Long> ls = new ArrayList<>();
		
		for (int l = 0; l < ids.length; l++) {
			ls.add(Long.valueOf(ids[l]));
		}
		
		return (List<SysRole>) sysRoleRepository.findAllById(ls);
	}

}
