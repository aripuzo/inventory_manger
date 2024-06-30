package com.example.inventorymanager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventorymanager.data.model.InventoryItem

@Database(entities = [InventoryItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryItemDao
}