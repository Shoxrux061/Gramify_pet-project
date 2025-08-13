package uz.shoxrux.main.data.dto.comment

import com.google.firebase.Timestamp

data class CommentDTO(
    val postId: String = "",
    val content: String = "",
    val authorName: String = "",
    val authorAvatarUrl: String? = "",
    val postedTime: Timestamp = Timestamp.now()
)