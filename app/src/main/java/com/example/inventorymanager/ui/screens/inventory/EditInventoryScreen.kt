package com.example.inventorymanager.ui.screens.inventory

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.inventorymanager.data.model.InventoryItem
import com.example.inventorymanager.ui.components.DeleteDialog

@Composable
fun EditInventoryScreen(
    inventoryItem: InventoryItem,
    onUpdateInventoryItem: (InventoryItem) -> Unit,
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

        val context = LocalContext.current

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
            onClick = {
                if (itemName.text.isNotEmpty() &&
                    totalStock.text.isNotEmpty()&&
                    price.text.isNotEmpty() &&
                    description.text.isNotEmpty()
                ) {
                    inventoryItem.name = itemName.text
                    inventoryItem.totalStock = totalStock.text.toInt()
                    inventoryItem.price = price.text.toDouble()
                    inventoryItem.description = description.text
                    onUpdateInventoryItem(inventoryItem)
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
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
        onUpdateInventoryItem = {},
        onDeleteButtonClicked = {}
    )
}