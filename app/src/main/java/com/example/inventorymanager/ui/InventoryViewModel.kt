package com.example.inventorymanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanager.data.model.InventoryItem
import com.example.inventorymanager.data.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    fun getInventoryItems() = inventoryRepository.getAllInventoryItems()

    fun saveInventoryItem(item: InventoryItem) {
        viewModelScope.launch {
            inventoryRepository.saveInventoryItem(item)
        }
    }

    fun editInventoryItem(item: InventoryItem) {
        viewModelScope.launch {
            inventoryRepository.updateInventoryItem(item)
        }
    }

    fun deleteInventoryItem(item: InventoryItem) {
        viewModelScope.launch {
            inventoryRepository.deleteInventoryItem(item)
        }
    }

    fun getInventoryItem(id: Int) = inventoryRepository.getInventoryItem(id)
}