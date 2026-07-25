package com.akbar.chessvisionpro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puzzles")
data class Puzzle(
    @PrimaryKey
    val puzzleId: String,
    val rating: Int,
    val popularity: Int,
    val themes: String,
    val openingTags: String,
    val gameUrl: String,
    val pgn: String,
    val solution: String,
    val isFavorite: Boolean = false
)

data class PuzzleSearchRequest(
    val minRating: Int,
    val maxRating: Int,
    val minPopularity: Int,
    val themes: List<String>,
    val opening: String,
    val variation: String
)
