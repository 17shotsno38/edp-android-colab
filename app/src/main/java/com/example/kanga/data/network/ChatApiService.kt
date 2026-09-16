package com.example.kanga.data.network

import com.example.kanga.data.network.dto.MessageDto
import com.example.kanga.data.network.dto.NewMessageDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatApiService {
    @GET("messages")
    suspend fun getMessages(
        @Query("sortBy") sortBy: String = "createdAt",
        @Query("order") order: String = "desc"
    ): List<MessageDto>

    @POST("messages")
    suspend fun sendMessage(
        @Body message: NewMessageDto
    ): MessageDto
}
