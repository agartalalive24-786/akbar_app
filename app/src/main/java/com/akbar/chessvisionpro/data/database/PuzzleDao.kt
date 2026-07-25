package com.akbar.chessvisionpro.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.akbar.chessvisionpro.data.models.Puzzle
import kotlinx.coroutines.flow.Flow

@Dao
interface PuzzleDao {
    @Query("SELECT * FROM puzzles WHERE puzzleId = :id")
    fun getPuzzleById(id: String): Flow<Puzzle>
    
    @Query("""
        SELECT * FROM puzzles
        WHERE rating BETWEEN :minRating AND :maxRating
        AND popularity >= :minPopularity
        AND themes LIKE '%' || :theme || '%'
        LIMIT :limit
    """)
    fun searchPuzzles(
        minRating: Int,
        maxRating: Int,
        minPopularity: Int,
        theme: String,
        limit: Int = 50
    ): Flow<List<Puzzle>>
    
    @Query("SELECT * FROM puzzles WHERE isFavorite = 1 LIMIT :limit")
    fun getFavoritePuzzles(limit: Int = 100): Flow<List<Puzzle>>
    
    @Insert
    suspend fun insertPuzzle(puzzle: Puzzle)
    
    @Query("UPDATE puzzles SET isFavorite = :isFav WHERE puzzleId = :id")
    suspend fun updateFavorite(id: String, isFav: Boolean)
}
