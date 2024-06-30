package com.example.inventorymanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_item")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "total_stock") val totalStock: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "description") val description: String
)