package com.hadsol

import com.hadsol.logic.RPS3Rules
import com.hadsol.service.GamePlay
import model.RandomPlayer
import model.RockPlayer

fun main() {
    val game = GamePlay(RandomPlayer(), RockPlayer(), RPS3Rules())
    game.play(100)
}