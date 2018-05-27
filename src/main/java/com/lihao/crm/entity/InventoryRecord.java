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
public class InventoryRecord {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Company company;
	@ManyToOne
	private Department department;
	@ManyToOne
	private Project project;
	@OneToOne
	private Customer customer;
	@ManyToOne
	private Inventory inventory;
	@Column(updatable = false)
	private Float count;
	@ManyToOne
	private SysInventoryRecordState state;
	@ManyToOne
	private SysInventoryRecordType type;
	@ManyToOne
	private SysUser proposer;
	@ManyToOne
	private SysUser approver;
	@Column(updatable = false)
	private Date timeApplication = new Date();
	@Column(updatable = false)
	private Date timeApproach;
}
