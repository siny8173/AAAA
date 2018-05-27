package com.lihao.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.Department;
import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.InventoryRecord;
import com.lihao.crm.entity.Project;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.InventoryRecordRepository;

@Service
public class InventoryRecordService {

	@Autowired
	InventoryRecordRepository inventoryRecordRepository;

	public InventoryRecord findById(long id) {
		InventoryRecord inventoryRecord = new InventoryRecord();

		inventoryRecord.setId(id);
		inventoryRecord.setTimeApplication(null);
		Example<InventoryRecord> example = Example.of(inventoryRecord);
		Optional<InventoryRecord> temp = inventoryRecordRepository.findOne(example);

		return temp.get();
	}

	public List<InventoryRecord> loadAll() {
		return (List<InventoryRecord>) inventoryRecordRepository.findAll();
	}

	public void save(InventoryRecord inventoryRecord) {
		inventoryRecordRepository.save(inventoryRecord);
	}

	public List<InventoryRecord> findByInventory(Inventory inventory) {
		return (List<InventoryRecord>) inventoryRecordRepository.findByInventory(inventory);
	}
	
	public List<InventoryRecord> findByInventory(Department department, SysUser proposer) {
		return (List<InventoryRecord>) inventoryRecordRepository.findByDepartmentAndProposer(department, proposer);
	}
	
	public List<InventoryRecord> findByInventory(Project project, SysUser proposer) {
		return (List<InventoryRecord>) inventoryRecordRepository.findByProjectAndProposer(project, proposer);
	}

}
