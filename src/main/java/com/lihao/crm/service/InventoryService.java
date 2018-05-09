package com.lihao.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.SysUser;
import com.lihao.crm.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	public Inventory findById(long id) {
		Inventory inventory = new Inventory();

		inventory.setId(id);
		Example<Inventory> example = Example.of(inventory);
		Optional<Inventory> temp = inventoryRepository.findOne(example);

		return temp.get();
	}

	public List<Inventory> loadAll() {
		return (List<Inventory>) inventoryRepository.findAll();
	}
	
	public List<Inventory> loadByUser(SysUser user) {
		return (List<Inventory>) inventoryRepository.findAllByUser(user);
	}

	public void save(Inventory inventory) {
		inventoryRepository.save(inventory);
	}
	
	
}
