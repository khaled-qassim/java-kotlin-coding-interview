package com.hadsol.logic

import com.hadsol.enums.RPSResult
import enums.Move

interface GameRules {
    fun determineResult(move1: Move, move2: Move): RPSResult
}