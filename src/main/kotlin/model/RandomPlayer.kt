package model

import enums.Move

class RandomPlayer : Player {

    override fun makeMove() = arrayOf(Move.ROCK, Move.PAPER, Move.SCISSORS).random()
}
