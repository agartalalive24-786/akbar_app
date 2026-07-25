package com.akbar.chessvisionpro.parser

import timber.log.Timber
import java.io.File

class PgnParser {
    
    fun parsePgnFile(file: File): List<PgnGame> {
        val games = mutableListOf<PgnGame>()
        val content = file.readText()
        val gameStrings = content.split("\\n\\n\\n")
        
        for (gameString in gameStrings) {
            if (gameString.isBlank()) continue
            
            try {
                val game = parseSingleGame(gameString)
                if (game != null) {
                    games.add(game)
                }
            } catch (e: Exception) {
                Timber.e(e, "Error parsing PGN game")
            }
        }
        
        return games
    }
    
    fun parsePgnString(pgnString: String): PgnGame? {
        return try {
            parseSingleGame(pgnString)
        } catch (e: Exception) {
            Timber.e(e, "Error parsing PGN string")
            null
        }
    }
    
    private fun parseSingleGame(gameString: String): PgnGame? {
        val lines = gameString.lines()
        val tags = mutableMapOf<String, String>()
        var moveIndex = 0
        
        for (i in lines.indices) {
            val line = lines[i].trim()
            if (line.startsWith("[")) {
                val match = Regex("\\[(\\w+)\\s+\"([^\"]*)\"]")
                    .find(line)
                if (match != null) {
                    val (key, value) = match.destructured
                    tags[key] = value
                    moveIndex = i + 1
                }
            } else if (line.isNotEmpty() && !line.startsWith("[")) {
                moveIndex = i
                break
            }
        }
        
        val moveLines = lines.drop(moveIndex).joinToString(" ")
        val moves = parseMovesFromString(moveLines)
        
        return PgnGame(
            event = tags["Event"] ?: "Unknown",
            site = tags["Site"] ?: "Unknown",
            date = tags["Date"] ?: "Unknown",
            white = tags["White"] ?: "Unknown",
            black = tags["Black"] ?: "Unknown",
            result = tags["Result"] ?: "*",
            eco = tags["ECO"] ?: "Unknown",
            whiteElo = tags["WhiteElo"]?.toIntOrNull() ?: 0,
            blackElo = tags["BlackElo"]?.toIntOrNull() ?: 0,
            moves = moves
        )
    }
    
    private fun parseMovesFromString(moveString: String): List<String> {
        val moves = mutableListOf<String>()
        val cleanString = moveString.replace(Regex("\\*|1-0|0-1|1/2-1/2"), "")
        val movePattern = Regex("[1-9]\\d*\\.\\s*")
        val parts = cleanString.split(movePattern)
        
        for (part in parts) {
            if (part.isBlank()) continue
            val movePair = part.trim().split(Regex("\\s+"))
            for (move in movePair) {
                if (move.isNotEmpty() && !move.contains(".")) {
                    val cleanMove = move.replace(Regex("[!?]+"), "")
                    if (cleanMove.isNotEmpty()) {
                        moves.add(cleanMove)
                    }
                }
            }
        }
        
        return moves
    }
}

data class PgnGame(
    val event: String,
    val site: String,
    val date: String,
    val white: String,
    val black: String,
    val result: String,
    val eco: String,
    val whiteElo: Int,
    val blackElo: Int,
    val moves: List<String>
)
