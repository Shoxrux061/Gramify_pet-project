package uz.shoxrux.main.domain.model

import com.google.firebase.Timestamp

data class CommentModel(
    val postId: String = "",
    val content: String = "",
    val authorName: String = "",
    val authorAvatarUrl: String? = "",
    val postedTime: Timestamp = Timestamp.now(),
    val authorId: String = ""
)