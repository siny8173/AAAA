package com.lihao.crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Contact {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne
	private SysSex sex;
	@ManyToOne
	private SysDepartment department;
	@ManyToOne
	private SysDuty duty;
	@ManyToOne
	private SysContactType type;

	private String officePhone;
	private String fax;
	private String phone;
	private String email;
	private String address;
	private Boolean isPrimary;
}
