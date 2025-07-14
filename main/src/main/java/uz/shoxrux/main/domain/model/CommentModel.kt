package uz.shoxrux.main.domain.model

import com.google.firebase.Timestamp
import java.util.UUID

data class CommentModel(
    val content: String = "",
    val commentAuthor: String = "",
    val postedTime: Timestamp = Timestamp.now(),
    val id: String = UUID.randomUUID().toString()
)