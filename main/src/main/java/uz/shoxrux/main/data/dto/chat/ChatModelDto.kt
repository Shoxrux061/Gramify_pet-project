package uz.shoxrux.main.data.dto.chat

import com.google.firebase.Timestamp
import uz.shoxrux.main.domain.model.chats.ChatType

data class ChatModelDto(

    val chatId: String = "",
    val members: Map<String, Boolean> = emptyMap(),
    val type: ChatType = ChatType.Personal,
    val lastMessage: String,
    val lastMessageTimestamp: Timestamp

)