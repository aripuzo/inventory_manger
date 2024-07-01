package com.example.inventorymanager.data.repository

import androidx.lifecycle.LiveData
import com.example.inventorymanager.data.db.InventoryItemDao
import com.example.inventorymanager.data.model.InventoryItem
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class InventoryRepository @Inject constructor (private val inventoryItemDao: InventoryItemDao) {
    fun getAllInventoryItems(): Flow<List<InventoryItem>> {
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

    suspend fun getInventoryItem(id: Int): InventoryItem {
        return inventoryItemDao.getItem(id)
    }
}