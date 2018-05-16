package com.lihao.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.Company;
import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.CompanyRepository;
import com.lihao.crm.repository.DepartmentRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Company findById(long id) {
		Company company = new Company();
		company.setId(id);
		Example<Company> example = Example.of(company);
		Optional<Company> temp = companyRepository.findOne(example);
		return temp.get();
	}

	public List<Company> loadMine(SysUser me) {
		return (List<Company>) companyRepository.findAllByUserAndIsDeleteNot(me, true);
	}
	
	public void add(Company company) {
		Department department = new Department();
		department.setId(0l);
		department.setName("默认");
		departmentRepository.save(department);
		List<Department> departments = new ArrayList<>();
		departments.add(department);
		company.setDepartments(departments);
		companyRepository.save(company);
	}

	public void save(Company company) {
		companyRepository.save(company);
	}
}
