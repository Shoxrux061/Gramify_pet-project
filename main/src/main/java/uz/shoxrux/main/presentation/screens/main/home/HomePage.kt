package uz.shoxrux.main.presentation.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val posts = viewModel.posts.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val colors = LocalAppColors.current

    LaunchedEffect(Unit) {
        viewModel.getPosts()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Scaffold(
            modifier = Modifier
                .padding(0.dp)
                .background(colors.background),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "GRAMIFY",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                                color = colors.brandPrimary,
                                fontSize = 20.sp
                            )
                        )
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = null,
                                tint = colors.brandPrimary
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.ic_notification),
                                contentDescription = null,
                                tint = colors.brandPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colors.background,
                        scrolledContainerColor = colors.background
                    )
                )
            }
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier
                    .background(colors.background)
                    .fillMaxSize()
            ) {
                item {
                    StoryItem(
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(posts.size) { index ->
                    HomePageItem(
                        imageUrl = posts[index].imageUrl,
                        likeCount = posts[index].likes.size.toString(),
                        sharesCount = "0",
                        commentCount = "0",
                        onCommentClicked = {},
                        onLikeClicked = {},
                        onShareClicked = {},
                        title = posts[index].content,
                        postTime = posts[index].postTime.toString()
                    )
                }
            }
        }
    }
}
