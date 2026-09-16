package com.example.kanga.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kanga.domain.Message

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String,
    val sender: String,
    val text: String,
    val createdAt: Long
)

fun MessageEntity.toDomain() = Message(
    id = id,
    sender = sender,
    text = text,
    createdAt = createdAt
)

fun Message.toEntity() = MessageEntity(
    id = id,
    sender = sender,
    text = text,
    createdAt = createdAt
)

fun List<MessageEntity>.toDomain() = map { it.toDomain() }
