package uz.shoxrux.main.domain.model.chats

import com.google.firebase.Timestamp
import java.util.UUID

data class MessageModel(
    val messageId:String = UUID.randomUUID().toString(),
    val type:MessageTypes = MessageTypes.Text,
    val sender:String = "",
    val imageUrl:String? = null,
    val text:String? = null,
    val authorId:String = "",
    val timestamp: Timestamp? = null

)