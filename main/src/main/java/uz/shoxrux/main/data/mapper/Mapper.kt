package uz.shoxrux.main.data.mapper

import uz.shoxrux.main.data.dto.chat.ChatModelDto
import uz.shoxrux.main.data.dto.wiki.OriginalDto
import uz.shoxrux.main.data.dto.wiki.PageDto
import uz.shoxrux.main.data.dto.wiki.QueryDto
import uz.shoxrux.main.data.dto.wiki.WikiListResponseDto
import uz.shoxrux.main.domain.model.chats.ChatModel
import uz.shoxrux.main.domain.model.wiki.Original
import uz.shoxrux.main.domain.model.wiki.Page
import uz.shoxrux.main.domain.model.wiki.Query
import uz.shoxrux.main.domain.model.wiki.WikiList

fun PageDto.toDomain(): Page {
    return Page(
        ns = this.ns,
        pageid = this.pageid,
        title = this.title,
        original = this.original?.toDomain(),
        extract = this.extract
    )
}

fun OriginalDto.toDomain(): Original {

    return Original(
        width = this.width,
        height = this.height,
        source = this.source
    )
}

fun QueryDto.toDomain(): Query {
    return Query(
        pages = this.pages.map { (key, value) ->
            key to value.toDomain()
        }.toMap()
    )
}

fun WikiListResponseDto.toDomain(): WikiList {
    return WikiList(
        query = this.query.toDomain()
    )
}

fun ChatModelDto.toDomain(): ChatModel {

    return ChatModel(
        chatId = this.chatId,
        members = this.members,
        type = this.type,
        lastMessage = this.lastMessage,
        lastMessageTimestamp = this.lastMessageTimestamp
    )

}