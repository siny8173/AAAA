package com.lihao.crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SysNation {

	@Id
    @GeneratedValue
    private Long id;
	private String name;	
//	@OneToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
//	private Set<SysProvince> provinces;
}
