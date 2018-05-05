package com.lihao.crm.repository;

import java.util.List;

import com.lihao.crm.entity.Inventory;
import com.lihao.crm.entity.InventoryRecord;

public interface InventoryRecordRepository extends BaseRepository<InventoryRecord, Long> {

	public List<InventoryRecord> findByInventory(Inventory inventory);
}
