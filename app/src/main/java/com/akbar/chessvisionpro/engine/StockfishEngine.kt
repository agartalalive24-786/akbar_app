package com.akbar.chessvisionpro.engine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockfishEngine @Inject constructor() {
    private var process: Process? = null
    private var input: OutputStreamWriter? = null
    private var output: BufferedReader? = null
    private var isInitialized = false
    
    fun initialize(enginePath: String): Flow<Result<Unit>> = flow {
        try {
            process = ProcessBuilder(enginePath).start()
            input = OutputStreamWriter(process!!.outputStream)
            output = BufferedReader(InputStreamReader(process!!.inputStream))
            
            sendCommand("uci")
            
            var response = ""
            while (!response.contains("uciok") && response.isNotEmpty()) {
                response = output?.readLine() ?: ""
            }
            
            isInitialized = true
            Timber.d("Stockfish engine initialized")
            emit(Result.success(Unit))
        } catch (e: Exception) {
            Timber.e(e, "Failed to initialize Stockfish engine")
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.Default)
    
    fun analyze(
        fen: String,
        depth: Int = 20,
        multiPV: Int = 4
    ): Flow<Result<EngineAnalysis>> = flow {
        if (!isInitialized) {
            emit(Result.failure(Exception("Engine not initialized")))
            return@flow
        }
        
        try {
            sendCommand("setoption name MultiPV value $multiPV")
            sendCommand("position fen $fen")
            sendCommand("go depth $depth")
            
            val lines = mutableListOf<String>()
            var bestMove = ""
            var ponder = ""
            
            while (true) {
                val line = output?.readLine() ?: break
                lines.add(line)
                
                if (line.startsWith("bestmove")) {
                    val parts = line.split(" ")
                    bestMove = parts.getOrNull(1) ?: ""
                    ponder = parts.getOrNull(3) ?: ""
                    break
                }
            }
            
            val analysis = parseAnalysis(lines, bestMove, ponder)
            emit(Result.success(analysis))
        } catch (e: Exception) {
            Timber.e(e, "Error analyzing position")
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.Default)
    
    fun shutdown(): Flow<Result<Unit>> = flow {
        try {
            sendCommand("quit")
            process?.waitFor()
            isInitialized = false
            Timber.d("Stockfish engine shutdown")
            emit(Result.success(Unit))
        } catch (e: Exception) {
            Timber.e(e, "Error shutting down engine")
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.Default)
    
    private fun sendCommand(command: String) {
        try {
            input?.write(command + "\\n")
            input?.flush()
            Timber.d("Sent to engine: $command")
        } catch (e: Exception) {
            Timber.e(e, "Error sending command to engine")
        }
    }
    
    private fun parseAnalysis(
        lines: List<String>,
        bestMove: String,
        ponder: String
    ): EngineAnalysis {
        val lines_mutable = mutableListOf<AnalysisLine>()
        var depth = 0
        var nodes = 0L
        var time = 0L
        var nps = 0L
        
        for (line in lines) {
            when {
                line.startsWith("info") -> {
                    val parts = line.split(" ")
                    for (i in parts.indices) {
                        when (parts.getOrNull(i)) {
                            "depth" -> depth = parts.getOrNull(i + 1)?.toIntOrNull() ?: 0
                            "nodes" -> nodes = parts.getOrNull(i + 1)?.toLongOrNull() ?: 0L
                            "time" -> time = parts.getOrNull(i + 1)?.toLongOrNull() ?: 0L
                            "nps" -> nps = parts.getOrNull(i + 1)?.toLongOrNull() ?: 0L
                            "pv" -> {
                                val pvMoves = parts.drop(i + 1)
                                val scoreStr = parts.dropWhile { it != "score" }.drop(1).firstOrNull() ?: "0"
                                val score = scoreStr.toIntOrNull() ?: 0
                                
                                lines_mutable.add(
                                    AnalysisLine(
                                        depth = depth,
                                        score = score,
                                        moves = pvMoves,
                                        nodes = nodes
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
        
        return EngineAnalysis(
            lines = lines_mutable.take(4),
            bestMove = bestMove,
            ponder = ponder,
            depth = depth,
            nodes = nodes,
            time = time,
            nps = nps
        )
    }
}

data class EngineAnalysis(
    val lines: List<AnalysisLine>,
    val bestMove: String,
    val ponder: String,
    val depth: Int,
    val nodes: Long,
    val time: Long,
    val nps: Long
)

data class AnalysisLine(
    val depth: Int,
    val score: Int,
    val moves: List<String>,
    val nodes: Long
)
