package com.lihao.crm.web.transform;

import com.lihao.crm.entity.Contact;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.web.object.SysUserDto;

public class SysUserTransform {

	public static SysUserDto SysUser2Dto(SysUser sysUser) {
		
		SysUserDto dto = new SysUserDto();
		
		dto.userId = sysUser.getId();
		dto.password = sysUser.getPassword();
		dto.username = sysUser.getUsername();
		dto.roles = sysUser.getRoles();
		
		Contact contact = sysUser.getContact();
		
		dto.contactId = contact.getId();
		dto.name = contact.getName();
		dto.sex = contact.getSex();
		
		dto.department = contact.getDepartment();
		dto.duty = contact.getDuty();
		dto.type = contact.getType();
		
		dto.officePhone = contact.getOfficePhone();
		dto.fax = contact.getFax();
		dto.phone = contact.getPhone();
		dto.email = contact.getEmail();
		dto.address = contact.getAddress();
		dto.isPrimary = contact.getIsPrimary();
		
		return dto;
	}
	
	public static SysUser Dto2SysUser(SysUserDto dto) {
		SysUser sysUser = new SysUser();
		sysUser.setId(dto.userId);
		sysUser.setPassword(dto.password);
		sysUser.setUsername(dto.username);
		sysUser.setRoles(dto.roles);
		
		Contact contact = new Contact();
		sysUser.setContact(contact);
		
		contact.setId(dto.contactId);
		contact.setName(dto.name);
		contact.setDepartment(dto.department);
		contact.setDuty(dto.duty);
		contact.setEmail(dto.email);
		contact.setFax(dto.fax);
		contact.setIsPrimary(dto.isPrimary);
		contact.setOfficePhone(dto.officePhone);
		contact.setPhone(dto.phone);
		contact.setSex(dto.sex);
		contact.setType(dto.type);
		contact.setAddress(dto.address);
		return sysUser;
	}
}
