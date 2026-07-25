package com.akbar.chessvisionpro.data.remote

import timber.log.Timber
import javax.inject.Inject

class LichessService @Inject constructor(
    private val lichessApi: LichessApi
) {
    suspend fun getDailyPuzzle(): Result<LichessApi.PuzzleResponse> = try {
        Result.success(lichessApi.getDailyPuzzle())
    } catch (e: Exception) {
        Timber.e(e, "Error fetching daily puzzle")
        Result.failure(e)
    }

    suspend fun searchPuzzles(
        rating: String,
        themes: String,
        page: Int = 1
    ): Result<LichessApi.PuzzleSearchResponse> = try {
        Result.success(lichessApi.searchPuzzles(rating, themes, page))
    } catch (e: Exception) {
        Timber.e(e, "Error searching puzzles")
        Result.failure(e)
    }
}
