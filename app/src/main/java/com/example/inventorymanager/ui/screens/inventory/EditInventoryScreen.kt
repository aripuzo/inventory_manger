package com.example.inventorymanager.ui.screens.inventory

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.inventorymanager.data.model.InventoryItem
import com.example.inventorymanager.ui.components.DeleteDialog

@Composable
fun EditInventoryScreen(
    inventoryItem: InventoryItem,
    onSaveButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
) {
    var itemName by remember { mutableStateOf(TextFieldValue(inventoryItem.name)) }
    var totalStock by remember { mutableStateOf(TextFieldValue("${inventoryItem.totalStock}")) }
    var price by remember { mutableStateOf(TextFieldValue("${inventoryItem.price}")) }
    var description by remember { mutableStateOf(TextFieldValue(inventoryItem.description)) }

    val deleteAlertDialog = remember { mutableStateOf(false) }

    if(deleteAlertDialog.value) {
        DeleteDialog(
            item = inventoryItem,
            onDismissRequest = { deleteAlertDialog.value = false },
            onConfirmation = {
                onDeleteButtonClicked()
                deleteAlertDialog.value = false
            }
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            isError = itemName.text.isEmpty(),
            singleLine = true
        )
        OutlinedTextField(
            value = totalStock,
            onValueChange = { totalStock = it },
            label = { Text("Total Stock") },
            isError = totalStock.text.isEmpty() || !totalStock.text.isDigitsOnly(),
            singleLine = true
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            isError = price.text.isEmpty() || !price.text.isDigitsOnly(),
            singleLine = true
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            isError = description.text.isEmpty() || description.text.split(" ").size < 3,
            singleLine = true
        )
        Button(
            onClick = { onSaveButtonClicked() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Save")
        }
        Button(
            onClick = { deleteAlertDialog.value = true},
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Delete")
        }
    }
}

@Preview
@Composable
fun EditInventoryScreenPreview() {
    EditInventoryScreen(
        inventoryItem = InventoryItem(
            id = 1,
            name = "Item 1",
            totalStock = 10,
            price = 100.0,
            description = "Description for item 1"
        ),
        onSaveButtonClicked = {},
        onDeleteButtonClicked = {}
    )
}