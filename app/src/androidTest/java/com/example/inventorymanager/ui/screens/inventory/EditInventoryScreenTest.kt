package com.example.inventorymanager.ui.screens.inventory

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventorymanager.data.model.InventoryItem
import com.example.inventorymanager.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditInventoryScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
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
    }

    @Test
    fun deleteButton_ShowsConfirmationDialog() {
        composeTestRule.onNodeWithText("Delete").performClick()
        composeTestRule.onNodeWithText("Are you sure you want to delete Item 1?").assertExists()
    }
}