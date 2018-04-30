package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.entity.TechnicalApplication;

public interface TechnicalApplicationRepository extends BaseRepository<TechnicalApplication, Long> {
	
	public List<TechnicalApplication> findAllByUserAndIsDeleteNot(SysUser me, boolean flag);
}
