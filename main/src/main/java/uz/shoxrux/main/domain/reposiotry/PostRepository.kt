package uz.shoxrux.main.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.dto.post.PostDTO

interface PostRepository {

    suspend fun post(postModel: PostDTO, byteArray: ByteArray) : Flow<NetworkResult<Boolean>>

}