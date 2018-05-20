package com.lihao.crm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Project {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String subject;
	
	@ManyToOne
	private Company company;

	@ManyToOne
	private Department department;

	@OneToOne
	private Customer customer;

	@OneToOne
	private SysProjectState state;

	private Double money;

	private Date timeToSuccess;

	private Integer probability;

	private String content;
	
	@Column(updatable = false)
	private Date cretateTime = new Date();

	@ManyToOne
	private SysUser user;

	private Boolean isDelete = false;
}
