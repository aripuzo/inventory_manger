package com.example.inventorymanager.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.inventorymanager.ui.screens.login.LoginScreen
import com.example.inventorymanager.ui.theme.InventoryManagerTheme

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventoryManagerTheme {
                // A surface container using the 'background' color from the theme
                LoginScreen()
            }
        }
    }
}

