package me.diarye.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.diarye.data.local_database.Database
import javax.inject.Singleton
import androidx.room.Room
import me.diarye.data.local_database.dao.DiaryDao

@Module
@InstallIn(SingletonComponent::class)
object LocalData {
    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context = context,
            klass = Database::class.java,
            name = "local_db"
        ).build()
    }

    @Provides
    @Singleton
    fun diaryDao(database: Database): DiaryDao = database.diaryDao()
}