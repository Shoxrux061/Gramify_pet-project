package uz.shoxrux.main.presentation.screens.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.dto.like.LikeModel
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

    private val _localLikes = MutableStateFlow<List<LikeModel>>(emptyList())

    private val _localUnlikes = MutableStateFlow<List<LikeModel>>(emptyList())

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

    fun saveLocalLikes(
        like: LikeModel? = null,
        unlike: LikeModel? = null
    ) {
        like?.takeIf { it.likedPost.isNotEmpty() }?.let { newLike ->
            if (_localLikes.value.none { it.likedPost == newLike.likedPost && it.owner == newLike.owner }) {
                _localLikes.value += newLike
            }
            Log.d("TAGLiked", "Likes: ${_localLikes.value}")
        }

        unlike?.takeIf { it.likedPost.isNotEmpty() }?.let { newUnlike ->
            if (_localUnlikes.value.none { it.likedPost == newUnlike.likedPost && it.owner == newUnlike.owner }) {
                _localUnlikes.value += newUnlike
            }
            Log.d("TAGLiked", "Unlikes: ${_localUnlikes.value}")
        }
    }

    fun toggleLike(postId: String) {
        _posts.update { list ->
            list.map { post ->
                if (post.id == postId) {
                    if (post.isLiked) {
                        post.copy(isLiked = false, likeCount = post.likeCount - 1)
                    } else {
                        post.copy(isLiked = true, likeCount = post.likeCount + 1)
                    }
                } else {
                    post
                }
            }
        }
    }

    fun syncLikes() {

        viewModelScope.launch {

            try {

                repository.syncLikes(
                    likes = _localLikes.value,
                    unlikes = _localUnlikes.value
                ).collect {}

            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }

}