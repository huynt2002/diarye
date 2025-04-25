package me.diarye.data.local_database.converter

import androidx.room.TypeConverter
import java.time.Instant

class InstantConverter {
    @TypeConverter
    fun toLong(instant: Instant?): Long? {
        return instant?.toEpochMilli()
    }

    @TypeConverter
    fun toInstant(long: Long?): Instant? {
        return long?.let {
            Instant.ofEpochMilli(it)
        }
    }
}