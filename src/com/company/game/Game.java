package com.company.game;


import com.company.core.Position;
import com.company.figures.figure_impls.King;

import static com.company.core.BoardLetters.*;

public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {
        board.resetBoard();

        moveReader.makeMove("e4");
        moveReader.makeMove("e5");
        moveReader.makeMove("Nf3");
        moveReader.makeMove("Nc6");
        moveReader.makeMove("c3");
        moveReader.makeMove("Nf6");
        moveReader.makeMove("d4");
        moveReader.makeMove("Nxe4");
        moveReader.makeMove("d5");
        moveReader.makeMove("Ne7");
        moveReader.makeMove("Nxe5");
        moveReader.makeMove("Nf5");
        moveReader.makeMove("Bd3");
        moveReader.makeMove("Qh4");
        moveReader.makeMove("g3");
        moveReader.makeMove("Nxf2");
        moveReader.makeMove("Kxf2");
        moveReader.makeMove("Qf6");
        moveReader.makeMove("Qf3");
        moveReader.makeMove("Qxe5");
        moveReader.makeMove("Qxf5");
        moveReader.makeMove("Bc5+");
        moveReader.makeMove("Kg2");
        moveReader.makeMove("d6");
        moveReader.makeMove("Qxe5+");
        moveReader.makeMove("dxe5");
        moveReader.makeMove("Re1");
        moveReader.makeMove("O-O");
        moveReader.makeMove("Be3");
        moveReader.makeMove("Bxe3");
        moveReader.makeMove("Rxe3");
        moveReader.makeMove("c6");
        moveReader.makeMove("dxc6");
        moveReader.makeMove("bxc6");
        moveReader.makeMove("Rxe5");
        moveReader.makeMove("Bb7");
        moveReader.makeMove("Kg1");
        moveReader.makeMove("Rfe8");
        moveReader.makeMove("Re2");
        moveReader.makeMove("c5");
        moveReader.makeMove("Nd2");
        moveReader.makeMove("a5");
        moveReader.makeMove("Rae1");
        moveReader.makeMove("Rxe2");
        moveReader.makeMove("Rxe2");
        moveReader.makeMove("Rc8");
        moveReader.makeMove("Nc4");
        moveReader.makeMove("Bf3");
        moveReader.makeMove("Re3");
        moveReader.makeMove("Bd5");
        moveReader.makeMove("Nb6");
        moveReader.makeMove("Rd8");
        moveReader.makeMove("Nxd5");
        moveReader.makeMove("Rxd5");
        moveReader.requestMove();


    }
}
