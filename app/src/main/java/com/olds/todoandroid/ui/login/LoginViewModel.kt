package com.olds.todoandroid.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olds.todoandroid.api.TodoRepository
import com.olds.todoandroid.navigation.AppDestination
import com.olds.todoandroid.navigation.NavigationService
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val navigationService: NavigationService,
) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onLoginClicked() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            try {
                todoRepository.login("", "")
                navigationService.navigate(
                    route = AppDestination.MainGraph,
                    popUpTo = AppDestination.AuthGraph,
                    inclusive = true,
                )
                uiState = uiState.copy(isLoading = false)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Login failed.",
                )
            }
        }
    }
}