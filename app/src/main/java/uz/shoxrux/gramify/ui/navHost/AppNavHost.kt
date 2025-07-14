package uz.shoxrux.gramify.ui.navHost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(paddingValues: PaddingValues) {

    val navController = rememberNavController()

    NavHost(
        startDestination = "",
        navController = navController
    ) {

    }

}