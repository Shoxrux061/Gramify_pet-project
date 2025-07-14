package uz.shoxrux.main.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.post.PostModel


interface HomeRepository {

    suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>>

}