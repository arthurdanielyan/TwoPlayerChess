package com.company

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

/**
 *  converts moves to commands as
 *
 *  moveReader.makeMove("e4")
 *  moveReader.makeMove("c6")
 *
 *  and copies to clipboard
 *
 *  input must be in this form
 *  1. e4 c6 2. d4 d5 3. f3 g6 4. Nc3 Bg7
 *  in a single line
 * */
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