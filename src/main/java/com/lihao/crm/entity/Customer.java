package com.lihao.crm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue
	private Long id;
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
	private String demand;
	@ManyToOne
	private SysUser user;
	private Boolean isDelete = false;

	@OneToOne
	private Contact contact;
}
