package uz.shoxrux.main.presentation.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.post.PostModel
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _posts = MutableStateFlow<List<PostModel>>(emptyList())
    val posts: StateFlow<List<PostModel>> = _posts

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getPosts() {

        viewModelScope.launch {

            try {

                repository.getPosts().collect { result ->

                    when (result) {
                        is NetworkResult.Loading -> {
                            _isLoading.value = true
                            _error.value = null
                        }

                        is NetworkResult.Success -> {
                            _isLoading.value = false
                            _error.value = null
                            _posts.value = result.data ?: emptyList()
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