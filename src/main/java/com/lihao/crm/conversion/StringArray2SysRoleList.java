package com.lihao.crm.conversion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.lihao.crm.entity.SysRole;
import com.lihao.crm.service.SysRoleService;

@Configuration
public class StringArray2SysRoleList implements Converter<String[], List<SysRole>> {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Override
	public List<SysRole> convert(String[] arg0) {
		return sysRoleService.findByIds(arg0);
	}

}
