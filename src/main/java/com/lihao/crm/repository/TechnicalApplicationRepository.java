package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.entity.TechnicalApplication;

public interface TechnicalApplicationRepository extends BaseRepository<TechnicalApplication, Long> {
	
	public List<TechnicalApplication> findAllByUserAndIsDeleteNotOrderByIdDesc(SysUser me, boolean flag);
	
	public List<TechnicalApplication> findAllByUserAndProjectAndIsDeleteNotOrderByIdDesc(SysUser me, Project project, boolean flag);
	
	public List<TechnicalApplication> findAllByTechnicistAndIsDeleteNotOrderByIdDesc(SysUser technicist, boolean flag);
	
}
