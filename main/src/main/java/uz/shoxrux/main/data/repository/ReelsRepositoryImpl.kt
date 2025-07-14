package uz.shoxrux.main.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.mapper.toDomain
import uz.shoxrux.main.data.network.ReelsService
import uz.shoxrux.main.domain.model.wiki.WikiList
import uz.shoxrux.main.domain.reposiotry.ReelsRepository
import javax.inject.Inject

class ReelsRepositoryImpl @Inject constructor(
    private val service: ReelsService
) : ReelsRepository {

    override suspend fun getReels(): Flow<NetworkResult<WikiList>> = flow {

        emit(NetworkResult.Loading())

        try {

            val response = service.getRandomWiki()
            if (response.isSuccessful && response.body() != null) {
                emit(NetworkResult.Success(response.body()?.toDomain()))
            }

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage))
        }

    }

}