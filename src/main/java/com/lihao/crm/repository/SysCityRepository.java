package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.SysCity;

public interface SysCityRepository extends BaseRepository<SysCity, Long> {
	
	public List<SysCity> findAllByProvinceId(long provinceId);
}
