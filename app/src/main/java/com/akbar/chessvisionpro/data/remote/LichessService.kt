package com.akbar.chessvisionpro.data.remote

import timber.log.Timber
import javax.inject.Inject

class LichessService @Inject constructor(
    private val lichessApi: LichessApi
) {
    suspend fun getDailyPuzzle() = try {
        lichessApi.getDailyPuzzle()
    } catch (e: Exception) {
        Timber.e(e, "Error fetching daily puzzle")
        throw e
    }

    suspend fun searchPuzzles(
        rating: String,
        themes: String,
        page: Int = 1
    ) = try {
        lichessApi.searchPuzzles(rating, themes, page)
    } catch (e: Exception) {
        Timber.e(e, "Error searching puzzles")
        throw e
    }
}