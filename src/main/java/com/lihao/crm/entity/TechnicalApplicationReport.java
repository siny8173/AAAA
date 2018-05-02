package com.lihao.crm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class TechnicalApplicationReport {
	@Id
	@GeneratedValue
	private Long id;
	
	private String filename;
	
	private String uuid;

	@ManyToOne
	private TechnicalApplication application;

	@Column(updatable = false)
	private Date cretateTime = new Date();

	@ManyToOne
	private SysUser user;

	private Boolean isDelete = false;
}
