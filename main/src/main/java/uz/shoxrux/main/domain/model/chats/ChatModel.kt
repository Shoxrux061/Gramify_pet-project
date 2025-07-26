package uz.shoxrux.main.domain.model.chats

import com.google.firebase.Timestamp

data class ChatModel(

    val chatId: String = "",
    val members: Map<String, Boolean> = emptyMap(),
    val type: ChatType = ChatType.Personal,
    val lastMessage: String,
    val lastMessageTimestamp: Timestamp,
    val unreadCount: Int = 0,
    val partnerAvatarUrl: String? = null,
    val partnerName:String = ""

)