package uz.shoxrux.main.presentation.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.data.dto.like.LikeModel
import uz.shoxrux.main.domain.model.CommentModel
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import uz.shoxrux.main.domain.use_case.HomeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    private val _localLikes = MutableStateFlow<List<LikeModel>>(emptyList())
    private val _localUnlikes = MutableStateFlow<List<LikeModel>>(emptyList())

    private fun updateCommentState(transform: (OpenedCommentState) -> OpenedCommentState) {
        _homeUiState.update { state ->
            state.copy(commentState = transform(state.commentState))
        }
    }

    fun onCommentValueChange(new: String) {
        updateCommentState { it.copy(commentInput = new) }
    }

    fun getPosts() {
        viewModelScope.launch {
            useCase.getPosts().collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _homeUiState.update { it.copy(isLoading = true, error = null) }
                    }

                    is NetworkResult.Success -> {
                        _homeUiState.update {
                            it.copy(
                                isLoading = false,
                                posts = result.data ?: emptyList(),
                                error = null
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _homeUiState.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
        }
    }

    fun saveLocalLikes(like: LikeModel? = null, unlike: LikeModel? = null) {
        like?.takeIf { it.likedPost.isNotEmpty() }?.let { newLike ->
            if (_localLikes.value.none { it.likedPost == newLike.likedPost && it.owner == newLike.owner }) {
                _localLikes.value += newLike
            }
        }
        unlike?.takeIf { it.likedPost.isNotEmpty() }?.let { newUnlike ->
            if (_localUnlikes.value.none { it.likedPost == newUnlike.likedPost && it.owner == newUnlike.owner }) {
                _localUnlikes.value += newUnlike
            }
        }
    }

    fun openComments(postId: String) {
        updateCommentState { it.copy(isOpened = true, postId = postId) }
        loadComments(postId)
    }

    fun closeComments() {
        updateCommentState { OpenedCommentState() }
    }

    private fun loadComments(postId: String) {
        viewModelScope.launch {
            useCase.getComments(postId).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        updateCommentState { it.copy(isLoading = true) }
                    }

                    is NetworkResult.Success -> {
                        updateCommentState {
                            it.copy(
                                isLoading = false,
                                comments = result.data ?: emptyList()
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _homeUiState.update { it.copy(error = result.message) }
                        updateCommentState { it.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    fun sendComment(comment: CommentModel) {
        viewModelScope.launch {
            try {
                useCase.sendComment(comment).collect {}
                updateCommentState { it.copy(comments = it.comments + comment, commentInput = "") }
            } catch (e: Exception) {
                _homeUiState.update { it.copy(error = e.localizedMessage) }
            }
        }
    }

    fun toggleLike(postId: String) {
        _homeUiState.update { state ->
            state.copy(posts = state.posts.map { post ->
                if (post.id == postId) {
                    post.copy(
                        isLiked = !post.isLiked,
                        likeCount = post.likeCount + if (post.isLiked) -1 else 1
                    )
                } else post
            })
        }
    }

    fun syncLikes() {
        viewModelScope.launch {
            try {
                useCase.syncLikes(likes = _localLikes.value, unlikes = _localUnlikes.value)
                    .collect {}
            } catch (e: Exception) {
                _homeUiState.update { it.copy(error = e.localizedMessage) }
            }
        }
    }
}