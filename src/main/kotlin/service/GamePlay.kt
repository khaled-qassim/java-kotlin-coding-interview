package com.hadsol.service

import com.hadsol.enums.RPSResult
import com.hadsol.logic.GameRules
import model.Player

class GamePlay(
    private val playerA: Player,
    private val playerB: Player,
    private val rules: GameRules
) {

    fun play(rounds: Int = 100) {
        var playerAWins = 0
        var playerBWins = 0
        var draws = 0

        repeat(rounds) {
            when (rules.determineResult(playerA.makeMove(), playerB.makeMove())) {
                RPSResult.WIN -> playerAWins++
                RPSResult.LOSE -> playerBWins++
                RPSResult.DRAW -> draws++
            }
        }

        displayFormat(playerAWins, playerBWins, draws)
    }

    private fun displayFormat(playerAWins: Int, playerBWins: Int, draws: Int) {
        println(
            """
            Player A wins $playerAWins of 100 games
            Player B wins $playerBWins of 100 games
            Draws: $draws of 100 games
            """.trimIndent()
        )
    }
}
