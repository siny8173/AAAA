package com.lihao.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	public Project findById(long id) {
		Project project = new Project();
		project.setCretateTime(null);
		project.setIsDelete(false);
		project.setId(id);
		Example<Project> example = Example.of(project);
		Optional<Project> temp = projectRepository.findOne(example);
		
		return temp.get();
	}

	public List<Project> loadMine(SysUser me) {
		return (List<Project>) projectRepository.findAllByUserAndIsDeleteNot(me, true);
	}
	
	public List<Project> loadMineByDepartment(SysUser me, Department department) {
		return (List<Project>) projectRepository.findAllByIsDeleteNotAndUserAndDepartment(true, me, department);
	}

	public void save(Project project) {
		projectRepository.save(project);
	}
}
