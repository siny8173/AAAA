package com.lihao.crm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lihao.crm.entity.SysUser;

public interface SysUserRepository extends PagingAndSortingRepository<SysUser, Long> {
	public SysUser findByUsername(String username);
}
