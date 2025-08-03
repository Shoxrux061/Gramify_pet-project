package uz.shoxrux.main.domain.model.post

import com.google.firebase.Timestamp

data class CommentModel(
    val content: String = "",
    val author: String = "",
    val postId: String = "",
    val postedTime: Timestamp = Timestamp.now()
)