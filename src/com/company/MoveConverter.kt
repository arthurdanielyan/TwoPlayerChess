package com.company

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection


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
    val movesCode = StringBuilder()
    moves.forEach {
        println("moveReader.makeMove(\"$it\");")
        movesCode.append("moveReader.makeMove(\"$it\");\n")
    }
    Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(movesCode.toString()), null)
}