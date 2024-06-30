package com.example.inventorymanager.ui.screens.inventory

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventorymanager.data.model.InventoryItem
import com.example.inventorymanager.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InventoryListingScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            InventoryListingScreen(
                items = listOf(
                    InventoryItem(
                        id = 1,
                        name = "Item 1",
                        totalStock = 10,
                        price = 100.0,
                        description = "Description for item 1"
                    )
                ),
                onAddItem = {},
                onLogout = {},
                onItemClicked = { item ->
                    navController.navigate("inventoryEdit/${item.id}")
                }
            )
        }
    }

    @Test
    fun clickInventoryItem_NavigatesToEditScreen() {
        composeTestRule.onNodeWithText("Item 1").performClick()
        composeTestRule.onNodeWithText("Edit Inventory Item").assertExists()
    }
}