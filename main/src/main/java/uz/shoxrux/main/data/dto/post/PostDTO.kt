package uz.shoxrux.main.data.dto.post

import com.google.firebase.Timestamp
import java.util.UUID

data class PostDTO(
    val authorId: String = "",
    val id: String = UUID.randomUUID().toString(),
    val postTime: Timestamp = Timestamp.now(),
    val content: String = "",
    val imageUrl: String = "",
    val commentCount:Int = 0,
    val likeCount: Int = 0
)