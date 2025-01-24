package logic

import com.hadsol.enums.RPSResult
import com.hadsol.logic.GameRules
import enums.Move

class RPS5Rules : GameRules {

    /** Rock Paper Scissors Lizard Spock rules,
    with Rock beating Scissors and Lizard,
    Scissors beating Paper and Lizard,
    Paper beating Rock and Spock,
    Lizard beating Spock and Paper,
    and Spock beating Rock and Scissors */
    override fun determineResult(move1: Move, move2: Move): RPSResult {
        return when {
            move1 == move2 -> RPSResult.DRAW
            (move1 == Move.ROCK && (move2 == Move.SCISSORS || move2 == Move.LIZARD)) ||
            (move1 == Move.PAPER && (move2 == Move.ROCK || move2 == Move.SPOCK)) ||
            (move1 == Move.SCISSORS && (move2 == Move.PAPER || move2 == Move.LIZARD)) ||
            (move1 == Move.LIZARD && (move2 == Move.SPOCK || move2 == Move.PAPER)) ||
            (move1 == Move.SPOCK && (move2 == Move.SCISSORS || move2 == Move.ROCK)) -> RPSResult.WIN
            else -> RPSResult.LOSE
        }
    }
}