package uz.shoxrux.main.presentation.screens.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.main.domain.model.profile.ProfileModel
import uz.shoxrux.main.domain.reposiotry.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _profileData = MutableStateFlow<ProfileModel?>(null)
    val profileData: StateFlow<ProfileModel?> = _profileData

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getProfileData() {

        viewModelScope.launch {

            try {

                repository.getSelfProfile().collect{result->

                    when(result){

                        is NetworkResult.Success -> {
                            _profileData.value = result.data
                            _error.value = null
                            _isLoading.value = false
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
            }catch (e:Exception){
                _error.value = e.localizedMessage
            }
        }
    }
}