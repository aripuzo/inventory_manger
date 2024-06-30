package com.example.inventorymanager.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventorymanager.ui.screens.inventory.EditInventoryScreen
import com.example.inventorymanager.ui.screens.inventory.InventoryCreateScreen
import com.example.inventorymanager.ui.screens.inventory.InventoryListingScreen
import com.example.inventorymanager.ui.theme.InventoryManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    //add InventoryViewModel
    private val viewModel by viewModels<InventoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("loggedIn", false)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContent {
            InventoryManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(viewModel = viewModel, sharedPreferences = sharedPreferences)
                }
            }
        }
    }
}

@Composable
fun AppNavigator(viewModel: InventoryViewModel, sharedPreferences: SharedPreferences) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inventoryList") {
        composable("inventoryList") {
            InventoryListingScreen(
                items = viewModel.getInventoryItems(),
                onAddItem = {
                    navController.navigate("inventoryCreate")
                },
                onLogout = {
                    with(sharedPreferences.edit()) {
                        clear()
                        apply()
                    }
                },
                onItemClicked = { item ->
                    navController.navigate("inventoryEdit/${item.id}")
                }
            )
        }
        composable("inventoryCreate") { InventoryCreateScreen(onSaveInventoryItem = {
            viewModel.saveInventoryItem(it)
            navController.popBackStack()
        }) }
        composable("inventoryEdit/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            // Fetch the item with the given ID from your data source
            val item = viewModel.getInventoryItem(itemId ?: 0)
            EditInventoryScreen(item, onSaveButtonClicked = {}, onDeleteButtonClicked = {})
        }
    }
}
