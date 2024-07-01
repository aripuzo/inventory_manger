package com.example.inventorymanager.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanager.data.model.InventoryItem
import com.example.inventorymanager.data.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    var item: InventoryItem? = null

    val inventoryItems: Flow<List<InventoryItem>> = inventoryRepository.getAllInventoryItems()

    fun saveInventoryItem(item: InventoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            println("got here")
            val r = inventoryRepository.saveInventoryItem(item)
            println(r)
        }
    }

    fun editInventoryItem(item: InventoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            inventoryRepository.updateInventoryItem(item)
        }
    }

    fun deleteInventoryItem(item: InventoryItem) {
        viewModelScope.launch {
            inventoryRepository.deleteInventoryItem(item)
        }
    }
}