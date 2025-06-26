package uz.shoxrux.auth.domain.use_case

import uz.shoxrux.auth.domain.model.SignUpModel
import uz.shoxrux.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend fun signIn(login: String, password: String) = repository.signIn(login, password)

    suspend fun signUp(data: SignUpModel) = repository.signUp(data)

}