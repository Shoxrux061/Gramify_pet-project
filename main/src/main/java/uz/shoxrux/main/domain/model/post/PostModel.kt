package uz.shoxrux.main.domain.model.post

import com.google.firebase.Timestamp
import uz.shoxrux.main.domain.model.post.CommentModel
import java.util.UUID

data class PostModel(
    val authorId: String = "",
    val id: String = UUID.randomUUID().toString(),
    val postTime: Timestamp = Timestamp.now(),
    val content: String = "",
    val imageUrl: String = "",
    val comments: List<CommentModel>? = emptyList(),
    val likes: List<String> = emptyList()
)