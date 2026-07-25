package com.akbar.chessvisionpro.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import timber.log.Timber

class LichessService @Inject constructor(
    private val lichessApi: LichessApi
) {
    fun getRandomPuzzle(): Flow<Result<PuzzleResponse>> = flow {
        try {
            emit(Result.success(lichessApi.getRandomPuzzle()))
        } catch (e: Exception) {
            Timber.e(e, "Error fetching random puzzle")
            emit(Result.failure(e))
        }
    }
    
    fun getPuzzlesByActivity(max: Int = 50): Flow<Result<List<PuzzleData>>> = flow {
        try {
            emit(Result.success(lichessApi.getPuzzlesByActivity(max)))
        } catch (e: Exception) {
            Timber.e(e, "Error fetching puzzles by activity")
            emit(Result.failure(e))
        }
    }
    
    fun getPuzzleById(puzzleId: String): Flow<Result<PuzzleData>> = flow {
        try {
            emit(Result.success(lichessApi.getPuzzleById(puzzleId)))
        } catch (e: Exception) {
            Timber.e(e, "Error fetching puzzle: $puzzleId")
            emit(Result.failure(e))
        }
    }
}
