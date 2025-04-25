package me.diarye.data.local_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.UUID

@Entity
data class Diary(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val text: String,
    val date: Instant = Instant.now(),
)