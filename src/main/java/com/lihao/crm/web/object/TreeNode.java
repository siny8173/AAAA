package com.lihao.crm.web.object;

import java.util.List;

import lombok.Data;

@Data
public class TreeNode {

	private long id;
	private String text;
	private String state;
	private List<TreeNode> children;
}
