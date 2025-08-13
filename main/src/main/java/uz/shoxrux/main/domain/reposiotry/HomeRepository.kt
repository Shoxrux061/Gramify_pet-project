package uz.shoxrux.main.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.dto.like.LikeModel
import uz.shoxrux.main.domain.model.CommentModel
import uz.shoxrux.main.domain.model.post.PostModel


interface HomeRepository {

    suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>>

    suspend fun syncLikes(
        likes: List<LikeModel>,
        unlikes: List<LikeModel>
    ): Flow<NetworkResult<Boolean>>

    suspend fun getComments(id: String): Flow<NetworkResult<List<CommentModel>>>

    suspend fun sendComment(comment: CommentModel): Flow<NetworkResult<Boolean>>

}