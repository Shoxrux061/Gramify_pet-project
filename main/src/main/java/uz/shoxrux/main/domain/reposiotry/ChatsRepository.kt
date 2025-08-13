package uz.shoxrux.main.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.dto.chat.ChatModelDto
import uz.shoxrux.main.domain.model.chats.ChatModel

interface ChatsRepository {

    fun getAllChats(): Flow<NetworkResult<List<ChatModel>>>

}