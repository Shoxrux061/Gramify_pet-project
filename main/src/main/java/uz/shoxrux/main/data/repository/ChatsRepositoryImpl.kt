package uz.shoxrux.main.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.constants.CollectionsConstants
import uz.shoxrux.main.data.dto.chat.ChatModelDto
import uz.shoxrux.main.data.mapper.toDomain
import uz.shoxrux.main.domain.model.chats.ChatModel
import uz.shoxrux.main.domain.model.profile.ProfileModel
import uz.shoxrux.main.domain.reposiotry.ChatsRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ChatsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) : ChatsRepository {

    override fun getAllChats(): Flow<NetworkResult<List<ChatModel>>> = flow {
        emit(NetworkResult.Loading())

        try {
            val chats = suspendCancellableCoroutine<List<ChatModelDto>> { cont ->
                database.getReference(CollectionsConstants.CHATS)
                    .orderByChild("members/${auth.uid}")
                    .equalTo(true)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val result = snapshot.children.mapNotNull {
                                it.getValue(ChatModelDto::class.java)
                            }
                            cont.resume(result)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            cont.resumeWithException(Exception(error.message))
                        }
                    })
            }
            val result = chats.mapNotNull { dto ->
                val otherUserId = dto.members.keys.firstOrNull { it != auth.uid } ?: return@mapNotNull null

                val userDoc = firestore.collection(CollectionsConstants.USERS)
                    .document(otherUserId)
                    .get()
                    .await()

                val profile = userDoc.toObject(ProfileModel::class.java)

                dto.toDomain().copy(
                    partnerName = profile?.username ?: "",
                    partnerAvatarUrl = profile?.profileImageUrl
                )
            }

            emit(NetworkResult.Success(result))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

}