package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.SysUser;

public interface InventoryRepository extends BaseRepository<Inventory, Long> {
	public List<Inventory> findAllByUser(SysUser me);
}
