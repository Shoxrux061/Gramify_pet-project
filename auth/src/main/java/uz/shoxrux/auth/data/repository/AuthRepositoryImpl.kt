package uz.shoxrux.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import uz.shoxrux.auth.domain.model.SignUpModel
import uz.shoxrux.auth.domain.repository.AuthRepository
import uz.shoxrux.auth.utils.CollectionsConstants
import uz.shoxrux.core.handler.NetworkResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun signIn(login: String, password: String): Flow<NetworkResult<Boolean>> =
        flow {
            emit(NetworkResult.Loading())

            try {
                val result = firebaseAuth.signInWithEmailAndPassword(login, password).await()
                val user = result.user

                if (user != null) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error("Login or password is incorrect"))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.localizedMessage ?: "Network Error"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun signUp(data: SignUpModel): Flow<NetworkResult<Boolean>> = flow {

        emit(NetworkResult.Loading())

        try {
            val result = firebaseAuth
                .createUserWithEmailAndPassword(data.email, data.password)
                .await()

            val user = result.user

            if (user != null) {

                firestore.collection(CollectionsConstants.USERS)
                    .document(user.uid)
                    .set(data)
                    .await()

                emit(NetworkResult.Success(true))
            } else {
                emit(NetworkResult.Error("Something went wrong"))
            }

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Network Error"))
        }

    }.flowOn(Dispatchers.IO)

}