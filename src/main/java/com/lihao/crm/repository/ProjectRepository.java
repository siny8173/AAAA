package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;

public interface ProjectRepository extends BaseRepository<Project, Long> {

	public List<Project> findAllByUserAndIsDeleteNot(SysUser me, boolean flag);

	public List<Project> findAllByIsDeleteNotAndUserAndDepartment(boolean flag, SysUser me, Department department);
}
