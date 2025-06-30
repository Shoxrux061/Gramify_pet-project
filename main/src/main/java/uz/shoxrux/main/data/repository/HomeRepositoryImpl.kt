package uz.shoxrux.main.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.PostModel
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : HomeRepository {

    override suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>> = flow {

        emit(NetworkResult.Loading())

        try {


        } catch (e: Exception) {
            emit(NetworkResult.Error("Unknown error"))
        }

    }


}