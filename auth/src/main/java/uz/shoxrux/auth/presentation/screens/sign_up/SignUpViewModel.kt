package uz.shoxrux.auth.presentation.screens.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.auth.domain.model.SignUpModel
import uz.shoxrux.auth.domain.use_case.AuthUseCase
import uz.shoxrux.core.handler.NetworkResult
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun signUp(data: SignUpModel) {

        try {

            viewModelScope.launch {

                useCase.signUp(data).collect { result ->

                    when (result) {

                        is NetworkResult.Loading -> {
                            _isLoading.value = true
                            _error.value = null
                        }

                        is NetworkResult.Success -> {
                            _isSuccess.value = true
                            _isLoading.value = false
                            _error.value = null
                        }

                        is NetworkResult.Error -> {
                            _error.value = result.message
                            _isLoading.value = false
                        }
                    }
                }
            }

        } catch (e: Exception) {
            _error.value = e.localizedMessage ?: "ViewModel: Unknown error"
        }
    }
}