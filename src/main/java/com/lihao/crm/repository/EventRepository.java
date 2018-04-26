package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Event;
import com.lihao.crm.entity.SysUser;

public interface EventRepository extends BaseRepository<Event, Long> {
	
	public List<Event> findAllByUserAndIsDeleteNot(SysUser me, boolean flag);
}
