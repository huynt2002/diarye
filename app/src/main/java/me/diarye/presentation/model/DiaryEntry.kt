package me.diarye.presentation.model

import java.time.Instant
import java.util.UUID

data class DiaryEntry(
    val id: String = UUID.randomUUID().toString(),
    val originalText: String,
    val summary: String,
    val timestamp: Instant = Instant.now(),
)
