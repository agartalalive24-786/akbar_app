package com.akbar.chessvisionpro.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.annotations.SerializedName

interface LichessApi {
    @GET("api/puzzles/random")
    suspend fun getRandomPuzzle(): PuzzleResponse
    
    @GET("api/puzzles/activity")
    suspend fun getPuzzlesByActivity(
        @Query("max") max: Int = 50
    ): List<PuzzleData>
    
    @GET("api/puzzle")
    suspend fun getPuzzleById(
        @Query("id") puzzleId: String
    ): PuzzleData
}

data class PuzzleResponse(
    @SerializedName("puzzle")
    val puzzle: PuzzleData,
    @SerializedName("game")
    val game: GameData
)

data class PuzzleData(
    @SerializedName("id")
    val id: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("ratingDeviation")
    val ratingDeviation: Int,
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("victories")
    val victories: Int,
    @SerializedName("themes")
    val themes: List<String>,
    @SerializedName("gameId")
    val gameId: String,
    @SerializedName("opening")
    val opening: OpeningInfo?,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("plays")
    val plays: Int,
    @SerializedName("initialPly")
    val initialPly: Int,
    @SerializedName("solution")
    val solution: List<String>
)

data class GameData(
    @SerializedName("id")
    val id: String,
    @SerializedName("pgn")
    val pgn: String,
    @SerializedName("players")
    val players: List<PlayerInfo>,
    @SerializedName("createdAt")
    val createdAt: Long
)

data class OpeningInfo(
    @SerializedName("eco")
    val eco: String,
    @SerializedName("name")
    val name: String
)

data class PlayerInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("color")
    val color: String
)
