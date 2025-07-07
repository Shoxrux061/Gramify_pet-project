package uz.shoxrux.main.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.main.presentation.screens.main.home.HomeViewModel
import uz.shoxrux.main.presentation.screens.main.reels.ReelsViewModel
import uz.shoxrux.main.presentation.ui.components.bottom_nav.BottomNavHost
import uz.shoxrux.main.presentation.ui.components.bottom_nav.MyBottomNavigation

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val reelsViewModel = hiltViewModel<ReelsViewModel>()



    Scaffold(
        modifier = Modifier.padding(0.dp).background(LocalAppColors.current.background),
        bottomBar = {
            MyBottomNavigation(
                navController = navController
            )
        }
    ) { paddingValues ->
        Column {

            BottomNavHost(
                navController,
                paddingValues,
                homeViewModel,
                reelsViewModel
            )
        }
    }
}