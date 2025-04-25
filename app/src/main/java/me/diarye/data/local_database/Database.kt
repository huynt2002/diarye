package me.diarye.data.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.diarye.data.local_database.converter.InstantConverter
import me.diarye.data.local_database.dao.DiaryDao
import me.diarye.data.local_database.model.Diary

@Database(version = 1, entities = [Diary::class],exportSchema = false)
@TypeConverters(InstantConverter::class)
abstract class Database: RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}