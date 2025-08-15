package uz.shoxrux.main.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.constants.CollectionsConstants
import uz.shoxrux.main.domain.model.post.PostModel
import uz.shoxrux.main.domain.model.profile.ProfileModel
import uz.shoxrux.main.domain.model.user.UserModel
import uz.shoxrux.main.domain.reposiotry.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val auth: FirebaseAuth
) : ProfileRepository {

    override suspend fun getSelfProfile(): Flow<NetworkResult<ProfileModel>> =
        flow {
            emit(NetworkResult.Loading())

            val snapshotUser = firestore
                .collection(CollectionsConstants.USERS)
                .document(auth.uid ?: "")
                .get()
                .await()

            val userModel = snapshotUser.toObject(UserModel::class.java)
            Log.d("TAGRepo", "getProfileDataById: $userModel")

            if (userModel == null) {
                emit(NetworkResult.Error("User not found"))
                return@flow
            }

            val snapshotPosts = firestore.collection(CollectionsConstants.POSTS)
                .whereEqualTo("authorId", auth.uid)
                .get()
                .await()

            val posts: List<PostModel> = snapshotPosts.toObjects(PostModel::class.java)

            val profileModel = ProfileModel(
                id = userModel.id ?: "",
                username = userModel.username ?: "",
                bio = userModel.bio ?: "",
                profileImageUrl = "",
                posts = posts
            )

            emit(NetworkResult.Success(profileModel))
        }.catch { e ->
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }

    override suspend fun getProfileDataById(id: String): Flow<NetworkResult<ProfileModel>> = flow {

        emit(NetworkResult.Loading())

        try {

            val snapshotUser =
                firestore.collection(CollectionsConstants.USERS).document(id).get()
                    .await()
            val userModel = snapshotUser.toObject(UserModel::class.java)

            if (userModel == null) {
                emit(NetworkResult.Error("User not found"))
                return@flow
            }

            val snapshotPosts = firestore.collection(CollectionsConstants.POSTS)
                .whereEqualTo(
                    "authorId", auth.uid
                )
                .get().await()


            val posts: List<PostModel> = snapshotPosts.toObjects(PostModel::class.java)

            val profileModel = ProfileModel(
                id = userModel.id ?: "",
                username = userModel.username ?: "",
                bio = userModel.bio ?: "",
                profileImageUrl = "",
                posts = posts
            )


            emit(NetworkResult.Success(profileModel))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }
    }


    override suspend fun getProfileId(): Flow<NetworkResult<String?>> = flow {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            emit(NetworkResult.Success(data = uid))
        } else {
            emit(NetworkResult.Error("Not Authorized"))
        }
    }

}