package uz.shoxrux.main.presentation.screens.main.home

import uz.shoxrux.main.domain.model.CommentModel
import uz.shoxrux.main.domain.model.post.PostModel

data class HomeUiState(

    val isLoading: Boolean = false,
    val error: String? = null,
    val posts: List<PostModel> = emptyList(),
    val commentState: OpenedCommentState = OpenedCommentState(),
)

data class OpenedCommentState(
    val isOpened: Boolean = false,
    val postId: String = "",
    val isLoading: Boolean = false,
    val commentInput: String = "",
    val comments: List<CommentModel> = emptyList()
)