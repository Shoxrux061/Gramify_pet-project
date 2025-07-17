package uz.shoxrux.main.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.constants.CollectionsConstants
import uz.shoxrux.main.domain.model.post.PostModel
import uz.shoxrux.main.domain.reposiotry.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth
) : PostRepository {

    override suspend fun post(
        postModel: PostModel,
        byteArray: ByteArray
    ): Flow<NetworkResult<Boolean>> = flow {

        emit(NetworkResult.Loading())

        try {

            val userId = auth.uid
            if (userId == null) {
                emit(NetworkResult.Error("Пользователь не авторизован"))
                return@flow
            }

            val imageRef = storage.reference.child(CollectionsConstants.IMAGES)
                .child("${postModel.id}_post_image.jpeg")

            imageRef.putBytes(byteArray).await()

            val downloadUrl = imageRef.downloadUrl.await()

            val updatedPost = postModel.copy(
                imageUrl = downloadUrl.toString(),
                authorId = userId
            )

            firestore.collection(CollectionsConstants.POSTS)
                .document(postModel.id)
                .set(updatedPost)
                .await()

            emit(NetworkResult.Success(true))

        } catch (e: Exception) {
            Log.d("TAGPostrepo", "post: $e")
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}