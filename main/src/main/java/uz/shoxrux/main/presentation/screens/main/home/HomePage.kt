package uz.shoxrux.main.presentation.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.firebase.Timestamp
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.components.ErrorComponent
import uz.shoxrux.core.ui.components.LoadingBar
import uz.shoxrux.core.utils.toReadableTime
import uz.shoxrux.main.data.dto.like.LikeModel
import uz.shoxrux.main.data.dto.like.LikeType
import uz.shoxrux.main.domain.model.CommentModel

@Composable
fun HomePage(viewModel: HomeViewModel) {
    val colors = LocalAppColors.current
    val state = viewModel.homeUiState.collectAsState().value
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.syncLikes()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Scaffold(
            modifier = Modifier.background(colors.background),
            topBar = {
                Row(
                    modifier = Modifier
                        .background(colors.background)
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "GRAMIFY",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                            color = colors.brandPrimary,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(Modifier.weight(1f))
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
                }
            }
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier
                    .background(colors.background)
                    .fillMaxSize()
            ) {
                item {
                    state.error?.let { ErrorComponent(it) }
                }

                item { StoryItem(modifier = Modifier.padding(16.dp)) }

                items(state.posts.size) { index ->
                    val post = state.posts[index]
                    HomePageItem(
                        imageUrl = post.imageUrl,
                        likeCount = post.likeCount.toString(),
                        sharesCount = "0",
                        commentCount = "0",
                        title = post.content,
                        postTime = post.postTime?.toReadableTime() ?: "",
                        isLiked = post.isLiked,
                        onCommentClicked = { viewModel.openComments(post.id) },
                        onLikeClicked = {
                            if (!post.isLiked) {
                                viewModel.saveLocalLikes(LikeModel(post.id, type = LikeType.Post))
                            } else {
                                viewModel.saveLocalLikes(
                                    unlike = LikeModel(
                                        post.id,
                                        type = LikeType.Post
                                    )
                                )
                            }
                            viewModel.toggleLike(post.id)
                        },
                        onShareClicked = {}
                    )
                }

                item {
                    if (state.posts.isEmpty() && !state.isLoading) {
                        Box(Modifier.fillMaxSize()) {
                            Text(
                                text = "No posts",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                                    color = colors.textTitle,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }

        if (state.isLoading) LoadingBar()

        CommentBottomSheet(
            state = state.commentState,
            onDismiss = { viewModel.closeComments() },
            onValueChange = { viewModel.onCommentValueChange(it) },
            onSend = {
                viewModel.sendComment(
                    CommentModel(
                        content = state.commentState.commentInput,
                        postId = state.commentState.postId
                    )
                )
            }
        )

    }
}


fun Timestamp.toReadableTime(): String = this.seconds.toReadableTime()