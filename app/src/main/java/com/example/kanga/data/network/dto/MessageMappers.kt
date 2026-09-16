package com.example.kanga.data.network.dto

import com.example.kanga.domain.Message
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.contentOrNull
import java.time.Instant
import java.time.format.DateTimeParseException

fun MessageDto.toDomain(): Message = Message(
    id = id ?: "",
    sender = sender ?: "Unknown",
    text = text ?: "",
    createdAt = createdAt.toLongTimestamp()
)

private fun kotlinx.serialization.json.JsonElement?.toLongTimestamp(): Long {
    val primitive = this as? JsonPrimitive ?: return 0L
    
    // Try as long first
    primitive.longOrNull?.let { return it }
    
    // Try as ISO string
    val content = primitive.contentOrNull ?: return 0L
    return try {
        Instant.parse(content).toEpochMilli()
    } catch (e: DateTimeParseException) {
        0L
    }
}

fun List<MessageDto>.toDomain(): List<Message> =
    map { it.toDomain() }
