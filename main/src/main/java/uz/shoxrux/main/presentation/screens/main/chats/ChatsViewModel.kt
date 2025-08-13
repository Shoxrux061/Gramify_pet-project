package uz.shoxrux.main.presentation.screens.main.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.chats.ChatModel
import uz.shoxrux.main.domain.reposiotry.ChatsRepository
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val repository: ChatsRepository
) : ViewModel() {

    private val _chats = MutableStateFlow<List<ChatModel>?>(emptyList())
    val chats: StateFlow<List<ChatModel>?> = _chats

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getChats() {

        viewModelScope.launch {

            try {

                repository.getAllChats().collect { result ->

                    when (result) {

                        is NetworkResult.Success -> {
                            _isLoading.value = false
                            _error.value = null
                            _chats.value = result.data ?: emptyList()
                        }

                        is NetworkResult.Loading -> {
                            _isLoading.value = true
                            _error.value = null
                        }

                        is NetworkResult.Error -> {
                            _isLoading.value = false
                            _error.value = result.message
                        }
                    }
                }

            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }
}