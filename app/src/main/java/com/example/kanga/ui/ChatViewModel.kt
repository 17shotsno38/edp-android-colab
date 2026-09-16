package com.example.kanga.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kanga.core.AppResult
import com.example.kanga.data.local.ChatDatabase
import com.example.kanga.data.network.NetworkModule
import com.example.kanga.data.repository.ChatRepositoryImpl
import com.example.kanga.domain.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel(
    private val repository: ChatRepository
) : ViewModel() {
    var uiState: ChatUiState by mutableStateOf(ChatUiState.Loading)
        private set
    var myName: String by mutableStateOf("")
        private set
    var draft: String by mutableStateOf("")
        private set
    var isSending: Boolean by mutableStateOf(false)
        private set

    fun onNameChange(value: String) { myName = value }
    fun onDraftChange(value: String) { 
        draft = value 
        // Clear error when user starts typing again
        val state = uiState
        if (state is ChatUiState.Ready && state.errorMessage != null) {
            uiState = state.copy(errorMessage = null)
        }
    }

    init { load() }

    fun load() {
        viewModelScope.launch {
            val currentState = uiState
            if (currentState is ChatUiState.Ready) {
                uiState = currentState.copy(isRefreshing = true, errorMessage = null)
            } else {
                uiState = ChatUiState.Loading
            }

            uiState = when (val result = repository.getMessages()) {
                is AppResult.Success -> {
                    if (result.data.isEmpty()) ChatUiState.Empty else ChatUiState.Ready(result.data)
                }
                is AppResult.Failure -> {
                    if (currentState is ChatUiState.Ready) {
                        currentState.copy(isRefreshing = false, errorMessage = "Failed to refresh messages")
                    } else {
                        val message = when (result) {
                            AppResult.Failure.NoInternet -> "No internet connection."
                            AppResult.Failure.Timeout -> "The server took too long."
                            is AppResult.Failure.Unknown -> result.message ?: "Something went wrong."
                        }
                        ChatUiState.Error(message)
                    }
                }
            }
        }
    }

    fun send() {
        if (myName.isBlank() || draft.isBlank() || isSending) return
        val currentDraft = draft
        draft = ""
        isSending = true
        viewModelScope.launch {
            when (val result = repository.sendMessage(myName, currentDraft)) {
                is AppResult.Success -> {
                    val currentState = uiState
                    if (currentState is ChatUiState.Ready) {
                        uiState = currentState.copy(
                            messages = listOf(result.data) + currentState.messages,
                            errorMessage = null
                        )
                    } else {
                        load()
                    }
                }
                is AppResult.Failure -> {
                    draft = currentDraft // Restore draft if failed
                    val message = when (result) {
                        is AppResult.Failure.Unknown -> result.message ?: "Could not send"
                        AppResult.Failure.NoInternet -> "Check your connection"
                        AppResult.Failure.Timeout -> "Server timeout"
                    }
                    val currentState = uiState
                    if (currentState is ChatUiState.Ready) {
                        uiState = currentState.copy(errorMessage = message)
                    } else {
                        uiState = ChatUiState.Error(message)
                    }
                }
            }
            isSending = false
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY]!!
                val database = ChatDatabase.getDatabase(application)
                ChatViewModel(
                    ChatRepositoryImpl(
                        NetworkModule.chatApi,
                        database.messageDao()
                    )
                )
            }
        }
    }
}
