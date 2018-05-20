package com.lihao.crm.web.transform;

import java.util.ArrayList;
import java.util.List;

import com.lihao.crm.entity.Company;
import com.lihao.crm.entity.Customer;
import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.Project;
import com.lihao.crm.web.object.TreeNode;

public class TreaNodeTransform {

	public static final String COLSED = "closed";
	public static final String OPEN = "OPEN";

	public static List<TreeNode> ProjectToTreeNode(List<Project> projects) {

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		projects.forEach(p -> treeNodes.add(ProjectToTreeNode(p)));
		return treeNodes;
	}

	public static TreeNode ProjectToTreeNode(Project project) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId(project.getId());
		treeNode.setText(project.getName());
		treeNode.setState(OPEN);
		treeNode.setType(TreeNode.CONTACT);
		treeNode.setAttributes(project);
		return treeNode;
	}

	public static List<TreeNode> CustomerToTreeNode(List<Customer> customers) {

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		customers.forEach(c -> treeNodes.add(CustomerToTreeNode(c)));
		return treeNodes;
	}

	public static TreeNode CustomerToTreeNode(Customer customer) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId(customer.getId());
		treeNode.setText(customer.getName());
		treeNode.setState(OPEN);
		treeNode.setType(TreeNode.CONTACT);
		treeNode.setCustomer(customer);
		return treeNode;
	}

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
		treeNode.setType(TreeNode.COMPANY);

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
		treeNode.setType(TreeNode.DEPARTMENT);
		return treeNode;
	}

}
