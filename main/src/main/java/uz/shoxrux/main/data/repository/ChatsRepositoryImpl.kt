package uz.shoxrux.main.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.constants.CollectionsConstants
import uz.shoxrux.main.data.dto.chat.ChatModelDto
import uz.shoxrux.main.data.mapper.toDomain
import uz.shoxrux.main.domain.model.chats.ChatModel
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

            val result = chats.map { it.toDomain().copy() }

            emit(NetworkResult.Success(result))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}