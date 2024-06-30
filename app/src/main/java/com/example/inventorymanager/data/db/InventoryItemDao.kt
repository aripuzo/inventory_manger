package com.example.inventorymanager.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventorymanager.data.model.InventoryItem

@Dao
interface InventoryItemDao {
    @Query("SELECT * FROM inventory_item")
    fun getAllItems(): List<InventoryItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: InventoryItem): Long

    @Update
    suspend fun update(item: InventoryItem)

    @Delete
    suspend fun delete(item: InventoryItem)

    @Query("SELECT * FROM inventory_item WHERE id = :id")
    fun getItem(id: Int): InventoryItem
}