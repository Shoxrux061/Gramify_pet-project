package uz.shoxrux.main.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.main.presentation.screens.main.chats.ChatsViewModel
import uz.shoxrux.main.presentation.screens.main.home.HomeViewModel
import uz.shoxrux.main.presentation.screens.main.post.PostViewModel
import uz.shoxrux.main.presentation.screens.main.profile.ProfileViewModel
import uz.shoxrux.main.presentation.screens.main.reels.ReelsViewModel
import uz.shoxrux.main.presentation.ui.components.bottom_nav.BottomNavHost
import uz.shoxrux.main.presentation.ui.components.bottom_nav.MyBottomNavigation

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val reelsViewModel = hiltViewModel<ReelsViewModel>()
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val postViewModel = hiltViewModel<PostViewModel>()
    val chatsViewModel = hiltViewModel<ChatsViewModel>()

    LaunchedEffect(Unit) {
        homeViewModel.getPosts()
        profileViewModel.getProfileData()
        chatsViewModel.getChats()
    }

    Scaffold(
        modifier = Modifier.background(LocalAppColors.current.background),
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
                reelsViewModel,
                profileViewModel,
                postViewModel,
                chatsViewModel
            )
        }
    }
}