package com.example.inventorymanager.data.repository

import com.example.inventorymanager.data.db.InventoryItemDao
import com.example.inventorymanager.data.model.InventoryItem

class InventoryRepository(private val inventoryItemDao: InventoryItemDao) {
    fun getAllInventoryItems(): List<InventoryItem> {
        return inventoryItemDao.getAllItems()
    }

    suspend fun saveInventoryItem(item: InventoryItem) {
        inventoryItemDao.insert(item)
    }

    suspend fun updateInventoryItem(item: InventoryItem) {
        inventoryItemDao.update(item)
    }

    suspend fun deleteInventoryItem(item: InventoryItem) {
        inventoryItemDao.delete(item)
    }

    fun getInventoryItem(id: Int): InventoryItem {
        return inventoryItemDao.getItem(id)
    }
}