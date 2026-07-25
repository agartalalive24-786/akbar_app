package com.akbar.chessvisionpro.data.repository

import com.akbar.chessvisionpro.data.database.PuzzleDao
import com.akbar.chessvisionpro.data.models.Puzzle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PuzzleRepository @Inject constructor(
    private val puzzleDao: PuzzleDao
) {
    fun searchPuzzles(
        minRating: Int,
        maxRating: Int,
        minPopularity: Int,
        theme: String,
        limit: Int = 50
    ): Flow<List<Puzzle>> {
        return puzzleDao.searchPuzzles(minRating, maxRating, minPopularity, theme, limit)
    }
    
    fun getFavoritePuzzles(limit: Int = 100): Flow<List<Puzzle>> {
        return puzzleDao.getFavoritePuzzles(limit)
    }
    
    suspend fun addFavorite(puzzle: Puzzle) {
        puzzleDao.insertPuzzle(puzzle.copy(isFavorite = true))
    }
}
