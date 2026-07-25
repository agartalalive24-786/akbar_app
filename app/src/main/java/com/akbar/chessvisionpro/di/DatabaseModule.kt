package com.akbar.chessvisionpro.di

import android.content.Context
import androidx.room.Room
import com.akbar.chessvisionpro.data.database.PuzzleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providePuzzleDatabase(
        @ApplicationContext context: Context
    ): PuzzleDatabase {
        return Room.databaseBuilder(
            context,
            PuzzleDatabase::class.java,
            "chess_vision_pro.db"
        ).build()
    }
    
    @Provides
    @Singleton
    fun providePuzzleDao(database: PuzzleDatabase) = database.puzzleDao()
}
