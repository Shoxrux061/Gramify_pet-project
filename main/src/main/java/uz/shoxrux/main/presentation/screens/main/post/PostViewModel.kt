package uz.shoxrux.main.presentation.screens.main.post

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.mapper.toData
import uz.shoxrux.main.domain.model.post.PostModel
import uz.shoxrux.main.domain.reposiotry.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _captionText = MutableStateFlow("")
    val captionText: StateFlow<String> = _captionText

    fun setUri(uri: Uri) {
        _selectedImageUri.value = uri
    }

    fun updateCaptionText(new: String) {
        _captionText.value = new
    }

    fun addPost(
        postModel: PostModel,
        byteArray: ByteArray
    ) {

        viewModelScope.launch {

            try {

                repository.post(postModel.toData(), byteArray).collect { result ->

                    when (result) {

                        is NetworkResult.Loading -> {
                            _isLoading.value = true
                            _error.value = null
                        }

                        is NetworkResult.Success -> {
                            _isLoading.value = false
                            _error.value = null
                            _isSuccess.value = true
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

    fun clear(){
        _captionText.value = ""
        _selectedImageUri.value = null
    }

}