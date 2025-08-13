package uz.shoxrux.main.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.dto.like.LikeModel
import uz.shoxrux.main.domain.model.CommentModel
import uz.shoxrux.main.domain.model.post.PostModel
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import uz.shoxrux.main.domain.reposiotry.ProfileRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val profileRepository: ProfileRepository
) {

    suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>> {
        return homeRepository.getPosts()
    }

    suspend fun getComments(postId: String): Flow<NetworkResult<List<CommentModel>>> {
        return homeRepository.getComments(postId)
    }

    suspend fun sendComment(comment: CommentModel): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading())
        try {
            val profileResult =
                profileRepository.getSelfProfile().first { it !is NetworkResult.Loading }

            val profile = when (profileResult) {
                is NetworkResult.Success -> profileResult.data
                is NetworkResult.Error -> {
                    emit(NetworkResult.Error("Не удалось загрузить профиль"))
                    return@flow
                }

                else -> null
            } ?: return@flow

            val commentWithAuthor = comment.copy(
                authorName = profile.username,
            )

            homeRepository.sendComment(commentWithAuthor).collect { result ->
                emit(result)
            }

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    suspend fun syncLikes(
        likes: List<LikeModel>,
        unlikes: List<LikeModel>
    ): Flow<NetworkResult<Boolean>> {
        return homeRepository.syncLikes(likes, unlikes)
    }
}