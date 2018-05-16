package com.lihao.crm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Company {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	private String address;
	private String postcode;

	@OneToOne
	private SysCity city;
	private String business;
	private Date registrationTime;
	@OneToOne
	private SysCustomerLevel level;
	@OneToOne
	private SysCustomerType type;
	@OneToOne
	private SysCustomerSource source;
	@OneToMany
	private List<Department> departments;
	
	private String demand;
	@ManyToOne
	private SysUser user;
	private Boolean isDelete = false;
}
