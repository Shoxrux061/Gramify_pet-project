package uz.shoxrux.main.domain.model.post

import com.google.firebase.Timestamp
import java.util.UUID

data class PostModel(
    val authorId: String = "",
    val id: String = UUID.randomUUID().toString(),
    val postTime: Timestamp? = null,
    val content: String = "",
    val imageUrl: String = "",
    val commentCount: Int = 0,
    val likeCount: Int = 0,
    val isLiked: Boolean = false
)