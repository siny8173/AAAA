package com.lihao.crm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SysInventoryRecordType {

	@Id
    private Long id;
	private String name;	
}
