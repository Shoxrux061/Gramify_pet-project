package uz.shoxrux.main.data.repository

import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.constants.CollectionsConstants
import uz.shoxrux.main.domain.model.PostModel
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : HomeRepository {

    override suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>> = flow {
        emit(NetworkResult.Loading())

        try {
            val snapshot = firestore.collection(CollectionsConstants.POSTS).get().await()
            val posts = snapshot.documents.mapNotNull { it.toObject(PostModel::class.java) }
            emit(NetworkResult.Success(posts))
        } catch (e: FirebaseException) {
            emit(NetworkResult.Error("Ошибка Firebase: ${e.message}"))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown error"))
        }
    }

}