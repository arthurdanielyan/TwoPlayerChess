package com.company


// convert chess.com pgn-s
fun main() {
    val input = readLine()
    val moves = input!!
        .split(' ')
        .toMutableList()

    moves.removeIf {
        it.contains('.')
    }

    println(moves)
    moves.forEach {
        val s = it.removeSuffix("+")
        println("moveReader.makeMove(\"$s\");")
    }
}