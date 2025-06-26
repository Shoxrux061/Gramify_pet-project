package uz.shoxrux.auth.presentation.screens.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.auth.domain.use_case.AuthUseCase
import uz.shoxrux.core.handler.NetworkResult
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun signIn(login: String, password: String) {

        try {

            viewModelScope.launch {

                useCase.signIn(login, password).collect { result ->

                    when (result) {
                        is NetworkResult.Loading -> {
                            _error.value = null
                            _isLoading.value = true
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
            }

        } catch (e: Exception) {
            _error.value = e.localizedMessage ?: "ViewModel: Unknown error"
        }
    }
}