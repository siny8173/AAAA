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
public class Event {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Customer customer;
	
	private Date startTime;
	
	private Date stopTime;
	@OneToOne
	private SysEventType type;
	
	private String content;
	
	@ManyToOne
	private SysUser user;
}
