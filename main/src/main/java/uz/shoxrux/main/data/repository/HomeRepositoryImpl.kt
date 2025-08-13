package uz.shoxrux.main.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.constants.CollectionsConstants
import uz.shoxrux.main.data.dto.like.LikeModel
import uz.shoxrux.main.data.dto.post.PostDTO
import uz.shoxrux.main.domain.model.CommentModel
import uz.shoxrux.main.domain.model.post.PostModel
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : HomeRepository {

    override suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>> = flow {
        emit(NetworkResult.Loading())

        try {

            val postsSnapshot = firestore.collection(CollectionsConstants.POSTS).get().await()
            val posts = postsSnapshot.toObjects(PostDTO::class.java)

            val userLikesSnapshot = firestore.collection(CollectionsConstants.LIKES)
                .whereEqualTo("owner", auth.uid)
                .get()
                .await()

            val likedPostIds = userLikesSnapshot.toObjects(LikeModel::class.java)
                .map { it.likedPost }
                .toSet()

            val postUiList = posts.map { post ->
                PostModel(
                    id = post.id,
                    authorId = post.authorId,
                    content = post.content,
                    imageUrl = post.imageUrl,
                    postTime = post.postTime,
                    likeCount = post.likeCount,
                    isLiked = likedPostIds.contains(post.id)
                )
            }

            emit(NetworkResult.Success(postUiList))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown error"))
        }
    }


    override suspend fun syncLikes(
        likes: List<LikeModel>,
        unlikes: List<LikeModel>
    ): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading())

        try {
            firestore.runBatch { batch ->

                likes.forEach {
                    val like = it.copy(owner = auth.uid ?: "")
                    val likeRef = firestore.collection(CollectionsConstants.LIKES)
                        .document("${like.likedPost}_${like.owner}")
                    val postRef = firestore.collection(CollectionsConstants.POSTS)
                        .document(like.likedPost)

                    batch.set(likeRef, like)

                    batch.update(postRef, "likeCount", FieldValue.increment(1))
                }

                unlikes.forEach {
                    val unlike = it.copy(owner = auth.uid ?: "")
                    val likeRef = firestore.collection(CollectionsConstants.LIKES)
                        .document("${unlike.likedPost}_${unlike.owner}")
                    val postRef = firestore.collection(CollectionsConstants.POSTS)
                        .document(unlike.likedPost)

                    batch.delete(likeRef)

                    batch.update(postRef, "likeCount", FieldValue.increment(-1))
                }
            }.await()

            emit(NetworkResult.Success(true))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    override suspend fun getComments(id: String): Flow<NetworkResult<List<CommentModel>>> = flow {

        emit(NetworkResult.Loading())

        try {

            val snapshot = firestore.collection(CollectionsConstants.COMMENTS)
                .whereEqualTo("postId", id)
                .get()
                .await()

            val comments = snapshot.toObjects(CommentModel::class.java)
            emit(NetworkResult.Success(comments))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage))
        }

    }

    override suspend fun sendComment(comment: CommentModel): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading())

        try {
            firestore.collection(CollectionsConstants.COMMENTS)
                .add(comment)
                .await()

            emit(NetworkResult.Success(true))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

}