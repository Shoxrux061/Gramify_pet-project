package uz.shoxrux.main.presentation.ui.components.bottom_nav

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.shoxrux.core.utils.constants.NavRoutes
import uz.shoxrux.main.presentation.screens.main.home.HomePage
import uz.shoxrux.main.presentation.screens.main.home.HomeViewModel
import uz.shoxrux.main.presentation.screens.main.reels.ReelsPage
import uz.shoxrux.main.presentation.screens.main.reels.ReelsViewModel

@Composable
fun BottomNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel,
    reelsViewModel: ReelsViewModel
) {

    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        navController = navController,
        startDestination = NavRoutes.ITEM_HOME_PAGE,
        enterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 250))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 250))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 250))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 250))
        }
    ) {
        composable(route = NavRoutes.ITEM_HOME_PAGE) { HomePage(homeViewModel) }
        composable(NavRoutes.ITEM_CHAT_PAGE) {
            Text("Chat")
        }
        composable(NavRoutes.ITEM_REELS_PAGE) {
            ReelsPage(reelsViewModel)
        }
        composable(NavRoutes.ITEM_POST_PAGE) {
            Text("Post")
        }
        composable(NavRoutes.ITEM_PROFILE_PAGE) {
            Text("Profile  ")
        }


    }
}