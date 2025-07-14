package uz.shoxrux.main.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.wiki.WikiList

interface ReelsRepository {

    suspend fun getReels() : Flow<NetworkResult<WikiList>>

}