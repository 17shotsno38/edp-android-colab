package com.example.kanga.domain

import com.example.kanga.core.AppResult

interface ChatRepository {
    suspend fun getMessages(): AppResult<List<Message>>
    suspend fun sendMessage(sender: String, text: String): AppResult<Unit>
}
