package com.lihao.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.InventoryRecord;
import com.lihao.crm.repository.InventoryRecordRepository;

@Service
public class InventoryRecordService {

	@Autowired
	InventoryRecordRepository inventoryRecordRepository;

	// public Inventory findById(long id) {
	// Inventory inventory = new Inventory();
	//
	// inventory.setId(id);
	// Example<Inventory> example = Example.of(inventory);
	// Optional<Inventory> temp = inventoryRepository.findOne(example);
	//
	// return temp.get();
	// }
	//
	// public List<Inventory> loadAll() {
	// return (List<Inventory>) inventoryRepository.findAll();
	// }

	public void save(InventoryRecord inventoryRecord) {
		inventoryRecordRepository.save(inventoryRecord);
	}
	
	public List<InventoryRecord> findByInventory(Inventory inventory) {		
		return (List<InventoryRecord>) inventoryRecordRepository.findByInventory(inventory);
	}

}
