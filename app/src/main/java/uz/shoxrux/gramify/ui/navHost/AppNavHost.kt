package uz.shoxrux.gramify.ui.navHost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.shoxrux.auth.presentation.navigation.authNavGraph
import uz.shoxrux.core.utils.constants.NavRoutes
import uz.shoxrux.main.presentation.navigation.mainNavGraph

@Composable
fun AppNavHost(paddingValues: PaddingValues) {

    val navController = rememberNavController()

    NavHost(
        startDestination = NavRoutes.AUTH_SCREEN,
        navController = navController
    ) {

        authNavGraph(navController)

        mainNavGraph(navController)

    }

}