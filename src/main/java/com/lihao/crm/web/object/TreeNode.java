package com.lihao.crm.web.object;

import java.util.List;

import com.lihao.crm.entity.Customer;

import lombok.Data;

@Data
public class TreeNode {

	public static final String COMPANY = "COMPANY";
	public static final String DEPARTMENT = "DEPARTMENT";
	public static final String CONTACT = "CONTACT";
	
	private long id;
	private String text;
	private String state;
	private List<TreeNode> children;
	private String type;
	
	private Customer customer;
}
