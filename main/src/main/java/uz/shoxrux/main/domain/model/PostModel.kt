package uz.shoxrux.main.domain.model

import com.google.firebase.Timestamp
import java.util.UUID

data class PostModel(
    val authorId: String = "",
    val id: String = UUID.randomUUID().toString(),
    val postTime: Timestamp = Timestamp.now(),
    val content: String = "",
    val imageUrl: String = "",
    val comments: List<CommentModel>? = emptyList(),
    val likes: List<String>
)