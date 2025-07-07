package uz.shoxrux.main.presentation.screens.main.reels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.wiki.WikiList
import uz.shoxrux.main.domain.reposiotry.ReelsRepository
import javax.inject.Inject

@HiltViewModel
class ReelsViewModel @Inject constructor(
    private val repository: ReelsRepository
) : ViewModel() {

    private val _wikis = MutableStateFlow<WikiList?>(null)
    val wikis: StateFlow<WikiList?> = _wikis

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getWikis() {
        viewModelScope.launch {
            try {
                repository.getReels().collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> {
                            _isLoading.value = true
                            _error.value = null
                        }

                        is NetworkResult.Success -> {
                            result.data?.let { updatePages(it) }
                            _isLoading.value = false
                            _error.value = null
                        }

                        is NetworkResult.Error -> {
                            _error.value = result.message
                            _isLoading.value = false
                        }
                    }
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
            }
        }
    }

    private fun updatePages(newData: WikiList) {
        val current = _wikis.value

        _wikis.value = if (current == null) {
            newData
        } else {
            val mergedPages = current.query.pages + newData.query.pages
            current.copy(
                query = current.query.copy(
                    pages = mergedPages
                )
            )
        }
    }
}