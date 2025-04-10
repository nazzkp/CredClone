package com.example.clcred.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clcred.view.ShopScreen
import com.example.clcred.view.StoreScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Shop") {

        composable("Shop") {
            ShopScreen(navController)
        }
        composable("Store") {
            StoreScreen(navController)
        }
    }
}