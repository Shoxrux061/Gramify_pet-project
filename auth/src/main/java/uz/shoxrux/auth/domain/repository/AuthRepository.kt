package uz.shoxrux.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.auth.domain.model.SignUpModel
import uz.shoxrux.core.handler.NetworkResult

interface AuthRepository {

    suspend fun signIn(login: String, password: String): Flow<NetworkResult<Boolean>>

    suspend fun signUp(data: SignUpModel): Flow<NetworkResult<Boolean>>

}