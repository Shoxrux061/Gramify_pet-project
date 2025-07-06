package uz.shoxrux.gramify.ui.navHost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.utils.constants.NavRoutes
import uz.shoxrux.main.presentation.screens.main.MainScreen
import uz.shoxrux.main.presentation.screens.main.home.HomePage

@Composable
fun AppNavHost(paddingValues: PaddingValues) {

    val navController = rememberNavController()

    NavHost(
        startDestination = NavRoutes.MAIN_SCREEN,
        navController = navController,
        modifier = Modifier
            .background(LocalAppColors.current.background)
    ) {
        composable(NavRoutes.MAIN_SCREEN) {
            MainScreen()
        }

    }
}