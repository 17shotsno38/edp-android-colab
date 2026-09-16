package com.example.kanga.data.repository

import com.example.kanga.core.AppResult
import com.example.kanga.data.local.MessageDao
import com.example.kanga.data.local.toDomain
import com.example.kanga.data.local.toEntity
import com.example.kanga.data.network.ChatApiService
import com.example.kanga.data.network.dto.NewMessageDto
import com.example.kanga.data.network.dto.toDomain
import com.example.kanga.domain.ChatRepository
import com.example.kanga.domain.Message
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import retrofit2.HttpException

class ChatRepositoryImpl(
    private val api: ChatApiService,
    private val dao: MessageDao
) : ChatRepository {

    override suspend fun getMessages(): AppResult<List<Message>> {
        val result = safeCall { api.getMessages().toDomain() }
        
        return if (result is AppResult.Success) {
            dao.insertAll(result.data.map { it.toEntity() })
            result
        } else {
            val saved = dao.getAll().toDomain()
            if (saved.isNotEmpty()) {
                AppResult.Success(saved)
            } else {
                result
            }
        }
    }

    override suspend fun sendMessage(sender: String, text: String): AppResult<Message> = safeCall {
        val dto = NewMessageDto(sender, text, System.currentTimeMillis())
        val response = api.sendMessage(dto).toDomain()
        dao.insertAll(listOf(response.toEntity()))
        response
    }

    private inline fun <T> safeCall(block: () -> T): AppResult<T> =
        try {
            AppResult.Success(block())
        } catch (e: UnknownHostException) {
            AppResult.Failure.NoInternet
        } catch (e: SocketTimeoutException) {
            AppResult.Failure.Timeout
        } catch (e: HttpException) {
            val message = e.response()?.errorBody()?.string()?.replace("\"", "") ?: "Server error"
            AppResult.Failure.Unknown(message)
        } catch (e: IOException) {
            AppResult.Failure.NoInternet
        } catch (e: Exception) {
            AppResult.Failure.Unknown(e.message)
        }
}
