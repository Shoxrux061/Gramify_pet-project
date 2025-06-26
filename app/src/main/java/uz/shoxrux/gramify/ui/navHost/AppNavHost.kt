package uz.shoxrux.gramify.ui.navHost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.shoxrux.auth.presentation.navigation.AUTH_GRAPH_ROUTE
import uz.shoxrux.auth.presentation.navigation.authNavGraph
import uz.shoxrux.core.ui.theme.LocalAppColors

@Composable
fun AppNavHost(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    val colors = LocalAppColors.current

    NavHost(
        modifier = Modifier
            .background(colors.background)
            .padding(paddingValues),
        startDestination = AUTH_GRAPH_ROUTE,
        navController = navController
    ) {
        authNavGraph(navController)
    }
}