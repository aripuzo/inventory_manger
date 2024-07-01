package com.example.inventorymanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_item")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "total_stock") var totalStock: Int,
    @ColumnInfo(name = "price") var price: Double,
    @ColumnInfo(name = "description") var description: String
)