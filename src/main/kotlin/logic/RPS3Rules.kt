package com.hadsol.logic

import com.hadsol.enums.RPSResult
import enums.Move

class RPS3Rules : GameRules {

    /** Normal Rock Paper Scissors rules, with Rock beating Scissors, Scissors beating Paper, and Paper beating Rock */
    override fun determineResult(move1: Move, move2: Move): RPSResult {
        return when {
            move1 == move2 -> RPSResult.DRAW
            (move1 == Move.ROCK && move2 == Move.SCISSORS)||
                    (move1 == Move.SCISSORS && move2 == Move.PAPER) ||
                    (move1 == Move.PAPER && move2 == Move.ROCK) -> RPSResult.WIN
            else -> RPSResult.LOSE
        }
    }
}
