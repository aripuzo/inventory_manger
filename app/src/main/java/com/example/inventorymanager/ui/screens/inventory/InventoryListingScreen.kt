package com.example.inventorymanager.ui.screens.inventory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventorymanager.data.model.InventoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryListingScreen(
    items: List<InventoryItem>,
    onAddItem: () -> Unit,
    onLogout: () -> Unit,
    onItemClicked: (InventoryItem) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventory Listing") },
                actions = {
                    IconButton(onClick = { onLogout() }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddItem() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEach { item ->
                Box(modifier = Modifier.clickable { onItemClicked(item) }) {
                    Text("Name: ${item.name}")
                    Text("Price: $${item.price}")
                    Text("Stock: ${item.totalStock}")
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInventoryListingScreen() {
    InventoryListingScreen(
        items = listOf(
            InventoryItem(1,"Item 1", 10, 100.0, "Description 1"),
            InventoryItem(2, "Item 2", 20, 200.0, "Description 2"),
            InventoryItem(2, "Item 3", 30, 300.0, "Description 3"),
        ),
        onAddItem = {},
        onLogout = {},
        onItemClicked = {}
    )
}