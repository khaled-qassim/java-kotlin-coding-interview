package model

import enums.Move

class RockPlayer : Player {
    override fun makeMove() = Move.ROCK
}