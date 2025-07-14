package uz.shoxrux.auth.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import uz.shoxrux.auth.presentation.screens.sign_in.SignInScreen
import uz.shoxrux.auth.presentation.screens.sign_up.SignUpScreen
import uz.shoxrux.core.utils.constants.NavRoutes

const val LOGIN_ROUTE = "login"
const val REGISTER_ROUTE = "register"

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = NavRoutes.AUTH_SCREEN,
        startDestination = REGISTER_ROUTE
    ) {

        composable(LOGIN_ROUTE) {
            SignInScreen(navController)
        }
        composable(REGISTER_ROUTE) {
            SignUpScreen(navController)
        }
    }

}