package com.akbar.chessvisionpro.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface LichessApi {
    @GET("api/puzzle/daily")
    suspend fun getDailyPuzzle(): PuzzleResponse

    @GET("api/puzzles/search")
    suspend fun searchPuzzles(
        @Query("rating") rating: String,
        @Query("themes") themes: String,
        @Query("page") page: Int = 1
    ): PuzzleSearchResponse

    data class PuzzleResponse(
        val puzzle: PuzzleDto,
        val game: GameDto
    )

    data class PuzzleSearchResponse(
        val puzzles: List<PuzzleDto>
    )

    data class PuzzleDto(
        val id: String,
        val rating: Int,
        val popularity: Int,
        val themes: List<String>,
        val gameUrl: String,
        val pgn: String,
        val solution: List<String>
    )

    data class GameDto(
        val id: String,
        val pgn: String
    )
}