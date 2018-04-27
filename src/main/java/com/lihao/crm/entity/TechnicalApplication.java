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
public class TechnicalApplication {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Project project;

	@OneToOne
	private Customer customer;

	private Date startTime;

	private Date stopTime;
	
	private Boolean isNeedReport = true;
	
	@OneToOne
	private SysReportType sysReportType;
	
	@OneToOne
	private SysTechnicalApplicationType type;

	@Column(updatable = false)
	private Date cretateTime = new Date();

	@ManyToOne
	private SysUser user;

	private Boolean isDelete = false;
}
