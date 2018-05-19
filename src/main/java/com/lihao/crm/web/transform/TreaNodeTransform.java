package com.lihao.crm.web.transform;

import java.util.ArrayList;
import java.util.List;

import com.lihao.crm.entity.Company;
import com.lihao.crm.entity.Department;
import com.lihao.crm.web.object.TreeNode;

public class TreaNodeTransform {

	public static final String COLSED = "closed";

	public static List<TreeNode> CompanyToTreeNode(List<Company> companies) {

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		companies.forEach(c -> treeNodes.add(CompanyToTreeNode(c)));
		return treeNodes;
	}

	public static TreeNode CompanyToTreeNode(Company company) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId(company.getId());
		treeNode.setText(company.getName());
		treeNode.setState(COLSED);

		List<TreeNode> children = new ArrayList<TreeNode>();
		company.getDepartments().forEach(d -> children.add(CompanyToTreeNode(d)));
		treeNode.setChildren(children);
		return treeNode;
	}

	public static TreeNode CompanyToTreeNode(Department department) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId(department.getId());
		treeNode.setText(department.getName());
		treeNode.setState(COLSED);
		return treeNode;
	}

}
