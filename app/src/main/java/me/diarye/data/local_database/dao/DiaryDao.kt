package me.diarye.data.local_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.diarye.data.local_database.model.Diary

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary ORDER BY date DESC")
    fun getDiary(): List<Diary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDiary(diary: Diary)

    @Query("SELECT * FROM diary WHERE id = :id")
    fun getDiaryById(id: String): Diary
}