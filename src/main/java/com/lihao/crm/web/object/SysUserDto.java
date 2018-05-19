package com.lihao.crm.web.object;

import java.util.List;

import com.lihao.crm.entity.Company;
import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.SysContactType;
import com.lihao.crm.entity.SysDuty;
import com.lihao.crm.entity.SysRole;
import com.lihao.crm.entity.SysSex;

import lombok.Data;

@Data
public class SysUserDto {


	public Long userId;
	public String username;
	public String password;
    
	public List<SysRole> roles;
	public Long contactId;
	public String name;
	public SysSex sex;
	public Company company;
	public Department department;
	public SysDuty duty;
	public SysContactType type;
	public String officePhone;
	public String fax;
	public String phone;
	public String email;
	public String address;
	public Boolean isPrimary;
}
