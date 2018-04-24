package com.lihao.crm.web.object;

import java.util.Date;

import com.lihao.crm.entity.SysCity;
import com.lihao.crm.entity.SysContactType;
import com.lihao.crm.entity.SysCustomerLevel;
import com.lihao.crm.entity.SysCustomerSource;
import com.lihao.crm.entity.SysCustomerType;
import com.lihao.crm.entity.SysDepartment;
import com.lihao.crm.entity.SysDuty;
import com.lihao.crm.entity.SysSex;
import com.lihao.crm.entity.SysUser;

import lombok.Data;

@Data
public class CustomerDto {

	public Long customerId;
	public String postcode;

	public SysCity city;
	public String business;
	public Date registrationTime;
	public SysCustomerLevel level;
	public SysCustomerType customertype;
	public SysCustomerSource source;
	public String demand;
	public SysUser user;
	public Boolean isDelete;

	public Long contactId;
	public String name;
	public SysSex sex;
	public SysDepartment department;
	public SysDuty duty;
	public SysContactType type;
	public String officePhone;
	public String fax;
	public String phone;
	public String email;
	public String address;
	public Boolean isPrimary;
}
