package com.lihao.crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Inventory {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String trademark;
	private Float count;
	@OneToOne
	private SysInventoryType type;
	
}
