package uz.shoxrux.main.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.profile.ProfileModel

interface ProfileRepository {

    suspend fun getSelfProfile(): Flow<NetworkResult<ProfileModel>>
    suspend fun getProfileDataById(id: String): Flow<NetworkResult<ProfileModel>>
    suspend fun getProfileId(): Flow<NetworkResult<String?>>

}