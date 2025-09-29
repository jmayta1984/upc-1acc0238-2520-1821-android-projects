package pe.edu.upc.easyshop.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.easyshop.core.utils.Resource
import pe.edu.upc.easyshop.core.utils.UiState
import pe.edu.upc.easyshop.features.auth.domain.models.User
import pe.edu.upc.easyshop.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _user = MutableStateFlow<UiState<User>>(UiState.Initial)
    val user: StateFlow<UiState<User>> = _user

    fun updateUsername(username: String) {
        _username.value = username
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun login() {
        _user.value = UiState.Loading
        viewModelScope.launch {
            val resource = repository.login(
                username.value,
                password.value
            )

            when (resource) {
                is Resource.Success -> _user.value = UiState.Success(resource.data as User)
                is Resource.Error -> _user.value = UiState.Error(resource.message as String)
            }
        }
    }
}