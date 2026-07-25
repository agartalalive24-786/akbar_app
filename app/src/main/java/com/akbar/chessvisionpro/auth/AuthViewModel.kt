package com.akbar.chessvisionpro.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState = _authState.asStateFlow()
    
    private val _authError = MutableStateFlow<String?>(null)
    val authError = _authError.asStateFlow()
    
    init {
        checkAuthStatus()
    }
    
    private fun checkAuthStatus() {
        viewModelScope.launch {
            if (authRepository.isUserLoggedIn()) {
                _authState.value = AuthState.Authenticated(
                    authRepository.getCurrentUser() ?: User()
                )
            }
        }
    }
    
    fun signUp(email: String, password: String, username: String) {
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading
                val user = authRepository.signUp(email, password, username)
                _authState.value = AuthState.Authenticated(user)
                Timber.d("Sign up successful: $email")
            } catch (e: Exception) {
                _authError.value = e.message ?: "Sign up failed"
                _authState.value = AuthState.Unauthenticated
                Timber.e(e, "Sign up error")
            }
        }
    }
    
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading
                val user = authRepository.signIn(email, password)
                _authState.value = AuthState.Authenticated(user)
                Timber.d("Sign in successful: $email")
            } catch (e: Exception) {
                _authError.value = e.message ?: "Sign in failed"
                _authState.value = AuthState.Unauthenticated
                Timber.e(e, "Sign in error")
            }
        }
    }
    
    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            _authState.value = AuthState.Unauthenticated
            Timber.d("User signed out")
        }
    }
    
    fun clearError() {
        _authError.value = null
    }
}

sealed class AuthState {
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Authenticated(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}

data class User(
    val id: String = "",
    val email: String = "",
    val username: String = "",
    val profileImageUrl: String = "",
    val stats: UserStats = UserStats()
)

data class UserStats(
    val puzzlesSolved: Int = 0,
    val accuracy: Float = 0f,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val totalPlayTime: Long = 0L
)
