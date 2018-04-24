package com.lihao.crm.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class SysProvince {

	@Id
    private Long id;
	private String name;	
	@OneToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
	private Set<SysCity> cities;
}
