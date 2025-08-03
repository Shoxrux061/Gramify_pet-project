package uz.shoxrux.main.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.dto.like.LikeModel
import uz.shoxrux.main.domain.model.post.PostModel


interface HomeRepository {

    suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>>

    suspend fun syncLikes(
        likes: List<LikeModel>,
        unlikes: List<LikeModel>
    ): Flow<NetworkResult<Boolean>>

}