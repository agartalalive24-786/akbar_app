package com.akbar.chessvisionpro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akbar.chessvisionpro.data.models.Puzzle

@Database(entities = [Puzzle::class], version = 1, exportSchema = false)
abstract class PuzzleDatabase : RoomDatabase() {
    abstract fun puzzleDao(): PuzzleDao
}
