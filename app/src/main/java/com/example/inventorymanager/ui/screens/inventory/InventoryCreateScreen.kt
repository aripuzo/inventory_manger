package com.example.inventorymanager.ui.screens.inventory

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavHostController
import com.example.inventorymanager.data.model.InventoryItem
import com.example.inventorymanager.ui.InventoryViewModel

@Composable
fun InventoryCreateScreen(
    onSaveInventoryItem: (InventoryItem) -> Unit
) {
    var itemName by remember { mutableStateOf(TextFieldValue()) }
    var totalStock by remember { mutableStateOf(TextFieldValue()) }
    var price by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(16.dp)) {
        val context = LocalContext.current
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            isError = itemName.text.isEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = totalStock,
            onValueChange = { totalStock = it },
            label = { Text("Total Stock") },
            isError = totalStock.text.isEmpty() || !totalStock.text.isDigitsOnly(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            isError = price.text.isEmpty() || !price.text.isDigitsOnly(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            isError = description.text.isEmpty() || description.text.split(" ").size < 3,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Perform validation and save the item to the inventory
                if (itemName.text.isNotEmpty() &&
                    totalStock.text.isNotEmpty() && totalStock.text.isDigitsOnly() &&
                    price.text.isNotEmpty() && price.text.isDigitsOnly() &&
                    description.text.isNotEmpty()
                ) {
                    val item = InventoryItem(
                        id = 0,
                        name = itemName.text,
                        totalStock = totalStock.text.toInt(),
                        price = price.text.toDouble(),
                        description = description.text
                    )
                    onSaveInventoryItem(item)
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Save")
        }
    }
}

@Preview
@Composable
fun PreviewInventoryCreateScreen() {
    InventoryCreateScreen(
        onSaveInventoryItem = {}
    )
}