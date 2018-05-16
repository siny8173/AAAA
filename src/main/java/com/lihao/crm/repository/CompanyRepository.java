package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Company;
import com.lihao.crm.entity.SysUser;

public interface CompanyRepository extends BaseRepository<Company, Long> {
	
	public List<Company> findAllByUserAndIsDeleteNot(SysUser me, boolean flag);
}
