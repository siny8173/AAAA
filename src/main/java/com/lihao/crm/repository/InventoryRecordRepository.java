package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.InventoryRecord;
import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;

public interface InventoryRecordRepository extends BaseRepository<InventoryRecord, Long> {

	public List<InventoryRecord> findByInventory(Inventory inventory);
	
	public List<InventoryRecord> findByDepartmentAndProposer(Department department, SysUser proposer);
	
	public List<InventoryRecord> findByProjectAndProposer(Project project, SysUser proposer);
}
