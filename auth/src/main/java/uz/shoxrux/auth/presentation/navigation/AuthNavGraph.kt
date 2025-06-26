package uz.shoxrux.auth.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import uz.shoxrux.auth.presentation.screens.sign_in.SignInScreen
import uz.shoxrux.auth.presentation.screens.sign_up.SignUpScreen

const val AUTH_GRAPH_ROUTE = "auth_graph"
const val LOGIN_ROUTE = "login"
const val REGISTER_ROUTE = "register"

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AUTH_GRAPH_ROUTE,
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