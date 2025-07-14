package uz.shoxrux.main.presentation.navigation

import androidx.navigation.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import uz.shoxrux.core.utils.constants.NavRoutes
import uz.shoxrux.main.presentation.screens.main.MainScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {

    navigation(
        route = "main_graph",
        startDestination = NavRoutes.MAIN_SCREEN
    ) {
        composable(NavRoutes.MAIN_SCREEN) {
            MainScreen()
        }
    }

}