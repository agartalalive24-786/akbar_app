package com.akbar.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        // Load initial data
        _uiState.value = UiState.Ready
    }

    sealed class UiState {
        object Loading : UiState()
        object Ready : UiState()
        data class Error(val message: String) : UiState()
    }
}
